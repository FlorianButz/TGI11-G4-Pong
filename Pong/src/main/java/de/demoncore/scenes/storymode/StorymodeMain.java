package de.demoncore.scenes.storymode;

import java.awt.Color;
import java.util.ArrayList;

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
import de.demoncore.gui.dialog.Dialog;
import de.demoncore.main.Main;
import de.demoncore.scenes.BaseScene;
import de.demoncore.utils.LevelLoader;
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
	
	@Override
	public void initializeScene() {
		super.initializeScene();

		addObject(new PauseMenu());

		saveData = getSaveData();
		saveData.playerHealth = 6;
		
		LevelLoader.LoadLevel(Main.class.getResourceAsStream("/levels/storymode_main.plv"));

		int allCount = 0;
		int completedCount = 0;
		for(GameObject go : new ArrayList<GameObject>(getSceneObjects())) {
			if(go instanceof DungeonDoor) {
				allCount++;
				if(((DungeonDoor)go).isDungeonComplete)
					completedCount++;
			}
		}

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

		completedDungeons = new GUIText(45, 65, Translation.literal(completedCount + "/" + allCount), Resources.uiFont.deriveFont(50F), Color.white);
		completedDungeons.alignment = GUIAlignment.TopLeft;
		completedDungeons.SetTextAlignment(TextAlignment.Left);
		addObject(completedDungeons);

		addObject(new GUICompass());

		StorymodePlayer.getPlayerInstance().setPermPosition(new Vector3(saveData.playerX, saveData.playerY));
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
		saveData.playerXP = StorymodePlayer.getPlayerInstance().getPlayerXP();

		SaveManager.SaveToFile("storymode.g4pong", saveData);
	}

}
