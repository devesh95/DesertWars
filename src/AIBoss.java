import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class AIBoss {
	private String path;
	private int x;
	private int y;
	private int width;
	private int height;
	private int dy;
	private int dx;
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
		this.height = 92;
		this.alive = true;
		dy = 5;
		dx = 4;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void move() {
		//Automatically detects where the player is and moves towards it 
		y -= dy;
		x -= dx;
		if(y < 1){
			dy = -5;
			smartMove();
		}
		if(y > 450 - height) {
			dy = 5;
			smartMove();
		}
		if(x < 1)
			dx = -dy;
		if(x > 720)
			dx = -dy;
	}
	
	public void smartMove() {
		if(player.getX() < x)
			dx = 4;
		else
			dx = -4;
	}
	public void kill() {
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
