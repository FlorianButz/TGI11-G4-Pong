package de.demoncore.scenes;

import de.demoncore.actions.GameActionListener;
import de.demoncore.actions.KeyHandler;
import de.demoncore.audio.MusicManager;
import de.demoncore.game.GameObject;
import de.demoncore.gameObjects.PauseMenu;
import de.demoncore.gameObjects.PongPlayer;
import de.demoncore.gui.dialog.Dialog;
import de.demoncore.gui.dialog.DialogLine;
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
		
		try {
			KeyHandler.listeners.add(new GameActionListener() {
				@Override
				public void OnInteractionKeyPressed() {
					super.OnInteractionKeyPressed();
					Dialog d = new Dialog(new DialogLine("Test dialog", "Test line", null));
					AddObject(d);
					d.ShowDialog();
				}
			});
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	PauseMenu pauseMenu;
}
