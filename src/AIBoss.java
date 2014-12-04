import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class AIBoss {
	private String path;
	private int x;
	private int y;
	private int width;
	private int height;
	private double dy;
	private double dx;
	private Image image;
	private Player player;
	private boolean alive;

	public AIBoss(int x, int y, String location, Player p) {
		this.path = location;
		this.player = p;
		ImageIcon ii = new ImageIcon(this.getClass().getResource(path));
		image = ii.getImage();
		this.x = x;
		this.y = y;
		this.width = 173;
		this.height = 98;
		this.alive = true;
		dy = 5;
		dx = 1;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void attack() {
		//Automatically detects where the player is and moves towards it 
		y -= dy;
		x -= dx;
		if(y < 1 || y > 450 - height){
			dy = -dy;
		}
		else if(x < 1 || x > 720)
			dx = -dx;
		else
			smartMove();
	}

	public void defend() {
		y -= dy;
		if(y < 1){
			dy = -5;
		}
		if(y > 450 - height) {
			dy = 5;
		}
	}

	public void smartMove() {
		double choice = Math.random();
		if(choice < 0.5) {
			if(player.getX() < x)
				dx = 1;
			else
				dx = -1;
		}else{
			if(player.getY() < y-45)
				dy = 5;
			else
				dy = -5;
		}
	}

	public void kill() {
		x += 15;
		y += 15;
		if(y > MainPanel.HEIGHT + height + 100)
			y = MainPanel.HEIGHT + height + 100;
	}
	public Image getImage() {
		return image;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
	public boolean isAlive() {
		return alive;
	}
	public void setLife(boolean b) {
		this.alive = b;
	}

}
