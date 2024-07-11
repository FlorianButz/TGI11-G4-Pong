package de.demoncore.rendering;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.JPanel;

import de.demoncore.game.GameLogic;
import de.demoncore.game.GameObject;
import de.demoncore.game.SceneManager;
import de.demoncore.game.Settings;
import de.demoncore.gameObjects.RigidBody;
import de.demoncore.gui.GUIAlignment;
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

	static double accuratelastTime = 0;
	static double lastTime = 0;
	static double fps = 0;
	static double accurateFps = 0;
	static double countedFps = 0;

	public static float screenScale = 1f;
	public static float screenSize = 1f;
	
	public Draw() {
		lastTime = System.currentTimeMillis();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
	
		accurateFps = 1000000000.0 / (System.nanoTime() - accuratelastTime);
		accuratelastTime = System.nanoTime();
		
		countedFps++;
		if((System.currentTimeMillis() - lastTime) > 1000) {
			fps = countedFps;
			countedFps = 0;
			lastTime = System.currentTimeMillis();
		}
		
		gameObjectsInScene = new ArrayList<GameObject>(SceneManager.getActiveScene().getSceneObjects());
		Graphics2D g2d = (Graphics2D) g;
		
		int screenwidth = (int) Gui.GetScreenDimensions().x;
		int screenheight = (int) Gui.GetScreenDimensions().y;
		
		Settings.setUIScale((float)Math.min((float)screenwidth / 1920f, (float)screenheight / 1080f));
		
		// Zeichne Hintergrund
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, screenwidth, screenheight);
		
		AffineTransform ogTransform = g2d.getTransform();
		g2d.translate((int)(screenwidth / 2), (int)(screenheight / 2));
		//g2d.scale(scale, scale);
		g2d.translate((int)-(screenwidth / 2), (int)-(screenheight / 2));
		
		Shape clip = g2d.getClip();
		
		screenSize = Math.min((float)screenwidth / 1920f, (float)screenheight / 1080f);
		
		g2d.translate((float)screenwidth / 2, (float)screenheight / 2);
		//g2d.scale(screenScale * screenSize, screenScale * screenSize);
		g2d.scale(screenScale, screenScale);
		g2d.translate((float)-screenwidth / 2, (float)-screenheight / 2);
		
		// Speichern von altem transform
		AffineTransform oldTransform = g2d.getTransform();
		
		// Anti aliasing
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);	// Anti aliasing - sehr wichtig
		g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);	// Dithering / Ist nicht so wichtig
		g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);	// Schalte akkurate text anzeige an
		g2d.setRenderingHint( RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB ); // Text Anti aliasing - sehr wichtig
		
		// Neuen transform erstellen
		AffineTransform transformation = new AffineTransform();
		transformation.rotate(Math.toRadians(SceneManager.getActiveScene().cameraZRotation),
				-SceneManager.getActiveScene().cameraPosition.x + screenwidth / 2,
				-SceneManager.getActiveScene().cameraPosition.y + screenheight / 2);
		transformation.translate(
				-SceneManager.getActiveScene().cameraPosition.x + SceneManager.getActiveScene().localCameraPosition.x + Gui.GetScreenDimensions().x / 2,
				-SceneManager.getActiveScene().cameraPosition.y + SceneManager.getActiveScene().localCameraPosition.y + Gui.GetScreenDimensions().y / 2);
		
		g2d.transform(transformation);
		
		// Maske erstellen
		
		if(SceneManager.getActiveScene() != null) {
			Rectangle viewport = SceneManager.getActiveScene().getCameraViewport();
			g2d.setClip(viewport);
		}
		
		// Zeichne alle Spielobjekte

		g2d.setColor(Color.WHITE);
		for (int i = 0; i < gameObjectsInScene.size(); i++) {
			GameObject currentGameObj = gameObjectsInScene.get(i);
			
			Graphics2D g2dGobj = (Graphics2D) g2d.create();
			
			// Debug modus
			if(!(currentGameObj instanceof GUIObject) && Settings.getDebugMode()){
				if(currentGameObj == null) continue;
				Rectangle r = currentGameObj.getBoundingBox();
				if(currentGameObj.collisionEnabled)
					g2d.setColor(Color.green);
				else
					g2d.setColor(new Color(1, 0, 0, 0.25f));
				
				g2d.setStroke(new BasicStroke(2));
				g2d.draw(r);
				
				g2d.setColor(new Color(g2d.getColor().getRed(), g2d.getColor().getGreen(), g2d.getColor().getBlue(), 100));
				
				g2d.setFont(g2d.getFont().deriveFont(10F));
				g2d.drawString(currentGameObj.getClass().getSimpleName(), currentGameObj.getPosition().x - 10, currentGameObj.getPosition().y - 10);

				g2d.drawString("Pos: " + currentGameObj.getPosition().ToString(), currentGameObj.getPosition().x - 10, currentGameObj.getPosition().y - 20);
				
				if(currentGameObj instanceof RigidBody) {
					g2d.drawString("Vel: " + ((RigidBody)currentGameObj).velocity.ToString(), currentGameObj.getPosition().x - 10, currentGameObj.getPosition().y - 30);
				}
			}
			
			if(currentGameObj == null) continue;
			
			if(currentGameObj.enableRendering && !currentGameObj.isDistanceCulled) {
				if(!(currentGameObj instanceof GUIObject)) {	// Gucken ob es GUI objekt ist, weil die müssen als letztes auf den Bildschirm
					g2dGobj.rotate(Math.toRadians(currentGameObj.getRotationZ()), currentGameObj.getPosition().x, currentGameObj.getPosition().y);

					currentGameObj.draw(g2dGobj, screenwidth, screenheight);

					g2dGobj.rotate(Math.toRadians(-currentGameObj.getRotationZ()), currentGameObj.getPosition().x, currentGameObj.getPosition().y);
				}
			}
			
			g2dGobj.dispose();
		}
		
		if(Settings.getDebugMode()) {
			Rectangle viewport = SceneManager.getActiveScene().getCameraViewport();
			g2d.setStroke(new BasicStroke(5));
			g2d.setColor(new Color(1, 1, 0, 0.75f));
			g2d.drawString("Camera viewport", viewport.x + 10, (int)viewport.getMaxY() - 15);
			g2d.setColor(new Color(1, 1, 0, 0.25f));
			g2d.draw(viewport);
		}
		
		g2d.setTransform(oldTransform);	// Alten transform wiederherstellen
		
		// Zeichne GUI
		for(int guiObj = 0; guiObj < gameObjectsInScene.size(); guiObj++) {
			
			Graphics2D g2dGobj = (Graphics2D) g2d.create();
			
			if(gameObjectsInScene.get(guiObj) instanceof GUIObject) {				
				GUIObject guiObject = (GUIObject) gameObjectsInScene.get(guiObj);

				if(!guiObject.doUIScale) {
					guiObject.draw(g2dGobj, screenwidth, screenheight);
					continue;
				}
			}
			
			// Top Left
			
			scaleUI(g2d, 0, 0, Settings.getUIScale());
			
			if(gameObjectsInScene.get(guiObj) instanceof GUIObject) {				
				GUIObject guiObject = (GUIObject) gameObjectsInScene.get(guiObj);

				if(guiObject.alignment == GUIAlignment.TopLeft) {
					guiObject.draw(g2d, screenwidth, screenheight);
				}
			}
			
			scaleUI(g2d, 0, 0, 1f / Settings.getUIScale());
			
			// Top Center
			
			scaleUI(g2d, screenwidth / 2, 0, Settings.getUIScale());
			
			if(gameObjectsInScene.get(guiObj) instanceof GUIObject) {				
				GUIObject guiObject = (GUIObject) gameObjectsInScene.get(guiObj);

				if(guiObject.alignment == GUIAlignment.TopMiddle) {
					guiObject.draw(g2d, screenwidth, screenheight);
				}
			}
			
			scaleUI(g2d, screenwidth / 2, 0, 1f / Settings.getUIScale());
			
			// Top Right
			
			scaleUI(g2d, screenwidth, 0, Settings.getUIScale());
			
			if(gameObjectsInScene.get(guiObj) instanceof GUIObject) {				
				GUIObject guiObject = (GUIObject) gameObjectsInScene.get(guiObj);

				if(guiObject.alignment == GUIAlignment.TopRight) {
					guiObject.draw(g2d, screenwidth, screenheight);
				}
			}
			
			scaleUI(g2d, screenwidth, 0, 1 / Settings.getUIScale());
			
			// Middle Left
			
			scaleUI(g2d, 0, screenheight / 2, Settings.getUIScale());
			
			if(gameObjectsInScene.get(guiObj) instanceof GUIObject) {				
				GUIObject guiObject = (GUIObject) gameObjectsInScene.get(guiObj);

				if(guiObject.alignment == GUIAlignment.MiddleLeft) {
					guiObject.draw(g2d, screenwidth, screenheight);
				}
			}
			
			scaleUI(g2d, 0, screenheight / 2, 1 / Settings.getUIScale());
			
			// Middle Center
			
			scaleUI(g2d, screenwidth / 2, screenheight / 2, Settings.getUIScale());
			
			if(gameObjectsInScene.get(guiObj) instanceof GUIObject) {				
				GUIObject guiObject = (GUIObject) gameObjectsInScene.get(guiObj);

				if(guiObject.alignment == GUIAlignment.Center) {
					guiObject.draw(g2d, screenwidth, screenheight);
				}
			}
			
			scaleUI(g2d, screenwidth / 2, screenheight / 2, 1 / Settings.getUIScale());
			
			// Middle RIght
			
			scaleUI(g2d, screenwidth, screenheight / 2, Settings.getUIScale());
			
			if(gameObjectsInScene.get(guiObj) instanceof GUIObject) {				
				GUIObject guiObject = (GUIObject) gameObjectsInScene.get(guiObj);

				if(guiObject.alignment == GUIAlignment.MiddleRight) {
					guiObject.draw(g2d, screenwidth, screenheight);
				}
			}
			
			scaleUI(g2d, screenwidth, screenheight / 2, 1 / Settings.getUIScale());
			
			// Bottom Left
			
			scaleUI(g2d, 0, screenheight, Settings.getUIScale());
			
			if(gameObjectsInScene.get(guiObj) instanceof GUIObject) {				
				GUIObject guiObject = (GUIObject) gameObjectsInScene.get(guiObj);

				if(guiObject.alignment == GUIAlignment.DownLeft) {
					guiObject.draw(g2d, screenwidth, screenheight);
				}
			}
			
			scaleUI(g2d, 0, screenheight, 1 / Settings.getUIScale());
			
			// Bottom Center
			
			scaleUI(g2d, screenwidth / 2, screenheight, Settings.getUIScale());
			
			if(gameObjectsInScene.get(guiObj) instanceof GUIObject) {				
				GUIObject guiObject = (GUIObject) gameObjectsInScene.get(guiObj);

				if(guiObject.alignment == GUIAlignment.DownMiddle) {
					guiObject.draw(g2d, screenwidth, screenheight);
				}
			}
			
			scaleUI(g2d, screenwidth / 2, screenheight, 1 / Settings.getUIScale());
			
			// Bottom Right
			
			scaleUI(g2d, screenwidth, screenheight, Settings.getUIScale());
			
			if(gameObjectsInScene.get(guiObj) instanceof GUIObject) {				
				GUIObject guiObject = (GUIObject) gameObjectsInScene.get(guiObj);

				if(guiObject.alignment == GUIAlignment.DownRight) {
					guiObject.draw(g2d, screenwidth, screenheight);
				}
			}
			
			scaleUI(g2d, screenwidth, screenheight, 1 / Settings.getUIScale());
		}
		
		// Mauszeiger langsam ausblenden wenn er sich nicht bewegt
		if(MouseInfo.getPointerInfo().getLocation().getX() != mouseLastPosition.x ||
				MouseInfo.getPointerInfo().getLocation().getY() != mouseLastPosition.y)
		{
			mouseAlpha = 15f;
		}else
		{
			mouseAlpha = GameMath.Lerp(mouseAlpha, 0f, 0.75f / (float)fps);
		}

		
		// Debug modus
		if(Settings.getDebugMode()) {			
			g2d.setFont(Resources.uiFont.deriveFont(15F));
			g2d.setColor(new Color(1, 1, 1, 0.25f));
			g2d.drawString("Version -> " + Main.version, 15, 25);
			g2d.drawString("FPS -> " + fps + " / Inf", 15, 45);
			g2d.drawString("TPS -> " + GameLogic.getInstance().GetAverageTps() + " / 63.0", 15, 65);
			g2d.drawString("Aktive Threads -> " + Thread.activeCount(), 15, 85);
		}

		g2d.setTransform(ogTransform);
		g2d.setClip(clip);
		
		// Mauszeiger anzeigen
		g2d.setColor(new Color(1, 1, 1, GameMath.clamp(mouseAlpha, 0, 1)));
		g2d.fillOval((int)(GetMousePos().x  - Gui.GetScreenLocation().x),
						(int)(GetMousePos().y - Gui.GetScreenLocation().y),
						11, 11);
		g2d.setColor(new Color(0, 0, 0, GameMath.clamp(mouseAlpha, 0, 1)));
		g2d.fillOval((int)(GetMousePos().x  - Gui.GetScreenLocation().x + 3),
						(int)(GetMousePos().y  - Gui.GetScreenLocation().y + 3),
						5, 5);

		mouseLastPosition = GetMousePos();
		
		repaint();
	}
	
	void scaleUI(Graphics2D g2d, int posX, int posY, float scale) {
		g2d.translate((int)(posX), (int)(posY));
		g2d.scale(scale, scale);
		g2d.translate((int)-(posX), (int)-(posY));
	}
	
	public static float GetFramesPerSecond() {
		return (float) accurateFps;
	}
	
	public static Vector3 GetMousePos() {
		return new Vector3(
				(((float)MouseInfo.getPointerInfo().getLocation().getX())),
				(((float)MouseInfo.getPointerInfo().getLocation().getY())));
	}
}
