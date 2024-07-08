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

public class Renderable extends GameObject {
	
	public Renderable(int posX, int posY, int width, int height) {
		super(posX, posY, width, height);

		collisionEnabled = false;
	}
	
	@Override
	public Vector3 getScale() {
		return size;
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
	}
	
	@Override
	public Vector3 getPosition() {	// Gibt die korrekte Position vom GameObject zurück
		return position.subtract(new Vector3(
				getScale().x * anchorPoint.x + localPosition.x,
				getScale().y * anchorPoint.y + localPosition.y
				));
	}
	
	@Override
	public void update() {
		super.update();
	}
	
	@Override
	public void draw(Graphics2D g2d, int screenWidth, int screenHeight) {
		g2d.setColor(color);
	}

	
	@Override
	public boolean checkDistanceCulled(Rectangle viewport) {
		return false;
	}
}



