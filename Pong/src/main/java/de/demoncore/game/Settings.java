package de.demoncore.game;

import de.demoncore.audio.AudioMaster;
import de.demoncore.gui.Gui;
import de.demoncore.utils.GameMath;
import de.demoncore.utils.Logger;

public class Settings {

	// EINSTELLUNGEN SPEICHERN UND LADEN
	
	public static void LoadAllSettings() {

		// Gespeicherte datei Laden

		Logger.logInfo("Einstellungen werden geladen...");
		
		SettingsSave deserializedSettings = SaveManager.LoadSave("settings.g4pong");
		if(deserializedSettings == null) deserializedSettings = new SettingsSave();
		
		setMusicVolume(deserializedSettings.musicVolume);
		setVolume(deserializedSettings.masterVolume);
		setFullscreen(deserializedSettings.fullscreen);
		setDebugMode(deserializedSettings.debugMode);
		setLang(deserializedSettings.language);
		setParticleEffects(deserializedSettings.particleEffects);
		setSlowPedals(deserializedSettings.slowPedal);
		setCameraShake(deserializedSettings.cameraShake);
		setSmallGui(deserializedSettings.smallGui);
		
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
		classToSave.cameraShake = cameraShake;
		classToSave.particleEffects = particleEffects;
		classToSave.slowPedal = slowPedals;
		classToSave.smallGui = smallGui;

		// Speicherdatei erstellen
		
		SaveManager.SaveToFile("settings.g4pong", classToSave);
	}

	// EINSTELLUNGEN

	private static float masterVolume;
	private static float musicVolume;
	private static boolean fullscreen;

	private static float uiScale = 1f;
	private static float smallUiScale = 0.5f;
	private static float uiScaleMultiplier = 0.85f;

	private static boolean debugMode;
	private static boolean particleEffects;
	private static boolean cameraShake;
	private static boolean slowPedals;
	private static boolean smallGui;
	
	public static boolean isSmallGui() {
		return smallGui;
	}

	public static void setSmallGui(boolean isOn) {
		smallGui = isOn;
	}

	private static Language language = Language.Deutsch;
	
	// SET / GET METHODEN
	
	public static float getVolume() {
		return masterVolume;
	}

	public static void setLang(Language lang) {
		if(lang == null) return;
		language = lang;
		Translation.activeLanguage = language;
	}
	
	public static Language getLang() {
		return language;
	}
	
	public static void setUIScale(float scale) {
		uiScale = scale * uiScaleMultiplier;
		smallUiScale = (scale / 2f) * uiScaleMultiplier;
	}
	
	public static float getUIScale() {
		return smallGui ? smallUiScale : uiScale;
	}
	
	public static void setVolume(float newVolume) {
		masterVolume = GameMath.clamp(newVolume, 0, 100);
		AudioMaster.SetMasterVolume(newVolume / 100f);
	}
	
	public static float getMusicVolume() {
		return musicVolume;
	}

	public static void setMusicVolume(float newVolume) {
		musicVolume = GameMath.clamp(newVolume, 0, 100);
		AudioMaster.SetMusicVolume(newVolume / 100f);
	}
	
	public static boolean getFullscreen() {
		return fullscreen;
	}

	public static void setFullscreen(boolean isOn) {
		fullscreen = isOn;
	}
	
	public static boolean getDebugMode() {
		return debugMode;
	}

	public static void setDebugMode(boolean isOn) {
		debugMode = isOn;
	}

	public static boolean isParticleEffects() {
		return particleEffects;
	}

	public static void setParticleEffects(boolean isOn) {
		particleEffects = isOn;
	}

	public static boolean isCameraShake() {
		return cameraShake;
	}

	public static void setCameraShake(boolean isOn) {
		cameraShake = isOn;
	}

	public static boolean isSlowPedals() {
		return slowPedals;
	}

	public static void setSlowPedals(boolean isOn) {
		slowPedals = isOn;
	}
}