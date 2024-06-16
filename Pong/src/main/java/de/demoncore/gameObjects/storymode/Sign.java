package de.demoncore.gameObjects.storymode;

import de.demoncore.game.SceneManager;
import de.demoncore.gameObjects.InteractEvent;
import de.demoncore.gameObjects.InteractableObject;
import de.demoncore.gui.dialog.Dialog;
import de.demoncore.gui.dialog.DialogLine;
import de.demoncore.sprites.SpriteObject;
import de.demoncore.utils.Resources;

public class Sign extends SpriteObject {

	InteractableObject interaction;
	Dialog dialog;
	
	public Sign(int posX, int posY, DialogLine line) {
		super(posX, posY, (int)(19 * 2.5f), (int)(21 * 2.5f), Resources.sign);
		
		interaction = new InteractableObject(posX, posY, 175, 175, StorymodePlayer.getPlayerInstance(), new InteractEvent() {
		@Override
		public void OnInteract() {
			super.OnInteract();
			
			if(!Dialog.isActiveDialog){
				if(dialog != null)
					SceneManager.getActiveScene().destroyObject(dialog);
				
				dialog = new Dialog(line);
				dialog.showDialog();
				SceneManager.getActiveScene().addObject(dialog);
			}
		}
		});
		interaction.interactionString = "Read sign";
		
		SceneManager.getActiveScene().addObject(interaction);
	}
	
	void Destroy() {
		SceneManager.getActiveScene().destroyObject(this);
		SceneManager.getActiveScene().destroyObject(dialog);
		SceneManager.getActiveScene().destroyObject(interaction);
	}
	
}
