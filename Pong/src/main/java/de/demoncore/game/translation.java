package de.demoncore.game;

import java.util.HashMap;
import java.util.Map;

public class translation {

	static Language activeLanguage;
	static Map<String, String> germanTranslation;
	static Map<String, String> englishTranslation;
	
	public static void InitializeTranslation() {
		
		 activeLanguage = Language.Deutsch;
		
		germanTranslation = new HashMap<String, String>();
		germanTranslation.put("test","Test Deutsch");
		
		englishTranslation = new HashMap<String, String>();
		englishTranslation.put("test", "Test English!");
		
	
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
