package de.demoncore.scenes;

import de.demoncore.game.GameObject;
import de.demoncore.gameObjects.BeweglichesRechteck;
import de.demoncore.gameObjects.PauseMenu;
import de.demoncore.gameObjects.PongBall;
import de.demoncore.gameObjects.PongPlayer;
import de.demoncore.gameObjects.RigidBody;

public class OnePlayerPong extends BaseScene {

	PongBall Ball;
	PongPlayer Player_One;
	GameObject Wand_Links;
	GameObject Wand_Rechts;
	GameObject Wand_Oben;
	GameObject Wand_Unten;	

	@Override
	public void InitializeScene() {
		super.InitializeScene();
		
		AddObject(new PauseMenu());
	
		Ball = new PongBall(0, 0);
		AddObject(Ball);

		Player_One = new PongPlayer(0, 0);
		AddObject(Player_One);
	
		Wand_Links = new GameObject(-960, 0, 25, 1150); // x, y, breite ,läne

		AddObject(Wand_Links);
		
	
		Wand_Rechts = new GameObject(960, 0, 25, 1150); // x, y, breite ,läne
		AddObject(Wand_Rechts);
		
		
		Wand_Oben = new GameObject(0, 560, 1900, 25); // x, y, breite ,läne
		AddObject(Wand_Oben);
		
		
		Wand_Unten = new GameObject(0, -560, 1900, 25); // x, y, breite ,läne
		AddObject(Wand_Unten);
	}
}
