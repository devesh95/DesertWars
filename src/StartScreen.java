import java.awt.*;
import java.util.ArrayList;

import com.sun.glass.events.KeyEvent;

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
		g.setFont(new Font("Helvetica",Font.PLAIN,72));
		g.setColor(new Color(100,100,100));
		g.drawString("SPACE WARS", 217, 133);
		g.setColor(new Color(255,255,255));
		g.drawString("SPACE WARS", 220, 130);

		
		//draw MENU OPTIONS
		g.setFont(new Font("Helvetica",Font.PLAIN, 30));
		for (int i=0; i< options.size(); i++) {
			if(i == choice){
				g.setColor(new Color(0, 178, 45));
				g.setFont(new Font("Helvetica", Font.BOLD,30));
			}
			else{
				g.setColor(new Color(255, 255, 255));
				g.setFont(new Font("Helvetica", Font.PLAIN,30));
			}
			g.drawString(options.get(i), 350, 230 + (i*35));
		}
	};
	public void keyPressed(java.awt.event.KeyEvent e){
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_ENTER) {
			switch(choice){
			case 0:
				//start game
				System.out.println("Game request");
				screens.setScreen(screens.MAINLEVEL);
				break;
			case 1: 
				//show instructions
				System.out.println("NO HELP");
				break;
			case 2:
				//show scores
				System.out.println("No scores as yet");
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
