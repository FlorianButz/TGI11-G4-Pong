package de.demoncore.gameObjects.storymode;

import java.awt.Color;

import de.demoncore.game.Translation;
import de.demoncore.game.TranslationComponent;

public class ZombieEnemy extends BaseEnemy {

	public ZombieEnemy(int posX, int posY) {
		super(posX, posY);
		
		enemySpeed = 0.6f;
		attackSpeed = 55;
		setInitHealth(8);
		damageAmount = 1;
		stoppingDistance = 175;
		chaseDistance = 325;
		
		color = new Color(0.2f, 0.8f, 0.35f);
	}
	
	@Override
	protected TranslationComponent getDeathReason() {
		return Translation.get("deathReason.zombie");
	}
	
}
