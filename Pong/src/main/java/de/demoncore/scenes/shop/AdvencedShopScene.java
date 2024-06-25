package de.demoncore.scenes.shop;

import java.awt.Color;

import de.demoncore.game.GameObject;
import de.demoncore.game.SceneManager;
import de.demoncore.game.Settings;
import de.demoncore.game.Translation;
import de.demoncore.gameObjects.SettingsMenu;
import de.demoncore.gui.GUIAlignment;
import de.demoncore.gui.GUIButton;
import de.demoncore.gui.GUIButtonClickEvent;
import de.demoncore.gui.GUIMenu;
import de.demoncore.scenes.BaseScene;
import de.demoncore.utils.Resources;
import de.demoncore.gui.GUIButton;
public class AdvencedShopScene extends BaseScene{

	GameObject Block;
	@Override
	public void initializeScene() {
			
		
		Block = new GameObject(0, 450, 2000, 250);
		addObject(Block);
		
		Block.color = Color.DARK_GRAY;
		
		GUIButton back = new GUIButton(0, 500, 600, 75, Translation.get("settings.saveandback"), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				SceneManager.loadScene(new ShopScene());;
			}
		});
		back.alignment = GUIAlignment.Center;
		addObject(back);;
		
		
	
		
		super.initializeScene();
	}

	
	
}
