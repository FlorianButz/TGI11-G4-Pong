package de.demoncore.gameObjects.storymode;

import java.util.Timer;
import java.util.TimerTask;

import de.demoncore.audio.AudioSource;
import de.demoncore.audio.MusicManager;
import de.demoncore.game.SceneManager;
import de.demoncore.gameObjects.InteractEvent;
import de.demoncore.gameObjects.InteractableObject;
import de.demoncore.scenes.storymode.EndbossFight;
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

		if(StorymodeMain.saveData.completedDungeons.contains(-1)) {
			sprite = Resources.bossDoorBroken;
			return;
		}

		boolean canEnterBossRoom = (StorymodeMain.getDungeonCount() == StorymodeMain.getCompleteDungeonCount());
		
		if(canEnterBossRoom) {

			interaction = new InteractableObject(posX, posY, 625, 775, StorymodePlayer.getPlayerInstance(), new InteractEvent() {
				@Override
				public void OnInteract() {
					super.OnInteract();

					AudioSource source = new AudioSource();
					source.setSpacial(false);
					source.SetVolume(0.75f);
					SceneManager.getActiveScene().addObject(source);
					
					source.Play(Resources.endDoorOpen);
					
					MusicManager.ForcePlayMusic(MusicManager.endboss, true);
					
					((StorymodeMain)SceneManager.getActiveScene()).cameraFollow = getObj();
					((StorymodeMain)SceneManager.getActiveScene()).currentZoomLevel = 0.75f;
					
					SceneManager.getActiveScene().ShakeCamera(20, 15, 200);
					
					Timer timer = new Timer();
					timer.schedule(new TimerTask() {
						@Override
						public void run() {
							SceneManager.loadScene(new EndbossFight());
						}
					}, 3500);
					
					destroyInteraction();
				}
			});
			interaction.interactionString = "Enter Boss Room";
			SceneManager.getActiveScene().addObject(interaction);
		}

	}

	protected void destroyInteraction() {
		SceneManager.getActiveScene().destroyObject(interaction);		
	}

	BossDoor getObj() {
		return this;
	}
	
	void Destroy() {
		SceneManager.getActiveScene().destroyObject(this);
		if(interaction != null)
			SceneManager.getActiveScene().destroyObject(interaction);
	}
}
