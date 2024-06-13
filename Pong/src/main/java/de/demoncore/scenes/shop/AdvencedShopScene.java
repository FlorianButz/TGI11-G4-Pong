package de.demoncore.scenes.shop;

import java.awt.Color;

import de.demoncore.game.GameObject;
import de.demoncore.game.Settings;
import de.demoncore.game.Translation;
import de.demoncore.gui.GUIAlignment;
import de.demoncore.gui.GUIButton;
import de.demoncore.gui.GUIButtonClickEvent;
import de.demoncore.scenes.BaseScene;
import de.demoncore.utils.Resources;
import de.demoncore.gui.GUIButton;
public class AdvencedShopScene extends BaseScene{


	GameObject Background;
	
	@Override
	public void initializeScene() {
	
		Background = new GameObject(500, 0, 1000, 2000);
		addObject(Background);
		
		
		Background.color = Color.darkGray;

		
		GUIButton CreateBackButton() {
			GUIButton back = new GUIButton(0, 0, 500, 50, Translation.get("settings.saveandback"), Resources.uiFont.deriveFont(25F), new GUIButtonClickEvent() {
				@Override
				public void ButtonClick() {
					super.ButtonClick();
					//HideMenu();
					Settings.SaveAllSettings();
				}
			});
			back.alignment = GUIAlignment.Center;
			return back;
		}
		
		
	
		
		super.initializeScene();
	}

	
	
}
