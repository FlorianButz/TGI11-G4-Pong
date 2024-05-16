package gui;

import javax.swing.JFrame;

import actions.KeyHandler;
import game.GameLogic;

public class Gui {
	
	private int screenwidth;
	private int screenheight;
	private JFrame frame;
	
	public Gui(GameLogic spiellogik) {
		
		screenwidth = 800;
		screenheight = 600;
		spiellogik.screenwidth = screenwidth;
		spiellogik.screenheight = screenheight;
		
		frame = new JFrame();
		frame.setSize(screenwidth, screenheight);
		frame.setTitle("Pong");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.addKeyListener(new KeyHandler(spiellogik));
		frame.requestFocus();
		
		Draw lbldraw = new Draw(spiellogik, screenwidth, screenheight);
		lbldraw.setBounds(0,0, screenwidth, screenheight);
		lbldraw.setVisible(true);
		frame.add(lbldraw);
		
		frame.setVisible(true);
	}

}
