package de.demoncore.scenes;

import java.awt.Color;
import java.awt.Font;

import de.demoncore.game.Animator;
import de.demoncore.game.SceneManager;
import de.demoncore.gameObjects.ParticleSystem;
import de.demoncore.gui.GUIAlignment;
import de.demoncore.gui.GUIButton;
import de.demoncore.gui.GUIButtonClickEvent;
import de.demoncore.gui.GUIText;
import de.demoncore.utils.Resources;
import de.demoncore.utils.Vector3;

public class MainMenu extends BaseScene {
	
	public MainMenu() {
		
		Animator anim = new Animator(1, 1, 1, 5, false);
		
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
		
		GUIButton localMultiplayer = new GUIButton(0, 0, 800, 75, "Zweispieler Modus", Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				SceneManager.LoadScene(new TwoPlayerPong());
			}
		});
		localMultiplayer.alignment = GUIAlignment.Center;
		AddObject(localMultiplayer);
		
		GUIButton storymode = new GUIButton(0, 100, 800, 75, "Storymode", Resources.uiFont.deriveFont(35F),  new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				SceneManager.LoadScene(null);
			}
		});
		storymode.alignment = GUIAlignment.Center;
		AddObject(storymode);
		
		GUIButton settings = new GUIButton(0, 200, 800, 75, "Einstellungen", Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				SceneManager.LoadScene(new SettingsMenu());
			}
		});
		settings.alignment = GUIAlignment.Center;
		AddObject(settings);
		
		GUIButton quit = new GUIButton(0, 300, 800, 75, "Verlassen", Resources.uiFont.deriveFont(35F),  new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				System.exit(0);
			}
		});
		quit.alignment = GUIAlignment.Center;
		AddObject(quit);
	}
}
