package de.demoncore.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import de.demoncore.actions.GameActionListener;
import de.demoncore.actions.KeyHandler;
import de.demoncore.game.GameObject;
import de.demoncore.game.SceneManager;
import de.demoncore.game.Settings;
import de.demoncore.utils.Logger;
import de.demoncore.utils.Vector3;

public class Renderable {
	
	public Vector3 size;	// Gr��e
	protected Vector3 position;	// Position

	public Color color = Color.white;	// Farbe vom GameObject
	
	public Vector3 anchorPoint = new Vector3(0.5f, 0.5f);	// Der "Mittelpunkt" vom GameObject
	
	public Renderable(int posX, int posY, int width, int height) {
		position = new Vector3(posX, posY);
		size = new Vector3(width, height);
	}
	
	public Vector3 getScale() {
		return size;
	}
	
	public Vector3 getPosition() {	// Gibt die korrekte Position vom GameObject zurück
		return position.subtract(new Vector3(
				getScale().x * anchorPoint.x,
				getScale().y * anchorPoint.y
				));
	}
	
	public void setPosition(Vector3 position) {
		this.position = position;
	}
	
	public void draw(Graphics2D g2d, int screenWidth, int screenHeight) {
		g2d.setColor(color);
	}

	
	public boolean checkDistanceCulled(Rectangle viewport) {
		return false;
	}
}



