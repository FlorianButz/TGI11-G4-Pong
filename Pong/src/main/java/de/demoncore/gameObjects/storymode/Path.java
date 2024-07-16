package de.demoncore.gameObjects.storymode;

import de.demoncore.sprites.SpriteObject;
import de.demoncore.utils.Resources;

public class Path extends SpriteObject {

	public Path(int posX, int posY) {
		super(posX, posY, (int)(17 * 5), (int)(16 * 5), Resources.path);
		collisionEnabled = false;
		
		if(Math.random() >= 0.6){
			setSprite(Resources.path2);
			if(Math.random() >= 0.6){
				setSprite(Resources.path3);
			}
		}
		
//		switch((int)(Math.random() * 5)) {
//		case 0:
//			rotationZ = 0;
//			break;
//		case 1:
//			rotationZ = 90;
//			break;
//		case 2:
//			rotationZ = 180;
//			break;
//		case 3:
//			rotationZ = 270;
//			break;
//		}
	}
}
