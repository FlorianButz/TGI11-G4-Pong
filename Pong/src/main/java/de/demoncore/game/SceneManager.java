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
import de.demoncore.rendering.Draw;
import de.demoncore.scenes.BaseScene;
import de.demoncore.scenes.DefaultScene;
import de.demoncore.scenes.MainMenu;
import de.demoncore.scenes.SplashScreen;
import de.demoncore.utils.GameMath;

public class SceneManager {

	private static BaseScene activeScene;
	
	private static boolean isInitialized = false;
	
	public static BaseScene getActiveScene(){
		CheckForInit();
		return activeScene;
	}
	
	private static void CheckForInit() {
		if(isInitialized == true) return;
		isInitialized = true;
		
		PLoadScene(new SplashScreen());
	}
	
	public static void UpdateScenes() {
		CheckForInit();
		
		if(activeScene.isInitialized())
			activeScene.updateScene();
	}
	
	private static void PLoadScene(BaseScene scene) {
		activeScene = scene;
		scene.initializeScene();
		scene.setInitializedComplete();
	}
	
	public static void loadScene(BaseScene scene) {
		ArrayList<GameObject> objectsInCurrentScene = new ArrayList<GameObject>(getActiveScene().getSceneObjects());
		
		GUIRectangle blackScreen = new GUIRectangle(0, 0, (int)Gui.GetScreenDimensions().x * 2, (int)Gui.GetScreenDimensions().y * 2, new Color(0, 0, 0, 0f)); // Level uebergang
		blackScreen.doUIScale = false;
		ColorAnimator fadeOut = new ColorAnimator(blackScreen.color, Color.black, 1f, EasingType.InOutQuint); // Uebergang aus
		ColorAnimator fadeIn = new ColorAnimator(Color.black, blackScreen.color, 1f, EasingType.InOutQuint); // Uebergang ein
		
		blackScreen.alignment = GUIAlignment.Center;
		getActiveScene().addObject(blackScreen);
		
		fadeOut.setOnUpdate(new AnimatorUpdateEvent() {
		@Override
		public void onUpdate(Color value) {
			super.onUpdate(value);
			blackScreen.color = value;
			Draw.screenScale = GameMath.RemapValue((float)value.getAlpha() / 255, 0, 1, 1, 0);
		}
		});
		
		fadeIn.setOnUpdate(new AnimatorUpdateEvent() {
			@Override
			public void onUpdate(Color value) {
				super.onUpdate(value);
				blackScreen.color = value;
				Draw.screenScale = GameMath.RemapValue((float)value.getAlpha() / 255, 0, 1, 1, 0);
			}
		});
		
		fadeOut.SetOnComplete(new AnimatorOnCompleteEvent() {
		@Override
		public void onComplete() {
			super.onComplete();
		
			for(GameObject o : objectsInCurrentScene) {
				if(o == blackScreen) continue;
				getActiveScene().destroyObject(o);
			}
			
			GameLogic.SetGamePaused(false);
		
			if(scene != null) {
				PLoadScene(scene);
			}
			else {
				PLoadScene(new DefaultScene());
			}
			
			getActiveScene().addObject(blackScreen);
			
			fadeIn.Play();
		}
		});
		
		fadeIn.SetOnComplete(new AnimatorOnCompleteEvent() {
		@Override
		public void onComplete() {
			super.onComplete();
			getActiveScene().destroyObject(blackScreen);
		}
		});
		
		fadeOut.Play();
	}
}
