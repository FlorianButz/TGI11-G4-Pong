package de.demoncore.scenes.shopnew;

import de.demoncore.game.SaveManager;
import de.demoncore.utils.Logger;

public class ShopValues {

	public static ShopValuesSave shopData;
	
	// EINSTELLUNGEN SPEICHERN UND LADEN
	
	public static void LoadAllSettings() {

		// Gespeicherte datei Laden

		Logger.logInfo("Shopdaten werden geladen...");
		
		ShopValuesSave deserializedData = SaveManager.LoadSave("shop.g4pong");
		if(deserializedData == null) deserializedData = new ShopValuesSave();
		
		shopData = deserializedData;
	}

	public static void SaveAllSettings() {
		// Speicherdatei erstellen
		
		SaveManager.SaveToFile("shop.g4pong", shopData);
	}
	
}