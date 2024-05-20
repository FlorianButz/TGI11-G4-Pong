package de.demoncore.gameObjects;

import java.awt.Color;
import java.awt.event.MouseEvent;

import de.demoncore.actions.GameActionListener;
import de.demoncore.actions.KeyHandler;
import de.demoncore.game.GameLogic;
import de.demoncore.game.GameObject;
import de.demoncore.game.SceneManager;
import de.demoncore.gui.GUIAlignment;
import de.demoncore.gui.GUIButton;
import de.demoncore.gui.GUIButtonClickEvent;
import de.demoncore.gui.GUIRectangle;
import de.demoncore.gui.GUIText;
import de.demoncore.gui.Gui;
import de.demoncore.scenes.MainMenu;
import de.demoncore.utils.Resources;

public class PauseMenuHolder extends GameObject implements GameActionListener {

	GUIText pausedText;
	GUIButton returnToGameButton;
	GUIButton backToMainMenuButton;
	GUIRectangle background;
	
	public PauseMenuHolder() {
		super(0, 0, 0, 0);
		
		collisionEnabled = false;
		color = new Color(0, 0, 0, 0);
		
		KeyHandler.listeners.add(this);
	}
	
	@Override
	public void OnDestroy() {
		super.OnDestroy();
	
		KeyHandler.listeners.remove(this);
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
		if(GameLogic.isGamePaused) HideMenu();
		else ShowMenu();
	}

	void ShowMenu() {
		GameLogic.isGamePaused = true;
		
		background = new GUIRectangle(0, 0, (int)Gui.GetScreenDimensions().x, (int)Gui.GetScreenDimensions().y, new Color(0, 0, 0, 0.8f));
		background.alignment = GUIAlignment.Center;
		SceneManager.GetActiveScene().AddObject(background);	
		
		pausedText = new GUIText(0, 175, "Spiel Pausiert", Resources.dialogFont.deriveFont(125F), Color.white);
		pausedText.alignment = GUIAlignment.TopMiddle;
		SceneManager.GetActiveScene().AddObject(pausedText);
		
		returnToGameButton = new GUIButton(0, 100, 750, 100, "Spiel fortsetzen", Resources.uiFont.deriveFont(40F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				HideMenu();
			}
		});
		returnToGameButton.alignment = GUIAlignment.Center;
		SceneManager.GetActiveScene().AddObject(returnToGameButton);
		
		backToMainMenuButton = new GUIButton(0, 225, 750, 100, "Zurueck zum Hauptmenue", Resources.uiFont.deriveFont(40F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				SceneManager.LoadScene(new MainMenu());
			}
		});
		backToMainMenuButton.alignment = GUIAlignment.Center;
		SceneManager.GetActiveScene().AddObject(backToMainMenuButton);
		
	}
	
	void HideMenu() {
		GameLogic.isGamePaused = false;

		SceneManager.GetActiveScene().DestroyObject(background);
		SceneManager.GetActiveScene().DestroyObject(pausedText);
		SceneManager.GetActiveScene().DestroyObject(returnToGameButton);
		SceneManager.GetActiveScene().DestroyObject(backToMainMenuButton);
	}
}
