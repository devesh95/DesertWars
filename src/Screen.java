import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

//necessary methods needed in every screen class

public interface Screen {
	
	public void init();
	public void update();
	public void draw(Graphics2D g);
	public void keyPressed(KeyEvent e);
	public void keyReleased(KeyEvent e);
}
