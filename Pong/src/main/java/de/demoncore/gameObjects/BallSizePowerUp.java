package de.demoncore.gameObjects;

public class BallSizePowerUp extends PowerupBase {

	float ballSizeMultiplier = 2f;
	
	public BallSizePowerUp(int posX, int posY) {
		super(posX, posY);
	}

	@Override
	protected void startPowerup() {
		super.startPowerup();
		PongBall.getInstance().currentScale = PongBall.getInstance().getScale().multiply(ballSizeMultiplier);
	}
	
	@Override
	protected void stopPowerup() {
		super.stopPowerup();
		PongBall.getInstance().currentScale = PongBall.getInstance().getScale().multiply(1f / ballSizeMultiplier);
	}
}
