package de.demoncore.game;

import java.util.ArrayList;

import de.demoncore.scenes.BaseScene;
import de.demoncore.scenes.DefaultScene;
import de.demoncore.scenes.MainMenu;

public class SceneManager {

	private static BaseScene activeScene;
	private static BaseScene gameStartScene = (BaseScene) new MainMenu();
	
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
		ArrayList<GameObject> objectsInCurrentScene = new ArrayList<GameObject>(GetActiveScene().GetSceneObjects());
		
		for(GameObject o : objectsInCurrentScene) {
			GetActiveScene().DestroyObject(o);
		}
		
		if(scene != null)
			activeScene = scene;
		else
			activeScene = new DefaultScene();
	}
}
