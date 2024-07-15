package de.demoncore.gameObjects.storymode;

import java.awt.Color;

public class ZombieEnemy extends BaseEnemy {

	public ZombieEnemy(int posX, int posY) {
		super(posX, posY);
		
		enemySpeed = 0.6f;
		attackSpeed = 55;
		setInitHealth(8);
		damageAmount = 2;
		stoppingDistance = 175;
		chaseDistance = 325;
		
		color = new Color(0.2f, 0.8f, 0.35f);
	}

}
