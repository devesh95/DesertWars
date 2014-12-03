import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

/**
 * cool moving background for the start screen
 * made completely generic so that it can be used as needed later on
 * @author deveshdayal
 */
public class MovingBackground {

	BufferedImage background;
	
	private double x;
	private double y;
	private double dx;
	private double dy;
	
	MovingBackground(String filename) {
		try{
			background = ImageIO.read(getClass().getResourceAsStream(filename));
		}
		catch(Exception ioe){
			ioe.printStackTrace();
		}
	}
	
	public void speedPosition(double x, double y){
		this.x = x / MainPanel.WIDTH;
		this.y = y / MainPanel.HEIGHT;
	}
	
	public void setSpeed(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}
	
	public void move() {
		x += dx;
		y += dy;
	}
	
	public double getDX(){
		return dx;
	}
	
	public double getDY(){
		return dy;
	}
	
	public void draw(Graphics2D g) {
		g.drawImage(background, (int)x, (int)y, null);
		
		if(x < 0){
			g.drawImage(background, (int)x + MainPanel.WIDTH, (int)y, null);
		}
		if((x + MainPanel.WIDTH) < 0)
			x = MainPanel.WIDTH;
		if(x > 0){
			g.drawImage(background, (int)x - MainPanel.WIDTH, (int)y, null);
		}
		if(x > MainPanel.WIDTH)
			x = 0;

	}
}
