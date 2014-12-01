import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import com.sun.glass.events.KeyEvent;

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
	private boolean alive;
	private Player player;
	private ArrayList<Obstacles> obstacles;
	private ArrayList<Missiles> weapons;

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
		obstacles = new ArrayList<Obstacles>();
		weapons = new ArrayList<Missiles>();
		alive = true;
		player = new Player("player.gif");
		addObstacles();
	}

	public void update() {
		// TODO Auto-generated method stub
		if(alive){
			if(lives > 0) {
				if(bgspeed > -8.0) 
					bgspeed = -8.0;
				else
					this.bgspeed -= 0.1;
				bg.setSpeed(bgspeed, 0);
				score = Math.round(score + 0.5);
			}
			player.move();
		}else if(!alive){
			bg.setSpeed(0,0);
			player.die();
		}
		
		bg.move();
		checkCollisions();

		if(score % (int) (100*Math.random() + 20) == 0) {
			addObstacles();
		}

		for(int i = 0; i < obstacles.size(); i++){
			Obstacles o = obstacles.get(i);
			if (o.isActive())
				o.move();
			else
				o.kill();
			if(o.getY() > MainPanel.HEIGHT)
				obstacles.remove(i);
		}
		weapons = player.getMissiles();

		for (int i = 0; i < weapons.size(); i++) {
			Missiles m = weapons.get(i);
			if (m.isVisible() && player.weaponsFired())
				m.move();
			else if(!m.isVisible())
				weapons.remove(i);
		}
	}

	public void addObstacles() {
		obstacles.add(new Obstacles(
				MainPanel.WIDTH + 35, 
				(int) ((MainPanel.HEIGHT - 300)*Math.random()), 
				(int) (12 * Math.random()) + 3, 
				"obstacles.png"));
	}

	public void checkCollisions() {
		Rectangle playerbound = player.getBounds();
		for (int j = 0; j<obstacles.size(); j++) {
			Obstacles o = obstacles.get(j);
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

			for (int j = 0; j<obstacles.size(); j++) {
				Obstacles a = obstacles.get(j);
				Rectangle r2 = a.getBounds();

				if (r1.intersects(r2)) {
					m.setVisible(false);
					a.setActivity(false);
				}
			}
		}
	}

	public void draw(Graphics2D g) {
		//draw the background
		bg.draw(g);
		//draw the score + lives
		g.setFont(new Font("Helvetica",Font.PLAIN,25));
		g.setColor(new Color(255,255,255));
		g.drawString("Score: "+score, 30, 30);
		g.drawString("Lives left: " + lives, 30, 60);
		g.drawString("Missiles left: "+player.getNumWeapons(), 700, 30);
		//draw the player
		g.drawImage(player.getImage(), player.getX(), player.getY(), null);
		//draw the obstacles
		for(int i = 0; i < obstacles.size(); i++){
			Obstacles o = obstacles.get(i);
			g.drawImage(o.getImage(), o.getX(), o.getY(), null);
		}
		//draw missiles
		for (int i = 0; i < weapons.size(); i++) {
			Missiles m = weapons.get(i);
			g.drawImage(m.getImage(), m.getX(), m.getY(), null);
		}

	}


	public void keyPressed(java.awt.event.KeyEvent e) {
		// TODO Auto-generated method stub
		int key = e.getKeyCode();
		player.keyPressed(e);
		if(key == KeyEvent.VK_Q) {
			screens.setScreen(0);
		}
	}

	public void keyReleased(java.awt.event.KeyEvent e) {
		// TODO Auto-generated method stub
		player.keyReleased(e);
	}

}
