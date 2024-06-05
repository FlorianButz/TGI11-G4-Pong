package de.demoncore.game;

import java.util.HashMap;
import java.util.Map;

public class Translation {

	static Language activeLanguage = Language.Deutsch;
	static Map<String, String> germanTranslation;
	static Map<String, String> englishTranslation;

	public static void InitializeTranslation() {

		germanTranslation = new HashMap<String, String>();
		englishTranslation = new HashMap<String, String>();

		germanTranslation.put("settings.on", "An");
		englishTranslation.put("settings.on", "On");

		germanTranslation.put("settings.off", "Aus");
		englishTranslation.put("settings.off", "Off");

		germanTranslation.put("settings.saveandback", "Speichern & Zurrueck");
		englishTranslation.put("settings.saveandback", "Save & Back");

		germanTranslation.put("settings.lang", "Sprache (Neustart):");
		englishTranslation.put("settings.lang", "Language (Restart):");

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
	}

	public static String Get(String translationID) {
		switch (activeLanguage) {
		case Deutsch:
			return germanTranslation.get(translationID);
		case English:
			return englishTranslation.get(translationID);
		default:
			return englishTranslation.get(translationID);
		}
	}

	public static void ChangeLanguage(Language language) {
		activeLanguage = language;
	}
}
