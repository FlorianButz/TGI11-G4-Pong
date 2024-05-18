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

		GUIText title = new GUIText(0, 175, "Pong auf Crack", Resources.dialogFont.deriveFont(Font.PLAIN, 125F), Color.WHITE);
		AddObject(title);
		
		
		GUIText volumeText = new GUIText(-250, -100, "Lautstaerke: ", Resources.uiFont.deriveFont(50F), Color.white);
		volumeText.alignment = GUIAlignment.Center;
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
	}
	
}
