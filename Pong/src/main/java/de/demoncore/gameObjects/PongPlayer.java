package de.demoncore.gameObjects;


import de.demoncore.actions.KeyHandler;
import de.demoncore.game.GameLogic;


public class PongPlayer extends RigidBody {

	public float playerAcceleration = 7.75f; // Speed
	
	public PongPlayer(int posX, int posY) {
		super(posX, posY, 25, 150);
	}
	
	@Override
	public void Update() {
		
		if(GameLogic.IsGamePaused()) return;
		
		
	
		AddForce(KeyHandler.playerInput.Normalized().multiply(playerAcceleration));
		
		
		super.Update();
	}

}
