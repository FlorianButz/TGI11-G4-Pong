package de.demoncore.gameObjects.storymode;

import java.awt.Color;

import de.demoncore.game.SceneManager;
import de.demoncore.game.Translation;
import de.demoncore.game.TranslationComponent;
import de.demoncore.utils.Vector3;

public class SkeletonEnemy extends BaseEnemy {

	public SkeletonEnemy(int posX, int posY) {
		super(posX, posY);
		
		enemySpeed = 0.15f;
		stoppingDistance = 600f;
		chaseDistance = 750f;
		attackSpeed = 125;
		setInitHealth(5);
		damageAmount = 1;
		
		color = new Color(0.5f, 0.5f, 0.5f);
	}
	
	@Override
	protected TranslationComponent getDeathReason() {
		return Translation.get("deathReason.skeleton");
	}
	
	@Override
	protected void attackPlayer() {
		Vector3 spawnPosition = getPosition();
		
		spawnPosition = spawnPosition.add(StorymodePlayer.getPlayerInstance().getPosition().subtract(position.add(getScale().multiply(0.5f))).normalized().multiply(65f));
		
		SceneManager.getActiveScene().addObject(new Arrow(spawnPosition.getX(), spawnPosition.getY(), damageAmount));
	}
}
