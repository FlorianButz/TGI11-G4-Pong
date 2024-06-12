package de.demoncore.scenes;

import java.awt.Color;

import de.demoncore.game.GameObject;
import de.demoncore.gameObjects.PauseMenu;


public class ShopScene extends BaseScene {

	GameObject Base1;
	GameObject Base2;
	GameObject Base3;
	GameObject Base4;
	
	
	@Override
	public void initializeScene() {
		
		AddObject(new PauseMenu());
		
		Base1 = new GameObject(-500, -250, 300, 300);
		AddObject(Base1);
		
		Base1.color = Color.GRAY;
		
		Base2 = new GameObject(500, -250, 300, 300);
		AddObject(Base2);
		
		Base3 = new GameObject(-500, 250, 300, 300);
		AddObject(Base3);
		
		Base4 = new GameObject(500, 250, 300, 300);
		AddObject(Base4);
		
		
		super.initializeScene();
	}
	
	
	
	
	
	
}
