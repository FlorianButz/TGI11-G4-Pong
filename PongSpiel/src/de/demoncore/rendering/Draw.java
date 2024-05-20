package de.demoncore.rendering;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import de.demoncore.game.GameObject;
import de.demoncore.game.SceneManager;
import de.demoncore.game.Settings;
import de.demoncore.gameObjects.Particle;
import de.demoncore.gameObjects.ParticleSystem;
import de.demoncore.gui.GUIObject;
import de.demoncore.gui.Gui;
import de.demoncore.main.Main;
import de.demoncore.utils.GameMath;
import de.demoncore.utils.Resources;
import de.demoncore.utils.Vector3;

@SuppressWarnings("serial")
public class Draw extends JPanel {
	
	ArrayList<GameObject> gameObjectsInScene; // Alle Objekte, die sich in dem Level befinden

	public float mouseAlpha;
	public Vector3 mouseLastPosition = Vector3.one();

	double lastTime;
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
	
		double fps = 1000000000.0 / (System.nanoTime() - lastTime);
		
		lastTime = System.nanoTime();
		
		gameObjectsInScene = new ArrayList<GameObject>(SceneManager.GetActiveScene().GetSceneObjects());
		Graphics2D g2d = (Graphics2D) g;
		
		int screenwidth = (int) Gui.GetScreenDimensions().x;
		int screenheight = (int) Gui.GetScreenDimensions().y;
		
		// Zeichne Hintergrund
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, screenwidth, screenheight);
		
		AffineTransform oldTransform = g2d.getTransform();
		
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		AffineTransform transformation = new AffineTransform();
		transformation.rotate(Math.toRadians(SceneManager.GetActiveScene().cameraZRotation),
				SceneManager.GetActiveScene().cameraPosition.x + screenwidth / 2,
				SceneManager.GetActiveScene().cameraPosition.y + screenheight / 2);
		transformation.translate(
				SceneManager.GetActiveScene().cameraPosition.x + SceneManager.GetActiveScene().localCameraPosition.x + Gui.GetScreenDimensions().x / 2,
				SceneManager.GetActiveScene().cameraPosition.y + SceneManager.GetActiveScene().localCameraPosition.y + Gui.GetScreenDimensions().y / 2);
		
		g2d.transform(transformation);
		
		// Zeichne alle Spielobjekte
		g.setColor(Color.WHITE);
		for (int i = 0; i < gameObjectsInScene.size(); i++) {
			GameObject currentGameObj = gameObjectsInScene.get(i);
			
			if(!(currentGameObj instanceof GUIObject) && Settings.GetDebugMode()){				
				Rectangle r = currentGameObj.GetBoundingBox();
				if(currentGameObj.collisionEnabled)
					g2d.setColor(Color.green);
				else
					g2d.setColor(new Color(1, 0, 0, 0.25f));
				
				g2d.setStroke(new BasicStroke(2));
				g2d.draw(r);
				
				g2d.setColor(new Color(g2d.getColor().getRed(), g2d.getColor().getGreen(), g2d.getColor().getBlue(), 100));
				
				g2d.setFont(g2d.getFont().deriveFont(10F));
				g2d.drawString(currentGameObj.getClass().getSimpleName(), currentGameObj.GetPosition().x - 10, currentGameObj.GetPosition().y - 10);
			}
			
			if(currentGameObj.renderSpecial == true) {
				
				if(currentGameObj instanceof ParticleSystem) {
					ParticleSystem system = (ParticleSystem) currentGameObj;

					List<Particle> ps = new ArrayList<Particle>(system.particles);
					for (Particle p : ps){
						if(p == null) continue;
						
						Vector3 worldPos = GetWorldPoint(p.position);

						if(Settings.GetDebugMode()) {
							g2d.setColor(Color.white);
							g2d.drawString("P" + ps.indexOf(p), worldPos.x + 2, worldPos.y - 5);
						}
						
						g.setColor(p.color);
					    g2d.rotate(Math.toRadians(p.rotation), p.position.x, p.position.y);
						g.fillRect((int)worldPos.x + (int)(p.size.x / 4), (int)worldPos.y + (int)(p.size.y / 4), (int)p.size.x, (int)p.size.y);
						g2d.rotate(Math.toRadians(-p.rotation), p.position.x, p.position.y);
					}
				}
				
			}else {
				Vector3 worldPos = GetWorldPoint(currentGameObj.GetPosition());
				g.setColor(currentGameObj.color);
				g.fillRect((int)worldPos.x, (int)worldPos.y, (int)currentGameObj.size.x, (int)currentGameObj.size.y);
			}
		}
		
		g2d.setTransform(oldTransform);
		
		// Zeichne GUI
		for(int guiObj = 0; guiObj < gameObjectsInScene.size(); guiObj++) {
			if(gameObjectsInScene.get(guiObj) instanceof GUIObject) {				
				GUIObject guiObject = (GUIObject) gameObjectsInScene.get(guiObj);
				guiObject.Draw(g2d, screenwidth, screenheight);
			}
		}
		
		// Mauszeiger
		
		// Mauszeiger langsam ausblenden wenn er sich nicht bewegt
		
		if(MouseInfo.getPointerInfo().getLocation().getX() != mouseLastPosition.x ||
				MouseInfo.getPointerInfo().getLocation().getY() != mouseLastPosition.y)
		{
			mouseAlpha = 15f;
		}else
		{
			mouseAlpha = GameMath.Lerp(mouseAlpha, 0f, 0.75f / (float)fps);
		}
	
		// Mauszeiger anzeigen
		
		g2d.setColor(new Color(1, 1, 1, GameMath.Clamp(mouseAlpha, 0, 1)));
		g2d.fillOval((int)(MouseInfo.getPointerInfo().getLocation().getX()  - Gui.GetScreenLocation().x),
						(int)(MouseInfo.getPointerInfo().getLocation().getY() - Gui.GetScreenLocation().y),
						11, 11);
		g2d.setColor(new Color(0, 0, 0, GameMath.Clamp(mouseAlpha, 0, 1)));
		g2d.fillOval((int)(MouseInfo.getPointerInfo().getLocation().getX()  - Gui.GetScreenLocation().x + 3),
						(int)(MouseInfo.getPointerInfo().getLocation().getY()  - Gui.GetScreenLocation().y + 3),
						5, 5);

		mouseLastPosition.x = (float) MouseInfo.getPointerInfo().getLocation().getX();
		mouseLastPosition.y = (float) MouseInfo.getPointerInfo().getLocation().getY();
		
		if(Settings.GetDebugMode()) {			
			g2d.setFont(Resources.uiFont.deriveFont(15F));
			g2d.setColor(new Color(1, 1, 1, 0.25f));
			g2d.drawString("Version -> " + Main.version, 15, 25);
			g2d.drawString("FPS -> " + (int)fps, 15, 45);
		}
		
		repaint();
	}
	
	public Vector3 GetWorldPoint(Vector3 vec) {
		return vec;
	}
	
}
