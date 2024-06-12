package de.demoncore.scenes.shop;

import java.awt.Color;

import de.demoncore.game.GameObject;
import de.demoncore.scenes.BaseScene;

public class AdvencedShopScene extends BaseScene{


	GameObject Background;
	
	@Override
	public void initializeScene() {
	
		Background = new GameObject(500, 0, 1000, 2000);
		addObject(Background);
		
		
		Background.color = Color.darkGray;

		
		super.initializeScene();
	}
	
	
}
