package de.demoncore.scenes.storymode;

import java.awt.Color;
import java.util.ArrayList;

import de.demoncore.game.GameObject;
import de.demoncore.game.SaveManager;
import de.demoncore.game.Translation;
import de.demoncore.gameObjects.GUICompass;
import de.demoncore.gameObjects.PauseMenu;
import de.demoncore.gameObjects.storymode.DungeonDoor;
import de.demoncore.gameObjects.storymode.StorymodePlayer;
import de.demoncore.gui.GUIAlignment;
import de.demoncore.gui.GUIText;
import de.demoncore.gui.TextAlignment;
import de.demoncore.main.Main;
import de.demoncore.scenes.BaseScene;
import de.demoncore.utils.LevelLoader;
import de.demoncore.utils.Resources;
import de.demoncore.utils.Vector3;

public class StorymodeMain extends BaseScene {

	public static StorymodeSaveData saveData;
	
	GUIText completedDungeons;
	
	@Override
	public void initializeScene() {
		super.initializeScene();
		
		addObject(new PauseMenu());
		
		saveData = getSaveData();
		saveData.completedDungeons.toString();
		
		LevelLoader.LoadLevel(Main.class.getResourceAsStream("/levels/storymode_main.plv"));

		System.err.print("COMPASS UNDER ROCK - COMPASS SHRINE - SHRINE");
		
		int allCount = 0;
		int completedCount = 0;
		for(GameObject go : new ArrayList<GameObject>(getSceneObjects())) {
			if(go instanceof DungeonDoor) {
				allCount++;
				if(((DungeonDoor)go).isDungeonComplete)
					completedCount++;
			}
		}
		
		completedDungeons = new GUIText(45, 65, Translation.literal(completedCount + "/" + allCount), Resources.uiFont.deriveFont(50F), Color.white);
		completedDungeons.alignment = GUIAlignment.TopLeft;
		completedDungeons.SetTextAlignment(TextAlignment.Left);
		addObject(completedDungeons);
		
		addObject(new GUICompass());
		
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
