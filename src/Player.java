import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Player {

	private String path;
	private int dx;
	private int dy;
	private int x;
	private int y;
	private int width;
	private int height;
	private boolean visible;
	private Image image;


	public Player (String location) {
		this.path = location;
		ImageIcon ii = new ImageIcon(this.getClass().getResource(path));
		image = ii.getImage();
		width = 133;
		height = 62;
		visible = true;
		x = 30;
		y = 390;
	}


	public void move() {
		x += dx;
		y += dy;
		
		if (x < 1) {
			x = 1;
		}
		if (y < 1) {
			y = 1;
		}
		
		if (y > MainPanel.HEIGHT - height) {
			y = MainPanel.HEIGHT - height;
		}
		if (x > MainPanel.WIDTH - width) {
			x = MainPanel.WIDTH - width;
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getImage() {
		return image;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public boolean isVisible() {
		return visible;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

	public void keyPressed(java.awt.event.KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_SPACE) {

		}
		if (key == KeyEvent.VK_LEFT) {
			dx = -5;
		}
		if (key == KeyEvent.VK_RIGHT) {
			dx = 5;
		}
		if (key == KeyEvent.VK_UP) {
			dy = -5;
		}
		if (key == KeyEvent.VK_DOWN) {
			dy = +5;
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_LEFT) {
			dx = 0;
		}

		if (key == KeyEvent.VK_RIGHT) {
			dx = 0;
		}

		if (key == KeyEvent.VK_UP) {
			dy = 0;
		}
		if (key == KeyEvent.VK_DOWN) {
			dy = 0;
		}

	}
}
