package de.demoncore.game;

import java.awt.Color;
import java.awt.Rectangle;

import de.demoncore.utils.Vector3;

public class GameObject {
	
	public Vector3 size;
	protected Vector3 position;
	
	public Color color = Color.white;
	
	public Vector3 anchorPoint = new Vector3(0.5f, 0.5f);
	
	public boolean renderSpecial = false;
	
	public GameObject(int posX, int posY, int width, int height) {
		position = new Vector3(posX, posY);
		size = new Vector3(width, height);
	}
	
	public void Update() {}
	
	public Vector3 GetPosition() {
		return position.subtract(new Vector3(
				size.x * anchorPoint.x,
				size.y * anchorPoint.y
				));
	}
	
	public void SetPosition(Vector3 position) {
		this.position = position;
	}
	
	public void OnDestroy() {
		
	}
	
	public boolean collisionEnabled = true; // Ob das Objekt Kollisionen hat
	public float boundingMargin = 5f;
	
	public Rectangle GetBoundingBox() {
		Rectangle r = new Rectangle();
		
		int sizeX = (int)(size.x + boundingMargin);
		int sizeY = (int)(size.y + boundingMargin);
		
		r.x = (int)(position.x - sizeX / 2);
		r.y = (int)(position.y - sizeY / 2);
		r.width = sizeX;
		r.height = sizeY;
		
		return r;
	}

	public void OnCollisionEnter(GameObject other) {
		
	}
	
}
