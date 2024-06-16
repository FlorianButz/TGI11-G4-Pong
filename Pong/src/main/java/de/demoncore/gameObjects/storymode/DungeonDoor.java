package de.demoncore.gameObjects.storymode;

import de.demoncore.game.SceneManager;
import de.demoncore.gameObjects.InteractEvent;
import de.demoncore.gameObjects.InteractableObject;
import de.demoncore.scenes.storymode.Dungeon;
import de.demoncore.sprites.SpriteObject;
import de.demoncore.utils.Resources;

public class DungeonDoor extends SpriteObject {

	InteractableObject interaction;

	public DungeonDoor(int posX, int posY, boolean isFunctional) {
		super(posX, posY, 19 * 5, 25 * 5, Resources.dungeonDoor);

		if(isFunctional) {

			interaction = new InteractableObject(posX, posY, 250, 275, StorymodePlayer.getPlayerInstance(), new InteractEvent() {
				@Override
				public void OnInteract() {
					super.OnInteract();
					SceneManager.LoadScene(new Dungeon());
				}
			});
			interaction.interactionString = "Enter Dungeon";

			SceneManager.getActiveScene().addObject(interaction);
		}
	}

	void Destroy() {
		SceneManager.getActiveScene().destroyObject(this);
		if(interaction != null)
			SceneManager.getActiveScene().destroyObject(interaction);
	}
}
