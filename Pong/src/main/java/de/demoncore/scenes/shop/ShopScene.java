package de.demoncore.scenes.shop;

import java.awt.Color;
import java.security.PublicKey;

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


	
	
	@Override
	public void initializeScene() {			
		
		GUIButton BallColor = new GUIButton(-600, -200, 250, 400, Translation.get("shop.ballskins"), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				SceneManager.LoadScene(new BallColors());
			}
		});
		BallColor.alignment = GUIAlignment.Center;
		addObject(BallColor);
				
		GUIButton SchlaegerColor = new GUIButton(-300, -200, 250, 400, Translation.get("shop.pedals"), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				SceneManager.LoadScene(new SchleagerColor());
			}
		});
		SchlaegerColor.alignment = GUIAlignment.Center;
		addObject(SchlaegerColor);
		
		GUIButton PowerUps = new GUIButton(0, -200, 250, 400, Translation.get("shop.powerups"), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				SceneManager.LoadScene(new PowerUps());
			}
		});
		PowerUps.alignment = GUIAlignment.Center;
		addObject(PowerUps);
		
		GUIButton Background = new GUIButton(300, -200, 250, 400, Translation.get("shop.backgrounds"), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				SceneManager.LoadScene(new Background());
			}
		});
		Background.alignment = GUIAlignment.Center;
		addObject(Background);
		
		
		GUIButton BallTrails = new GUIButton(600, -200, 250, 400, Translation.get("shop.trails"), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				SceneManager.LoadScene(new DefaultScene());
			}
		});
		BallTrails.alignment = GUIAlignment.Center;
		addObject(BallTrails);
		
		GUIButton back = new GUIButton(0, 400, 600, 75, Translation.get("shop.back"), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				SceneManager.LoadScene(new MainMenu());;
			}
		});
		back.alignment = GUIAlignment.Center;
		addObject(back);
		
		super.initializeScene();
		
	}
	
	
	
	
	
	
}
