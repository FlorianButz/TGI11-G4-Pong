package de.demoncore.scenes.storymode;

import java.awt.Color;
import java.util.ArrayList;

import de.demoncore.audio.MusicManager;
import de.demoncore.game.GameObject;
import de.demoncore.game.SaveManager;
import de.demoncore.game.SceneManager;
import de.demoncore.game.Translation;
import de.demoncore.gameObjects.InteractEvent;
import de.demoncore.gameObjects.InteractableObject;
import de.demoncore.gameObjects.PauseMenu;
import de.demoncore.gameObjects.storymode.BossDoor;
import de.demoncore.gameObjects.storymode.DungeonDoor;
import de.demoncore.gameObjects.storymode.GUICompass;
import de.demoncore.gameObjects.storymode.StorymodePlayer;
import de.demoncore.gui.GUIAlignment;
import de.demoncore.gui.GUIText;
import de.demoncore.gui.TextAlignment;
import de.demoncore.main.Main;
import de.demoncore.scenes.BaseScene;
import de.demoncore.utils.GameMath;
import de.demoncore.utils.LevelLoader;
import de.demoncore.utils.Logger;
import de.demoncore.utils.Resources;
import de.demoncore.utils.Vector3;

public class StorymodeMain extends BaseScene {

	public static StorymodeSaveData saveData;

	GUIText completedDungeons;

	private InteractableObject compassInteraction;

	public static int getDungeonCount() {
		int count = 0;
		for(GameObject go : new ArrayList<GameObject>(SceneManager.getActiveScene().getSceneObjects())) {
			if(go instanceof DungeonDoor) {
				count++;
			}
		}
		return count;
	}
	
	public static int getCompleteDungeonCount() {
		int count = 0;
		for(GameObject go : new ArrayList<GameObject>(SceneManager.getActiveScene().getSceneObjects())) {
			if(go instanceof DungeonDoor) {
				if(((DungeonDoor)go).isDungeonComplete)
					count++;
			}
		}
		return count;
	}
	
	public static BossDoor getBossDoor() {
		for(GameObject go : new ArrayList<GameObject>(SceneManager.getActiveScene().getSceneObjects())) {
			if(go instanceof BossDoor) {
				return (BossDoor)go;
			}
		}
		return null;
	}
	
	public static float difficulty = 0;
	
	float getDifficulty() {
		float allCount = 0;
		float completedCount = 0;
		for(GameObject go : new ArrayList<GameObject>(SceneManager.getActiveScene().getSceneObjects())) {
			if(go instanceof DungeonDoor) {
				allCount++;
				if(((DungeonDoor)go).isDungeonComplete)
					completedCount++;
			}
		}
		
		Logger.logInfo("Difficulty set: "+ completedCount / allCount);
		
		return completedCount / allCount;
	}
	
	@Override
	public void initializeScene() {
		super.initializeScene();

		addObject(new PauseMenu());

		saveData = getSaveData();
		saveData.playerHealth = 6;
		
		LevelLoader.LoadLevel(Main.class.getResourceAsStream("/levels/storymode_main.plv"));

		if(!saveData.compassUnlocked) {

			compassInteraction = new InteractableObject(690, 215, 175, 175, StorymodePlayer.getPlayerInstance(), new InteractEvent() {
				@Override
				public void OnInteract() {
					super.OnInteract();
					
					saveData.compassUnlocked = true;
					removeCompassInteractable();
				}
			});
			compassInteraction.interactionString = "Compass";
			addObject(compassInteraction);
		}

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
		MusicManager.playStorymode();
		cameraFollow = StorymodePlayer.getPlayerInstance();
		
		difficulty = getDifficulty();
	}

	protected void removeCompassInteractable() {
		destroyObject(compassInteraction);
	}

	public static StorymodeSaveData getSaveData() {
		StorymodeSaveData s = SaveManager.LoadSave("storymode.g4pong");

		if (s == null) {
			s = new StorymodeSaveData();
		}

		return s;
	}

	public GameObject cameraFollow = StorymodePlayer.getPlayerInstance();
	public float currentZoomLevel = 1f;
	
	@Override
	public void updateScene() {
		super.updateScene();

		if(StorymodePlayer.getPlayerInstance() != null)
			cameraPosition = Vector3.Lerp(cameraPosition, cameraFollow.getRawPosition(), 0.065f);
		
		cameraZoom = GameMath.Lerp(cameraZoom, currentZoomLevel, 0.05f);
		
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
		saveData.playerXP = StorymodePlayer.getPlayerInstance().getPlayerXP();

		SaveManager.SaveToFile("storymode.g4pong", saveData);
	}

	public static void saveCurrent() {
		SaveManager.SaveToFile("storymode.g4pong", saveData);
	}

}
