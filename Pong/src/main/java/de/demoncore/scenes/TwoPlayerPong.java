package de.demoncore.scenes;

import de.demoncore.audio.MusicManager;
import de.demoncore.gameObjects.PauseMenu;
import de.demoncore.gameObjects.PongPlayer;
import de.demoncore.utils.Resources;

public class TwoPlayerPong extends BaseScene {

	PongPlayer player1;
	PongPlayer player2;

	@Override
	public void InitializeScene() {
		super.InitializeScene();

		MusicManager.PlayMusic(Resources.ambienceDark2);
		
		player1 = new PongPlayer(-100, 0);
		AddObject(player1);
		
		player2 = new PongPlayer(100, 0);
		AddObject(player2);
	}

	PauseMenu pauseMenu;
	
	@Override
	public void UpdateScene() {
		super.UpdateScene();
	}

}
