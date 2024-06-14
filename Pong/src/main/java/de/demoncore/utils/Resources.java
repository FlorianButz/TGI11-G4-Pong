package de.demoncore.utils;

import java.awt.Font;
import java.io.InputStream;
import java.lang.constant.ClassDesc;

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
	public static AudioClip pongPlayerHitWall;
	public static AudioClip pongPlayerHitPedal;
	public static AudioClip pongGoal;
	
	public static AudioClip dialogTalk;

	public static AudioClip ambienceDark1;
	public static AudioClip ambienceDark2;
	public static AudioClip ambienceHappy1;
	public static AudioClip ambienceMysterious1;
	public static AudioClip ambienceFast1;
	public static AudioClip sneakySnitch;

	public static Sprite test;
	public static Sprite fullHeart;
	public static Sprite brokenHeart;
	public static Sprite cake;
	public static Sprite playerIdle;
	public static Sprite playerWalk1;
	public static Sprite playerWalk2;
	public static Sprite shopIcon;
	public static Sprite shopHoverIcon;
	public static Sprite latiku;
	
	public static DialogLine testLine;
	
	public static void loadFonts() {
		
		// Schriftarten werden geladen
		
		Logger.logInfo("Lade Ressourcen: Lade Schriftarten");
		
		try {
			InputStream is = Main.class.getResourceAsStream("/fonts/PixeloidSans.ttf");
			try {
				uiFont = Font.createFont(Font.TRUETYPE_FONT, is);
			}catch(Exception e) {
				Logger.logError("Konnte Schriftart nicht erstellen. | " + e.getMessage(), e);		
			}
		
			is = Main.class.getResourceAsStream("/fonts/OldEnglishGothicPixel.ttf");
			try {
				dialogFont = Font.createFont(Font.TRUETYPE_FONT, is);
			}catch(Exception e) {
				Logger.logError("Konnte Schriftart nicht erstellen. | " + e.getMessage(), e);		
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
			Logger.logError("Fehler beim laden der Schriftarten. | " + e.getMessage(), e);
			return;
		}
		
		Logger.logMessage("Laden der Schriftarten Erfolgreich!");
	}
	
	public static void loadAudio() {
		
		// Soundeffekte werden geladen

		Logger.logInfo("Lade Ressourcen: Lade Audio");
		
		buttonClick = AudioMaster.loadSoundWav("/audio/Button_Click.wav");
		buttonHover = AudioMaster.loadSoundWav("/audio/Button_Hover.wav");

		pongPowerup = AudioMaster.loadSoundWav("/audio/Pong_Powerup.wav");
		pongPlayerHitPedal = AudioMaster.loadSoundWav("/audio/Pong_PlayerHitPedal.wav");
		pongPlayerHitWall = AudioMaster.loadSoundWav("/audio/Pong_PlayerHitWall.wav");
		pongGoal = AudioMaster.loadSoundWav("/audio/Pong_Goal.wav");
		
		dialogTalk = AudioMaster.loadSoundWav("/audio/Dialog_Talk.wav");
		
		Logger.logMessage("Laden der Soundeffekte Erfolgreich!");
		
		// Musik wird geladen

		Logger.logInfo("Lade Ressourcen: Lade Musik");
		
		ambienceDark1 = AudioMaster.loadSoundMp3(Main.class.getResourceAsStream("/audio/music/Gone.mp3"));
		ambienceDark2 = AudioMaster.loadSoundMp3(Main.class.getResourceAsStream("/audio/music/TheLastLight.mp3"));
		ambienceFast1 = AudioMaster.loadSoundMp3(Main.class.getResourceAsStream("/audio/music/NearTheEnd.mp3"));
		ambienceHappy1 = AudioMaster.loadSoundMp3(Main.class.getResourceAsStream("/audio/music/G4Pong_HappyOrchestra.mp3"));
		ambienceMysterious1 = AudioMaster.loadSoundMp3(Main.class.getResourceAsStream("/audio/music/FarAway.mp3"));
		sneakySnitch = AudioMaster.loadSoundMp3(Main.class.getResourceAsStream("/audio/music/SneakySnitch.mp3"));
		
		Logger.logMessage("Laden der Musik Erfolgreich!");
	}
	
	public static void loadSprites() {

		// Texturen werden geladen

		Logger.logInfo("Lade Ressourcen: Lade Texturen");
		
		test = new Sprite(Main.class.getResourceAsStream("/textures/test.jpg")).load();
		fullHeart = new Sprite(Main.class.getResourceAsStream("/textures/HeartFull.png")).load();
		brokenHeart = new Sprite(Main.class.getResourceAsStream("/textures/HeartBroken.png")).load();
		cake = new Sprite(Main.class.getResourceAsStream("/textures/Cake.png")).load();
		playerIdle = new Sprite(Main.class.getResourceAsStream("/textures/PlayerIdle.png")).load();
		playerWalk1 = new Sprite(Main.class.getResourceAsStream("/textures/PlayerWalk1.png")).load();
		playerWalk2 = new Sprite(Main.class.getResourceAsStream("/textures/PlayerWalk3.png")).load();
		shopIcon = new Sprite(Main.class.getResourceAsStream("/textures/ShopIcon.png")).load();
		shopHoverIcon = new Sprite(Main.class.getResourceAsStream("/textures/ShopHoverIcon.png")).load();
		latiku = new Sprite(Main.class.getResourceAsStream("/textures/PongLatiku.png")).load();
		
		Logger.logMessage("Laden der Texturen Erfolgreich!");
	}
	
	public static void loadDialog() {
		
		// Dialoge laden
		
		Logger.logInfo("Lade Ressourcen: Lade Dialoge...");
		
		testLine = new DialogLine("Wise Old Man", "Hello, this is a test!", null);

		Logger.logMessage("Laden der Dialoge Erfolgreich!");
	}
}
