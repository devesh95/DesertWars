import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;

/**
 * Main game screen
 * @author deveshdayal
 *
 */
public class GameScreen implements Screen{

	private ScreenManager screens;
	private MovingBackground bg;
	private double bgspeed;
	private double score;
	private int lives;
	private int livesx;
	private boolean alive;
	private Player player;
	private ArrayList<Enemies> enemies;
	private ArrayList<AIBoss> boss;
	private ArrayList<Missiles> weapons;
	private HighScoreManager hsm;
	private int bossfrequency;

	public GameScreen (ScreenManager s){
		this.screens = s;
		bgspeed = -4.0;
		init();
		try{
			bg = new MovingBackground("startscreen.png");
			bg.setSpeed(bgspeed, 0);	
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void init() {
		score = 0;
		lives = 3;
		livesx = lives;
		enemies = new ArrayList<Enemies>();
		weapons = new ArrayList<Missiles>();
		boss = new ArrayList<AIBoss>();
		alive = true;
		player = new Player("player.gif");
		addEnemies();
		hsm = new HighScoreManager();
		bossfrequency = 750;
	}

	public void update() {
		if(alive){
			if(lives > 0) {
				player.move();
				if(bgspeed > -4.0) 
					bgspeed = -4.0;
				else
					this.bgspeed -= 0.1;
				bg.setSpeed(bgspeed, 0);

				score = Math.round(score + 0.5);

				for(int i=0;i<enemies.size(); i++) {
					Enemies o = enemies.get(i);
					if (o.isActive())
						o.move();
					else
						o.kill();
					if(o.getY() > MainPanel.HEIGHT)
						enemies.remove(i);
				}

				weapons = player.getMissiles();

				for (int i = 0; i < weapons.size(); i++) {
					Missiles m = weapons.get(i);
					if (m.isVisible())
						m.move();
					else if(!m.isVisible())
						weapons.remove(i);
				}
				for (int i = 0; i < boss.size(); i++) {
					if(boss.get(i).isAlive())
						boss.get(i).attack();
					else
						boss.get(i).kill();
					if(boss.get(i).getY() > MainPanel.HEIGHT)
						boss.remove(i);
				}
				if((score % (bossfrequency)) == 0 ) {
					//create a new boss every time the score reaches a multiple 
					//of 1500 which CAN stack onto existing bosses
					addAIBoss();
				}

				player.updateTimer();
			}
		}else if(!alive){
			if(bgspeed < 0) 
				bgspeed += 3.0;
			else 
				bgspeed = 0;
			bg.setSpeed(bgspeed,0);
			player.die();
		}

		bg.move();
		checkCollisions();

		if(score % (int) (150*Math.random()+25) == 0) {
			//add enemies at a random time, position and speed
			addEnemies();
		}
	}

	public void addEnemies() {
		enemies.add(new Enemies(
				MainPanel.WIDTH + 35, 
				(int) ((MainPanel.HEIGHT - 300)*Math.random()), 
				(int) (10 * Math.random()) + 10, 
				"obstacles.gif"));
	}

	public void addAIBoss() {
		boss.add(new AIBoss(720, 300, "boss.png", this.player, weapons));
	}

	public void checkCollisions() {
		Rectangle playerbound = player.getBounds();
		for(int i=0; i<enemies.size(); i++) {
			Enemies o = enemies.get(i);
			if(o.isActive()){
				Rectangle obstaclebound = o.getBounds();

				if (obstaclebound.intersects(playerbound)) {
					lives -= 1;
					o.setActivity(false);
					if(lives < 1) {
						lives = 0;
						alive = false;
					}
				}
			}
		}

		weapons = player.getMissiles();
		for (int i = 0; i < weapons.size(); i++) {
			Missiles m = weapons.get(i);

			Rectangle r1 = m.getBounds();

			for(int j=0; j<enemies.size(); j++) {
				Enemies a = enemies.get(j);
				Rectangle r2 = a.getBounds();

				if (r1.intersects(r2)) {
					m.setVisible(false);
					a.setActivity(false);
				}
			}

			for(int j = 0; i < boss.size(); i++) {
				Rectangle bossbounds = boss.get(j).getBounds();
				if(r1.intersects(bossbounds)){
					lives = 3;
					player.add5Weapons();
					boss.get(j).setLife(false);
					m.setVisible(false);
				}
			}
		}
		for (int i =0; i<boss.size(); i++) {
			Rectangle bossbounds = boss.get(i).getBounds();
			if(playerbound.intersects(bossbounds)){
				boss.get(i).setLife(false);
				alive = false;
			}
		}
	}

	public void draw(Graphics2D g) {
		//draw the background
		bg.draw(g);

		//draw the player
		g.drawImage(player.getImage(), player.getX(), player.getY(), null);

		for(int i = 0; i<boss.size(); i++) {
			AIBoss aiboss = boss.get(i);
			g.drawImage(aiboss.getImage(), aiboss.getX(), aiboss.getY(), null);
			for(int j=0; j<enemies.size(); j++){
				enemies.get(j).setActivity(false);
			}
		}
		//draw the obstacles
		for(int i=0; i<enemies.size(); i++) {
			Enemies o = enemies.get(i);
			g.drawImage(o.getImage(), o.getX(), o.getY(), null);
		}
		//draw missiles
		for (int i = 0; i < weapons.size(); i++) {
			Missiles m = weapons.get(i);
			g.drawImage(m.getImage(), m.getX(), m.getY(), null);
		}

		if(alive) {
			g.setFont(new Font("Helvetica",Font.BOLD,25));
			g.setColor(new Color(100, 100, 100));
			g.drawString("Score: "+score, 20, 30);
			g.drawString("Missiles left: "+ player.getNumWeapons(), 660, 30);
			if(lives != livesx) {
				g.setColor(new Color(255,100,0));
			}else{
				g.setColor(new Color(255,255,255));
			}
			g.drawString("Lives left: " + lives, 20, 60);
		}else{
			g.setColor(new Color(100, 100, 100));
			g.setFont(new Font("Helvetica", Font.BOLD, 72));
			g.drawString("SCORE "+score, 200, 130);
			g.drawString("Game Over!", 238, 272);
			g.setFont(new Font("Helvetica", Font.PLAIN, 36));
			g.drawString("Press 'Q' to go back to the main menu", 158, 342);
			g.setFont(new Font("Helvetica", Font.BOLD, 72));
			g.setColor(new Color(243, 170, 0));
			g.drawString("Game Over!", 240, 270);
			g.setFont(new Font("Helvetica", Font.PLAIN, 36));
			g.drawString("Press 'Q' to go back to the main menu", 160, 340);

		}
	}

	public void keyPressed(java.awt.event.KeyEvent e) {
		int key = e.getKeyCode();
		player.keyPressed(e);
		if(key == KeyEvent.VK_Q) {
			if(!alive && (score > hsm.getLowestScore())) {
				String name = (String) JOptionPane.showInputDialog(
	                    null,
	                    "Hold on! Looks like you've made a high score!\n\n"
	                    + "Enter your username for the Hall Of Fame!\n"
	                    + "Less than 8 characters please!",
	                    "New High Score!",
	                    JOptionPane.PLAIN_MESSAGE,
	                    null,
	                    null,
	                    "Player");
				
				if(name.length() > 8)
					name = "Tiernan";
				
				if(name != null)
					hsm.addScore(name, (int)score);
			}
			screens.setScreen(0);
		}
	}

	public void keyReleased(java.awt.event.KeyEvent e) {
		player.keyReleased(e);
	}

}
