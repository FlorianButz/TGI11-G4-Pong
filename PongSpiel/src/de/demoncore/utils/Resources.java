package de.demoncore.utils;

import java.awt.Font;
import java.io.InputStream;

import de.demoncore.main.Main;

public class Resources {
	
	// Schriftarten
	
	public static Font uiFont;
	public static Font dialogFont;
	
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
