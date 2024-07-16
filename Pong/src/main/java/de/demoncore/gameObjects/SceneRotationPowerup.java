package de.demoncore.gameObjects;

import de.demoncore.scenes.PowerupPong;

public class SceneRotationPowerup extends PowerupBase {

	public SceneRotationPowerup(int posX, int posY) {
		super(posX, posY);
		powerupDuration = 5000;

	}

	@Override
	protected void startPowerup() {
		super.startPowerup();
		PowerupPong.sceneRotation = 180;
	}

	@Override
	protected void stopPowerup() {
		super.stopPowerup();
		PowerupPong.sceneRotation = 0;
	}
}
