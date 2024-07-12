package de.demoncore.gameObjects;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import de.demoncore.game.GameLogic;
import de.demoncore.game.OnLanguageUpdateListener;
import de.demoncore.game.SceneManager;
import de.demoncore.game.Translation;
import de.demoncore.gui.GUIAlignment;
import de.demoncore.gui.GUIButton;
import de.demoncore.gui.GUIButtonClickEvent;
import de.demoncore.gui.GUIMenu;
import de.demoncore.gui.GUIObject;
import de.demoncore.gui.GUIText;
import de.demoncore.scenes.MainMenu;
import de.demoncore.utils.Resources;

public class PauseMenu extends GUIMenu implements OnLanguageUpdateListener {
	
	public static boolean isPauseMenuActive = false;
	
	@Override
	public void onDestroy() {
		super.onDestroy();

		isPauseMenuActive = false;
		Translation.listeners.remove(this);
	}
	
	@Override
	public void OnEscape() {
		if(!isMenuVisible)
			ShowMenu();
		else if(isMenuFocused && isMenuVisible)
			HideMenu();
	}

	@Override
	protected List<GUIObject> addMenuContent() {
		List<GUIObject> content = new ArrayList<GUIObject>();
		
		Translation.listeners.add(this);
		
		GUIText pausedText = new GUIText(0, 175, Translation.get("pausemenu.paused"), Resources.dialogFont.deriveFont(125F), Color.white);
		pausedText.alignment = GUIAlignment.TopMiddle;
		content.add(pausedText);
		
		GUIButton settingsButton = new GUIButton(0, 100, 750, 100, Translation.get("pausemenu.settings"), Resources.uiFont.deriveFont(40F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				SettingsMenu settingsMenu = new SettingsMenu();
				SceneManager.getActiveScene().addObject(settingsMenu);
				settingsMenu.ShowMenu();
			}
		});
		settingsButton.alignment = GUIAlignment.Center;
		content.add(settingsButton);
		
		GUIButton backToMainMenuButton = new GUIButton(0, 225, 750, 100, Translation.get("pausemenu.backtomainmenu"), Resources.uiFont.deriveFont(40F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				SceneManager.loadScene(new MainMenu());
			}
		});
		backToMainMenuButton.alignment = GUIAlignment.Center;
		content.add(backToMainMenuButton);
		
		return content;
	}
	
	@Override
	protected GUIButton createBackButton() {
		GUIButton returnToGameButton = new GUIButton(0, -25, 750, 100, Translation.get("pausemenu.returntogame"), Resources.uiFont.deriveFont(40F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				HideMenu();
			}
		});
		returnToGameButton.alignment = GUIAlignment.Center;
		return returnToGameButton;
	}
	
	@Override
	public void ShowMenu() {
		for(GUIObject o : menuContent) {
			SceneManager.getActiveScene().onTop(o);
		}
		
		isPauseMenuActive = true;
		
		super.ShowMenu();
	}
	
	@Override
	public void HideMenu() {
		super.HideMenu();

		isPauseMenuActive = false;
	}

	@Override
	public void OnLanguageUpdate() {
		
	}
}
