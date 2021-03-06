import java.awt.*;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import java.awt.event.KeyEvent;

/**
 * Extended Screen class to make a starting screen
 * @author deveshdayal
 */
public class StartScreen implements Screen {

	ScreenManager screens;
	MovingBackground bg;

	//options for the start screen can be another ArrayList
	private ArrayList<String> options;
	private int choice = 0;

	public StartScreen (ScreenManager s) {
		this.screens = s;
		options = new ArrayList<String>();
		options.add("START");
		options.add("INSTRUCTIONS");
		options.add("HIGH SCORES");
		options.add("QUIT");
		try{
			bg = new MovingBackground("startscreen.png");
			bg.setSpeed(-4, 0);


		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void init(){};
	public void update(){ bg.move(); };

	public void draw(Graphics2D g) { 
		bg.draw(g); 

		//draw TITLE
		ImageIcon titlei = new ImageIcon(this.getClass().getResource("title.gif"));
		Image title = titlei.getImage();
		g.drawImage(title, 30, 60, null);


		//draw MENU OPTIONS
		g.setFont(new Font("IMPACT",Font.PLAIN, 50));
		FontMetrics f = g.getFontMetrics();
		for (int i=0; i< options.size(); i++) {
			if(i == choice){
				g.setColor(new Color(100, 100, 100));
				g.setFont(new Font("Impact", Font.BOLD,50));
			}
			else{
				g.setColor(new Color(232, 146, 113));
				g.setFont(new Font("Impact", Font.PLAIN,50));
			}
			int width = f.stringWidth(options.get(i));
			g.drawString(options.get(i), MainPanel.WIDTH/2 - width/2, 250 + (i*48));
		}
	};
	public void keyPressed(java.awt.event.KeyEvent e){
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_ENTER) {
			switch(choice){
			case 0:
				//start game
				screens.setScreen(screens.MAINLEVEL);
				break;
			case 1: 
				//show instructions
				screens.setScreen(screens.INSTRUCTIONS);
				break;
			case 2:
				screens.setScreen(screens.HIGHSCORES);
				break;
			case 3:
				//close game
				System.exit(0);
				break;
			default: 
				break;
			}
		}
		if(key == KeyEvent.VK_UP){
			if(choice < 1)
				choice = 0;
			else
				choice --;
		}
		if(key == KeyEvent.VK_DOWN){
			if(choice > 2)
				choice = 3;
			else
				choice ++;
		}
	};
	public void keyReleased(java.awt.event.KeyEvent e){};
}
