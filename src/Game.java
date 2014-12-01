import javax.swing.JFrame;

public class Game {

	public static void main(String[] args) {
		final JFrame window = new JFrame("PLATFORMER");
		window.setContentPane(new MainPanel());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.pack();
		window.setVisible(true);
	}

}
