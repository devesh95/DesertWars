import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.event.*;

import javax.swing.JPanel;

/**
 * The main visible JPanel added to the frame. We'll move stuff in and out of 
 * this panel and can update it's contents dynamically
 * @author deveshdayal
 *
 */
@SuppressWarnings("serial")
public class MainPanel extends JPanel implements Runnable, KeyListener{

	final static int WIDTH = 900;
	final static int HEIGHT = 644;

	private long rate = 1000/60;
	private Thread thread;
	private boolean game;
	private BufferedImage image;
	private Graphics2D g;
	private ScreenManager screens;

	public MainPanel() {
		super();
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		setFocusable(true);
		setDoubleBuffered(true);
		requestFocus();
	}

	public void addNotify() {
		super.addNotify();
		if(thread == null) {
			thread = new Thread(this);
			addKeyListener(this);
			thread.start();
		}
	}

	public void init() {
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();

		//general font smoothing
		g.setRenderingHint(
				RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);

		game = true;
		screens = new ScreenManager();
	}

	public void run() {
		// TODO Auto-generated method stub
		init();

		while(game){
			long initT = System.currentTimeMillis();

			update();
			draw();
			onScreen();
			long currT = System.currentTimeMillis() - initT;

			try {
				Thread.sleep(rate - currT/1000);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	private void onScreen() {
		Graphics g2 = getGraphics();
		//draw on the top corner of the window
		g2.drawImage(image, 0, 0, null);
		Toolkit.getDefaultToolkit().sync();
		g2.dispose();
	}

	private void draw() {
		// TODO Auto-generated method stub
		screens.draw(g);
	}

	private void update() {
		// TODO Auto-generated method stub
		screens.update();
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		screens.keyPressed(e);
		
		if (e.getKeyCode()==KeyEvent.VK_ESCAPE)
		{
			System.exit(0);
		}       
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		screens.keyReleased(e);
	}
}
