package de.demoncore.gui;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import de.demoncore.game.GameLogic;
import de.demoncore.game.GameObject;
import de.demoncore.game.SceneManager;
import de.demoncore.utils.Vector3;

@SuppressWarnings("serial")
public class Draw extends JPanel {
	
	private int screenwidth;
	private int screenheight;
	
	ArrayList<GameObject> gameObjectsInScene; // Alle Objekte, die sich in dem Level befinden

	public Draw(GameLogic spiellogik, int screenBreite, int screenHoehe) {
		gameObjectsInScene = SceneManager.GetActiveScene().GetSceneObjects();

		screenwidth = screenBreite;
		screenheight = screenHoehe;
	}
	
	public void paintComponent(Graphics g){
		
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		// Zeichne Hintergrund
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, screenwidth, screenheight);
		
		AffineTransform transformation = new AffineTransform();
		transformation.rotate(Math.toRadians(SceneManager.GetActiveScene().cameraZRotation), this.screenwidth / 2, this.screenheight / 2);
		transformation.translate(
				SceneManager.GetActiveScene().cameraPosition.x + SceneManager.GetActiveScene().localCameraPosition.x,
				SceneManager.GetActiveScene().cameraPosition.y + SceneManager.GetActiveScene().localCameraPosition.y);
		
		g2d.transform(transformation);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		// Zeichne alle Spielobjekte
		g.setColor(Color.WHITE);
		for (int i = 0; i < gameObjectsInScene.size(); i++) {
			GameObject currentGameObj = gameObjectsInScene.get(i);
			Vector3 worldPos = GetWorldPoint(currentGameObj.position);
			g.fillRect((int)worldPos.x, (int)worldPos.y, (int)currentGameObj.size.x, (int)currentGameObj.size.y);
		}
		
		repaint();
	}
	
	public Vector3 GetWorldPoint(Vector3 vec) {
		return vec;
	}
	
}
