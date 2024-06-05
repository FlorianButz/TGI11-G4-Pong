package de.demoncore.scenes;

import de.demoncore.gameObjects.BeweglichesRechteck;
import de.demoncore.gameObjects.PauseMenu;
import de.demoncore.gameObjects.StorymodePlayer;

public class OnePlayerPong extends BaseScene{

<<<<<<< Updated upstream
	PongPlayer Player_One;
	BeweglichesRechteck Wand_Links;
	BeweglichesRechteck Wand_Rechts;
	BeweglichesRechteck Wand_Oben;
	BeweglichesRechteck Wand_Unten;
	
	;
	
	
=======
	StorymodePlayer Player_One;
	BeweglichesRechteck Wand;
>>>>>>> Stashed changes
	
	@Override
	public void InitializeScene() {
		super.InitializeScene();
		
		
		AddObject(new PauseMenu());
	
		Player_One = new StorymodePlayer(0, 0);
		AddObject(Player_One);
	
		Wand_Links = new BeweglichesRechteck(-960, 0, 25, 1150); // x, y, breite ,läne
		AddObject(Wand_Links);
		
	
		Wand_Rechts = new BeweglichesRechteck(960, 0, 25, 1150); // x, y, breite ,läne
		AddObject(Wand_Rechts);
		
		
		Wand_Oben = new BeweglichesRechteck(0, 560, 1900, 25); // x, y, breite ,läne
		AddObject(Wand_Oben);
		
		
		Wand_Unten = new BeweglichesRechteck(0, -560, 1900, 25); // x, y, breite ,läne
		AddObject(Wand_Unten);
			
		
		
		
		
		
		
		
		
	}
	
	
	
}
