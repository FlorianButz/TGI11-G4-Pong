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

		germanTranslation.put("test","Test Deutsch");
		englishTranslation.put("test", "Test English!");

		System.out.println(Translation.Get("test"));
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
