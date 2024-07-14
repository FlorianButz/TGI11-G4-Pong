package de.demoncore.gameObjects.storymode;

import java.awt.Color;

public class BabyZombieEnemy extends BaseEnemy {

	public BabyZombieEnemy(int posX, int posY) {
		super(posX, posY);
		
		enemySpeed = 3f;
		attackSpeed = 50;
		setInitHealth(1);
		
		setScale(getScale().multiply(0.65f));
		
		color = new Color(0.85f, 0.65f, 0.05f);
	}

}
