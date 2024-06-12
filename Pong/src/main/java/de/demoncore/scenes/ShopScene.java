package de.demoncore.scenes;

import java.awt.Color;

import de.demoncore.game.GameObject;
import de.demoncore.gameObjects.PauseMenu;


public class ShopScene extends BaseScene {

	GameObject Base1;
	GameObject Base2;
	GameObject Base3;
	GameObject Base4;
	GameObject Throne1;
	GameObject Throne2;
	GameObject Throne3;
	GameObject Throne4;
	
	@Override
	public void initializeScene() {
		
		AddObject(new PauseMenu());	
		
		Base1 = new GameObject(-300, -250, 300, 300);
		AddObject(Base1);
		
		Base2 = new GameObject(300, -250, 300, 300);
		AddObject(Base2);
		
		Base3 = new GameObject(-300, 250, 300, 300);
		AddObject(Base3);
		
		Base4 = new GameObject(300, 250, 300, 300);
		AddObject(Base4);
		
		Base1.color = Color.DARK_GRAY;
		Base2.color = Color.DARK_GRAY;
		Base3.color = Color.DARK_GRAY;
		Base4.color = Color.DARK_GRAY;
		
		Throne1 = new GameObject(-300, -250, 200, 200);
		AddObject(Throne1);
		
		Throne2 = new GameObject(300, -250, 200, 200);
		AddObject(Throne2);
		
		Throne3 = new GameObject(-300, 250, 200, 200);
		AddObject(Throne3);
		
		Throne4 = new GameObject(300, 250, 200, 200);
		AddObject(Throne4);
		
		Throne1.color = Color.LIGHT_GRAY;
		Throne2.color = Color.lightGray;
		Throne3.color = Color.lightGray;
		Throne4.color = Color.LIGHT_GRAY;
		
		
		
		super.initializeScene();
	}
	
	
	
	
	
	
}
