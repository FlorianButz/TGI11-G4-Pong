package de.demoncore.scenes;

import de.demoncore.game.GameLogic;
import de.demoncore.gameObjects.BeweglichesRechteck;
import de.demoncore.gameObjects.PongPlayer;

public class TwoPlayerPong extends BaseScene {

	BeweglichesRechteck beispielObjekt1;
	PongPlayer player1;
	
	public TwoPlayerPong() {
		
		// Objekte im Spiel:
		beispielObjekt1 = new BeweglichesRechteck(50, 100, 20, 20);
		AddObject(beispielObjekt1);
		beispielObjekt1.richtung = 0; // Startrichtung
		
		player1 = new PongPlayer();
		AddObject(player1);
	
	}
	
	@Override
	public void UpdateScene() {
		super.UpdateScene();
		
		// Laufende Ausführungen im Spiel:
		beispielObjekt1.automatischeKreisbewegung();	

		this.cameraPosition.x = (float) Math.sin(GameLogic.GetInstance().GetGameTime()) * 0.5f;
		this.cameraPosition.y = (float) Math.cos(GameLogic.GetInstance().GetGameTime()) * 0.5f;
		//this.cameraZRotation = GameLogic.GetInstance().GetGameTime() * 2;
	}
	
}
