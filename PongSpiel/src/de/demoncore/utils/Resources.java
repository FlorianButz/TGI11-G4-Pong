package de.demoncore.utils;

import java.awt.Font;
import java.io.InputStream;

import de.demoncore.audio.AudioClip;
import de.demoncore.audio.AudioMaster;
import de.demoncore.main.Main;

public class Resources {
	
	// Schriftarten
	
	public static Font uiFont;
	public static Font dialogFont;
	
	// Audio dateien

	public static AudioClip buttonClick;
	public static AudioClip buttonHover;

	public static AudioClip pongPowerup;
	
	public static void LoadResources() {
		
		// Schriftarten
		
		try {
			InputStream is = Main.class.getResourceAsStream("/resources/fonts/PixeloidSans.ttf");
			uiFont = Font.createFont(Font.TRUETYPE_FONT, is);
			is = Main.class.getResourceAsStream("/resources/fonts/OldEnglishGothicPixel.ttf");
			dialogFont = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		// Audio

		buttonClick = AudioMaster.LoadSound("/resources/audio/Button_Click.wav");
		buttonHover = AudioMaster.LoadSound("/resources/audio/Button_Hover.wav");
		pongPowerup = AudioMaster.LoadSound("/resources/audio/Pong_Powerup.wav");
		
	}
	
}
