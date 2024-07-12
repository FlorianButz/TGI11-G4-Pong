package de.demoncore.gameObjects.storymode;

import de.demoncore.sprites.SpriteObject;
import de.demoncore.utils.Resources;

public class Pillar extends SpriteObject {

	public Pillar(int posX, int posY) {
		super(posX, posY, 9 * 5, 30 * 5, Resources.pillar);
	}
}
