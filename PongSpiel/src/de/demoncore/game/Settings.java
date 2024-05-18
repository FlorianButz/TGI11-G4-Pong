package de.demoncore.game;

import de.demoncore.utils.GameMath;

public class Settings {

	public static void LoadAllSettings() {

		// Gespeicherte datei Laden

		SettingsSave deserializedSettings = SaveManager.LoadSave("settings.g4pong");
		masterVolume = deserializedSettings.masterVolume;
	}

	public static void SaveAllSettings() {

		SettingsSave classToSave = new SettingsSave();

		classToSave.masterVolume = (int) masterVolume;

		// Speicherdatei erstellen
		
		SaveManager.SaveToFile("settings.g4pong", classToSave);
	}

	private static float masterVolume = 85;

	public static float GetVolume() {
		return masterVolume;
	}

	public static void SetVolume(float newVolume) {
		masterVolume = GameMath.Clamp(newVolume, 0, 100);
	}
}