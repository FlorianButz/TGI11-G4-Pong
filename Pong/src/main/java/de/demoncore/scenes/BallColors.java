package de.demoncore.scenes;

import de.demoncore.game.GameObject;
import de.demoncore.gameObjects.PongBall;

public class BallColors extends BaseScene {

	GameObject ColorVorschau;
	
	
	
	
	
	@Override
	public void initializeScene() {
		
		ColorVorschau = new PongBall(-300, -300, null, null);
		AddObject(ColorVorschau);
		
		
		
		
		
		
		
		
		
		
		super.initializeScene();
	}
	
	
	
}
