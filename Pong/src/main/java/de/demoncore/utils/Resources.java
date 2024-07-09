package de.demoncore.utils;

import java.awt.Font;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import de.demoncore.audio.AudioClip;
import de.demoncore.audio.AudioMaster;
import de.demoncore.gui.dialog.DialogLine;
import de.demoncore.main.Main;
import de.demoncore.scenes.SplashScreen;
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

	public static Sprite test;
	public static Sprite fullHeart;
	public static Sprite brokenHeart;
	public static Sprite cake;
	public static Sprite sign;
	public static Sprite playerIdle;
	public static Sprite playerWalk1;
	public static Sprite playerWalk2;
	public static Sprite shopIcon;
	public static Sprite shopHoverIcon;
	public static Sprite latiku;
	public static Sprite smallStone;
	public static Sprite bigStone;
	public static Sprite grass1;
	public static Sprite grass2;
	public static Sprite grass3;
	public static Sprite tree;
	public static Sprite dungeonDoor;
	public static Sprite path;

	public static Sprite shop_ballskin;
	public static Sprite shop_ballspawn;
	public static Sprite shop_balltrail;
	public static Sprite shop_pedalskin;

	public static Sprite shop_notrailicon;
	public static Sprite shop_simpletrailicon;
	public static Sprite shop_particletrailicon;
	public static Sprite shop_beamtrailicon;

	public static Sprite shop_whitepedalicon;
	public static Sprite shop_linespedalicon;
	public static Sprite shop_wavespedalicon;
	public static Sprite shop_dotspedalicon;

	public static Sprite ball;
	
	public static Sprite blocked_icon;
	public static Sprite info_icon;
	
	public static DialogLine testLine;
	public static DialogLine signTest1;
	
	static List<DialogLine> dialogs;
	
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
		sign = new Sprite(Main.class.getResourceAsStream("/textures/Sign.png")).load();
		smallStone = new Sprite(Main.class.getResourceAsStream("/textures/SmallStone.png")).load();
		bigStone = new Sprite(Main.class.getResourceAsStream("/textures/BigStone.png")).load();
		grass1 = new Sprite(Main.class.getResourceAsStream("/textures/Grass1.png")).load();
		grass2 = new Sprite(Main.class.getResourceAsStream("/textures/Grass2.png")).load();
		grass3 = new Sprite(Main.class.getResourceAsStream("/textures/Grass3.png")).load();
		tree = new Sprite(Main.class.getResourceAsStream("/textures/Tree.png")).load();
		dungeonDoor = new Sprite(Main.class.getResourceAsStream("/textures/DungeonDoor.png")).load();
		path = new Sprite(Main.class.getResourceAsStream("/textures/Path.png")).load();

		shop_ballskin = new Sprite(Main.class.getResourceAsStream("/textures/Shop_BallSkinsIcon.png")).load();
		shop_pedalskin = new Sprite(Main.class.getResourceAsStream("/textures/Shop_PedalSkinsIcon.png")).load();
		shop_balltrail = new Sprite(Main.class.getResourceAsStream("/textures/Shop_BallTrailIcon.png")).load();
		shop_ballspawn = new Sprite(Main.class.getResourceAsStream("/textures/Shop_BallSpawnIcon.png")).load();

		shop_notrailicon = new Sprite(Main.class.getResourceAsStream("/textures/Shop_NoTrailIcon.png")).load();
		shop_simpletrailicon = new Sprite(Main.class.getResourceAsStream("/textures/Shop_SimpleTrailIcon.png")).load();
		shop_particletrailicon = new Sprite(Main.class.getResourceAsStream("/textures/Shop_ParticleTrailIcon.png")).load();
		shop_beamtrailicon = new Sprite(Main.class.getResourceAsStream("/textures/Shop_BeamTrailIcon.png")).load();
		
		shop_whitepedalicon = new Sprite(Main.class.getResourceAsStream("/textures/Shop_WhitePedal.png")).load();
		shop_linespedalicon = new Sprite(Main.class.getResourceAsStream("/textures/Shop_LinesPedal.png")).load();
		shop_wavespedalicon = new Sprite(Main.class.getResourceAsStream("/textures/Shop_WavesPedal.png")).load();
		shop_dotspedalicon	= new Sprite(Main.class.getResourceAsStream("/textures/Shop_DotsPedal.png")).load();

		ball = new Sprite(Main.class.getResourceAsStream("/textures/Ball.png")).load();
		
		blocked_icon = new Sprite(Main.class.getResourceAsStream("/textures/BlockedIcon.png")).load();
		info_icon = new Sprite(Main.class.getResourceAsStream("/textures/InfoIcon.png")).load();
		
		Logger.logMessage("Laden der Texturen Erfolgreich!");
	}
	
	public static void loadDialog() {
		
		// Dialoge laden
		
		Logger.logInfo("Lade Ressourcen: Lade Dialoge...");
		
		dialogs = new ArrayList<DialogLine>();

		testLine = new DialogLine("Wise Old Man", "Hello, this is a test!", null);
		dialogs.add(testLine);

		signTest1 = new DialogLine("Sign", "This is a sign!", new DialogLine("Sign", "This is the second text!", new DialogLine("Sign", "Please don't leave me alone :(", null)));
		dialogs.add(signTest1);
		
		Logger.logMessage("Laden der Dialoge Erfolgreich!");
	}
}
