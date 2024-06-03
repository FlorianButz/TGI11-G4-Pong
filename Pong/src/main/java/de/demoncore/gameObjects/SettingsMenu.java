package de.demoncore.gameObjects;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import de.demoncore.game.SceneManager;
import de.demoncore.game.Settings;
import de.demoncore.gui.GUIAlignment;
import de.demoncore.gui.GUIButton;
import de.demoncore.gui.GUIButtonClickEvent;
import de.demoncore.gui.GUIMenu;
import de.demoncore.gui.GUIObject;
import de.demoncore.gui.GUIText;
import de.demoncore.gui.GUITheme;
import de.demoncore.gui.GUITheme.Theme;
import de.demoncore.gui.TextAlignment;
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
	GUIButton toggleFullscreen;

	GUIText debugModeText;
	GUIButton toggleDebugMode;
	
	GUIText languageText;
	GUIButton toggleLanguage;
	
	public SettingsMenu() {
		backgroundColor = new Color(0, 0, 0, 0.975f);
	}
	
	float startSpeed = 0.025f;
	float speedIncrement = 0.0065f;
	
	@Override
	public List<GUIObject> AddMenuContent() {
		
		List<GUIObject> content = new ArrayList<GUIObject>();

		float settingsTextSize = 25F;
		int buttonHeight = 50;
		int spacing = 15;
		int startPos = 0;
		
		title = new GUIText(0, 175, "Einstellungen", Resources.dialogFont.deriveFont(Font.PLAIN, 125F), Color.WHITE);
		content.add(title);
		
		volumeText = new GUIText(-450, buttonHeight * -3 + spacing + startPos, "Lautstaerke: ", Resources.uiFont.deriveFont(settingsTextSize), Color.white);
		volumeText.alignment = GUIAlignment.Center;
		volumeText.SetTextAlignment(TextAlignment.Left);
		content.add(volumeText);
		
		volumeDisplay = new GUIText(40, buttonHeight * -3 + spacing + startPos, ""+(int)Settings.GetVolume() + "%", Resources.uiFont.deriveFont(settingsTextSize + 15F), Color.white);
		volumeDisplay.alignment = GUIAlignment.Center;
		content.add(volumeDisplay);
		
		volumeDown = new GUIButton(250, buttonHeight * -3 + spacing + startPos, 125, buttonHeight, "\\/", Resources.uiFont.deriveFont(settingsTextSize), new GUIButtonClickEvent() {
			
			float speed = 0f;
			
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
		content.add(volumeDown);
		
		volumeUp = new GUIButton(400, buttonHeight * -3 + spacing + startPos, 125, buttonHeight, "/\\", Resources.uiFont.deriveFont(settingsTextSize), new GUIButtonClickEvent() {
			
			float speed = 0f;
			
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
		content.add(volumeUp);
		
		musicVolumeText = new GUIText(-450, buttonHeight * -2 + spacing * 2 + startPos, "Musik Lautstaerke: ", Resources.uiFont.deriveFont(settingsTextSize), Color.white);
		musicVolumeText.alignment = GUIAlignment.Center;
		musicVolumeText.SetTextAlignment(TextAlignment.Left);
		content.add(musicVolumeText);
		
		musicVolumeDisplay = new GUIText(40, buttonHeight * -2 + spacing * 2 + startPos, ""+(int)Settings.GetMusicVolume() + "%", Resources.uiFont.deriveFont(settingsTextSize + 15F), Color.white);
		musicVolumeDisplay.alignment = GUIAlignment.Center;
		content.add(musicVolumeDisplay);
		
		musicVolumeDown = new GUIButton(250, buttonHeight * -2 + spacing * 2 + startPos, 125, buttonHeight, "\\/", Resources.uiFont.deriveFont(settingsTextSize), new GUIButtonClickEvent() {
			
			float speed = 0f;
			
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
		content.add(musicVolumeDown);
		
		musicVolumeUp = new GUIButton(400, buttonHeight * -2 + spacing * 2 + startPos, 125, buttonHeight, "/\\", Resources.uiFont.deriveFont(settingsTextSize), new GUIButtonClickEvent() {
			
			float speed = 0f;
			
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
		content.add(musicVolumeUp);
		
		fullscreenText = new GUIText(-450, buttonHeight * -1 + spacing * 3 + startPos, "Vollbild (Neustart): ", Resources.uiFont.deriveFont(settingsTextSize), Color.white);
		fullscreenText.alignment = GUIAlignment.Center;
		fullscreenText.SetTextAlignment(TextAlignment.Left);
		content.add(fullscreenText);
		
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
		content.add(toggleFullscreen);
		
		debugModeText = new GUIText(-450, buttonHeight * -0 + spacing * 4 + startPos, "Debug Modus: ", Resources.uiFont.deriveFont(settingsTextSize), Color.white);
		debugModeText.alignment = GUIAlignment.Center;
		debugModeText.SetTextAlignment(TextAlignment.Left);
		content.add(debugModeText);
		
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
		content.add(toggleDebugMode);
		
		languageText = new GUIText(-450, buttonHeight * 1 + spacing * 5 + startPos, "Sprache: ", Resources.uiFont.deriveFont(settingsTextSize), Color.white);
		languageText.alignment = GUIAlignment.Center;
		languageText.SetTextAlignment(TextAlignment.Left);
		content.add(languageText);
		
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
		content.add(toggleLanguage);
		
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
		
		return content;
	}

	@Override
	protected GUIButton CreateBackButton() {
		GUIButton back = new GUIButton(0, -125, 800, 50, "Save & Back", Resources.uiFont.deriveFont(25F), new GUIButtonClickEvent() {
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