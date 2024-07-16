package de.demoncore.gameObjects;

import de.demoncore.utils.Vector3;

public class BallSizePowerUp extends PowerupBase {

	float ballSizeMultiplier = 100;
	
	public BallSizePowerUp(int posX, int posY) {
		super(posX, posY);
		// TODO Auto-generated constructor stub
	}

	
	
	@Override
	protected void startPowerup() {
		// TODO Auto-generated method stub
		super.startPowerup();
		
		PongBall.getInstance().setScale(PongBall.getInstance().getScale().add(Vector3.one().multiply(ballSizeMultiplier)));
		}
	
	
	
	
	@Override
	protected void stopPowerup() {
		// TODO Auto-generated method stub
		super.stopPowerup();
		PongBall.getInstance().setScale(PongBall.getInstance().getScale().subtract(Vector3.one().multiply(ballSizeMultiplier)));
	}
	
	
	
}
