package de.demoncore.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import de.demoncore.utils.Vector3;

public class GameObject {
	
	public Vector3 size;	// Größe
	protected Vector3 position;	// Position
	
	public Color color = Color.white;	// Farbe vom GameObject
	
	public Vector3 anchorPoint = new Vector3(0.5f, 0.5f);	// Der "Mittelpunkt" vom GameObject

	public boolean distanceCulling = true;	// Ob das GameObject nicht gerendert werden soll, sobalt es zu weit weg ist
	public boolean enableRendering = true;	// Ob das GameObject gerendert werden soll
	
	public GameObject(int posX, int posY, int width, int height) {
		position = new Vector3(posX, posY);
		size = new Vector3(width, height);
	}
	
	public void Update() {}
	
	public Vector3 GetPosition() {	// Gibt die korrekte Position vom GameObject zurück
		return position.subtract(new Vector3(
				size.x * anchorPoint.x,
				size.y * anchorPoint.y
				));
	}
	
	public void SetPosition(Vector3 position) {
		this.position = position;
	}
	
	public void SetScale(Vector3 scale) {
		this.size = scale;
	}
	
	public Vector3 GetScale() {
		return size;
	}
	
	public void OnDestroy() {
		
	}
	
	public boolean collisionEnabled = true; // Ob das Objekt Kollisionen hat
	public float boundingMargin = 0f;
	
	public Rectangle GetBoundingBox() {	// Gibt die Kollisions box zurück
		Rectangle r = new Rectangle();
		
		int sizeX = (int)(size.x + boundingMargin);
		int sizeY = (int)(size.y + boundingMargin);
		
		r.x = (int)(position.x - sizeX / 2);
		r.y = (int)(position.y - sizeY / 2);
		r.width = sizeX;
		r.height = sizeY;
		
		return r;
	}
	
	public void Draw(Graphics2D g2d, int screenWidth, int screenHeight) {
		Vector3 worldPos = GetPosition();
		g2d.setColor(color);
		g2d.fillRect((int)worldPos.x, (int)worldPos.y, (int)size.x, (int)size.y);
	}	
}
