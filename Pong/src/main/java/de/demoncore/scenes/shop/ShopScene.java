package de.demoncore.scenes.shop;

import java.awt.Color;

import de.demoncore.game.GameObject;
import de.demoncore.game.SceneManager;
import de.demoncore.game.Translation;
import de.demoncore.gameObjects.PauseMenu;
import de.demoncore.gui.GUIAlignment;
import de.demoncore.gui.GUIButton;
import de.demoncore.gui.GUIButtonClickEvent;
import de.demoncore.scenes.BaseScene;
import de.demoncore.utils.Resources;


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
		
		addObject(new PauseMenu());	
		
		Base1 = new GameObject(-300, -250, 300, 300);
		addObject(Base1);
		
		Base2 = new GameObject(300, -250, 300, 300);
		addObject(Base2);
		
		Base3 = new GameObject(-300, 250, 300, 300);
		addObject(Base3);
		
		Base4 = new GameObject(300, 250, 300, 300);
		addObject(Base4);
		
		Base1.color = Color.DARK_GRAY;
		Base2.color = Color.DARK_GRAY;
		Base3.color = Color.DARK_GRAY;
		Base4.color = Color.DARK_GRAY;
		
		Throne1 = new GameObject(-300, -250, 200, 200);
		addObject(Throne1);
		
		Throne2 = new GameObject(300, -250, 200, 200);
		addObject(Throne2);
		
		Throne3 = new GameObject(-300, 250, 200, 200);
		addObject(Throne3);
		
		Throne4 = new GameObject(300, 250, 200, 200);
		addObject(Throne4);
		
		Throne1.color = Color.LIGHT_GRAY;
		Throne2.color = Color.lightGray;
		Throne3.color = Color.lightGray;
		Throne4.color = Color.LIGHT_GRAY;
		
		GUIButton BallColor = new GUIButton(-300, -250, 200, 75, Translation.get("mainmenu.BallColor"), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				SceneManager.LoadScene(new BallColors());
			}
		});
		BallColor.alignment = GUIAlignment.Center;
		addObject(BallColor);
				
		GUIButton SchlaegerColor = new GUIButton(300, -250, 200, 75, Translation.get("mainmenu.Schl�ger"), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				SceneManager.LoadScene(new SchleagerColor());
			}
		});
		SchlaegerColor.alignment = GUIAlignment.Center;
		addObject(SchlaegerColor);
		
		GUIButton PowerUps = new GUIButton(-300, 250, 200, 75, Translation.get("mainmenu.PowerUps"), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				SceneManager.LoadScene(new PowerUps());
			}
		});
		PowerUps.alignment = GUIAlignment.Center;
		addObject(PowerUps);
		
		GUIButton Background = new GUIButton(300, 250, 200, 75, Translation.get("mainmenu.background"), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				SceneManager.LoadScene(new Background());
			}
		});
		Background.alignment = GUIAlignment.Center;
		addObject(Background);
		
		
		
		super.initializeScene();
	}
	
	
	
	
	
	
}