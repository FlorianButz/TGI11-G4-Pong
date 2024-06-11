package de.demoncore.gameObjects;


import de.demoncore.game.GameLogic;


public class PongPlayer extends RigidBody {

	public float playerAcceleration = 7.75f; // Speed
	
	public PongPlayer(int posX, int posY) {
		super(posX, posY, 15, 135);
	}
	
	@Override
	public void Update() {
		if(GameLogic.IsGamePaused()) return;
		
		super.Update();
	}

}
