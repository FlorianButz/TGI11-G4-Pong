package de.demoncore.gameObjects.storymode;

import de.demoncore.sprites.Sprite;
import de.demoncore.sprites.SpriteObject;
import de.demoncore.utils.Resources;

public class House extends SpriteObject {

	public House(int posX, int posY, int houseId) {
		super(posX, posY, (int)(getSizeX(houseId) * 7.5f), (int)(getSizeY(houseId) * 7.5f), getSprite(houseId));
	}

	static int getSizeX(int id) {
		if(id == 1)
			return 29;
		else if(id == 2)
			return 37;
		else if(id == 3)
			return 35;
		else
			return 29;
	}

	static int getSizeY(int id) {		
		if(id == 1)
			return 22;
		else if(id == 2)
			return 21;
		else if(id == 3)
			return 31;
		else
			return 22;
	}

	static Sprite getSprite(int id) {
		if(id == 1)
			return Resources.house1;
		else if(id == 2)
			return Resources.house2;
		else if(id == 3)
			return Resources.house3;
		else
			return Resources.house4;
	}
}
