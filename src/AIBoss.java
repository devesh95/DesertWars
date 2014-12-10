import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

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
	private boolean alternate;
	private ArrayList<Missiles> weapons;

	public AIBoss(int x, int y, String location, Player p, ArrayList<Missiles> m) {
		this.path = location;
		this.player = p;
		ImageIcon ii = new ImageIcon(this.getClass().getResource(path));
		image = ii.getImage();
		this.x = x;
		this.y = y;
		this.width = 173;
		this.height = 98;
		this.alive = true;
		this.alternate = true;
		this.weapons = m;
		dy = 5;
		dx = 3;
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

		if(weapons.size() > 0 || player.weaponsFired()){
			smartDodge();
		}
		else {
			smartMove();
		}

	}

	public void smartMove() {
		int pY = player.getY();
		if(pY - player.height > y)
			dy = - 5;
		else if(y > pY + 2*player.height)
			dy = 5;
		else
			dy = 0;
	}

	public void smartDodge() {
		for(int i = 0; i< weapons.size(); i++){
			int mY = weapons.get(i).getY();
			if(mY - 30 > y && mY + 30 < y + height){
				if(alternate) 
					incrBY(10, 15);
				else 
					incrBY(-10, 15);
				alternate = !alternate;
			}
		}
	}

	public void kill() {
		x += 15;
		y += 15;
		if(y > MainPanel.HEIGHT + height + 100)
			y = MainPanel.HEIGHT + height + 100;
	}

	public void incrBY(int v, int cap) {
		dy -= v;
		if(dy > cap)
			dy = cap;
		else if(dy < (-1*cap))
			dy = (1*cap);
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
