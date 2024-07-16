package de.demoncore.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.demoncore.utils.Logger;

public class Translation {

	static Language activeLanguage = Language.Deutsch;
	static Map<String, String> germanTranslation;
	static Map<String, String> englishTranslation;

	public static List<OnLanguageUpdateListener> listeners = new ArrayList<OnLanguageUpdateListener>();
	
	public static void initializeTranslation() {

		Logger.logInfo("Uebersetzungen werden geladen...");
		
		germanTranslation = new HashMap<String, String>();
		englishTranslation = new HashMap<String, String>();

		germanTranslation.put("settings.on", "An");
		englishTranslation.put("settings.on", "On");

		germanTranslation.put("settings.off", "Aus");
		englishTranslation.put("settings.off", "Off");

		germanTranslation.put("settings.saveandback", "Speichern & Zurueck");
		englishTranslation.put("settings.saveandback", "Save & Back");

		germanTranslation.put("settings.lang", "Sprache:");
		englishTranslation.put("settings.lang", "Language:");

		germanTranslation.put("settings.fullscreen", "Vollbild (Neustart):");
		englishTranslation.put("settings.fullscreen", "Fullscreen (Restart):");
		
		germanTranslation.put("settings.debug", "Debug Modus:");
		englishTranslation.put("settings.debug", "Debug Mode:");
		
		germanTranslation.put("settings.sfxVol", "SFX Lautstaerke:");
		englishTranslation.put("settings.sfxVol", "SFX Volume:");

		germanTranslation.put("settings.musicVol", "Musik Lautstaerke:");
		englishTranslation.put("settings.musicVol", "Music Volume:");

		germanTranslation.put("settings.settings", "Einstellungen");
		englishTranslation.put("settings.settings", "Settings");

		germanTranslation.put("settings.camerashake", "Kamerawackeln");
		englishTranslation.put("settings.camerashake", "Camera shake");
		
		germanTranslation.put("settings.particles", "Partikeleffekte");
		englishTranslation.put("settings.particles", "Particle effects");

		germanTranslation.put("settings.pedalspeed", "Pedal Geschwindigkeit");
		englishTranslation.put("settings.pedalspeed", "Pedal speed");

		germanTranslation.put("settings.pedals.slow", "Langsam");
		englishTranslation.put("settings.pedals.slow", "Slow");

		germanTranslation.put("settings.pedals.fast", "Schnell");
		englishTranslation.put("settings.pedals.fast", "Fast");
		
		germanTranslation.put("settings.smallgui", "Kleine GUI");
		englishTranslation.put("settings.smallgui", "Small GUI");

		
		germanTranslation.put("mainmenu.singleplayer", "1 Spieler");
		englishTranslation.put("mainmenu.singleplayer", "1 Player");

		germanTranslation.put("mainmenu.multiplayer", "2 Spieler");
		englishTranslation.put("mainmenu.multiplayer", "2 Player");
		
		germanTranslation.put("mainmenu.storymode", "Story Modus");
		englishTranslation.put("mainmenu.storymode", "Storymode");

		germanTranslation.put("mainmenu.settings", "Einstellungen");
		englishTranslation.put("mainmenu.settings", "Settings");

		germanTranslation.put("mainmenu.quit", "Verlassen");
		englishTranslation.put("mainmenu.quit", "Quit");
		

		germanTranslation.put("pausemenu.paused", "Pausiert");
		englishTranslation.put("pausemenu.paused", "Paused");

		germanTranslation.put("pausemenu.settings", "Einstellungen");
		englishTranslation.put("pausemenu.settings", "Settings");

		germanTranslation.put("pausemenu.returntogame", "Zurueck zum Spiel");
		englishTranslation.put("pausemenu.returntogame", "Return to Game");
		
		germanTranslation.put("pausemenu.backtomainmenu", "Zurueck zum Speisekarte");
		englishTranslation.put("pausemenu.backtomainmenu", "Back to Main Menu");
	
		
		germanTranslation.put("dialog.continue", "Druecke [Space] >");
		englishTranslation.put("dialog.continue", "Press [Space] >");
	
		germanTranslation.put("dialog.skip", "Dialog ueberspringen >");
		englishTranslation.put("dialog.skip", "Skip dialog >");

		
		germanTranslation.put("defscene.error", "Etwas ist schief gelaufen...");
		englishTranslation.put("defscene.error", "Something went wrong :/");
	
		germanTranslation.put("defscene.error2", "Das Level konnte nicht geladen werden.");
		englishTranslation.put("defscene.error2", "There was an error whilst trying to load the level.");

		germanTranslation.put("defscene.return", "Zurueck zum Start");
		englishTranslation.put("defscene.return", "Back to start");
		
		
		germanTranslation.put("interactable.interact", "[E] Interagieren");
		englishTranslation.put("interactable.interact", "[E] Interact");
	
		
		germanTranslation.put("pong.play_again", "Erneut spielen");
		englishTranslation.put("pong.play_again", "Play again");

		germanTranslation.put("pong.end_title", " hat das Spiel gewonnen!");
		englishTranslation.put("pong.end_title", " won the game!");
		
		germanTranslation.put("pong.end_title_points", "Pong Taler zum Konto hinzugef�gt: ");
		englishTranslation.put("pong.end_title_points", "Pong Taler gathered: ");

		germanTranslation.put("pong.back", "Zurueck zum Hauptmenue");
		englishTranslation.put("pong.back", "Back to Main Menu");
		

		germanTranslation.put("shop.pedals", "Schlaeger Skins");
		englishTranslation.put("shop.pedals", "Pedal skins");

		germanTranslation.put("shop.ballskins", "Ball Skins");
		englishTranslation.put("shop.ballskins", "Ball skins");

		germanTranslation.put("shop.spawns", "Wiedererscheinung");
		englishTranslation.put("shop.spawns", "Spawn animation");

		germanTranslation.put("shop.trails", "Ball Spur");
		englishTranslation.put("shop.trails", "Ball trails");

		germanTranslation.put("shop.back", "Zurueck");
		englishTranslation.put("shop.back", "Back");
		
		germanTranslation.put("shop.buy", "Kaufen");
		englishTranslation.put("shop.buy", "Buy");

		germanTranslation.put("color.white", "Weiss");
		englishTranslation.put("color.white", "White");
		
		germanTranslation.put("color.red", "Rot");
		englishTranslation.put("color.red", "Red");

		germanTranslation.put("color.yellow", "Gelb");
		englishTranslation.put("color.yellow", "Yellow");
		
		germanTranslation.put("color.rgb", "Regenbogen");
		englishTranslation.put("color.rgb", "Rainbow");

		germanTranslation.put("trails.none", "Keine");
		englishTranslation.put("trails.none", "None");

		germanTranslation.put("trails.simple", "Einfach");
		englishTranslation.put("trails.simple", "Simple");

		germanTranslation.put("trails.particles", "Partikel");
		englishTranslation.put("trails.particles", "Particles");

		germanTranslation.put("trails.beam", "Beam");
		englishTranslation.put("trails.beam", "Beam");
		
		germanTranslation.put("pedals.white", "Weiss");
		englishTranslation.put("pedals.white", "White");

		germanTranslation.put("pedals.lines", "Striche");
		englishTranslation.put("pedals.lines", "Lines");

		germanTranslation.put("pedals.waves", "Wellen");
		englishTranslation.put("pedals.waves", "Waves");
		
		germanTranslation.put("pedals.dots", "Punkte");
		englishTranslation.put("pedals.dots", "Dots");

		germanTranslation.put("spawns.none", "Keine");
		englishTranslation.put("spawns.none", "None");
		
		germanTranslation.put("spawns.latiku", "Latiku");
		englishTranslation.put("spawns.latiku", "Latiku");
	
		germanTranslation.put("shop.info", "Pong Taler koennen im Einzelspieler durch Gewinnen des Spiels erlangt werden.");
		englishTranslation.put("shop.info", "Pong Taler can be obtained in 1 Player Mode by winning the game.");

		
		germanTranslation.put("deathReason.baseEnemy", "Spieler wurde vom primitiven Gegnertyp getoetet.");
		englishTranslation.put("deathReason.baseEnemy", "Player was killed by the primitive enemy type.");
		
		germanTranslation.put("deathReason.arrow", "Spieler wurde von einem Pfeil aufgespießt.");
		englishTranslation.put("deathReason.arrow", "Player was impaled by an arrow.");

		germanTranslation.put("deathReason.endbossLine", "Spieler wurde durch einen Laser dezimiert.");
		englishTranslation.put("deathReason.endbossLine", "Player was decimated by a laser.");
		
		germanTranslation.put("deathReason.bone", "Spieler wurde von einem fliegenden Knochen zerschmettert.");
		englishTranslation.put("deathReason.bone", "Player was shattered by a flying bone.");
		
		germanTranslation.put("deathReason.movingWalls", "Spieler wurde von den Waenden zerquetscht.");
		englishTranslation.put("deathReason.movingWalls", "Player was squished by moving walls.");
		
		germanTranslation.put("deathReason.skeleton", "Spieler wurde von einer magischen Kraft getoetet.");
		englishTranslation.put("deathReason.skeleton", "Player was killed with magic.");
		
		germanTranslation.put("deathReason.zombie", "Spieler wurde von einem Zombie getoetet.");
		englishTranslation.put("deathReason.zombie", "Player was killed by a zombie.");
		
		germanTranslation.put("deathReason.babyZombie", "Spieler wurde von der Pest getoetet.");
		englishTranslation.put("deathReason.babyZombie", "Player was killed by an baby zombie.");
		
		germanTranslation.put("deathReason.ghost", "Spieler ist durch unerklaerliche Gruende gestorben.");
		englishTranslation.put("deathReason.ghost", "Player was killed by an unknown force.");
	

		germanTranslation.put("sign.start.0", "Nur die mutigsten aller Kaempfer koennen lernen das Boese in dieser Welt zu besiegen.");
		englishTranslation.put("sign.start.0", "Only the one's brave enough can attempt learning to fight the evil that spread in our world.");

		germanTranslation.put("sign.start.1", "Nimm den Kompass, der unter dem Stein auf der rechten Seite liegt, und finde den Schrein des Wissens.");
		englishTranslation.put("sign.start.1", "Take the compass that lies under the rock on the right and find the shrine of knowledge where the truth lies.");

		germanTranslation.put("sign.stone.0", "Dieser einst friedliche Ort wurde in ein Konflikt der Menschheit gezogen.");
		englishTranslation.put("sign.stone.0", "This once peaceful place was torn in to another on of the unnecessary conflicts from the human race.");

		germanTranslation.put("sign.stone.1", "Zwei unschuldige Steine, die in ihrer Gemeinschaft von der Natur platziert wurden waren der Grund des Konfliktes.");
		englishTranslation.put("sign.stone.1", "Two innocent rocks placed in unison were the core of this conflict.");

		germanTranslation.put("sign.stone.2", "Ein Stein so perfekt, dass man sich an den Kanten schneiden konnte. Der andere natuerlicher und Bildschoen.");
		englishTranslation.put("sign.stone.2", "One so perfect that fruits can be cut on its edges. The other more aesthetically pleasing and naturally correct.");
		
		germanTranslation.put("sign.stone.3", "Der Konflikt wurde schnell zu einem Krieg der nur durch ummauern der zwei Steine geloest wurden konnte, so dass die Menschheit und zukuenftige Generationen wieder in Frieden leben koennen.");
		englishTranslation.put("sign.stone.3", "The conflict turned in to a war in which the only way to end it was to seal it away from the outside world returning peace to the world and its future generations.");

		germanTranslation.put("sign.stone.4", "Mach was du willst aber zahl den Preis.");
		englishTranslation.put("sign.stone.4", "Do what you want but pay the price.");

		germanTranslation.put("sign.stone.natural", "Dies ist ein normaler Stein.");
		englishTranslation.put("sign.stone.natural", "This is a normal rock.");

		germanTranslation.put("sign.stone.perfect.0", "Dies ist der perfekteste Stein der jemals existiert hat.");
		englishTranslation.put("sign.stone.perfect.0", "This is the most perfect rock that has ever existed.");
		
		germanTranslation.put("sign.stone.perfect.1", "Aber nicht alles was perfekt ist, ist schoen.");
		englishTranslation.put("sign.stone.perfect.1", "But in beauty does not always lie perfection.");
		

		germanTranslation.put("endboss.dialog.1", "... wurde auch langsam mal Zeit, dass du hier aufkreutzt.");
		englishTranslation.put("endboss.dialog.1", "... you've finally found me.");

		germanTranslation.put("endboss.dialog.2", "Ich habe gesehen, wie du meine Figuren zerstoert hast. Aber kannst du einer echten Herausforderung standhalten?");
		englishTranslation.put("endboss.dialog.2", "I've seen you destroy my allies, but can you hold up to a real challenge?");

		germanTranslation.put("endboss.dialog.3", "Kaempfe gegen mich. Wenn du mich besiegen kannst, kannst du deine kleine Welt retten. Aber wenn nicht...");
		englishTranslation.put("endboss.dialog.3", "Fight me. If you can defeat me, you get to save your little world. But if not...");

		germanTranslation.put("endboss.dialog.4", "Ich wuensch dir viel Glueck, du wirst es brauchen. :)");
		englishTranslation.put("endboss.dialog.4", "I wish you the best of luck, you'll need it. :)");
		

		germanTranslation.put("endbossend.dialog.1", "Ich muss zugeben, dass ich dich unterschaetzt habe.");
		englishTranslation.put("endbossend.dialog.1", "I must admit.. I underestimated you.");
		
		germanTranslation.put("endbossend.dialog.2", "Du verdienst den Sieg. Aber du wirst sehen. Einest Tages werde ich wiederkehren!");
		englishTranslation.put("endbossend.dialog.2", "You deserve your victory. But you'll see. One day I'll be back!");		

		
		germanTranslation.put("sans.dialog.1", "Diese Welt.. schade wie sehr sie doch leidet. Aber die Einwohner tragen die volle Schuld daran.");
		englishTranslation.put("sans.dialog.1", "This world... it's sad how it got ruined. But the people are responsible for this on their own.");
		
		germanTranslation.put("sans.dialog.2", "Bitte mach dich schnell auf den Weg und versuche diese Welt wieder gerade zu biegen. Ich habe keine Lust schonwieder wo anders hin zu reisen.");
		englishTranslation.put("sans.dialog.2", "Please, if you can. Fix this world. I don't feel like moving to a different place again already.");

		
		germanTranslation.put("well.dialog.1", "...");
		englishTranslation.put("well.dialog.1", "....");
		
		germanTranslation.put("well.dialog.2", "Hallo.. ich spüre, dass du hier bist, um einen Einblick in mein Wissen zu erhalten.");
		englishTranslation.put("well.dialog.2", "Hello there... I sense you are here to get a glimpse of my knowledge.");
		
		germanTranslation.put("well.dialog.3", "In dieser Welt sind Dungeons verteilt. Finde sie und besiege alle Gegner darin, um den Dungeon zu zerstoeren. Sobald du alle Dungeons beseitigt hast, kannst du gegen den Endboss kaempfen.");
		englishTranslation.put("well.dialog.3", "There are dungeons spread around this world. Find them and defeat all of the enemies inside to destroy the dungeon. Once you beat all of the dungeons you can fight the endboss.");

		germanTranslation.put("well.dialog.4", "Anhand der Zahl oben links auf dem Bildschirm kannst du den Ueberblick über die von dir zerstoerten Dungeons behalten.");
		englishTranslation.put("well.dialog.4", "You can keep track of the dungeons you've destroyed by the value on the top left of the screen.");

		germanTranslation.put("well.dialog.5", "Um einen Gegner anzugreifen, musst du Geschwindigkeit aufbauen und die Leertaste druecken. Du verwandelst dich in eine Ball Form. In diesem Zustand kannst du von den meisten Gegnern nicht angegriffen werden.");
		englishTranslation.put("well.dialog.5", "To attack an enemy you have to build up speed and press the space bar. You will turn in to a ball. In this state you can't be damaged by most enemies.");

		germanTranslation.put("well.dialog.6", "Wenn viele Gegner in der Naehe sind und du mehr als 3/4 deiner Ausdauer hast, kanst du auch mit einem Radialangriff [Ctrl + Space] angreifen.");
		englishTranslation.put("well.dialog.6", "If there are many enemies around and you have more than 3/4 of your stamina left you can also attack with a radial attack [Ctrl + Space].");	

		germanTranslation.put("well.dialog.7", "Ich gebe dir mein Segen. Ich hoffe du wirst es nicht brauchen.");
		englishTranslation.put("well.dialog.7", "I give you my blessing. I hope you won't need it.");
		

		germanTranslation.put("npc3.dialog.1", "Wow.. du hast es also geschafft.");
		englishTranslation.put("npc3.dialog.1", "Wow.. you really did it.");

		germanTranslation.put("npc3.dialog.2", "Anfangs dachte ich schon wir sind verloren als ich dich sah, aber scheint so als koenntest du mehr als man denkt.");
		englishTranslation.put("npc3.dialog.2", "When I first saw you I thought we already lost, but it seems like you can do more than you look like.");

		germanTranslation.put("npc3.dialog.3", "Ich danke dir fuer deinen Beitrag. Auf wiedersehen Reisender.");
		englishTranslation.put("npc3.dialog.3", "I must thank you for your service. Hopefully we'll see you here again, traveller.");
		

		germanTranslation.put("npc0.dialog.1", "Hey! Du bist der Reisende, oder?");
		englishTranslation.put("npc0.dialog.1", "Hey there! You're the traveller, right?");

		germanTranslation.put("npc0.dialog.2", "Unsere Welt hatte ziemlich viel Stress in letzter Zeit... Unsere ganze Hoffnung steckt in dir, also verkack es nicht! :)");
		englishTranslation.put("npc0.dialog.2", "Our world has had some serious trouble lately... All our hopes are on you, so don't mess up :)");

		germanTranslation.put("npc1.dialog.1", "Hallo? Wer bist du denn?");
		englishTranslation.put("npc1.dialog.1", "Hello? Who are you?");

		germanTranslation.put("npc1.dialog.2", "Ist ja auch egal.. hast du diese riesige Tuer hinter dem Rathaus gesehen? Was das wohl sein mag...");
		englishTranslation.put("npc1.dialog.2", "Anyway, have you seen that big door above the mayor's house? Who knows what could be inside...");
	}

	public static TranslationComponent get(String translationID) {
		return new TranslationComponent(translationID);
	}

	public static TranslationComponent literal(String text) {
		TranslationComponent t = new TranslationComponent(text);
		t.isLiteral = true;
		return t;
	}
	
	public static String get(TranslationComponent comp) {
		if(comp.isLiteral) return comp.translationId;
		
		switch (activeLanguage) {
		case Deutsch:
			if(germanTranslation.get(comp.translationId) == null) return comp.translationId;
			else return germanTranslation.get(comp.translationId);
		case English:
			if(englishTranslation.get(comp.translationId) == null) return comp.translationId;
			else return englishTranslation.get(comp.translationId);
		default:
			if(englishTranslation.get(comp.translationId) == null) return comp.translationId;
			else return englishTranslation.get(comp.translationId);
		}
	}

	public static void changeLanguage(Language language) {
		activeLanguage = language;
		
		for(OnLanguageUpdateListener l : listeners) {
			l.OnLanguageUpdate();
		}
	}
}
