package de.demoncore.utils;

import java.awt.Font;
import java.io.InputStream;

import de.demoncore.audio.AudioClip;
import de.demoncore.audio.AudioMaster;
import de.demoncore.main.Main;
import de.demoncore.sprites.Sprite;

public class Resources {
	
	// Schriftarten
	
	public static Font uiFont;
	public static Font dialogFont;
	
	// Audio dateien

	public static AudioClip buttonClick;
	public static AudioClip buttonHover;

	public static AudioClip pongPowerup;

	public static AudioClip ambienceMusic1;
	public static AudioClip ambienceMusic2;
	
	public static Sprite test;
	
	public static void LoadFonts() {
		
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
	
	public static void LoadAudio() {
		
		// Audio

		buttonClick = AudioMaster.LoadSoundWav("/resources/audio/Button_Click.wav");
		buttonHover = AudioMaster.LoadSoundWav("/resources/audio/Button_Hover.wav");
		
		pongPowerup = AudioMaster.LoadSoundWav("/resources/audio/Pong_Powerup.wav");
		
		// Musik

		ambienceMusic1 = AudioMaster.LoadSoundMp3(Main.class.getResourceAsStream("/resources/audio/music/G4Pong_DarkSong1.mp3"));
		ambienceMusic2 = AudioMaster.LoadSoundMp3(Main.class.getResourceAsStream("/resources/audio/music/G4Pong_DarkSong2.mp3"));
		
	}
	
	public static void LoadSprites() {

		// Textures
		
		test = new Sprite("src/resources/textures/test.jpg").Load();
	}
}
