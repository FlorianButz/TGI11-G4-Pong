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
		
		
		GUIText volumeText = new GUIText(-450, -100, "Lautstaerke: ", Resources.uiFont.deriveFont(50F), Color.white);
		volumeText.alignment = GUIAlignment.Center;
		volumeText.centerText = false;
		AddObject(volumeText);

		GUIText volumeDisplay = new GUIText(40, -100, ""+(int)Settings.GetVolume() + "%", Resources.uiFont.deriveFont(65F), Color.white);
		volumeDisplay.alignment = GUIAlignment.Center;
		AddObject(volumeDisplay);
		
		GUIButton volumeDown = new GUIButton(250, -100, 125, 75, "\\/", Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			
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
		
		GUIButton volumeUp = new GUIButton(400, -100, 125, 75, "/\\", Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			
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
		
		GUIText fullscreenText = new GUIText(-450, 0, "Vollbild (Neustart): ", Resources.uiFont.deriveFont(50F), Color.white);
		fullscreenText.alignment = GUIAlignment.Center;
		fullscreenText.centerText = false;
		AddObject(fullscreenText);
		
		String toggleFullscreenText = "Aus";
		if(Settings.GetFullscreen())
			toggleFullscreenText = "An";
		toggleFullscreen = new GUIButton(325, 0, 275, 75, toggleFullscreenText, Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				ToggleFullscreen();
			}
		});
		toggleFullscreen.alignment = GUIAlignment.Center;
		AddObject(toggleFullscreen);
		
		GUIText debugModeText = new GUIText(-450, 100, "Debug Modus: ", Resources.uiFont.deriveFont(50F), Color.white);
		debugModeText.alignment = GUIAlignment.Center;
		debugModeText.centerText = false;
		AddObject(debugModeText);
		
		String toggleDebugModeText = "Aus";
		if(Settings.GetDebugMode())
			toggleDebugModeText = "An";
		toggleDebugMode = new GUIButton(325, 100, 275, 75, toggleDebugModeText, Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				ToggleDebug();
			}
		});
		toggleDebugMode.alignment = GUIAlignment.Center;
		AddObject(toggleDebugMode);
		
		GUIButton back = new GUIButton(0, -125, 800, 75, "Save & Back", Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
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

		GUITheme.LoadButtonTheme(volumeDown, Theme.ButtonSecondary);
		GUITheme.LoadButtonTheme(volumeUp, Theme.ButtonSecondary);
		GUITheme.LoadButtonTheme(toggleDebugMode, Theme.ButtonSecondary);
		GUITheme.LoadButtonTheme(toggleFullscreen, Theme.ButtonSecondary);
	}
	
	GUIButton toggleFullscreen;
	GUIButton toggleDebugMode;
	
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
