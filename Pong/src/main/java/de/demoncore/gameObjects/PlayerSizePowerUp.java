package de.demoncore.gameObjects;

import java.time.Duration;
import java.util.Base64;

import de.demoncore.utils.Vector3;

public class PlayerSizePowerUp extends PowerupBase {

	boolean wasMovingTowardsPlayer2 = false;
	
	public PlayerSizePowerUp(int posX, int posY) {
		super(posX, posY);
		// TODO Auto-generated constructor stub
		powerupDuration = 15000;
		
	}
	@Override
	protected void startPowerup() {
		// TODO Auto-generated method stub
		super.startPowerup();
		
		wasMovingTowardsPlayer2 = PongBall.isMovingTowardsPlayer2;
		if (PongBall.isMovingTowardsPlayer2) {
			PongPlayer.player1.setScale(PongPlayer.player1.getScale().add(new Vector3(0, 50)));
			PongPlayer.player2.setScale(PongPlayer.player2.getScale().add(new Vector3(0, -50)));
		}
		else {
			PongPlayer.player1.setScale(PongPlayer.player1.getScale().add(new Vector3(0, -50)));
			PongPlayer.player2.setScale(PongPlayer.player2.getScale().add(new Vector3(0, 50)));
		}
	}
	
	@Override
	protected void stopPowerup() {
		// TODO Auto-generated method stub
		super.stopPowerup();
		if (!wasMovingTowardsPlayer2) {
			PongPlayer.player1.setScale(PongPlayer.player1.getScale().add(new Vector3(0, 50)));
			PongPlayer.player2.setScale(PongPlayer.player2.getScale().add(new Vector3(0, -50)));
		}
		else {
			PongPlayer.player1.setScale(PongPlayer.player1.getScale().add(new Vector3(0, -50)));
			PongPlayer.player2.setScale(PongPlayer.player2.getScale().add(new Vector3(0, 50)));
		}
	}
}
