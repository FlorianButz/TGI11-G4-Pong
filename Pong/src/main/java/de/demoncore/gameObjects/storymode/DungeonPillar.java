package de.demoncore.gameObjects.storymode;

import de.demoncore.sprites.SpriteObject;
import de.demoncore.utils.Resources;

public class DungeonPillar extends SpriteObject {

	public DungeonPillar(int posX, int posY) {
		super(posX, posY, (int)(8 * 7.5f), (int)(21 * 7.5f), Resources.dPillar);
	}
}
