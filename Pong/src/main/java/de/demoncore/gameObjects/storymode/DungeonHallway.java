package de.demoncore.gameObjects.storymode;

import de.demoncore.game.GameObject;

public class DungeonHallway extends GameObject {

	public int fromRPX, fromRPY;
	public int toRPX, toRPY;
	
	public DungeonHallway(int posX, int posY, int width, int height) {
		super(posX, posY, width, height);
	}
}
