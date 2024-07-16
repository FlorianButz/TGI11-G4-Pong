package de.demoncore.gameObjects;

public class BallSpeedUpPowerUp extends PowerupBase {

	float speedUpFactor = 1.5f;

	public BallSpeedUpPowerUp(int posX, int posY) {
		super(posX, posY);
		powerupDuration = 15000;

	}

	float speedBefore = 0;

	@Override
	protected void startPowerup() {
		super.startPowerup();
		speedBefore = PongBall.getInstance().speed;

		PongBall.getInstance().speed *= speedUpFactor;
	}

	@Override
	protected void stopPowerup() {
		super.stopPowerup();

		PongBall.getInstance().speed *= 1f / speedUpFactor;
	}
}
