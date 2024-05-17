package de.demoncore.gameObjects;

import de.demoncore.actions.KeyHandler;
import de.demoncore.game.GameObject;
import de.demoncore.utils.GameMath;

public class PongPlayer extends GameObject {

	public int playerSpeed = 4; // Die Geschwindigkeit vom Spieler
	public float playerVelocity; // Die jetzige Physikalische geschwindigkeit, die der Koerper Spieler hat
	
	public PongPlayer() {
		super(300, 400, 20, 20);
	}

	@Override
	public void Update() {
		super.Update();
		
		playerVelocity = GameMath.Lerp(playerVelocity, KeyHandler.playerInput.multiply(playerSpeed).x, 0.035f); // Berechnen der geschwindigkeit
		this.position.x += playerVelocity; // Fuege die geschwindigkeit zum spieler hinzu
	}
}
