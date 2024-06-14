package de.demoncore.gameObjects;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import de.demoncore.game.Language;
import de.demoncore.game.Settings;
import de.demoncore.game.Translation;
import de.demoncore.gui.GUIAlignment;
import de.demoncore.gui.GUIButton;
import de.demoncore.gui.GUIButtonClickEvent;
import de.demoncore.gui.GUIMenu;
import de.demoncore.gui.GUIObject;
import de.demoncore.gui.GUIText;
import de.demoncore.gui.GUITheme;
import de.demoncore.gui.GUITheme.Theme;
import de.demoncore.gui.GUIToggle;
import de.demoncore.gui.TextAlignment;
import de.demoncore.utils.Logger;
import de.demoncore.utils.Resources;

public class SettingsMenu extends GUIMenu {

	GUIText title;
	GUIText volumeText;
	GUIText volumeDisplay;
	
	GUIButton volumeDown;
	GUIButton volumeUp;
	
	GUIText musicVolumeText;
	GUIText musicVolumeDisplay;
	
	GUIButton musicVolumeDown;
	GUIButton musicVolumeUp;
	
	GUIText fullscreenText;
	GUIToggle toggleFullscreen;

	GUIText debugModeText;
	GUIToggle toggleDebugMode;
	
	GUIText languageText;
	GUIButton toggleLanguage;
	
	GUIButton togglePedalSpeed;
	
	public SettingsMenu() {
		backgroundColor = new Color(0, 0, 0, 0.975f);
	}
	
	float startSpeed = 0.025f;
	float speedIncrement = 0.0065f;
	
	@Override
	public List<GUIObject> addMenuContent() {
		
		List<GUIObject> content = new ArrayList<GUIObject>();

		float settingsTextSize = 25F;
		int buttonHeight = 50;
		int spacing = 15;
		int startPos = -75;
		
		title = new GUIText(0, 175, Translation.get("settings.settings"), Resources.dialogFont.deriveFont(Font.PLAIN, 125F), Color.WHITE);
		content.add(title);
		
		volumeText = new GUIText(-450, buttonHeight * -3 + spacing + startPos, Translation.get("settings.sfxVol"), Resources.uiFont.deriveFont(settingsTextSize), Color.white);
		volumeText.alignment = GUIAlignment.Center;
		volumeText.SetTextAlignment(TextAlignment.Left);
		content.add(volumeText);
		
		volumeDisplay = new GUIText(40, buttonHeight * -3 + spacing + startPos, Translation.literal(""+(int)Settings.getVolume() + "%"), Resources.uiFont.deriveFont(settingsTextSize + 15F), Color.white);
		volumeDisplay.alignment = GUIAlignment.Center;
		content.add(volumeDisplay);
		
		volumeDown = new GUIButton(250, buttonHeight * -3 + spacing + startPos, 125, buttonHeight, Translation.literal("\\/"), Resources.uiFont.deriveFont(settingsTextSize), new GUIButtonClickEvent() {
			
			float speed = 0f;
			
			@Override
			public void ButtonDown() {
				super.ButtonDown();
				speed = startSpeed;

				Settings.setVolume((float)Settings.getVolume() - 1);
				volumeDisplay.SetText(Translation.literal("" + (int)Settings.getVolume() + "%"));
			}
			
			@Override
			public void UpdateEvent() {
				super.UpdateEvent();
				
				speed += speedIncrement;
				
				if(isMouseDown) {
					Settings.setVolume((float)Settings.getVolume() - speed);
					volumeDisplay.SetText(Translation.literal("" + (int)Settings.getVolume() + "%"));
				}
			}
		});
		volumeDown.alignment = GUIAlignment.Center;
		content.add(volumeDown);
		
		volumeUp = new GUIButton(400, buttonHeight * -3 + spacing + startPos, 125, buttonHeight, Translation.literal("/\\"), Resources.uiFont.deriveFont(settingsTextSize), new GUIButtonClickEvent() {
			
			float speed = 0f;
			
			@Override
			public void ButtonDown() {
				super.ButtonDown();
				speed = startSpeed;

				Settings.setVolume((float)Settings.getVolume() + 1);
				volumeDisplay.SetText(Translation.literal("" + (int)Settings.getVolume() + "%"));
			}
			
			@Override
			public void UpdateEvent() {
				super.UpdateEvent();
				
				speed += speedIncrement;
				
				if(isMouseDown) {
					Settings.setVolume((float)Settings.getVolume() + speed);
					volumeDisplay.SetText(Translation.literal("" + (int)Settings.getVolume() + "%"));
				}
			}
		});
		volumeUp.alignment = GUIAlignment.Center;
		content.add(volumeUp);
		
		musicVolumeText = new GUIText(-450, buttonHeight * -2 + spacing * 2 + startPos, Translation.get("settings.musicVol"), Resources.uiFont.deriveFont(settingsTextSize), Color.white);
		musicVolumeText.alignment = GUIAlignment.Center;
		musicVolumeText.SetTextAlignment(TextAlignment.Left);
		content.add(musicVolumeText);
		
		musicVolumeDisplay = new GUIText(40, buttonHeight * -2 + spacing * 2 + startPos, Translation.literal(""+(int)Settings.getMusicVolume() + "%"), Resources.uiFont.deriveFont(settingsTextSize + 15F), Color.white);
		musicVolumeDisplay.alignment = GUIAlignment.Center;
		content.add(musicVolumeDisplay);
		
		musicVolumeDown = new GUIButton(250, buttonHeight * -2 + spacing * 2 + startPos, 125, buttonHeight, Translation.literal("\\/"), Resources.uiFont.deriveFont(settingsTextSize), new GUIButtonClickEvent() {
			
			float speed = 0f;
			
			@Override
			public void ButtonDown() {
				super.ButtonDown();
				speed = startSpeed;

				Settings.setMusicVolume((float)Settings.getMusicVolume() - 1);
				musicVolumeDisplay.SetText(Translation.literal("" + (int)Settings.getMusicVolume() + "%"));
			}
			
			@Override
			public void UpdateEvent() {
				super.UpdateEvent();
				
				speed += speedIncrement;
				
				if(isMouseDown) {
					Settings.setMusicVolume((float)Settings.getMusicVolume() - speed);
					musicVolumeDisplay.SetText(Translation.literal("" + (int)Settings.getMusicVolume() + "%"));
				}
			}
		});
		musicVolumeDown.alignment = GUIAlignment.Center;
		content.add(musicVolumeDown);
		
		musicVolumeUp = new GUIButton(400, buttonHeight * -2 + spacing * 2 + startPos, 125, buttonHeight, Translation.literal("/\\"), Resources.uiFont.deriveFont(settingsTextSize), new GUIButtonClickEvent() {
			
			float speed = 0f;
			
			@Override
			public void ButtonDown() {
				super.ButtonDown();
				speed = startSpeed;

				Settings.setMusicVolume((float)Settings.getMusicVolume() + 1);
				musicVolumeDisplay.SetText(Translation.literal("" + (int)Settings.getMusicVolume() + "%"));
			}
			
			@Override
			public void UpdateEvent() {
				super.UpdateEvent();
				
				speed += speedIncrement;
				
				if(isMouseDown) {
					Settings.setMusicVolume((float)Settings.getMusicVolume() + speed);
					musicVolumeDisplay.SetText(Translation.literal("" + (int)Settings.getMusicVolume() + "%"));
				}
			}
		});
		musicVolumeUp.alignment = GUIAlignment.Center;
		content.add(musicVolumeUp);
		
		fullscreenText = new GUIText(-450, buttonHeight * -1 + spacing * 3 + startPos, Translation.get("settings.fullscreen"), Resources.uiFont.deriveFont(settingsTextSize), Color.white);
		fullscreenText.alignment = GUIAlignment.Center;
		fullscreenText.SetTextAlignment(TextAlignment.Left);
		content.add(fullscreenText);
		
		toggleFullscreen = new GUIToggle(250, buttonHeight * -1 + spacing * 3 + startPos, 125, buttonHeight, new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();

				Settings.setFullscreen(!Settings.getFullscreen());
			}
		});
		toggleFullscreen.SetIsOn(Settings.getFullscreen());
		toggleFullscreen.alignment = GUIAlignment.Center;
		content.add(toggleFullscreen);
		
		debugModeText = new GUIText(-450, buttonHeight * -0 + spacing * 4 + startPos, Translation.get("settings.debug"), Resources.uiFont.deriveFont(settingsTextSize), Color.white);
		debugModeText.alignment = GUIAlignment.Center;
		debugModeText.SetTextAlignment(TextAlignment.Left);
		content.add(debugModeText);
		
		toggleDebugMode = new GUIToggle(250, buttonHeight * -0 + spacing * 4 + startPos, 125, buttonHeight, new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();

				Settings.setDebugMode(!Settings.getDebugMode());
			}
		});
		toggleDebugMode.SetIsOn(Settings.getDebugMode());
		toggleDebugMode.alignment = GUIAlignment.Center;
		content.add(toggleDebugMode);
		
		languageText = new GUIText(-450, buttonHeight * 1 + spacing * 5 + startPos, Translation.get("settings.lang"), Resources.uiFont.deriveFont(settingsTextSize), Color.white);
		languageText.alignment = GUIAlignment.Center;
		languageText.SetTextAlignment(TextAlignment.Left);
		content.add(languageText);
		
		String languageButtonText = Settings.getLang().toString(); // Ersetzen mit enum von Settings
		//if(Settings.GetDebugMode())
		//	toggleDebugModeText = "An";
		toggleLanguage = new GUIButton(325, buttonHeight * 1 + spacing * 5 + startPos, 275, buttonHeight, Translation.literal(languageButtonText), Resources.uiFont.deriveFont(settingsTextSize), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				
				SwitchLanguage();
			}
		});
		toggleLanguage.alignment = GUIAlignment.Center;
		content.add(toggleLanguage);
		
		GUIText cameraShakeText = new GUIText(-450, buttonHeight * 2 + spacing * 6 + startPos, Translation.get("settings.camerashake"), Resources.uiFont.deriveFont(settingsTextSize), Color.white);
		cameraShakeText.alignment = GUIAlignment.Center;
		cameraShakeText.SetTextAlignment(TextAlignment.Left);
		content.add(cameraShakeText);
		
		GUIToggle toggleCameraShake = new GUIToggle(250, buttonHeight * 2 + spacing * 6 + startPos, 125, buttonHeight, new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();

				Settings.setCameraShake(!Settings.isCameraShake());
			}
		});
		toggleCameraShake.SetIsOn(Settings.isCameraShake());
		toggleCameraShake.alignment = GUIAlignment.Center;
		content.add(toggleCameraShake);
		
		GUIText particleEffectsText = new GUIText(-450, buttonHeight * 3 + spacing * 7 + startPos, Translation.get("settings.particles"), Resources.uiFont.deriveFont(settingsTextSize), Color.white);
		particleEffectsText.alignment = GUIAlignment.Center;
		particleEffectsText.SetTextAlignment(TextAlignment.Left);
		content.add(particleEffectsText);
		
		GUIToggle toggleParticleEffectsText = new GUIToggle(250, buttonHeight * 3 + spacing * 7 + startPos, 125, buttonHeight, new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();

				Settings.setParticleEffects(!Settings.isParticleEffects());
			}
		});
		toggleParticleEffectsText.SetIsOn(Settings.isParticleEffects());
		toggleParticleEffectsText.alignment = GUIAlignment.Center;
		content.add(toggleParticleEffectsText);
		
		GUIText pedalSpeedText = new GUIText(-450, buttonHeight * 4 + spacing * 8 + startPos, Translation.get("settings.pedalspeed"), Resources.uiFont.deriveFont(settingsTextSize), Color.white);
		pedalSpeedText.alignment = GUIAlignment.Center;
		pedalSpeedText.SetTextAlignment(TextAlignment.Left);
		content.add(pedalSpeedText);
		
		togglePedalSpeed = new GUIButton(325, buttonHeight * 4 + spacing * 8 + startPos, 275, buttonHeight, Settings.isSlowPedals() ? Translation.get("settings.pedals.slow") : Translation.get("settings.pedals.fast"), Resources.uiFont.deriveFont(settingsTextSize), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				
				SwitchPedalSpeed();
			}
		});
		togglePedalSpeed.alignment = GUIAlignment.Center;
		content.add(togglePedalSpeed);
		
		GUIText smallGuiText = new GUIText(-450, buttonHeight * 5 + spacing * 9 + startPos, Translation.get("settings.smallgui"), Resources.uiFont.deriveFont(settingsTextSize), Color.white);
		smallGuiText.alignment = GUIAlignment.Center;
		smallGuiText.SetTextAlignment(TextAlignment.Left);
		content.add(smallGuiText);
		
		GUIToggle smallGuiToggle = new GUIToggle(250, buttonHeight * 5 + spacing * 9 + startPos, 125, buttonHeight, new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();

				Settings.setSmallGui(!Settings.isSmallGui());
			}
		});
		smallGuiToggle.SetIsOn(Settings.isSmallGui());
		smallGuiToggle.alignment = GUIAlignment.Center;
		content.add(smallGuiToggle);
		
		GUITheme.LoadTextTheme(debugModeText, Theme.TextSecondary);
		GUITheme.LoadTextTheme(fullscreenText, Theme.TextSecondary);
		GUITheme.LoadTextTheme(volumeDisplay, Theme.TextSecondary);
		GUITheme.LoadTextTheme(volumeText, Theme.TextSecondary);
		GUITheme.LoadTextTheme(musicVolumeDisplay, Theme.TextSecondary);
		GUITheme.LoadTextTheme(musicVolumeText, Theme.TextSecondary);
		GUITheme.LoadTextTheme(languageText, Theme.TextSecondary);
		GUITheme.LoadTextTheme(cameraShakeText, Theme.TextSecondary);
		GUITheme.LoadTextTheme(particleEffectsText, Theme.TextSecondary);
		GUITheme.LoadTextTheme(pedalSpeedText, Theme.TextSecondary);
		GUITheme.LoadTextTheme(smallGuiText, Theme.TextSecondary);

		GUITheme.LoadButtonTheme(volumeDown, Theme.ButtonSecondary);
		GUITheme.LoadButtonTheme(volumeUp, Theme.ButtonSecondary);
		GUITheme.LoadButtonTheme(musicVolumeDown, Theme.ButtonSecondary);
		GUITheme.LoadButtonTheme(musicVolumeUp, Theme.ButtonSecondary);
		GUITheme.LoadButtonTheme(toggleDebugMode, Theme.ButtonSecondary);
		GUITheme.LoadButtonTheme(toggleFullscreen, Theme.ButtonSecondary);
		GUITheme.LoadButtonTheme(toggleLanguage, Theme.ButtonSecondary);
		GUITheme.LoadButtonTheme(toggleCameraShake, Theme.ButtonSecondary);
		GUITheme.LoadButtonTheme(toggleParticleEffectsText, Theme.ButtonSecondary);
		GUITheme.LoadButtonTheme(togglePedalSpeed, Theme.ButtonSecondary);
		GUITheme.LoadButtonTheme(smallGuiToggle, Theme.ButtonSecondary);
		
		return content;
	}

	void SwitchLanguage() {

		if(Settings.getLang() == Language.Deutsch) Settings.setLang(Language.English);
		else if(Settings.getLang() == Language.English) Settings.setLang(Language.Deutsch);
		
		toggleLanguage.SetText(Translation.literal(Settings.getLang().toString()));
	}
	
	void SwitchPedalSpeed() {

		Settings.setSlowPedals(!Settings.isSlowPedals());
		togglePedalSpeed.SetText(Settings.isSlowPedals() ? Translation.get("settings.pedals.slow") : Translation.get("settings.pedals.fast"));
	}
	
	@Override
	protected GUIButton createBackButton() {
		GUIButton back = new GUIButton(0, -125, 800, 50, Translation.get("settings.saveandback"), Resources.uiFont.deriveFont(25F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				HideMenu();
				Settings.SaveAllSettings();
			}
		});
		back.alignment = GUIAlignment.DownMiddle;
		return back;
	}
}
