package de.demoncore.main;

import de.demoncore.audio.AudioManager;
import de.demoncore.game.GameLogic;
import de.demoncore.game.Settings;
import de.demoncore.gui.Gui;
import de.demoncore.utils.Resources;

public class Main {

	public static String version = "0.0.26a";
	
	public static void main(String[] args) {
		Resources.LoadResources();
		Settings.LoadAllSettings();
		
		//AudioManager.PlaySound("/music/G4Pong_DarkSong1", true);
		
		GameLogic gl = new GameLogic();
		new Gui(gl);	
	}
	
}
