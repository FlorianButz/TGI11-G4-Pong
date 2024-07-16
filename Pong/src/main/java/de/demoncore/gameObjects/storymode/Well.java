package de.demoncore.gameObjects.storymode;

import de.demoncore.game.SceneManager;
import de.demoncore.gameObjects.InteractEvent;
import de.demoncore.gameObjects.InteractableObject;
import de.demoncore.gui.dialog.Dialog;
import de.demoncore.sprites.SpriteObject;
import de.demoncore.utils.Resources;

public class Well extends SpriteObject {

	public static boolean isMainWell;
	public static Well mainInstance;

	public Well(int posX, int posY, boolean isMainWell) {
		super(posX, posY, (int)(17 * 7.5f), (int)(26 * 7.5f), Resources.well);

		Well.isMainWell = isMainWell;

		if(isMainWell)
			mainInstance = this;

		if(isMainWell) {
			InteractableObject interaction = new InteractableObject(posX, posY, 275, 275, StorymodePlayer.getPlayerInstance(), new InteractEvent() {
				@Override
				public void OnInteract() {
					super.OnInteract();

					Dialog dialog = new Dialog(Resources.wellDialog);
					SceneManager.getActiveScene().addObject(dialog);
					dialog.showDialog();
				}
			});
			interaction.interactionString = "Talk";
			SceneManager.getActiveScene().addObject(interaction);
		}
	}
}
