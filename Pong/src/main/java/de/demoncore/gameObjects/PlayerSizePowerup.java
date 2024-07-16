package de.demoncore.gameObjects;

public class PlayerSizePowerup extends PowerupBase {

	float scaleUpFactor = 1.75f;

	public PlayerSizePowerup(int posX, int posY) {
		super(posX, posY);
		powerupDuration = 15000;

	}

	float sizeBeforeP1 = 0;
	float sizeBeforeP2 = 0;

	@Override
	protected void startPowerup() {
		super.startPowerup();
		sizeBeforeP1 = PongPlayer.player1.getScale().y;
		sizeBeforeP2 = PongPlayer.player1.getScale().y;

		if (PongBall.isMovingTowardsPlayer2) {
			PongPlayer.player1.currentScale.y = PongPlayer.player1.getScale().multiply(scaleUpFactor).y;
			PongPlayer.player2.currentScale.y = PongPlayer.player1.getScale().multiply(1f / scaleUpFactor).y;
		}
		else {
			PongPlayer.player2.currentScale.y = PongPlayer.player1.getScale().multiply(scaleUpFactor).y;
			PongPlayer.player1.currentScale.y = PongPlayer.player1.getScale().multiply(1f / scaleUpFactor).y;
		}
	}

	@Override
	protected void stopPowerup() {
		super.stopPowerup();
		PongPlayer.player1.currentScale.y = sizeBeforeP1;
		PongPlayer.player2.currentScale.y = sizeBeforeP2;
	}
}
