package de.demoncore.main;

import java.io.InputStream;

import de.demoncore.audio.AudioMaster;
import de.demoncore.audio.MusicManager;
import de.demoncore.game.GameLogic;
import de.demoncore.game.Settings;
import de.demoncore.gui.Gui;
import de.demoncore.utils.Resources;

public class Main {

	public static String version = "0.0.31a";
	public static String gameName = "Pong auf Crack";
	
	public static void main(String[] args) {
			
		AudioMaster.InitializeOpenAL();

		Resources.LoadSprites();
		Resources.LoadFonts();
		Resources.LoadAudio();
		Resources.LoadDialog();
		
		Settings.LoadAllSettings();
		
		MusicManager.InitializeMusicManager();
		
		System.setProperty("sun.java2d.opengl", "true");
        System.setProperty("sun.java2d.accthreshold", "0");
		
		GameLogic gl = new GameLogic();
		gl.Start();
		new Gui(gl);
		
		
		Gui.AddOnApplicationClose(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        AudioMaster.DestroyOpenAL();
		    }
		});
		
	}
	
	public static InputStream GetResource(String path) {
		return Main.class.getClassLoader().getResourceAsStream(path);
	}
}
