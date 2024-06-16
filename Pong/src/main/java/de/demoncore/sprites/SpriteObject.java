package de.demoncore.sprites;

import java.awt.Graphics2D;

import de.demoncore.game.GameObject;
import de.demoncore.utils.Vector3;

public class SpriteObject extends GameObject {

	protected Sprite sprite;
	
	public SpriteObject(int posX, int posY, int width, int height, Sprite sprite) {
		super(posX, posY, width, height);
		
		this.sprite = sprite;
	}
	
	@Override
	public void draw(Graphics2D g2d, int screenWidth, int screenHeight) {
		Vector3 worldPos = getPosition();
		g2d.drawImage(sprite.getTexture(),
				(int)worldPos.x,
				(int)worldPos.y,
				(int)size.x,
				(int)size.y,
				null);
	}
}
