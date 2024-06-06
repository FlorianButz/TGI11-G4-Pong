package de.demoncore.utils;

import java.awt.Font;
import java.io.InputStream;

import de.demoncore.audio.AudioClip;
import de.demoncore.audio.AudioMaster;
import de.demoncore.gui.dialog.DialogLine;
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
	
	public static AudioClip dialogTalk;

	public static AudioClip ambienceDark1;
	public static AudioClip ambienceDark2;
	public static AudioClip ambienceHappy1;
	public static AudioClip ambienceMysterious1;
	public static AudioClip ambienceFast1;
	public static AudioClip sneakySnitch;
	
	public static Sprite test;
	
	public static DialogLine testLine;
	
	public static void LoadFonts() {
		
		// Schriftarten werden geladen
		
		try {
			InputStream is = Main.class.getResourceAsStream("/fonts/PixeloidSans.ttf");
			uiFont = Font.createFont(Font.TRUETYPE_FONT, is);
			is = Main.class.getResourceAsStream("/fonts/OldEnglishGothicPixel.ttf");
			dialogFont = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void LoadAudio() {
		
		// Soundeffekte werden geladen

		buttonClick = AudioMaster.LoadSoundWav("/audio/Button_Click.wav");
		buttonHover = AudioMaster.LoadSoundWav("/audio/Button_Hover.wav");

		pongPowerup = AudioMaster.LoadSoundWav("/audio/Pong_Powerup.wav");
		
		dialogTalk = AudioMaster.LoadSoundWav("/audio/Dialog_Talk.wav");
		
		// Musik wird geladen

		ambienceDark1 = AudioMaster.LoadSoundMp3(Main.class.getResourceAsStream("/audio/music/Gone.mp3"));
		ambienceDark2 = AudioMaster.LoadSoundMp3(Main.class.getResourceAsStream("/audio/music/TheLastLight.mp3"));
		ambienceFast1 = AudioMaster.LoadSoundMp3(Main.class.getResourceAsStream("/audio/music/NearTheEnd.mp3"));
		ambienceHappy1 = AudioMaster.LoadSoundMp3(Main.class.getResourceAsStream("/audio/music/G4Pong_HappyOrchestra.mp3"));
		ambienceMysterious1 = AudioMaster.LoadSoundMp3(Main.class.getResourceAsStream("/audio/music/FarAway.mp3"));
		sneakySnitch = AudioMaster.LoadSoundMp3(Main.class.getResourceAsStream("/audio/music/SneakySnitch.mp3"));
	}
	
	public static void LoadSprites() {

		// Texturen werden geladen
		
		test = new Sprite(Main.class.getResourceAsStream("/textures/test.jpg")).Load();
	}
	
	public static void LoadDialog() {
		
		// Dialoge laden
		
		testLine = new DialogLine("Wise Old Man", "Hello, this is a test!", null);
	}
}
