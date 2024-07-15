package de.demoncore.gameObjects.storymode;

import de.demoncore.audio.AudioSource;
import de.demoncore.game.SceneManager;
import de.demoncore.gameObjects.InteractEvent;
import de.demoncore.gameObjects.InteractableObject;
import de.demoncore.scenes.storymode.Dungeon;
import de.demoncore.scenes.storymode.StorymodeMain;
import de.demoncore.sprites.SpriteObject;
import de.demoncore.utils.Logger;
import de.demoncore.utils.Resources;

public class DungeonDoor extends SpriteObject {

	InteractableObject interaction;
	public boolean isDungeonComplete = false;

	public AudioSource source;
	
	public DungeonDoor(int posX, int posY, boolean notExit) {
		super(posX, posY,
				(int)(27 * 7.5),
				(int)(30 * 7.5),
				!StorymodeMain.saveData.completedDungeons.contains(posX * posY) ? Resources.dungeonDoor : Resources.dungeonDoorBroken);

		source = new AudioSource(this);
		SceneManager.getActiveScene().addObject(source);
				
		isDungeonComplete = StorymodeMain.saveData.completedDungeons.contains(posX * posY);
		
		if(!isDungeonComplete) {
			if(notExit) {

				interaction = new InteractableObject(posX, posY, 325, 375, StorymodePlayer.getPlayerInstance(), new InteractEvent() {
					@Override
					public void OnInteract() {
						super.OnInteract();
						SceneManager.loadScene(new Dungeon(posX * posY));
						source.Play(Resources.openDoor);

						destroyInteraction();
					}
				});
				interaction.interactionString = "Enter Dungeon";

				SceneManager.getActiveScene().addObject(interaction);
			}else {
				interaction = new InteractableObject(posX, posY, 325, 375, StorymodePlayer.getPlayerInstance(), new InteractEvent() {
					@Override
					public void OnInteract() {
						super.OnInteract();
						SceneManager.loadScene(new StorymodeMain());
						source.Play(Resources.openDoor);
						
						destroyInteraction();
					}
				});
				interaction.interactionString = "Exit Dungeon";

				SceneManager.getActiveScene().addObject(interaction);
			}
		}else {
			Logger.logInfo("Dungeon already completed!");
		}
	}

	protected void destroyInteraction() {
		SceneManager.getActiveScene().destroyObject(interaction);
	}

	void Destroy() {
		SceneManager.getActiveScene().destroyObject(this);
		if(interaction != null)
			SceneManager.getActiveScene().destroyObject(interaction);
		SceneManager.getActiveScene().destroyObject(source);
	}
}
