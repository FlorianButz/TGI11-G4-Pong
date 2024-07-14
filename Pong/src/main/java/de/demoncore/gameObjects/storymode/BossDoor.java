package de.demoncore.gameObjects.storymode;

import de.demoncore.game.SceneManager;
import de.demoncore.gameObjects.InteractEvent;
import de.demoncore.gameObjects.InteractableObject;
import de.demoncore.scenes.storymode.Dungeon;
import de.demoncore.scenes.storymode.StorymodeMain;
import de.demoncore.sprites.SpriteObject;
import de.demoncore.utils.Logger;
import de.demoncore.utils.Resources;

public class BossDoor extends SpriteObject {

	InteractableObject interaction;
	public boolean isDungeonComplete = false;

	public BossDoor(int posX, int posY) {
		super(posX, posY,
				(int)(69 * 7.5),
				(int)(81 * 7.5),
				Resources.bossDoor);

		boolean canEnterBossRoom = StorymodeMain.getDungeonCount() == StorymodeMain.getCompleteDungeonCount();

		if(canEnterBossRoom) {

			interaction = new InteractableObject(posX, posY, 625, 775, StorymodePlayer.getPlayerInstance(), new InteractEvent() {
				@Override
				public void OnInteract() {
					super.OnInteract();
					SceneManager.loadScene(new Dungeon(posX * posY));
				}
			});
			interaction.interactionString = "Enter Boss Room";
			SceneManager.getActiveScene().addObject(interaction);
		}

	}

	void Destroy() {
		SceneManager.getActiveScene().destroyObject(this);
		if(interaction != null)
			SceneManager.getActiveScene().destroyObject(interaction);
	}
}
