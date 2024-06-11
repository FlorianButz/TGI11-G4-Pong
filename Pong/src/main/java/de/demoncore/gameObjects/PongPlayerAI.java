package de.demoncore.gameObjects;

import de.demoncore.game.GameLogic;
import de.demoncore.utils.Vector3;

public class PongPlayerAI extends PongPlayer {

	public float aiStupidity = 3.5f; // Je hoeher der wert, desto duemmer ist die AI
	
	public PongPlayerAI(int posX, int posY) {
		super(posX, posY);

		playerControlsEnabled = false;
		playerAcceleration = 15;
		friction = 0.95f;
	}

	Vector3 lastPongBallPos = Vector3.zero();
	int randFactor = 0;
	int counter = 0;
	
	@Override
	public void Update() {
		if(GameLogic.IsGamePaused()) return;

		if(counter >= 35) {
			counter = 0;
			randFactor = (int)(((100 * aiStupidity * Math.random())) - 50 * aiStupidity);
		}
		counter++;
		
		Vector3 speed = Vector3.zero();
		int ballY = (int) (PongBall.getInstance().GetPosition().y + randFactor);

		Vector3 pPos = new Vector3(0, position.y);
		Vector3 bPos = new Vector3(0, ballY);
		float velMulti = Vector3.Distance(pPos, bPos);
		
		if(lastPongBallPos.x < PongBall.getInstance().GetPosition().x) {
			
			if(ballY >= GetPosition().y + size.y / 2)
				speed = new Vector3(0, (playerAcceleration * (velMulti / 100)) * (float)(Math.random() / 2 + 0.5), 0);
			else
				speed = new Vector3(0, (-playerAcceleration * (velMulti / 100)) * (float)(Math.random() / 2 + 0.5), 0);

			AddForce(speed);
		}else {
			if(-75 > GetPosition().y + size.y / 2) {
				speed = new Vector3(0, (playerAcceleration / 2) * (float)(Math.random() / 2 + 0.5), 0);

				AddForce(speed);
			}else if(75 < GetPosition().y + size.y / 2) {
				speed = new Vector3(0, (-playerAcceleration / 2) * (float)(Math.random() / 2 + 0.5), 0);

				AddForce(speed);				
			}
		}

		lastPongBallPos = PongBall.getInstance().GetPosition();
		
		super.Update();
	}

}
