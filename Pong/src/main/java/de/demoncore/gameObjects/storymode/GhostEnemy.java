package de.demoncore.gameObjects.storymode;

import java.awt.Color;

import de.demoncore.game.Translation;
import de.demoncore.game.TranslationComponent;

public class GhostEnemy extends BaseEnemy {

	public GhostEnemy(int posX, int posY) {
		super(posX, posY);
		
		enemySpeed = 1;
		attackSpeed = 95;
		setInitHealth(4);
		stoppingDistance = 125;
		chaseDistance = 675;
		
		setScale(getScale().multiply(2.25f));
		
		color = new Color(1f, 1f, 1f, 0.25f);
	}
	
	@Override
	protected TranslationComponent getDeathReason() {
		return Translation.get("deathReason.ghost");
	}
	
}
