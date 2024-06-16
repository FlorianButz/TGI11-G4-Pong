package de.demoncore.game;

import java.io.Serializable;

public class SettingsSave implements Serializable {

	private static final long serialVersionUID = 1L;
	public int masterVolume = 85;
	public int musicVolume = 85;
	public boolean fullscreen = false;

	public boolean debugMode = false;
	public boolean slowPedal = false;
	public boolean particleEffects = true;
	public boolean cameraShake = true;
	public boolean smallGui = false;
	
	public Language language;
	
}