package de.demoncore.utils;

import java.awt.Font;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import de.demoncore.audio.AudioClip;
import de.demoncore.audio.AudioMaster;
import de.demoncore.game.Translation;
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

	public static AudioClip playerStep;
	public static AudioClip playerAttackNormal;
	public static AudioClip playerAttackRadial;
	public static AudioClip playerTransformTB;
	public static AudioClip playerTransformFB;
	public static AudioClip playerDeath;
	public static AudioClip playerHurt;
	public static AudioClip playerHeal;
	public static AudioClip openDoor;
	public static AudioClip bossBigAttack;
	public static AudioClip bossLaserCharge;
	public static AudioClip bossLaserAttack;
	public static AudioClip bossDeath;
	public static AudioClip endDoorOpen;

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
	public static Sprite dungeonDoorBroken;
	public static Sprite path;
	public static Sprite pillar;
	public static Sprite compassEmpty;
	public static Sprite compassNeedle;
	public static Sprite bossDoor;
	public static Sprite arrow;
	public static Sprite bone1;
	public static Sprite bone2;

	public static Sprite bossIdle;
	public static Sprite bossEye1;
	public static Sprite bossEye2;

	public static Sprite sans;
	public static Sprite campfire1;
	public static Sprite campfire2;

	public static Sprite npc;

	public static Sprite house1;
	public static Sprite house2;
	public static Sprite house3;
	public static Sprite house4;

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
	public static Sprite credits_icon;
	public static Sprite credits_icon_dark;
	
	public static DialogLine testLine;
	public static DialogLine startDialogSign;
	public static DialogLine stoneDialogSign;
	public static DialogLine endbossDialog;
	public static DialogLine endbossDialogEnd;
	public static DialogLine sansDialog;
	
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

		playerStep = AudioMaster.loadSoundWav("/audio/Storymode_Player_Step.wav");
		playerAttackNormal = AudioMaster.loadSoundWav("/audio/Storymode_Player_NormalAttack.wav");
		playerAttackRadial = AudioMaster.loadSoundWav("/audio/Storymode_Player_RadialAttack.wav");
		playerTransformTB = AudioMaster.loadSoundWav("/audio/Storymode_Player_TransformToBall.wav");
		playerTransformFB = AudioMaster.loadSoundWav("/audio/Storymode_Player_TransformFromBall.wav");
		playerDeath = AudioMaster.loadSoundWav("/audio/Storymode_Player_Death.wav");
		playerHurt = AudioMaster.loadSoundWav("/audio/Storymode_Player_Hurt.wav");
		playerHeal = AudioMaster.loadSoundWav("/audio/Storymode_Player_Heal.wav");
		openDoor = AudioMaster.loadSoundWav("/audio/Storymode_Player_OpenDoor.wav");
		bossBigAttack = AudioMaster.loadSoundWav("/audio/Storymode_Boss_BigAttack.wav");
		bossLaserCharge = AudioMaster.loadSoundWav("/audio/Storymode_Boss_LaserCharge.wav");
		bossLaserAttack = AudioMaster.loadSoundWav("/audio/Storymode_Boss_LaserAttack.wav");
		bossDeath = AudioMaster.loadSoundWav("/audio/Storymode_Boss_Death.wav");
		endDoorOpen = AudioMaster.loadSoundWav("/audio/Storymode_EndDoor_Open.wav");
		
		Logger.logMessage("Laden der Soundeffekte Erfolgreich!");
	}
	
	public static void loadSprites() {
		
		// Texturen werden geladen

		Logger.logInfo("Lade Ressourcen: Lade Texturen");
		
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
		dungeonDoorBroken = new Sprite(Main.class.getResourceAsStream("/textures/DungeonDoorBroken.png")).load();
		path = new Sprite(Main.class.getResourceAsStream("/textures/Path.png")).load();
		pillar = new Sprite(Main.class.getResourceAsStream("/textures/Pillar.png")).load();
		compassEmpty = new Sprite(Main.class.getResourceAsStream("/textures/CompassEmpty.png")).load();
		compassNeedle = new Sprite(Main.class.getResourceAsStream("/textures/CompassNeedle.png")).load();
		bossDoor = new Sprite(Main.class.getResourceAsStream("/textures/BossDoor.png")).load();
		arrow = new Sprite(Main.class.getResourceAsStream("/textures/Arrow.png")).load();
		bone1 = new Sprite(Main.class.getResourceAsStream("/textures/Bone1.png")).load();
		bone2 = new Sprite(Main.class.getResourceAsStream("/textures/Bone2.png")).load();

		bossIdle = new Sprite(Main.class.getResourceAsStream("/textures/BossStone_1.png")).load();
		bossEye1 = new Sprite(Main.class.getResourceAsStream("/textures/BossStone_2.png")).load();
		bossEye2 = new Sprite(Main.class.getResourceAsStream("/textures/BossStone_3.png")).load();

		sans = new Sprite(Main.class.getResourceAsStream("/textures/Sans.png")).load();
		campfire1 = new Sprite(Main.class.getResourceAsStream("/textures/Campfire1.png")).load();
		campfire2 = new Sprite(Main.class.getResourceAsStream("/textures/Campfire2.png")).load();
		npc = new Sprite(Main.class.getResourceAsStream("/textures/NPC.png")).load();
		
		house1 = new Sprite(Main.class.getResourceAsStream("/textures/House1.png")).load();
		house2 = new Sprite(Main.class.getResourceAsStream("/textures/House2.png")).load();
		house3 = new Sprite(Main.class.getResourceAsStream("/textures/House3.png")).load();
		house4 = new Sprite(Main.class.getResourceAsStream("/textures/House4.png")).load();
		
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
		credits_icon = new Sprite(Main.class.getResourceAsStream("/textures/CreditsIcon.png")).load();
		credits_icon_dark = new Sprite(Main.class.getResourceAsStream("/textures/CreditsIconDark.png")).load();
		
		Logger.logMessage("Laden der Texturen Erfolgreich!");
	}
	
	public static void loadDialog() {
		
		// Dialoge laden
		
		Logger.logInfo("Lade Ressourcen: Lade Dialoge...");
		
		dialogs = new ArrayList<DialogLine>();

		dialogs.add(testLine);

		startDialogSign = new DialogLine("Sign", Translation.get("sign.start.0"), new DialogLine("Sign", Translation.get("sign.start.1"), null));
		dialogs.add(startDialogSign);
		
		stoneDialogSign = new DialogLine("Sign", Translation.get("sign.stone.0"),
				new DialogLine("Sign", Translation.get("sign.stone.1"),
						new DialogLine("Sign", Translation.get("sign.stone.2"),
								new DialogLine("Sign", Translation.get("sign.stone.3"),
										new DialogLine("Sign", Translation.get("sign.stone.4"), null)))));
		dialogs.add(stoneDialogSign);
		
		dialogs.add(new DialogLine("Sign", Translation.get("sign.stone.natural"), null));
		dialogs.add(new DialogLine("Sign", Translation.get("sign.stone.perfect.0"), new DialogLine("Sign", Translation.get("sign.stone.perfect.1"), null)));
		
		endbossDialog = new DialogLine("The Rock", Translation.get("endboss.dialog.1"),
				new DialogLine("The Rock", Translation.get("endboss.dialog.2"),
						new DialogLine("The Rock", Translation.get("endboss.dialog.3"),
								new DialogLine("The Rock", Translation.get("endboss.dialog.4"),
										null))));
		dialogs.add(endbossDialog);
		
		endbossDialogEnd = new DialogLine("The Rock", Translation.get("endbossend.dialog.1"),
				new DialogLine("The Rock", Translation.get("endbossend.dialog.2"), null));
		dialogs.add(endbossDialogEnd);
		

		sansDialog = new DialogLine("Sans", Translation.get("sans.dialog.1"),
				new DialogLine("Sans", Translation.get("sans.dialog.2"), null));
		dialogs.add(sansDialog);
		
		Logger.logMessage("Laden der Dialoge Erfolgreich!");
	}
}
