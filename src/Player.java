import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Player {

	private String path;
	private int dx;
	private int dy;
	private int x;
	private int y;
	private int numweapons;
	int width;
	int height;
	private boolean visible;
	private Image image;
	private boolean fired;
	private ArrayList<Missiles> weapons;
	private Konami check;
	private boolean reset;
	private double timer;

	public Player (String location) {
		this.path = location;
		ImageIcon ii = new ImageIcon(this.getClass().getResource(path));
		weapons = new ArrayList<Missiles>();
		image = ii.getImage();
		width = 133;
		height = 50;
		numweapons = 25;
		visible = true;
		x = 30;
		y = 390;
		check = new Konami();
		this.timer = 0;
		this.reset = false;
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

		if (y > 390) {
			y = 390;
		}
		if (x > MainPanel.WIDTH - width) {
			x = MainPanel.WIDTH - width;
		}
	}
	public void die() {
		y += 5;
		if(y > MainPanel.HEIGHT + 100)
			y = MainPanel.HEIGHT + 100;
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
	public ArrayList<Missiles> getMissiles() {
		return weapons;
	}
	public int getNumWeapons() {
		return numweapons;
	}
	public void add5Weapons() {
		numweapons += 5;
	}
	public void fireWeapons() {
		if(numweapons > 0) {
			weapons.add(new Missiles(x+25, y+5));
			numweapons -= 1;
		}
	}

	public void updateTimer() {
		if (reset) {
			timer = 0;
			this.reset = false;
		}
		timer += 1;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public boolean isVisible() {
		return visible;
	}
	public boolean weaponsFired() {
		return fired;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

	public void keyPressed(java.awt.event.KeyEvent e) {
		int key = e.getKeyCode();
		this.reset = true;
		if (key == KeyEvent.VK_SPACE) {
			fired = true;
			fireWeapons();
		}
		if (key == KeyEvent.VK_LEFT) {
			dx = -7;
		}
		if (key == KeyEvent.VK_RIGHT) {
			dx = 7;
		}
		if (key == KeyEvent.VK_UP) {
			dy = -8;
		}
		if (key == KeyEvent.VK_DOWN) {
			dy = +8;
		}

		//Konami cheat code:
			if(check.wasItKonami(e.getKeyCode())) {
				if(timer < 50){
					numweapons += 100;
					System.out.println("VALID CODE");
				}
				else
					System.out.println("Timer overload.");
			}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_SPACE) {
			fired= false;
		}
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
