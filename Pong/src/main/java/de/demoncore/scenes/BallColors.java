package de.demoncore.scenes;

import java.awt.Color;

import de.demoncore.game.GameObject;


public class BallColors extends BaseScene {

	GameObject ColorVorschau;
	GameObject Background;

	
	
	
	@Override
	public void initializeScene() {
		
		ColorVorschau = new GameObject(-500, -300, 100, 100);
		AddObject(ColorVorschau);
		
		Background = new GameObject(500, 0, 1000, 2000);
		AddObject(Background);
		
		
		Background.color = Color.darkGray;
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
		
		
		super.initializeScene();
	}
	

	
}
