package de.demoncore.utils;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import de.demoncore.game.GameObject;
import de.demoncore.game.SceneManager;
import de.demoncore.gameObjects.storymode.BigStone;
import de.demoncore.gameObjects.storymode.BossDoor;
import de.demoncore.gameObjects.storymode.Campfire;
import de.demoncore.gameObjects.storymode.DungeonDoor;
import de.demoncore.gameObjects.storymode.Grass;
import de.demoncore.gameObjects.storymode.House;
import de.demoncore.gameObjects.storymode.NPC;
import de.demoncore.gameObjects.storymode.Path;
import de.demoncore.gameObjects.storymode.Pillar;
import de.demoncore.gameObjects.storymode.Sans;
import de.demoncore.gameObjects.storymode.Sign;
import de.demoncore.gameObjects.storymode.SmallStone;
import de.demoncore.gameObjects.storymode.StorymodePlayer;
import de.demoncore.gameObjects.storymode.Tree;
import de.demoncore.gameObjects.storymode.Well;
import de.demoncore.scenes.storymode.StorymodeMain;

public class LevelLoader {

	// Level Objekt IDs
	
	// 0 Player Spawn
	// 1 Grass
	// 2 Kleiner Stein
	// 3 Stein
	// 4 Tree
	// 5 Sign
	// 6 Dungeon Door
	// 7 Path
	// 8 Rectangle
	// 9 Pillar
	// 10 BossDoor

	// 11 House1
	// 12 House2
	// 13 House3
	// 14 House4

	// 15 Campfire
	// 16 Sans
	// 17 Well

	// 18 NPC
	
	public static void LoadLevel(InputStream stream) {

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
			String line = null;
			StringBuilder stringBuilder = new StringBuilder();
			String ls = System.getProperty("line.separator");

			try {
				while((line = reader.readLine()) != null) {
					stringBuilder.append(line);
					stringBuilder.append(ls);
				}

				String fileContent = stringBuilder.toString();
				fileContent = fileContent.replaceAll("\r\n", "");
				
				String[] objects = fileContent.split(";");
				
				for(String obj : objects) {
					
					if(obj.isEmpty()) continue;
					
					String[] objectInfos = obj.split(":");

					int objectId = Integer.parseInt(objectInfos[0]);
					int objectPosX = Integer.parseInt(objectInfos[1]);
					int objectPosY = Integer.parseInt(objectInfos[2]);

					switch(objectId) {
					case 0:
						SceneManager.getActiveScene().addObject(new StorymodePlayer(objectPosX, objectPosY));
						break;
					case 1:
						SceneManager.getActiveScene().addObject(new Grass(objectPosX, objectPosY));
						break;
					case 2:
						SceneManager.getActiveScene().addObject(new SmallStone(objectPosX, objectPosY));
						break;
					case 3:
						SceneManager.getActiveScene().addObject(new BigStone(objectPosX, objectPosY));
						break;
					case 4:
						SceneManager.getActiveScene().addObject(new Tree(objectPosX, objectPosY));
						break;
					case 5:
						int signDialogId = Integer.parseInt(objectInfos[3]);
						SceneManager.getActiveScene().addObject(new Sign(objectPosX, objectPosY, Resources.dialogs.get(signDialogId)));
						break;
					case 6:
						SceneManager.getActiveScene().addObject(new DungeonDoor(objectPosX, objectPosY, true));
						break;
					case 7:
						SceneManager.getActiveScene().addObject(new Path(objectPosX, objectPosY));
						break;
					case 8:
						int sizeX = Integer.parseInt(objectInfos[3]);
						int sizeY = Integer.parseInt(objectInfos[4]);
						int col = Integer.parseInt(objectInfos[5]);
						
						GameObject obj1 = new GameObject(objectPosX, objectPosY, sizeX, sizeY);
						if(col == 1) obj1.color = new Color(0, 0, 0, 0);
						
						SceneManager.getActiveScene().addObject(obj1);
						break;
					case 9:
						SceneManager.getActiveScene().addObject(new Pillar(objectPosX, objectPosY));
						break;
					case 10:
						SceneManager.getActiveScene().addObject(new BossDoor(objectPosX, objectPosY));
						break;
					case 11:
						SceneManager.getActiveScene().addObject(new House(objectPosX, objectPosY, 1));
						break;
					case 12:
						SceneManager.getActiveScene().addObject(new House(objectPosX, objectPosY, 2));
						break;
					case 13:
						SceneManager.getActiveScene().addObject(new House(objectPosX, objectPosY, 3));
						break;
					case 14:
						SceneManager.getActiveScene().addObject(new House(objectPosX, objectPosY, 4));
						break;
					case 15:
						SceneManager.getActiveScene().addObject(new Campfire(objectPosX, objectPosY));
						break;
					case 16:
						SceneManager.getActiveScene().addObject(new Sans(objectPosX, objectPosY));
						break;
					case 17:
						int isMain = Integer.parseInt(objectInfos[3]);
						SceneManager.getActiveScene().addObject(new Well(objectPosX, objectPosY, (isMain != 15) ? true : false));
						break;
					case 18:
						int npcId = Integer.parseInt(objectInfos[3]);
						
						switch(npcId) {
						case 15:
							SceneManager.getActiveScene().addObject(new NPC(objectPosX, objectPosY, null));
							break;
						case 1:
							if(!StorymodeMain.saveData.completedDungeons.contains(-1))
							SceneManager.getActiveScene().addObject(new NPC(objectPosX, objectPosY, Resources.npc0Dialog));
							break;
						case 2:
							if(!StorymodeMain.saveData.completedDungeons.contains(-1))
								SceneManager.getActiveScene().addObject(new NPC(objectPosX, objectPosY, Resources.npc1Dialog));
							break;
						case 4:
							if(StorymodeMain.saveData.completedDungeons.contains(-1))
								SceneManager.getActiveScene().addObject(new NPC(objectPosX, objectPosY, Resources.npc2Dialog));
							break;
						}
						
						break;
					}
				}

			} finally {
				reader.close();
			}

		} catch (FileNotFoundException e) {
			Logger.logError("Level datei konnte nicht gefunden werden. | " + e.getMessage(), LevelLoader.class);
		} catch(IOException e) {
			Logger.logError("Level datei konnte nicht geladen werden. | " + e.getMessage(), LevelLoader.class);
		}
	}

}
