import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;

import com.sun.glass.events.KeyEvent;

/**
 * Main game screen
 * @author deveshdayal
 *
 */
public class GameScreen implements Screen{

	private ScreenManager screens;
	private MovingBackground bg;

	private String HUD;
	private int speed;
	private double score;
	private boolean alive;
	
	private Player player;

	public GameScreen (ScreenManager s){
		this.screens = s;
		speed = -4;
		score = 0;
		alive = true;
		player = new Player("player.gif");
		init();
		try{
			bg = new MovingBackground("startscreen.png");
			bg.setSpeed(speed, 0);	
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void init() {
		// TODO Auto-generated method stub
	}

	public void update() {
		// TODO Auto-generated method stub
		if(alive){
			this.speed -= 0.001;
			score = Math.round(score + 0.5);
		}else if(!alive){
			this.speed = 0;
		}
		bg.move();
		player.move();
	}

	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		bg.draw(g);

		g.setFont(new Font("Helvetica",Font.PLAIN,25));
		g.setColor(new Color(255,255,255));
		g.drawString("Score: "+score, 30, 30);
		
		if (player.isVisible())
			g.drawImage(player.getImage(), player.getX(), player.getY(),
					null);
	}

	public void keyPressed(java.awt.event.KeyEvent e) {
		// TODO Auto-generated method stub
		int key = e.getKeyCode();
		player.keyPressed(e);
		if(key == KeyEvent.VK_SPACE) {
			//PAUSE THE SCORE
			alive = !alive;	
		}
	}

	public void keyReleased(java.awt.event.KeyEvent e) {
		// TODO Auto-generated method stub
		player.keyReleased(e);
	}

}
