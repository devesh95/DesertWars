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
		alive = true;
		player = new Player("player.gif");
		addObstacles();
	}

	public void update() {
		// TODO Auto-generated method stub
		if(alive){
			if(bgspeed > -10) 
				bgspeed = -10.0;
			else
				this.bgspeed -= 0.1;
			bg.setSpeed(bgspeed, 0);
			score = Math.round(score + 0.5);
		}else if(!alive){
			bg.setSpeed(-4.0,0);
		}
		bg.move();
		player.move();
		checkCollisions();
		
		if(score % (int) (500*Math.random() + 20) == 0) {
			addObstacles();
		}
		
		for(int i = 0; i < obstacles.size(); i++){
			Obstacles o = obstacles.get(i);
			if (o.isVisible())
				o.move();
			else
				obstacles.remove(i);
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
			Rectangle obstaclebound = o.getBounds();

			if (obstaclebound.intersects(playerbound)) {
				//player.setVisible(false);
				lives -= 1;
				o.setVisible(false);
				if(lives == 0)
					lives = 0;
					alive = false;
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
		//draw the player
		if (player.isVisible())
			g.drawImage(player.getImage(), player.getX(), player.getY(), null);
		//draw the obstacles
		for(int i = 0; i < obstacles.size(); i++){
			Obstacles o = obstacles.get(i);
			if (o.isVisible())
				g.drawImage(o.getImage(), o.getX(), o.getY(), null);
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
