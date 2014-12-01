import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
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
	private int bgspeed;
	private double score;
	private boolean alive;
	private Player player;
	private ArrayList<Obstacles> obstacles;

	public GameScreen (ScreenManager s){
		this.screens = s;
		bgspeed = -4;
		score = 0;
		alive = true;
		player = new Player("player.gif");
		obstacles = new ArrayList<Obstacles>();
		addObstacles();
		init();
		try{
			bg = new MovingBackground("startscreen.png");
			bg.setSpeed(bgspeed, 0);	
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void init() {
		// TODO Auto-generated method stub
		score = 0;
	}

	public void update() {
		// TODO Auto-generated method stub
		if(alive){
			this.bgspeed -= 0.001;
			score = Math.round(score + 0.5);
		}else if(!alive){
			this.bgspeed = 0;
		}
		bg.move();
		player.move();
		if(score % 100 == 0) {
			System.out.println("TRUE");
			addObstacles();
		}
	}

	public void addObstacles() {
		obstacles.add(new Obstacles(MainPanel.WIDTH + 35, 
				//(int) ((MainPanel.HEIGHT - 300)*Math.random())
				250, 
				//(int) (15 * Math.random())
				5, 
				"obstacles.png"));
	}

	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		bg.draw(g);

		g.setFont(new Font("Helvetica",Font.PLAIN,25));
		g.setColor(new Color(255,255,255));
		g.drawString("Score: "+score, 30, 30);

		if (player.isVisible())
			g.drawImage(player.getImage(), player.getX(), player.getY(),null);

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
		if(key == KeyEvent.VK_SPACE) {
			//PAUSE THE SCORE
			alive = !alive;	
		}
		if(key == KeyEvent.VK_Q) {
			screens.setScreen(0);
		}
	}

	public void keyReleased(java.awt.event.KeyEvent e) {
		// TODO Auto-generated method stub
		player.keyReleased(e);
	}

}
