package de.demoncore.game;

import java.awt.Color;
import java.util.ArrayList;

import de.demoncore.game.animator.AnimatorOnCompleteEvent;
import de.demoncore.game.animator.AnimatorUpdateEvent;
import de.demoncore.game.animator.ColorAnimator;
import de.demoncore.game.animator.Easing.EasingType;
import de.demoncore.gui.GUIAlignment;
import de.demoncore.gui.GUIRectangle;
import de.demoncore.gui.Gui;
import de.demoncore.scenes.BaseScene;
import de.demoncore.scenes.DefaultScene;
import de.demoncore.scenes.MainMenu;

public class SceneManager {

	private static BaseScene activeScene;
	
	private static boolean isInitialized = false;
	
	public static BaseScene GetActiveScene(){
		CheckForInit();
		return activeScene;
	}
	
	private static void CheckForInit() {
		if(isInitialized == true) return;
		isInitialized = true;
		
		PLoadScene(new MainMenu());
	}
	
	public static void UpdateScenes() {
		CheckForInit();
		
		if(activeScene.IsInitialized())
			activeScene.UpdateScene();
	}
	
	private static void PLoadScene(BaseScene scene) {
		activeScene = scene;
		scene.InitializeScene();
		scene.SetInitializedComplete();
	}
	
	public static void LoadScene(BaseScene scene) {
		ArrayList<GameObject> objectsInCurrentScene = new ArrayList<GameObject>(GetActiveScene().GetSceneObjects());
		
		GUIRectangle blackScreen = new GUIRectangle(0, 0, (int)Gui.GetScreenDimensions().x, (int)Gui.GetScreenDimensions().y, new Color(0, 0, 0, 0f)); // Level uebergang
		ColorAnimator fadeOut = new ColorAnimator(blackScreen.color, Color.black, 1f, EasingType.InOutQuint); // Uebergang aus
		ColorAnimator fadeIn = new ColorAnimator(Color.black, blackScreen.color, 1f, EasingType.InOutQuint); // Uebergang ein
		
		blackScreen.alignment = GUIAlignment.Center;
		GetActiveScene().AddObject(blackScreen);
		
		fadeOut.SetOnUpdate(new AnimatorUpdateEvent() {
		@Override
		public void OnUpdate(Color value) {
			super.OnUpdate(value);
			blackScreen.color = value;
		}
		});
		
		fadeIn.SetOnUpdate(new AnimatorUpdateEvent() {
			@Override
			public void OnUpdate(Color value) {
				super.OnUpdate(value);
				blackScreen.color = value;
			}
		});
		
		fadeOut.SetOnComplete(new AnimatorOnCompleteEvent() {
		@Override
		public void OnComplete() {
			super.OnComplete();
		
			for(GameObject o : objectsInCurrentScene) {
				if(o == blackScreen) continue;
				GetActiveScene().DestroyObject(o);
			}
			
			GameLogic.isGamePaused = false;
		
			if(scene != null) {
				PLoadScene(scene);
			}
			else {
				PLoadScene(new DefaultScene());
			}
			
			GetActiveScene().AddObject(blackScreen);
			
			fadeIn.Play();
		}
		});
		
		fadeIn.SetOnComplete(new AnimatorOnCompleteEvent() {
		@Override
		public void OnComplete() {
			super.OnComplete();
			GetActiveScene().DestroyObject(blackScreen);
		}
		});
		
		fadeOut.Play();
	}
}
