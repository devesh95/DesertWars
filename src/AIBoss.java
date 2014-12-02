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
	private boolean active;
	private Image image;
	private Player player;

	public AIBoss(int x, int y, String location, Player p) {
		this.path = location;
		this.player = p;
		ImageIcon ii = new ImageIcon(this.getClass().getResource(path));
		image = ii.getImage();
		this.x = x;
		this.y = y;
		this.width = image.getHeight(null);
		this.height = image.getWidth(null);
		this.active = true;
		dy = 7;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void move() {
		y -= dy;
		if(y < 1)
			dy = -7;
		if(y > 500 - height) 
			dy = 7;
		if(player.weaponsFired()){
			//do something
		}

	}
	public boolean isActive(){
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
