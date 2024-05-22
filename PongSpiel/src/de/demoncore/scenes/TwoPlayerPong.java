package de.demoncore.scenes;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;

import de.demoncore.actions.GameActionListener;
import de.demoncore.actions.KeyHandler;
import de.demoncore.game.GameLogic;
import de.demoncore.game.GameObject;
import de.demoncore.gameObjects.BeweglichesRechteck;
import de.demoncore.gameObjects.ParticleSystem;
import de.demoncore.gameObjects.PauseMenu;
import de.demoncore.gameObjects.PongPlayer;
import de.demoncore.gui.GUIText;
import de.demoncore.utils.Resources;
import de.demoncore.utils.Vector3;

public class TwoPlayerPong extends BaseScene {

	BeweglichesRechteck beispielObjekt1;
	PongPlayer player1;
	PongPlayer player2;

	@Override
	public void InitializeScene() {
		super.InitializeScene();

		// Objekte im Spiel:
		beispielObjekt1 = new BeweglichesRechteck(50, 100, 20, 20);
		AddObject(beispielObjekt1);
		beispielObjekt1.richtung = 0; // Startrichtung

		AddObject(new GameObject(-200, 0, 25, 500));

		player1 = new PongPlayer(0, 0);
		AddObject(player1);

		//player2 = new PongPlayer(150, 350);
		//AddObject(player2);

		ParticleSystem p = new ParticleSystem(0, 0);

		p.initialParticleSpeedMax = new Vector3(5, -10);
		p.initialParticleSpeedMin = new Vector3(-5, 0);
		p.particleGravity = 0.05f;

		p.initialParticleSize = 20;
		p.initialParticleSizeRandom = 10;

		p.emitLoop = true;

		p.emitPause = 150;
		p.emitChunk = 25;

		p.particleColorFirst = Color.red;
		p.particleColorSecond = Color.yellow;

		p.particleColorEnd = Color.black;

		p.particleLifetime = 250;
		p.particleLifetimeRandom = 0;

		p.Init();
		AddObject(p);

		t = new GUIText(0, 150, "Hello, World!", Resources.dialogFont.deriveFont(Font.PLAIN, 65F), Color.WHITE);
		t.SetText("2dq2");
		AddObject(t);

		pauseMenu = new PauseMenu();
		AddObject(pauseMenu);
	}

	GUIText t;
	PauseMenu pauseMenu;

	@Override
	public void UpdateScene() {
		super.UpdateScene();

		// Laufende Ausführungen im Spiel:
		beispielObjekt1.automatischeKreisbewegung();

		t.SetText((int)GameLogic.GetInstance().GetGameTime() + "");

		cameraPosition = Vector3.Lerp(cameraPosition, player1.GetPosition(), 0.025f);
	}

}
