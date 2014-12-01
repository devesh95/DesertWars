import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;


public class Obstacles {

	private String path;
	private int x;
	private int y;
	private int width;
	private int height;
	private boolean visible;
	private int speed;
	private Image image;

	public Obstacles (int x, int y, int speed, String location) {
		this.path = location;
		ImageIcon ii = new ImageIcon(this.getClass().getResource(path));
		image = ii.getImage();
		width = 128;
		height = 62;
		this.visible = true;
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

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public Image getImage() {
		return image;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
}
