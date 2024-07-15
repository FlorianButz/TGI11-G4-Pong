package de.demoncore.gameObjects.storymode;

import de.demoncore.game.SceneManager;
import de.demoncore.gameObjects.InteractEvent;
import de.demoncore.gameObjects.InteractableObject;
import de.demoncore.gui.dialog.Dialog;
import de.demoncore.scenes.storymode.StorymodeMain;
import de.demoncore.sprites.SpriteObject;
import de.demoncore.utils.Resources;

public class Sans extends SpriteObject {

	private InteractableObject interaction;

	public Sans(int posX, int posY) {
		super(posX, posY, (int)(49 * 1.25f), (int)(64 * 1.25f), Resources.sans);


		interaction = new InteractableObject(posX, posY, 175, 175, StorymodePlayer.getPlayerInstance(), new InteractEvent() {
			@Override
			public void OnInteract() {
				super.OnInteract();

				Dialog dialog = new Dialog(Resources.sansDialog);
				SceneManager.getActiveScene().addObject(dialog);
				dialog.showDialog();

				destroyInteraction();
				StorymodeMain.saveData.spokenTo.add(posX * posY + posY);
			}
		});
		interaction.interactionString = "Talk";
		
		if(!StorymodeMain.saveData.spokenTo.contains(posX * posY + posY))
			SceneManager.getActiveScene().addObject(interaction);
	}

	void destroyInteraction() {
		SceneManager.getActiveScene().destroyObject(interaction);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		SceneManager.getActiveScene().destroyObject(interaction);	
	}
}
