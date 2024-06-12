package de.demoncore.scenes;

import java.awt.Color;

import de.demoncore.game.GameObject;

public class AdvencedShopScene extends BaseScene{


	GameObject Background;
	
	@Override
	public void initializeScene() {
	
		Background = new GameObject(500, 0, 1000, 2000);
		AddObject(Background);
		
		
		Background.color = Color.darkGray;
		
		
		
		
		
		
		
		
		
		
		
		
		super.initializeScene();
	}
	
	
}
