package de.demoncore.main;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import de.demoncore.audio.AudioMaster;
import de.demoncore.audio.Source;
import de.demoncore.game.GameLogic;
import de.demoncore.game.Settings;
import de.demoncore.gui.Gui;
import de.demoncore.utils.Resources;

public class Main {

	public static String version = "0.0.27a";
	
	public static void main(String[] args) {
		
		AudioMaster.InitializeOpenAL();
		AudioMaster.SetListener();
		
		Resources.LoadResources();
		Settings.LoadAllSettings();
		
		System.setProperty("sun.java2d.opengl", "true"); // Setze es auf true nachher
		
		// VolatileImage class benutzen anstatt buffered image !!!!!!
		
		int buffer = AudioMaster.LoadSound("resources/audio/Pong_Powerup.wav");
		Source source = new Source();
		source.Play(buffer);
		
		// Musik
		//AudioManager.PlaySound("/music/G4Pong_DarkSong1_Ambience", true);
		
		GameLogic gl = new GameLogic();
		new Gui(gl);
		
		Gui.AddOnApplicationClose(new WindowListener() {			
			@Override
			public void windowClosed(WindowEvent e) {
				source.DeleteSource();
				AudioMaster.DestroyOpenAL();
			}

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
}
