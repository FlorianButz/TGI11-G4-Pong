package de.demoncore.gui;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;

import de.demoncore.actions.KeyHandler;
import de.demoncore.game.GameLogic;
import de.demoncore.game.Settings;
import de.demoncore.main.Main;
import de.demoncore.rendering.Draw;
import de.demoncore.utils.Vector3;

public class Gui {
	
	private static JFrame frame;
	
	private static Draw draw;
	
	public Gui(GameLogic spiellogik) {
	
		frame = new JFrame();
		frame.setSize(1200, 900);
		frame.setMinimumSize(new Dimension(750, 600));
		frame.setTitle(Main.gameName);
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
		
		frame.setExtendedState(frame.MAXIMIZED_BOTH);
		
		// Aktiviere fullscreen nach einstellung
		Gui.Fullscreen(Settings.getFullscreen());
		
		// Kein Maus Cursor
		BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
		    cursorImg, new Point(0, 0), "blank cursor");
		frame.getContentPane().setCursor(blankCursor);
		
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
		if(frame == null) return Vector3.zero();
		if(frame.getContentPane() == null) return Vector3.zero();
		return new Vector3(frame.getContentPane().getWidth(), frame.getContentPane().getHeight());
	}
	
	public static Vector3 GetScreenLocation() {
		return new Vector3(frame.getLocationOnScreen().x, frame.getLocationOnScreen().y);
	}
	
	public static void Fullscreen(boolean isEnabled) {
		if(isEnabled) {

			frame.setExtendedState(frame.MAXIMIZED_BOTH);
			frame.setUndecorated(true);
			
		}else {
			
			frame.setExtendedState(frame.MAXIMIZED_BOTH);
			frame.setUndecorated(false);
			
		}
	}
	
	public static void addOnApplicationClose(WindowListener event) {
		frame.addWindowListener(event);
	}
	
	public static void RemoveOnApplicationClose(WindowListener event) {
		frame.removeWindowListener(event);
	}
	
	public static GraphicsConfiguration GetGraphicsConfig() {
		return frame.getGraphicsConfiguration();
	}
	
	
}
