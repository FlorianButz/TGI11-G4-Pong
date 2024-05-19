package de.demoncore.game;

import de.demoncore.utils.GameMath;

public class Settings {

	// EINSTELLUNGEN SPEICHERN UND LADEN
	
	public static void LoadAllSettings() {

		// Gespeicherte datei Laden

		SettingsSave deserializedSettings = SaveManager.LoadSave("settings.g4pong");
		if(deserializedSettings == null) deserializedSettings = new SettingsSave();
		
		masterVolume = deserializedSettings.masterVolume;
		fullscreen = deserializedSettings.fullscreen;
	}

	public static void SaveAllSettings() {

		SettingsSave classToSave = new SettingsSave();

		classToSave.masterVolume = (int) masterVolume;
		classToSave.fullscreen = fullscreen;

		// Speicherdatei erstellen
		
		SaveManager.SaveToFile("settings.g4pong", classToSave);
	}

	// EINSTELLUNGEN
	
	private static float masterVolume = 85;
	private static boolean fullscreen = false;
	
	// SET / GET METHODEN
	
	public static float GetVolume() {
		return masterVolume;
	}

	public static void SetVolume(float newVolume) {
		masterVolume = GameMath.Clamp(newVolume, 0, 100);
	}
	
	public static boolean GetFullscreen() {
		return fullscreen;
	}

	public static void SetFullscreen(boolean isOn) {
		fullscreen = isOn;
	}
}