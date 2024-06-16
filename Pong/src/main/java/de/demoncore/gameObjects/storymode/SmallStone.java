package de.demoncore.gameObjects.storymode;

import de.demoncore.sprites.SpriteObject;
import de.demoncore.utils.Resources;

public class SmallStone extends SpriteObject {

	public SmallStone(int posX, int posY) {
		super(posX, posY, 20 * 5, 11 * 5, Resources.smallStone);
	}
}
