import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;


public class Missiles {

	private int x;
	private int y;
	private Image image;
	boolean visible;
	private int width;
	private int height;

	public Missiles (int x, int y) {
		ImageIcon ii =
				new ImageIcon(this.getClass().getResource("missile.gif"));
		image = ii.getImage();
		visible = true;
		width = image.getWidth(null);
		height = image.getHeight(null);
		this.x = x;
		this.y = y;
	}
	
	public Image getImage() {
        return image;
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

    public void setVisible(boolean b) {
        this.visible = b;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void move() {
        x += 10;
        if (x > MainPanel.WIDTH)
            visible = false;
    }
}
