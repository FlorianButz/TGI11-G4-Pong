package de.demoncore.main;

import java.io.InputStream;

import de.demoncore.audio.AudioMaster;
import de.demoncore.audio.MusicManager;
import de.demoncore.game.GameLogic;
import de.demoncore.game.Settings;
import de.demoncore.game.Translation;
import de.demoncore.gui.Gui;
import de.demoncore.scenes.shopnew.ShopValues;
import de.demoncore.utils.Logger;
import de.demoncore.utils.Resources;

public class Main {

	public static String version = "0.5.152a";
	public static String gameName = "Pong auf Crack";
	
	boolean quickLoad = true; // Nachher auf false setzten
	
	public static void main(String[] args) {
			
		AudioMaster.initializeOpenAL();		// Initialisiere Audio 

		// Initialisiere Assets

		Resources.loadAudio();
		Resources.loadDialog();
		Resources.loadSprites();
		Resources.loadFonts();
		
		Settings.LoadAllSettings();
		ShopValues.LoadAllSettings();
		
		Translation.initializeTranslation();
		
		MusicManager.InitializeMusicManager();
		
		System.setProperty("sun.java2d.opengl", "true");
        System.setProperty("sun.java2d.accthreshold", "0");
		
		Logger.logInfo("GUI wird erstellt...");
        
		GameLogic gl = new GameLogic();
		gl.Start();
		new Gui(gl);
		
		Gui.addOnApplicationClose(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        AudioMaster.DestroyOpenAL();

		        Settings.SaveAllSettings();
				ShopValues.SaveAllSettings();
		    }
		});
		
	}
	
	public static InputStream getResource(String path) {
		return Main.class.getClassLoader().getResourceAsStream(path);
	}
}
