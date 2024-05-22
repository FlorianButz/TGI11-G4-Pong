package de.demoncore.sprites;

import java.awt.Graphics2D;

import de.demoncore.game.GameObject;
import de.demoncore.utils.Vector3;

public class SpriteObject extends GameObject {

	private Sprite sprite;
	
	public SpriteObject(int posX, int posY, int width, int height, Sprite sprite) {
		super(posX, posY, width, height);
		
		this.sprite = sprite;
	}
	
	@Override
	public void Draw(Graphics2D g2d, int screenWidth, int screenHeight) {
		Vector3 worldPos = GetPosition();
		g2d.drawImage(sprite.GetTexture(),
				(int)worldPos.x,
				(int)worldPos.y,
				(int)size.x,
				(int)size.y,
				null);
	}
}
