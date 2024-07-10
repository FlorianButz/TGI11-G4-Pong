package de.demoncore.main;

import java.io.InputStream;

import de.demoncore.audio.AudioMaster;
import de.demoncore.audio.MusicManager;
import de.demoncore.game.GameLogic;
import de.demoncore.game.Settings;
import de.demoncore.game.Translation;
import de.demoncore.gui.Gui;
import de.demoncore.scenes.SplashScreen;
import de.demoncore.scenes.shopnew.ShopValues;
import de.demoncore.utils.Logger;
import de.demoncore.utils.Resources;

public class Main {

	public static String version = "0.6.236a";
	public static String gameName = "G4 Pong";
	
	boolean quickLoad = true; // Nachher auf false setzten
	
	static float progressActions = 8; // Font, SFX, Sprites, Dialog, Music
	
	public static void main(String[] args) {
		
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
		
		SplashScreen.instance.createProgressBar(1);

		SplashScreen.instance.setProg(0, "OpenAL");
		
		AudioMaster.initializeOpenAL();	// Initialisiere Audio
		
		// Initialisiere Assets

		SplashScreen.instance.setProg((1 / progressActions) * 1, "audio");
		Resources.loadAudio();
		SplashScreen.instance.setProg((1 / progressActions) * 2, "dialogs");
		Resources.loadDialog();
		SplashScreen.instance.setProg((1 / progressActions) * 3, "sprites");
		Resources.loadSprites();
		SplashScreen.instance.setProg((1 / progressActions) * 4, "fonts");
		Resources.loadFonts();
		
		SplashScreen.instance.setProg((1 / progressActions) * 5, "save files");
		
		Settings.LoadAllSettings();
		ShopValues.LoadAllSettings();
		
		SplashScreen.instance.setProg((1 / progressActions) * 6, "translations");
		
		Translation.initializeTranslation();
		
		SplashScreen.instance.setProg((1 / progressActions) * 7, "music");
		
		MusicManager.InitializeMusicManager();
		
		System.setProperty("sun.java2d.opengl", "true");
        System.setProperty("sun.java2d.accthreshold", "0");
		
		SplashScreen.instance.setProg((1 / progressActions) * 8, "done");
	}
	
	public static InputStream getResource(String path) {
		return Main.class.getClassLoader().getResourceAsStream(path);
	}
}
