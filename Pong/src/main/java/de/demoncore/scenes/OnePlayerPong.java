package de.demoncore.scenes;

import de.demoncore.gameObjects.BeweglichesRechteck;
import de.demoncore.gameObjects.PauseMenu;
import de.demoncore.gameObjects.PongPlayer;

public class OnePlayerPong extends BaseScene{

	PongPlayer Player_One;
	BeweglichesRechteck Wand;
	
	@Override
	public void InitializeScene() {
		super.InitializeScene();
		
		AddObject(new PauseMenu());
	
		Player_One = new PongPlayer(0, 0);
		AddObject(Player_One);
	
		Wand = new BeweglichesRechteck(200, 500, 2, 1000); // x, y, z ,größe
		AddObject(Wand);
		Wand.richtung = 0;
	
	
	}
	
	
	
}
