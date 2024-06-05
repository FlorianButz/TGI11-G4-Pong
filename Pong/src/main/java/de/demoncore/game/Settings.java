package de.demoncore.game;

import de.demoncore.audio.AudioMaster;
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
		SetLang(deserializedSettings.language);
		
		AudioMaster.SetMasterVolume(masterVolume / 100f);
		AudioMaster.SetMusicVolume(musicVolume / 100f);
	}

	public static void SaveAllSettings() {

		SettingsSave classToSave = new SettingsSave();

		classToSave.masterVolume = (int) masterVolume;
		classToSave.musicVolume = (int) musicVolume;
		classToSave.fullscreen = fullscreen;
		classToSave.debugMode = debugMode;
		classToSave.language = language;

		// Speicherdatei erstellen
		
		SaveManager.SaveToFile("settings.g4pong", classToSave);
	}

	// EINSTELLUNGEN

	private static float masterVolume;
	private static float musicVolume;
	private static boolean fullscreen;
	
	private static boolean debugMode;
	
	private static Language language;
	
	// SET / GET METHODEN
	
	public static float GetVolume() {
		return masterVolume;
	}

	public static void SetLang(Language lang) {
		language = lang;
		Translation.activeLanguage = language;
	}
	
	public static Language GetLang() {
		return language;
	}
	
	public static void SetVolume(float newVolume) {
		masterVolume = GameMath.Clamp(newVolume, 0, 100);
		AudioMaster.SetMasterVolume(newVolume / 100f);
	}
	
	public static float GetMusicVolume() {
		return musicVolume;
	}

	public static void SetMusicVolume(float newVolume) {
		musicVolume = GameMath.Clamp(newVolume, 0, 100);
		AudioMaster.SetMusicVolume(newVolume / 100f);
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