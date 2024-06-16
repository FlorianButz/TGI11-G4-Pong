package de.demoncore.gameObjects.storymode;

import de.demoncore.sprites.SpriteObject;
import de.demoncore.utils.Resources;

public class Tree extends SpriteObject {

	public Tree(int posX, int posY) {
		super(posX, posY, 19 * 6, 21 * 6, Resources.tree);
	}
}
