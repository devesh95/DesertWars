import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class AIBoss extends Obstacles {
	private String path;
	private int x;
	private int y;
	private int width;
	private int height;
	private boolean active;
	private Image image;

	public AIBoss(int x, int y, String location) {
		super(x, y, 5, location);
		this.path = location;
		ImageIcon ii = new ImageIcon(this.getClass().getResource(path));
		image = ii.getImage();
		this.x = x;
		this.y = y;
		this.width = image.getHeight(null);
		this.height = image.getWidth(null);
	}
	
	@Override
	
	public void move() {
		if (x < 0 - width) {
			x = MainPanel.WIDTH + width;
			y = (int) ((MainPanel.HEIGHT-300)*Math.random());
		}
	}

}
