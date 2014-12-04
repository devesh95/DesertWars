import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Game implements ActionListener{
	
	private final JMenuBar jm;
	private final JMenu instr;
	private final JMenuItem read;
	
	public Game(){
		final JFrame window = new JFrame("PLATFORMER");
		jm = new JMenuBar();
		instr = new JMenu("Instructions");
		read = new JMenuItem("Read Here");
		read.addActionListener(this);
		instr.add(read);
		jm.add(instr);
		window.setContentPane(new MainPanel());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.pack();
		window.setVisible(true);
	}
	public static void main(String[] args) {
		new Game();
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == read){
			//readme text here
		}
			
	}

}
