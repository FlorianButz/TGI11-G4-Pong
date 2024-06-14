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
		
		germanTranslation.put("pausemenu.backtomainmenu", "Zurueck zum Hauptmenue");
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

		germanTranslation.put("pong.back", "Zurueck zum Hauptmenue");
		englishTranslation.put("pong.back", "Back to Main Menu");
	
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

	public static void ChangeLanguage(Language language) {
		activeLanguage = language;
		
		for(OnLanguageUpdateListener l : listeners) {
			l.OnLanguageUpdate();
		}
	}
}
