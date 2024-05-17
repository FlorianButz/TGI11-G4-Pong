package de.demoncore.rendering;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import de.demoncore.game.GameLogic;
import de.demoncore.game.GameObject;
import de.demoncore.game.Particle;
import de.demoncore.game.ParticleSystem;
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
			
			if(currentGameObj.renderSpecial == true) {
				
				if(currentGameObj instanceof ParticleSystem) {
					ParticleSystem system = (ParticleSystem) currentGameObj;

					List<Particle> ps = new ArrayList<Particle>(system.particles);
					for (Particle p : ps){
						if(p == null) continue;
						
						Vector3 worldPos = GetWorldPoint(p.position);
						g.setColor(p.color);
						g.fillRect((int)worldPos.x + (int)(p.size.x / 2), (int)worldPos.y + (int)(p.size.y / 2), (int)p.size.x, (int)p.size.y);
					}
				}
				
			}else {
				Vector3 worldPos = GetWorldPoint(currentGameObj.position);
				g.fillRect((int)worldPos.x, (int)worldPos.y, (int)currentGameObj.size.x, (int)currentGameObj.size.y);
			}
		}
		
		repaint();
	}
	
	public Vector3 GetWorldPoint(Vector3 vec) {
		return vec;
	}
	
}
