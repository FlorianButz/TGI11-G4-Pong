package de.demoncore.scenes;

import java.awt.Color;
import java.awt.Font;

import de.demoncore.game.ParticleSystem;
import de.demoncore.gui.GUIAlignment;
import de.demoncore.gui.GUIButton;
import de.demoncore.gui.GUIText;
import de.demoncore.main.Main;
import de.demoncore.utils.Vector3;

public class MainMenu extends BaseScene {
	
	public MainMenu() {
		
		ParticleSystem bgSys = new ParticleSystem(0, -550);
		bgSys.particleSpawnArea = new Vector3(1000, 0);
		bgSys.emitLoop = true;
		bgSys.emitPause = 2;
		bgSys.particleLifetime += 7500;
		bgSys.initialParticleSpeedMax = new Vector3(0, 1);
		bgSys.initialParticleSpeedMin = new Vector3(0, 0.25f);
		bgSys.Init();
		AddObject(bgSys);
		
		GUIText title = new GUIText(0, 125, "Pong auf Crack", Main.dialogFont.deriveFont(Font.PLAIN, 125F), Color.WHITE);
		AddObject(title);
		
		GUIButton singleplayer = new GUIButton(0, 0, 800, 75, "Singleplayer", Main.uiFont.deriveFont(35F), Color.white);
		singleplayer.alignment = GUIAlignment.Center;
		AddObject(singleplayer);
		
		GUIButton multiplayer = new GUIButton(0, 100, 800, 75, "Multiplayer", Main.uiFont.deriveFont(35F), Color.white);
		multiplayer.alignment = GUIAlignment.Center;
		AddObject(multiplayer);
		
		GUIButton quit = new GUIButton(0, 200, 800, 75, "Quit", Main.uiFont.deriveFont(35F), Color.white);
		quit.alignment = GUIAlignment.Center;
		AddObject(quit);
	}
}
