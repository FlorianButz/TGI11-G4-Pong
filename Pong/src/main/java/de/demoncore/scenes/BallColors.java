package de.demoncore.scenes;

import de.demoncore.game.GameObject;

public class BallColors extends BaseScene {

	GameObject ColorVorschau;
	
	
	
	
	
	@Override
	public void initializeScene() {
		
		ColorVorschau = new GameObject(-300, 0, 50, 50);
		AddObject(ColorVorschau);
		
		
		
		
		
		
		
		
		
		
		super.initializeScene();
	}
	
	
	
}
