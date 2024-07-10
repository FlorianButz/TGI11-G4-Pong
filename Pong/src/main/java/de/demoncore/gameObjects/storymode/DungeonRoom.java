package de.demoncore.gameObjects.storymode;

import java.awt.Color;
import java.awt.Graphics2D;

import de.demoncore.game.GameObject;

public class DungeonRoom extends GameObject {

	public DungeonRoom(int posX, int posY, int width, int height) {
		super(posX, posY, width, height);
		
		color = Color.black;
	}
	
	@Override
	public void draw(Graphics2D g2d, int screenWidth, int screenHeight) {
		super.draw(g2d, screenWidth, screenHeight);
	
		g2d.drawRect(screenHeight, screenHeight, screenWidth, screenHeight);
	}

	
	
}
