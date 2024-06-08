package de.demoncore.gameObjects;

import java.awt.Color;

import de.demoncore.actions.KeyHandler;
import de.demoncore.game.GameLogic;
import de.demoncore.game.GameObject;
import de.demoncore.game.SceneManager;
import de.demoncore.gui.GUIAlignment;
import de.demoncore.gui.GUIHealthbar;
import de.demoncore.utils.GameMath;
import de.demoncore.utils.Vector3;

public class StorymodePlayer extends RigidBody {

	public float playerAcceleration = 7.75f; // Die Geschwindigkeitszunahme vom Spieler
	
	ParticleSystem trail;
	
	public GUIHealthbar health;
	
	public StorymodePlayer(int x, int y) {
		super(x, y, 20, 20);
	
		health = new GUIHealthbar(65, 85, 45, 6);
		health.alignment = GUIAlignment.TopLeft;
	
		SceneManager.GetActiveScene().AddObject(health);
	}
	
	@Override
	public void Update() {
		
		if(GameLogic.IsGamePaused()) return;
		
		// Partikel effekt fuer den spieler
		if(trail == null) {
			trail = new ParticleSystem((int)this.position.x, (int)this.position.y);
			
			trail.emitLoop = true;
			trail.particleSpawnArea = new Vector3(10, 10, 10);
			trail.particleGravity = 0;
			trail.initialParticleEmitCount = 0;
			trail.initialParticleEmitCountRandom = 0;
			trail.emitPause = 5;
			
			trail.emitChunk = 2;

			trail.particleColorFirst = new Color(1, 1, 1, 0.2f);
			trail.particleColorSecond = new Color(1, 1, 1, 0.2f);
			
			trail.initialParticleSpeedMin = Vector3.one().multiply(-1);
			trail.initialParticleSpeedMax = Vector3.one();
			trail.particleSpeedMultiplier = 0.25f;
			
			trail.particleLifetime = 25;
			trail.Init();
			SceneManager.GetActiveScene().AddObject(trail);
		}
		
		if(Math.abs(velocity.Magnitude()) >= 0.1) trail.emitLoop = true;
		else trail.emitLoop = false;
		
		AddForce(KeyHandler.playerInput.Normalized().multiply(playerAcceleration));
		
		trail.SetPosition(this.position);
		
		super.Update();
	}
	
}
