import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class HighScoreScreen implements Screen {
	
	private ScreenManager screens;
	private MovingBackground bg;
	private double bgspeed;
	private String highscores;
	private HighScoreManager hsm;
	private String[] scoreList;

	public HighScoreScreen (ScreenManager s) {
		hsm = new HighScoreManager();
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
		highscores = hsm.getHighscores();
		parseScores(highscores);
	}
	
	public void parseScores(String s) {
		scoreList = s.split("\n");
	}

	public void update() {
		bg.move();
	}

	public void draw(Graphics2D g) {
		bg.draw(g);
		g.setColor(new Color(1f, 1f, 1f, 0.5f));
		g.fillRect(100, 50, 700, 500);
		g.setColor(new Color(243, 170, 0));
		g.setFont(new Font("Impact", Font.BOLD, 50));
		g.drawString("HALL OF FAME", 320, 100);
		g.setFont(new Font("Impact", Font.PLAIN, 40));
		g.setColor(new Color(100,100,100));
		for(int i = 0; i<scoreList.length/2; i++){
			g.drawString(scoreList[i], 160, 180+(i*50));
		}
		for(int i = 0; i<scoreList.length/2; i++){
			g.drawString(scoreList[i+scoreList.length/2], 490, 180+(i*50));
		}
		g.setColor(new Color(243, 170, 0));
		g.setFont(new Font("Impact", Font.PLAIN, 32));
		g.drawString("Press 'Q' to go back to the main menu", 220, 525);
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_Q) {
			screens.setScreen(0);
		}
	}

	public void keyReleased(KeyEvent e) {}

}
