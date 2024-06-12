package de.demoncore.gameObjects;

import de.demoncore.game.SceneManager;
import de.demoncore.sprites.SpriteObject;
import de.demoncore.utils.Resources;

public class Cake extends SpriteObject {

	InteractableObject interaction;
	
	public Cake(int posX, int posY) {
		super(posX, posY, 50, 50, Resources.cake);
		collisionEnabled = false;
		
		interaction = new InteractableObject(posX, posY, 175, 175, StorymodePlayer.GetPlayerInstance(), new InteractEvent() {
		@Override
		public void OnInteract() {
			super.OnInteract();
			StorymodePlayer.GetPlayerInstance().health.Heal(1);
			Destroy();
		}
		});
		interaction.interactionString = "Eat Cake";
		
		SceneManager.GetActiveScene().addObject(interaction);
	}
	
	void Destroy() {
		SceneManager.GetActiveScene().destroyObject(this);
		SceneManager.GetActiveScene().destroyObject(interaction);
	}
}
