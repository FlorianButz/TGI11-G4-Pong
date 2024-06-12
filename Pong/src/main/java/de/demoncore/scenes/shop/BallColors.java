package de.demoncore.scenes.shop;

import java.awt.Color;

import de.demoncore.game.GameObject;
import de.demoncore.scenes.BaseScene;


public class BallColors extends BaseScene {

	GameObject ColorVorschau;
	GameObject Background;

	
	
	
	@Override
	public void initializeScene() {
		
		ColorVorschau = new GameObject(-500, -300, 100, 100);
		addObject(ColorVorschau);
		
		Background = new GameObject(500, 0, 1000, 2000);
		addObject(Background);
		
		
		Background.color = Color.darkGray;
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
		
		
		super.initializeScene();
	}
	

	
}
