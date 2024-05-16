package de.demoncore.game;

import de.demoncore.scenes.*;

public class SceneManager {

	private static BaseScene activeScene;
	private static BaseScene gameStartScene = (BaseScene) new TwoPlayerPong();
	
	private static boolean isInitialized = false;
	
	public static BaseScene GetActiveScene(){
		CheckForInit();
		return activeScene;
	}
	
	private static void CheckForInit() {
		if(isInitialized == true) return;
		isInitialized = true;
		
		activeScene = gameStartScene;
	}
	
	public static void UpdateScenes() {
		CheckForInit();
		activeScene.UpdateScene();
	}
	
	public static void LoadScene(BaseScene scene) {
		activeScene = scene;
	}
}
