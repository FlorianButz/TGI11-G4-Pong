package de.demoncore.utils;

import java.awt.Font;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;

import de.demoncore.audio.AudioManager;
import de.demoncore.main.Main;

public class Resources {
	
	// Schriftarten
	
	public static Font uiFont;
	public static Font dialogFont;
	
	// Audio dateien

	public static String buttonClick = "Button_Click";
	public static String buttonHover = "Button_Hover";
	
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
		
	}
	
}
