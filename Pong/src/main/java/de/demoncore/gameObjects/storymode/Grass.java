package de.demoncore.gameObjects.storymode;

import java.util.Random;

import de.demoncore.sprites.SpriteObject;
import de.demoncore.utils.Resources;
import de.demoncore.utils.Vector3;

public class Grass extends SpriteObject {

	public Grass(int posX, int posY) {		
		super(posX, posY, 0, 0, Resources.grass1);
		
		collisionEnabled = false;
		
		Random random = new Random();
		
		switch(random.nextInt(3)) {
		case 0:
			size = new Vector3( 5 * 5, 3 * 5);
			setSprite(Resources.grass1);
			break;
		case 1:
			size = new Vector3( 3 * 5, 3 * 5);
			setSprite(Resources.grass2);
			break;
		case 2:
			size = new Vector3( 6 * 5, 4 * 5);
			setSprite(Resources.grass3);
			break;
		}
	}	
}
