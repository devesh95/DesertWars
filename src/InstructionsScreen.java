import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class InstructionsScreen implements Screen {

	private ScreenManager screens;
	private MovingBackground bg;
	private double bgspeed;

	public InstructionsScreen (ScreenManager s) {
		this.screens = s;
		bgspeed = -4.0;
		init();
		try{
			bg = new MovingBackground("startscreen.png");
			bg.setSpeed(bgspeed, 0);	
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void init() {}

	public void update() {
		bg.move();
	}

	public void draw(Graphics2D g) {
		bg.draw(g);
		g.setColor(new Color(1f, 1f, 1f, 0.5f));
		g.fillRect(100, 50, 700, 500);
		g.setColor(new Color(243, 170, 0));
		g.setFont(new Font("Impact", Font.BOLD, 50));
		g.drawString("HOW TO PLAY:", 320, 100);
		g.setFont(new Font("Impact", Font.PLAIN, 32));
		g.drawString("Press 'Q' to go back to the main menu", 220, 525);
		g.drawString("CONTROLS", 210, 250);
		g.drawString("FEATURES", 540, 250);
		g.setFont(new Font("Impact", Font.PLAIN, 25));
		g.drawString("Main Menu", 230, 425);
		g.drawString("Quit game", 230, 460);
		g.drawString("More Missiles! More Konami!", 470, 330);
		g.setColor(new Color(0f, 0f, 0f, 0.7f));
		g.drawString("Survive as long as you can against the onslaught of spaceships",
				130, 160);
		g.drawString("coming at you.  Beware: crashing into a boss is instant death!",
				130, 200);
		g.drawString("Press 'F' to see full details.", 490, 390);
		g.drawLine(450, 230, 450, 480);
		g.drawLine(460, 350, 790, 350);
		g.drawString("\u2190", 125, 310);
		g.drawString("\u2192", 180, 310);
		g.drawString("\u2191", 160, 290);
		g.drawString("\u2193", 160, 330);
		g.fillRect(130, 360, 70, 20);
		g.fillRect(155, 410, 20, 20);
		g.fillRect(147, 440, 36, 20);
		g.setColor(new Color(255, 255, 255));
		g.setFont(new Font("Helvetica", Font.PLAIN, 12));
		g.drawString("SPACE", 145, 375);
		g.drawString("Q", 160, 425);
		g.drawString("ESC", 152, 455);
		ImageIcon ii = new ImageIcon(this.getClass().getResource("player.gif"));
		g.drawImage(ii.getImage(), 220, 270, null);
		ImageIcon ij = new ImageIcon(this.getClass().getResource("missile.gif"));
		g.drawImage(ij.getImage(), 240, 365, null);
		ImageIcon ik = new ImageIcon(this.getClass().getResource("konami.png"));
		g.drawImage(ik.getImage(), 460, 270, null);
		ImageIcon il = new ImageIcon(this.getClass().getResource("boss.png"));
		g.drawImage(il.getImage(), 530, 395,null);
	}

	static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}


	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_Q) {
			screens.setScreen(0);
		}
		if(key == KeyEvent.VK_F) {
			try {
				//edit the README file directly to change instructions
				JOptionPane.showMessageDialog(null, 
						readFile("README.txt",StandardCharsets.UTF_8));
			} catch (HeadlessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public void keyReleased(KeyEvent e) {}

}
