package de.demoncore.scenes;

import java.awt.Color;
import java.awt.Font;

import de.demoncore.game.SceneManager;
import de.demoncore.game.Settings;
import de.demoncore.gameObjects.ParticleSystem;
import de.demoncore.gui.GUIAlignment;
import de.demoncore.gui.GUIButton;
import de.demoncore.gui.GUIButtonClickEvent;
import de.demoncore.gui.GUIText;
import de.demoncore.gui.GUITheme;
import de.demoncore.gui.GUITheme.Theme;
import de.demoncore.gui.Gui;
import de.demoncore.utils.Resources;
import de.demoncore.utils.Vector3;

public class SettingsMenu extends BaseScene {
	
	public SettingsMenu() {

		ParticleSystem bgSys = new ParticleSystem(0, 0);
		bgSys.particleSpawnArea = new Vector3(1000, 1000);
		bgSys.emitLoop = true;
		bgSys.particleColorFirst = new Color(1, 1, 1, 0.085f);
		bgSys.particleColorSecond = new Color(1, 1, 1, 0.04f);
		bgSys.emitPause = 10;
		bgSys.initialParticleSize = 15;
		bgSys.particleLifetime += 2500;
		bgSys.initialParticleSpeedMax = new Vector3(0.25f, 0.25f);
		bgSys.initialParticleSpeedMin = new Vector3(-0.25f, -0.25f);
		bgSys.particleGravity = 0;
		bgSys.endParticleSize = 0;
		bgSys.Init();
		AddObject(bgSys);

		GUIText title = new GUIText(0, 175, "Einstellungen", Resources.dialogFont.deriveFont(Font.PLAIN, 125F), Color.WHITE);
		AddObject(title);
		
		float settingsTextSize = 25F;
		int buttonHeight = 50;
		int spacing = 15;
		int startPos = 0;
		
		GUIText volumeText = new GUIText(-450, buttonHeight * -3 + spacing + startPos, "Lautstaerke: ", Resources.uiFont.deriveFont(settingsTextSize), Color.white);
		volumeText.alignment = GUIAlignment.Center;
		volumeText.centerText = false;
		AddObject(volumeText);

		GUIText volumeDisplay = new GUIText(40, buttonHeight * -3 + spacing + startPos, ""+(int)Settings.GetVolume() + "%", Resources.uiFont.deriveFont(settingsTextSize + 15F), Color.white);
		volumeDisplay.alignment = GUIAlignment.Center;
		AddObject(volumeDisplay);
		
		GUIButton volumeDown = new GUIButton(250, buttonHeight * -3 + spacing + startPos, 125, buttonHeight, "\\/", Resources.uiFont.deriveFont(settingsTextSize), new GUIButtonClickEvent() {
			
			float startSpeed = 0.025f;
			float speed = 0f;
			float speedIncrement = 0.0005f;
			
			@Override
			public void ButtonDown() {
				super.ButtonDown();
				speed = startSpeed;

				Settings.SetVolume((float)Settings.GetVolume() - 1);
				volumeDisplay.SetText("" + (int)Settings.GetVolume() + "%");
			}
			
			@Override
			public void UpdateEvent() {
				super.UpdateEvent();
				
				speed += speedIncrement;
				
				if(isMouseDown) {
					Settings.SetVolume((float)Settings.GetVolume() - speed);
					volumeDisplay.SetText("" + (int)Settings.GetVolume() + "%");
				}
			}
		});
		volumeDown.alignment = GUIAlignment.Center;
		AddObject(volumeDown);
		
		GUIButton volumeUp = new GUIButton(400, buttonHeight * -3 + spacing + startPos, 125, buttonHeight, "/\\", Resources.uiFont.deriveFont(settingsTextSize), new GUIButtonClickEvent() {
			
			float startSpeed = 0.025f;
			float speed = 0f;
			float speedIncrement = 0.0005f;
			
			@Override
			public void ButtonDown() {
				super.ButtonDown();
				speed = startSpeed;

				Settings.SetVolume((float)Settings.GetVolume() + 1);
				volumeDisplay.SetText("" + (int)Settings.GetVolume() + "%");
			}
			
			@Override
			public void UpdateEvent() {
				super.UpdateEvent();
				
				speed += speedIncrement;
				
				if(isMouseDown) {
					Settings.SetVolume((float)Settings.GetVolume() + speed);
					volumeDisplay.SetText("" + (int)Settings.GetVolume() + "%");
				}
			}
		});
		volumeUp.alignment = GUIAlignment.Center;
		AddObject(volumeUp);

		GUIText musicVolumeText = new GUIText(-450, buttonHeight * -2 + spacing * 2 + startPos, "Musik Lautstaerke: ", Resources.uiFont.deriveFont(settingsTextSize), Color.white);
		musicVolumeText.alignment = GUIAlignment.Center;
		musicVolumeText.centerText = false;
		AddObject(musicVolumeText);

		GUIText musicVolumeDisplay = new GUIText(40, buttonHeight * -2 + spacing * 2 + startPos, ""+(int)Settings.GetMusicVolume() + "%", Resources.uiFont.deriveFont(settingsTextSize + 15F), Color.white);
		musicVolumeDisplay.alignment = GUIAlignment.Center;
		AddObject(musicVolumeDisplay);
		
		GUIButton musicVolumeDown = new GUIButton(250, buttonHeight * -2 + spacing * 2 + startPos, 125, buttonHeight, "\\/", Resources.uiFont.deriveFont(settingsTextSize), new GUIButtonClickEvent() {
			
			float startSpeed = 0.025f;
			float speed = 0f;
			float speedIncrement = 0.0005f;
			
			@Override
			public void ButtonDown() {
				super.ButtonDown();
				speed = startSpeed;

				Settings.SetMusicVolume((float)Settings.GetMusicVolume() - 1);
				musicVolumeDisplay.SetText("" + (int)Settings.GetMusicVolume() + "%");
			}
			
			@Override
			public void UpdateEvent() {
				super.UpdateEvent();
				
				speed += speedIncrement;
				
				if(isMouseDown) {
					Settings.SetMusicVolume((float)Settings.GetMusicVolume() - speed);
					musicVolumeDisplay.SetText("" + (int)Settings.GetMusicVolume() + "%");
				}
			}
		});
		musicVolumeDown.alignment = GUIAlignment.Center;
		AddObject(musicVolumeDown);
		
		GUIButton musicVolumeUp = new GUIButton(400, buttonHeight * -2 + spacing * 2 + startPos, 125, buttonHeight, "/\\", Resources.uiFont.deriveFont(settingsTextSize), new GUIButtonClickEvent() {
			
			float startSpeed = 0.025f;
			float speed = 0f;
			float speedIncrement = 0.0005f;
			
			@Override
			public void ButtonDown() {
				super.ButtonDown();
				speed = startSpeed;

				Settings.SetMusicVolume((float)Settings.GetMusicVolume() + 1);
				musicVolumeDisplay.SetText("" + (int)Settings.GetMusicVolume() + "%");
			}
			
			@Override
			public void UpdateEvent() {
				super.UpdateEvent();
				
				speed += speedIncrement;
				
				if(isMouseDown) {
					Settings.SetMusicVolume((float)Settings.GetMusicVolume() + speed);
					musicVolumeDisplay.SetText("" + (int)Settings.GetMusicVolume() + "%");
				}
			}
		});
		musicVolumeUp.alignment = GUIAlignment.Center;
		AddObject(musicVolumeUp);
		
		
		GUIText fullscreenText = new GUIText(-450, buttonHeight * -1 + spacing * 3 + startPos, "Vollbild (Neustart): ", Resources.uiFont.deriveFont(settingsTextSize), Color.white);
		fullscreenText.alignment = GUIAlignment.Center;
		fullscreenText.centerText = false;
		AddObject(fullscreenText);
		
		String toggleFullscreenText = "Aus";
		if(Settings.GetFullscreen())
			toggleFullscreenText = "An";
		toggleFullscreen = new GUIButton(325, buttonHeight * -1 + spacing * 3 + startPos, 275, buttonHeight, toggleFullscreenText, Resources.uiFont.deriveFont(settingsTextSize), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				ToggleFullscreen();
			}
		});
		toggleFullscreen.alignment = GUIAlignment.Center;
		AddObject(toggleFullscreen);
		
		GUIText debugModeText = new GUIText(-450, buttonHeight * -0 + spacing * 4 + startPos, "Debug Modus: ", Resources.uiFont.deriveFont(settingsTextSize), Color.white);
		debugModeText.alignment = GUIAlignment.Center;
		debugModeText.centerText = false;
		AddObject(debugModeText);
		
		String toggleDebugModeText = "Aus";
		if(Settings.GetDebugMode())
			toggleDebugModeText = "An";
		toggleDebugMode = new GUIButton(325, buttonHeight * -0 + spacing * 4 + startPos, 275, buttonHeight, toggleDebugModeText, Resources.uiFont.deriveFont(settingsTextSize), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				ToggleDebug();
			}
		});
		toggleDebugMode.alignment = GUIAlignment.Center;
		AddObject(toggleDebugMode);
		
		GUIText languageText = new GUIText(-450, buttonHeight * 1 + spacing * 5 + startPos, "Sprache: ", Resources.uiFont.deriveFont(settingsTextSize), Color.white);
		languageText.alignment = GUIAlignment.Center;
		languageText.centerText = false;
		AddObject(languageText);
		
		String languageButtonText = "Deutsch"; // Ersetzen mit enum von Settings
		//if(Settings.GetDebugMode())
		//	toggleDebugModeText = "An";
		toggleLanguage = new GUIButton(325, buttonHeight * 1 + spacing * 5 + startPos, 275, buttonHeight, languageButtonText, Resources.uiFont.deriveFont(settingsTextSize), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				
			}
		});
		toggleLanguage.alignment = GUIAlignment.Center;
		AddObject(toggleLanguage);
		
		
		GUIButton back = new GUIButton(0, -125, 800, buttonHeight, "Save & Back", Resources.uiFont.deriveFont(settingsTextSize), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				SceneManager.LoadScene(new MainMenu());
				Settings.SaveAllSettings();
			}
		});
		back.alignment = GUIAlignment.DownMiddle;
		AddObject(back);

		GUITheme.LoadTextTheme(debugModeText, Theme.TextSecondary);
		GUITheme.LoadTextTheme(fullscreenText, Theme.TextSecondary);
		GUITheme.LoadTextTheme(volumeDisplay, Theme.TextSecondary);
		GUITheme.LoadTextTheme(volumeText, Theme.TextSecondary);
		GUITheme.LoadTextTheme(musicVolumeDisplay, Theme.TextSecondary);
		GUITheme.LoadTextTheme(musicVolumeText, Theme.TextSecondary);
		GUITheme.LoadTextTheme(languageText, Theme.TextSecondary);

		GUITheme.LoadButtonTheme(volumeDown, Theme.ButtonSecondary);
		GUITheme.LoadButtonTheme(volumeUp, Theme.ButtonSecondary);
		GUITheme.LoadButtonTheme(musicVolumeDown, Theme.ButtonSecondary);
		GUITheme.LoadButtonTheme(musicVolumeUp, Theme.ButtonSecondary);
		GUITheme.LoadButtonTheme(toggleDebugMode, Theme.ButtonSecondary);
		GUITheme.LoadButtonTheme(toggleFullscreen, Theme.ButtonSecondary);
		GUITheme.LoadButtonTheme(toggleLanguage, Theme.ButtonSecondary);
	}
	
	GUIButton toggleFullscreen;
	GUIButton toggleDebugMode;
	GUIButton toggleLanguage;
	
	void ToggleFullscreen() {
		Settings.SetFullscreen(!Settings.GetFullscreen());
		String toggleFullscreenTextNew = "Aus";
		
		if(Settings.GetFullscreen()) 
			toggleFullscreenTextNew = "An";
		
		toggleFullscreen.SetText(toggleFullscreenTextNew);
	}
	
	void ToggleDebug() {
		Settings.SetDebugMode(!Settings.GetDebugMode());
		String toggleDebugModeTextNew = "Aus";
		
		if(Settings.GetDebugMode()) 
			toggleDebugModeTextNew  = "An";
		
		toggleDebugMode.SetText(toggleDebugModeTextNew );
	}
	
}
