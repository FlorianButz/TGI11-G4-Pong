package de.demoncore.gameObjects.storymode;

import de.demoncore.sprites.SpriteObject;
import de.demoncore.utils.Resources;

public class BigStone extends SpriteObject {

	public BigStone(int posX, int posY) {
		super(posX, posY, 21 * 5, 19 * 5, Resources.bigStone);
	}
}
