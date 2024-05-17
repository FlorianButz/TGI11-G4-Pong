package de.demoncore.gui;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;

import de.demoncore.actions.KeyHandler;
import de.demoncore.game.GameLogic;
import de.demoncore.rendering.Draw;
import de.demoncore.utils.Vector3;

public class Gui {
	
	private static JFrame frame;
	
	private static Draw draw;
	
	public Gui(GameLogic spiellogik) {
	
		frame = new JFrame();
		frame.setSize(800, 600);
		frame.setTitle("Pong Auf Crack");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);

		Dimension d = new Dimension(450, 450);
		
		frame.getContentPane().setPreferredSize(d);
		frame.getContentPane().setMinimumSize(d);
		frame.getContentPane().setMaximumSize(d);
		
		frame.setLocationRelativeTo(null);
		
		KeyHandler k = new KeyHandler(spiellogik);
		frame.addKeyListener(k);
		frame.addMouseListener(k);
		
		frame.requestFocus();
		
		BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
		    cursorImg, new Point(0, 0), "blank cursor");
		frame.getContentPane().setCursor(blankCursor);
		
		// FULLSCREEN
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
		
		draw = new Draw();
		
		draw.setVisible(true);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.CENTER)
				.addComponent(draw, GroupLayout.PREFERRED_SIZE, 785, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.CENTER)
				.addComponent(draw, GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
		);
		frame.getContentPane().setLayout(groupLayout);
		
		frame.setVisible(true);
	}

	public static Vector3 GetScreenDimensions() {
		return new Vector3(draw.getWidth(), draw.getHeight());
	}
	
	public static Vector3 GetScreenLocation() {
		return new Vector3(frame.getLocationOnScreen().x, frame.getLocationOnScreen().y);
	}
}
