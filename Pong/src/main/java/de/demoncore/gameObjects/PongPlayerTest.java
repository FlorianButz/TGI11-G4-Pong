package de.demoncore.gameObjects;


import de.demoncore.actions.KeyHandler;
import de.demoncore.game.GameLogic;


public class PongPlayerTest extends RigidBody {

	public float playerAcceleration = 7.75f; // Die Geschwindigkeitszunahme vom Spieler
	
	
	public PongPlayerTest(int posX, int posY) {
		super(posX, posY, 25, 150);
	}
	
	@Override
	public void Update() {
		
		if(GameLogic.IsGamePaused()) return;
		
		
	
		AddForce(KeyHandler.playerInput.Normalized().multiply(playerAcceleration));
		
		
		super.Update();
	}

}
