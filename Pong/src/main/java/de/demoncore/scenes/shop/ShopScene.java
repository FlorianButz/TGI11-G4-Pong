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
import de.demoncore.scenes.DefaultScene;
import de.demoncore.scenes.MainMenu;
import de.demoncore.utils.Resources;


public class ShopScene extends BaseScene {

	GameObject Base;
	
	
	@Override
	public void initializeScene() {
		
		Base = new GameObject(0, 0, 0, 0);
		addObject(Base);
		
		
		
		
		Base.color = Color.DARK_GRAY;
		
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
		
		GUIButton back = new GUIButton(0, 400, 600, 75, Translation.get("Back"), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				SceneManager.LoadScene(new MainMenu());;
			}
		});
		back.alignment = GUIAlignment.Center;
		addObject(back);;
		
		super.initializeScene();
	}
	
	
	
	
	
	
}
