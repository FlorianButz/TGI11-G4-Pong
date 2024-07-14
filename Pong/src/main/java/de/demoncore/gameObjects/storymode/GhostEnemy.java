package de.demoncore.gameObjects.storymode;

import java.awt.Color;

public class GhostEnemy extends BaseEnemy {

	public GhostEnemy(int posX, int posY) {
		super(posX, posY);
		
		enemySpeed = 1;
		attackSpeed = 95;
		setInitHealth(4);
		
		setScale(getScale().multiply(2.25f));
		
		color = new Color(1f, 1f, 1f, 0.25f);
	}

}
