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
		
		LevelLoader.LoadLevel(Main.class.getResourceAsStream("/levels/storymode_main.plv"));
		StorymodeSaveData saveData = SaveManager.LoadSave("storymode.g4pong");
		
		if (saveData == null) {
			saveData = new StorymodeSaveData();
		}
		
		StorymodePlayer.getPlayerInstance().setHealth(saveData.playerHealth);
		StorymodePlayer.getPlayerInstance().setPermPosition(new Vector3(saveData.playerX, saveData.playerY));
		StorymodePlayer.getPlayerInstance().setPlayerXP(saveData.playerXP);
		
	}
	
	@Override
	public void updateScene() {
		super.updateScene();
		
		System.out.println(StorymodePlayer.getPlayerInstance().getHealth());
		
		if(StorymodePlayer.getPlayerInstance() != null)
			cameraPosition = Vector3.Lerp(cameraPosition, StorymodePlayer.getPlayerInstance().getPosition(), 0.065f);
	}
	
	@Override
	public void uninitializeScene() {
		super.uninitializeScene();
		
		StorymodeSaveData saveData = new StorymodeSaveData();
		
		saveData.playerHealth = StorymodePlayer.getPlayerInstance().getHealth();
		saveData.playerX = StorymodePlayer.getPlayerInstance().getRawPosition().x;
		saveData.playerY = StorymodePlayer.getPlayerInstance().getRawPosition().y;
		saveData.playerXP = StorymodePlayer.getPlayerInstance().getPlayerXP();
		
		SaveManager.SaveToFile("storymode.g4pong", saveData);
	}
	
}
