package de.demoncore.game;

import de.demoncore.audio.AudioManager;
import de.demoncore.utils.GameMath;

public class Settings {

	// EINSTELLUNGEN SPEICHERN UND LADEN
	
	public static void LoadAllSettings() {

		// Gespeicherte datei Laden

		SettingsSave deserializedSettings = SaveManager.LoadSave("settings.g4pong");
		if(deserializedSettings == null) deserializedSettings = new SettingsSave();
		
		SetMusicVolume(deserializedSettings.musicVolume);
		SetVolume(deserializedSettings.masterVolume);
		SetFullscreen(deserializedSettings.fullscreen);
		SetDebugMode(deserializedSettings.debugMode);
	}

	public static void SaveAllSettings() {

		SettingsSave classToSave = new SettingsSave();

		classToSave.masterVolume = (int) masterVolume;
		classToSave.musicVolume = (int) musicVolume;
		classToSave.fullscreen = fullscreen;
		classToSave.debugMode = debugMode;

		// Speicherdatei erstellen
		
		SaveManager.SaveToFile("settings.g4pong", classToSave);
	}

	// EINSTELLUNGEN

	private static float masterVolume;
	private static float musicVolume;
	private static boolean fullscreen;
	
	private static boolean debugMode;
	
	// SET / GET METHODEN
	
	public static float GetVolume() {
		return masterVolume;
	}

	public static void SetVolume(float newVolume) {
		masterVolume = GameMath.Clamp(newVolume, 0, 100);
		AudioManager.ChangeMasterVolume(masterVolume);
	}
	
	public static float GetMusicVolume() {
		return musicVolume;
	}

	public static void SetMusicVolume(float newVolume) {
		musicVolume = GameMath.Clamp(newVolume, 0, 100);
		AudioManager.ChangeMusicVolume(musicVolume);
	}
	
	public static boolean GetFullscreen() {
		return fullscreen;
	}

	public static void SetFullscreen(boolean isOn) {
		fullscreen = isOn;
	}
	
	public static boolean GetDebugMode() {
		return debugMode;
	}

	public static void SetDebugMode(boolean isOn) {
		debugMode = isOn;
	}
}