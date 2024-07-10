package de.demoncore.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import de.demoncore.utils.Vector3;

public class GameObject {
	
	public Vector3 size;	// Gr��e
	protected Vector3 position;	// Position
	protected Vector3 localPosition;	// Lokale Position
	
	protected double rotationZ = 0;
	
	public Color color = Color.white;	// Farbe vom GameObject
	
	public Vector3 anchorPoint = new Vector3(0.5f, 0.5f);	// Der "Mittelpunkt" vom GameObject

	public boolean distanceCulling = true;	// Ob das GameObject nicht gerendert werden soll, sobalt es zu weit weg ist
	public boolean isDistanceCulled = true;	// Ob das GameObject zu weit weg ist von der kamera
	public boolean enableRendering = true;	// Ob das GameObject gerendert werden soll
	protected boolean isInScene = false; // Ob das GameObject in der Szene ist
	
	public GameObject(int posX, int posY, int width, int height) {
		position = new Vector3(posX, posY);
		localPosition = Vector3.zero();
		size = new Vector3(width, height);
	}
	
	public void update() {}
	
	public void onAddToScene() {
		isInScene = true;
	}
	
	public Vector3 getPosition() {	// Gibt die korrekte Position vom GameObject zurück
		return position.subtract(new Vector3(
				size.x * anchorPoint.x + localPosition.x,
				size.y * anchorPoint.y + localPosition.y
				));
	}
	
	public Vector3 getRawPosition() {	// Gibt die korrekte Position vom GameObject zurück
		return position;
	}
	
	public void setPosition(Vector3 position) {
		System.out.println("Set position " + position.ToString());
		this.position = position;
	}
	
	public Vector3 getLocalPosition() {	// Gibt die Lokale Position vom GameObject zurück
		return localPosition;
	}
	
	public void setLocalPosition(Vector3 position) {
		this.localPosition = position;
	}
	
	public double getRotationZ() { // Gibt rotation zurueck
		return rotationZ;
	}
	
	public void setScale(Vector3 scale) {
		this.size = scale;
	}
	
	public Vector3 getScale() {
		return size;
	}
	
	public void onDestroy() {
		isInScene = false;
	}
	
	public boolean collisionEnabled = true; // Ob das Objekt Kollisionen hat
	public float boundingMargin = 0f;
	
	public Rectangle getBoundingBox() {	// Gibt die Kollisions box zurueck
		Rectangle r = new Rectangle();
		
		int sizeX = (int)(size.x + boundingMargin);
		int sizeY = (int)(size.y + boundingMargin);
		
		r.x = (int)(position.x - sizeX / 2);
		r.y = (int)(position.y - sizeY / 2);
		r.width = sizeX;
		r.height = sizeY;
		
		return r;
	}
	
	public void draw(Graphics2D g2d, int screenWidth, int screenHeight) {
		Vector3 worldPos = getPosition();
		g2d.setColor(color);
		g2d.fillRect((int)worldPos.x, (int)worldPos.y, (int)size.x, (int)size.y);
	}
	
	public boolean checkDistanceCulled(Rectangle viewport) {
		return getBoundingBox().intersects(viewport);
	}
}
