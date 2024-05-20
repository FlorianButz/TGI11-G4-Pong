package de.demoncore.gameObjects;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import de.demoncore.actions.GameActionListener;
import de.demoncore.actions.KeyHandler;
import de.demoncore.game.GameLogic;
import de.demoncore.game.SceneManager;
import de.demoncore.game.animator.AnimatorOnCompleteEvent;
import de.demoncore.game.animator.AnimatorUpdateEvent;
import de.demoncore.game.animator.ColorAnimator;
import de.demoncore.game.animator.Easing.EasingType;
import de.demoncore.game.animator.Vector3Animator;
import de.demoncore.gui.GUIAlignment;
import de.demoncore.gui.GUIButton;
import de.demoncore.gui.GUIButtonClickEvent;
import de.demoncore.gui.GUIMenu;
import de.demoncore.gui.GUIObject;
import de.demoncore.gui.GUIRectangle;
import de.demoncore.gui.GUIText;
import de.demoncore.gui.Gui;
import de.demoncore.scenes.MainMenu;
import de.demoncore.utils.Resources;
import de.demoncore.utils.Vector3;

public class PauseMenu extends GUIMenu {

	@Override
	public void OnDestroy() {
		super.OnDestroy();
	}

	@Override
	public void OnMouseDown(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void OnMouseUp(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void OnEscapePressed() {
		if(!isMenuVisible)
			ShowMenu();
		else if(isMenuFocused && isMenuVisible)
			HideMenu();
	}

	@Override
	protected List<GUIObject> AddMenuContent() {
		List<GUIObject> content = new ArrayList<GUIObject>();
		
		GUIText pausedText = new GUIText(0, 175, "Spiel Pausiert", Resources.dialogFont.deriveFont(125F), Color.white);
		pausedText.alignment = GUIAlignment.TopMiddle;
		content.add(pausedText);
		
		GUIButton settingsButton = new GUIButton(0, 100, 750, 100, "Einstellungen", Resources.uiFont.deriveFont(40F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				SettingsMenu settingsMenu = new SettingsMenu();
				SceneManager.GetActiveScene().AddObject(settingsMenu);
				settingsMenu.ShowMenu();
			}
		});
		settingsButton.alignment = GUIAlignment.Center;
		content.add(settingsButton);
		
		GUIButton backToMainMenuButton = new GUIButton(0, 225, 750, 100, "Zurueck zum Hauptmenue", Resources.uiFont.deriveFont(40F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				SceneManager.LoadScene(new MainMenu());
			}
		});
		backToMainMenuButton.alignment = GUIAlignment.Center;
		content.add(backToMainMenuButton);
		
		return content;
	}
	
	@Override
	protected GUIButton CreateBackButton() {
		GUIButton returnToGameButton = new GUIButton(0, -25, 750, 100, "Spiel fortsetzen", Resources.uiFont.deriveFont(40F), new GUIButtonClickEvent() {
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
		super.ShowMenu();

		GameLogic.isGamePaused = true;
	}
	
	@Override
	public void HideMenu() {
		super.HideMenu();

		GameLogic.isGamePaused = false;
	}
}
