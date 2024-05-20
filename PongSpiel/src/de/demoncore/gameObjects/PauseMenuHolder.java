package de.demoncore.gameObjects;

import java.awt.Color;
import java.awt.event.MouseEvent;

import de.demoncore.actions.GameActionListener;
import de.demoncore.actions.KeyHandler;
import de.demoncore.game.GameLogic;
import de.demoncore.game.GameObject;
import de.demoncore.game.SceneManager;
import de.demoncore.game.animator.AnimatorOnCompleteEvent;
import de.demoncore.game.animator.AnimatorUpdateEvent;
import de.demoncore.game.animator.ColorAnimator;
import de.demoncore.game.animator.Easing.EasingType;
import de.demoncore.game.animator.Vector3Animator;
import de.demoncore.gui.GUIAlignment;
import de.demoncore.gui.GUIButton;
import de.demoncore.gui.GUIButtonClickEvent;
import de.demoncore.gui.GUIRectangle;
import de.demoncore.gui.GUIText;
import de.demoncore.gui.Gui;
import de.demoncore.scenes.MainMenu;
import de.demoncore.utils.Resources;
import de.demoncore.utils.Vector3;

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

		if(outAnim != null) outAnim.Stop();
		if(bgOutAnim != null) bgOutAnim.Stop();
		
		background = new GUIRectangle(0, 0, (int)Gui.GetScreenDimensions().x, (int)Gui.GetScreenDimensions().y, new Color(0, 0, 0, 0f));
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
		
		ChangePos();

		bgInAnim = new ColorAnimator(background.color, new Color(0f, 0f, 0f, 0.9f), 1, EasingType.OutExponential);
		bgInAnim.SetOnUpdate(new AnimatorUpdateEvent() {
		@Override
		public void OnUpdate(Color value) {
			super.OnUpdate(value);
			background.color = value;
		}
		});
		bgInAnim.Play();
		
		inAnim = new Vector3Animator(new Vector3(0, 1500, 0), new Vector3(0, 0, 0), 1, EasingType.OutExponential);
		inAnim.SetOnUpdate(new AnimatorUpdateEvent() {
			@Override
			public void OnUpdate(Vector3 value) {
				super.OnUpdate(value);
				menuYPos = (int) value.y;
				ChangePos();
			}
		});
		inAnim.Play();
	}
	
	int menuYPos = 0;
	Vector3Animator inAnim;
	Vector3Animator outAnim;
	ColorAnimator bgInAnim;
	ColorAnimator bgOutAnim;
	
	void ChangePos() {
		pausedText.SetPosition(new Vector3(0, 175 + menuYPos));
		returnToGameButton.SetPosition(new Vector3(0, 100 + menuYPos));
		backToMainMenuButton.SetPosition(new Vector3(0, 225 + menuYPos));
	}
	
	void HideMenu() {
		GameLogic.isGamePaused = false;
		
		bgInAnim.Stop();
		bgOutAnim = new ColorAnimator(background.color, new Color(0f, 0f, 0f, 0f), 1, EasingType.InOutQuint);
		bgOutAnim.SetOnUpdate(new AnimatorUpdateEvent() {
		@Override
		public void OnUpdate(Color value) {
			super.OnUpdate(value);
			background.color = value;
		}
		});
		bgOutAnim.SetOnComplete(new AnimatorOnCompleteEvent() {
			@Override
			public void OnComplete() {
				super.OnComplete();			
				SceneManager.GetActiveScene().DestroyObject(background);
			}
		});
		bgOutAnim.Play();
		
		inAnim.Stop();
		outAnim = new Vector3Animator(new Vector3(0, menuYPos, 0), new Vector3(0, 1500, 0), 0.5f, EasingType.InOutQuint);
		outAnim.SetOnUpdate(new AnimatorUpdateEvent() {
			@Override
			public void OnUpdate(Vector3 value) {
				super.OnUpdate(value);
				menuYPos = (int) value.y;
				ChangePos();
			}
		});
		outAnim.SetOnComplete(new AnimatorOnCompleteEvent() {
			@Override
			public void OnComplete() {
				super.OnComplete();
				
				SceneManager.GetActiveScene().DestroyObject(pausedText);
				SceneManager.GetActiveScene().DestroyObject(returnToGameButton);
				SceneManager.GetActiveScene().DestroyObject(backToMainMenuButton);
			}
		});
		outAnim.Play();		
	}
}
