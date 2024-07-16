package de.demoncore.sprites;

import java.awt.Graphics2D;
import java.awt.Image;

import de.demoncore.game.GameObject;
import de.demoncore.utils.Vector3;

public class SpriteObject extends GameObject {

	private Sprite sprite;
	Image image;
	
	public SpriteObject(int posX, int posY, int width, int height, Sprite sprite) {
		super(posX, posY, width, height);
		this.sprite = sprite;
		calcSprite();
	}
	
	public void setSprite(Sprite s) {
		this.sprite = s;
		calcSprite();
	}
	
	public Sprite getSprite() {
		return this.sprite;
	}
	
	@Override
	public void setScale(Vector3 scale) {
		super.setScale(scale);
		calcSprite();
	}
	
	void calcSprite() {
		Image image = sprite.getTexture();
		this.image = image.getScaledInstance(getScale().getX() + 1, getScale().getY() + 1, 0);
	}
	
	@Override
	public void draw(Graphics2D g2d, int screenWidth, int screenHeight) {
		Vector3 worldPos = getPosition();
		g2d.drawImage(image,
				(int)worldPos.x,
				(int)worldPos.y,
//				(int)size.x,
//				(int)size.y,
				null);
	}
}
