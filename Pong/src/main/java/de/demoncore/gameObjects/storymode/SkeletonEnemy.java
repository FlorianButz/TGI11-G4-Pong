package de.demoncore.gameObjects.storymode;

import java.awt.Color;

import de.demoncore.game.SceneManager;
import de.demoncore.game.Translation;
import de.demoncore.game.TranslationComponent;
import de.demoncore.utils.Vector3;

public class SkeletonEnemy extends BaseEnemy {

	public SkeletonEnemy(int posX, int posY) {
		super(posX, posY);
		
		enemySpeed = 0.1f;
		stoppingDistance = 600f;
		chaseDistance = 750f;
		attackSpeed = 165;
		setInitHealth(4);
		damageAmount = 1;
		
		color = new Color(0.5f, 0.5f, 0.5f);
	}
	
	@Override
	protected TranslationComponent getDeathReason() {
		return Translation.get("deathReason.skeleton");
	}
	
	@Override
	protected void attackPlayer() {
		Vector3 spawnPosition = getRawPosition();
		
		spawnPosition = spawnPosition.add(new Vector3(0, -60).rotated(getAngle(getPosition(), StorymodePlayer.getPlayerInstance().getRawPosition())));
		
		SceneManager.getActiveScene().addObject(new Arrow(spawnPosition.getX(), spawnPosition.getY(), damageAmount));
	}
	
	public float getAngle(Vector3 start, Vector3 target) {
		float angle = (float) Math.toDegrees(Math.atan2(target.y - start.y, target.x - start.x));

		if(angle < 0){
			angle += 360;
		}

		return angle + 90;
	}
	
}
