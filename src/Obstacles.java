import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;


public class Obstacles {

	private String path;
	private int x;
	private int y;
	private int width;
	private int height;
	private boolean active;
	private int speed;
	private Image image;

	public Obstacles (int x, int y, int speed, String location) {
		this.path = location;
		ImageIcon ii = new ImageIcon(this.getClass().getResource(path));
		image = ii.getImage();
		width = 128;
		height = 70;
		this.active = true;
		this.x = x;
		this.y = y;
		this.speed = speed;
	}

	public void move() {
		if (x < 0 - width) {
			x = MainPanel.WIDTH + width;
			y = (int) ((MainPanel.HEIGHT-300)*Math.random());
		}
		x -= speed;
	}
	public void kill() {
		y += 15;
		if(y > MainPanel.HEIGHT + height + 100)
			y = MainPanel.HEIGHT + height + 100;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isActive() {
		return active;
	}

	public void setActivity(boolean activity) {
		this.active = activity;
	}

	public Image getImage() {
		return image;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
}
