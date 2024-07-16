package de.demoncore.gameObjects.storymode;

import de.demoncore.game.SceneManager;
import de.demoncore.gameObjects.InteractEvent;
import de.demoncore.gameObjects.InteractableObject;
import de.demoncore.sprites.SpriteObject;
import de.demoncore.utils.Resources;

public class Cake extends SpriteObject {

	InteractableObject interaction;
	
	public Cake(int posX, int posY) {
		super(posX, posY, (int)(21 * 2.5f), (int)(19 * 2.5f), Resources.cake);
		collisionEnabled = false;
		
		interaction = new InteractableObject(posX, posY, 175, 175, StorymodePlayer.getPlayerInstance(), new InteractEvent() {
		@Override
		public void OnInteract() {
			super.OnInteract();
			StorymodePlayer.getPlayerInstance().heal(1);
			Destroy();
		}
		});
		interaction.interactionString = "Eat Cake";
		
		SceneManager.getActiveScene().addObject(interaction);
	}
	
	void Destroy() {
		SceneManager.getActiveScene().destroyObject(this);
		SceneManager.getActiveScene().destroyObject(interaction);
	}
}
