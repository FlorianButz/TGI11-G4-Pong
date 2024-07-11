package de.demoncore.scenes.storymode;

import de.demoncore.game.SaveManager;
import de.demoncore.gameObjects.PauseMenu;
import de.demoncore.gameObjects.storymode.StorymodePlayer;
import de.demoncore.main.Main;
import de.demoncore.scenes.BaseScene;
import de.demoncore.utils.LevelLoader;
import de.demoncore.utils.Vector3;

public class StorymodeMain extends BaseScene {

	public static StorymodeSaveData saveData;
	
	@Override
	public void initializeScene() {
		super.initializeScene();
		
		addObject(new PauseMenu());
		
		saveData = getSaveData();
				
		LevelLoader.LoadLevel(Main.class.getResourceAsStream("/levels/storymode_main.plv"));

		StorymodePlayer.getPlayerInstance().setPermPosition(new Vector3(saveData.playerX, saveData.playerY));
	}

	public static StorymodeSaveData getSaveData() {
		StorymodeSaveData s = SaveManager.LoadSave("storymode.g4pong");
		
		if (s == null) {
			s = new StorymodeSaveData();
		}

		return s;
	}
	
	@Override
	public void updateScene() {
		super.updateScene();
		
		if(StorymodePlayer.getPlayerInstance() != null)
			cameraPosition = Vector3.Lerp(cameraPosition, StorymodePlayer.getPlayerInstance().getPosition(), 0.065f);

		//cameraPosition = StorymodePlayer.getPlayerInstance().getPosition();
	}
	
	@Override
	public void uninitializeScene() {
		super.uninitializeScene();
		saveStorymode();
	}
	
	public static void saveStorymode() {
		if(saveData == null) return;
		
		saveData.playerHealth = StorymodePlayer.getPlayerInstance().getHealth();
		saveData.playerX = StorymodePlayer.getPlayerInstance().getRawPosition().x;
		saveData.playerY = StorymodePlayer.getPlayerInstance().getRawPosition().y;
		saveData.playerXP = StorymodePlayer.getPlayerInstance().getPlayerXP();
		
		SaveManager.SaveToFile("storymode.g4pong", saveData);
	}
	
	public static void savePlayerStats() {
		if(saveData == null) return;
		
		saveData.playerHealth = StorymodePlayer.getPlayerInstance().getHealth();
		saveData.playerX = saveData.playerX;
		saveData.playerY = saveData.playerY;
		saveData.playerXP = StorymodePlayer.getPlayerInstance().getPlayerXP();
		
		SaveManager.SaveToFile("storymode.g4pong", saveData);
	}
	
}
