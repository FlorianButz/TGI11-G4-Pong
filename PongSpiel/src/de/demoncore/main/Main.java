package de.demoncore.main;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import de.demoncore.game.GameLogic;
import de.demoncore.gui.Gui;

public class Main {

	public static String version = "0.0.1a";
	
	//public static String fontName = "Archivo";
	public static Font uiFont;
	public static Font dialogFont;
	
	public static void main(String[] args) {
		
		try {
			InputStream is = Main.class.getResourceAsStream("/resources/fonts/PixeloidSans.ttf");
			uiFont = Font.createFont(Font.TRUETYPE_FONT, is);
			is = Main.class.getResourceAsStream("/resources/fonts/OldEnglishGothicPixel.ttf");
			dialogFont = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		GameLogic gl = new GameLogic();
		new Gui(gl);			
	}
	
}
