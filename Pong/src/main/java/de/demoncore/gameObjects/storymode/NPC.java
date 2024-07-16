package de.demoncore.gameObjects.storymode;

import de.demoncore.game.SceneManager;
import de.demoncore.gameObjects.InteractEvent;
import de.demoncore.gameObjects.InteractableObject;
import de.demoncore.gui.dialog.Dialog;
import de.demoncore.gui.dialog.DialogLine;
import de.demoncore.sprites.SpriteObject;
import de.demoncore.utils.Resources;

public class NPC extends SpriteObject {

	public NPC(int posX, int posY, DialogLine startLine) {
		super(posX, posY, (startLine == null) ? (int)(11 * 2.5f) : (int)(26 * 2.5f), (startLine == null) ? (int)(19 * 2.5f) : (int)(23 * 2.5f), (startLine != null) ? Resources.npcSpeech : Resources.npc);

		if(startLine != null) {

			InteractableObject interaction = new InteractableObject(posX, posY, 175, 175, StorymodePlayer.getPlayerInstance(), new InteractEvent() {
				@Override
				public void OnInteract() {
					super.OnInteract();

					Dialog dialog = new Dialog(startLine);
					SceneManager.getActiveScene().addObject(dialog);
					dialog.showDialog();
				}
			});
			interaction.interactionString = "Talk";
			SceneManager.getActiveScene().addObject(interaction);		
		}
	}
}
