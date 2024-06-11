package de.demoncore.gameObjects;


import de.demoncore.actions.KeyHandler;
import de.demoncore.game.GameLogic;
import de.demoncore.game.Logger;
import de.demoncore.utils.Vector3;


public class PongPlayer extends RigidBody {

	public float playerAcceleration = 7.75f; // Speed
	
	public boolean isPlayer1 = true;
	
	public PongPlayer(int posX, int posY) {
		super(posX, posY, 15, 135);
	}
	
	@Override
	public void Update() {
		if(GameLogic.IsGamePaused()) return;
		
		Vector3 geschwindigkeit = Vector3.zero();
		if (isPlayer1) {
			geschwindigkeit = new Vector3(0,KeyHandler.playerInput1.y * playerAcceleration,0);
		} else {
			geschwindigkeit = new Vector3(0,KeyHandler.playerInput2.y * playerAcceleration,0);
		}
		AddForce(geschwindigkeit);
		
		super.Update();
	}

}
