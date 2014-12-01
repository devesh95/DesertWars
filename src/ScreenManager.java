
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class ScreenManager {

	//store the possible screens as a iterable list
	private ArrayList<Screen> screens;
	private int current;
	
	int STARTSCREEN = 0;
	int MAINLEVEL = 1;
	
	public ScreenManager() {
		
		screens = new ArrayList<Screen>();
		current = STARTSCREEN;
		screens.add(new StartScreen(this));
		screens.add(new GameScreen(this));
	}
	
	public void setScreen(int a) {
		current = a;
		screens.get(current).init();
	}	
	public void update() {
		screens.get(current).update();
	}	
	public void draw(Graphics2D g) {
		screens.get(current).draw(g);
	}
	public void keyPressed(KeyEvent e){
		screens.get(current).keyPressed(e);
	}
	public void keyReleased(KeyEvent e){
		screens.get(current).keyReleased(e);
	}
	
}
