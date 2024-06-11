package de.demoncore.gameObjects;

import java.awt.Color;
import java.awt.Rectangle;

import de.demoncore.audio.AudioSource;
import de.demoncore.game.GameObject;
import de.demoncore.game.Logger;
import de.demoncore.game.PointSystem;
import de.demoncore.game.SceneManager;
import de.demoncore.utils.Resources;
import de.demoncore.utils.Vector3;

public class PongBall extends GameObject {

	private PongPlayer player1, player2;
	private Vector3 velocity = Vector3.one().multiply(10f);

	private AudioSource sfxSource;
	
	public PongBall(int posX, int posY, PongPlayer player1, PongPlayer player2) {
		super(posX, posY, 25, 25);

		this.player1 = player1;
		this.player2 = player2;
		collisionEnabled = false;

		sfxSource = new AudioSource(this).SetSpacial(false);
		SceneManager.GetActiveScene().AddObject(sfxSource);
		sfxSource.SetVolume(0.65f);
	}

	@Override
	public void OnDestroy() {
		super.OnDestroy();

		SceneManager.GetActiveScene().DestroyObject(sfxSource);
	}
	
	@Override
	public void Update() {
		super.Update();

		position = position.add(velocity);

		if(player1.GetBoundingBox().intersects(GetBoundingBox()) || player2.GetBoundingBox().intersects(GetBoundingBox())) {
			Logger.logMessage("Intersektion Ball mit Spieler", this);
			sfxSource.Play(Resources.pongPlayerHitPedal);
			
			velocity = velocity.reflect(getPlayerNormal());
		}
		
		if (isNotFullyIntersecting(GetBoundingBox(), SceneManager.GetActiveScene().GetRawCameraViewport())) {
			Logger.logMessage("Intersektion Ball mit Wand", this);
			sfxSource.Play(Resources.pongPlayerHitWall);
			
			velocity = velocity.reflect(getWallNormal());
		}

	}

	private Vector3 getPlayerNormal() {
		Vector3 normal = new Vector3(-1, 0);

		if (GetPosition().x	<= 0) normal = normal.multiply(-1f);
		return normal;

	}

	private boolean isNotFullyIntersecting(Rectangle r1, Rectangle r2) {
		// Check if rectangles intersect
		if (!r1.intersects(r2)) {
			return true;
		}

		// Check if one rectangle is fully contained within the other
		Rectangle intersection = r1.intersection(r2);
		if (intersection.equals(r1) || intersection.equals(r2)) {
			return false;  // Fully intersecting
		}

		return true;  // Not fully intersecting
	}

	private Vector3 getWallNormal() {
		Vector3 normal = new Vector3(0, 0);

		// Check collision with the left wall
		if (GetPosition().x - GetScale().x < -SceneManager.GetActiveScene().GetRawCameraViewport().width / 2) {
			normal.x = -1;
			collisonWithGoal(false);
		}

		// Check collision with the right wall
		if (GetPosition().x + GetScale().x > SceneManager.GetActiveScene().GetRawCameraViewport().width / 2) {
			normal.x = 1;
			collisonWithGoal(true);
		}

		// Check collision with the top wall
		if (GetPosition().y - GetScale().y < -SceneManager.GetActiveScene().GetRawCameraViewport().height / 2) {
			normal.y = 1;
		}

		// Check collision with the bottom wall
		if (GetPosition().y + GetScale().y > SceneManager.GetActiveScene().GetRawCameraViewport().height / 2) {
			normal.y = -1;
		}
		
		return normal;
	}
	
	
	private void collisonWithGoal(boolean isRightWall) {
		if (isRightWall == true) {
			Logger.logMessage("Rechte Wand getroffen", this);
			PointSystem.addPlayer1Points(1);
			
			spawnParticles();
		}
		else {
			Logger.logMessage("Linke Wand getroffen", this);
			PointSystem.addPlayer2Points(1);

			spawnParticles();
		}
		
		sfxSource.Play(Resources.pongGoal);
		SetPosition(Vector3.zero());
	}
    
	private void spawnParticles() {
		ParticleSystem system = new ParticleSystem((int)GetPosition().x, (int)GetPosition().y);
		system.emitLoop = false;
		system.emitChunk = 450;
		system.initialParticleSpeedMax = Vector3.one().add(new Vector3(0, -0.45f, 0));
		system.initialParticleSpeedMin = Vector3.one().multiply(-1f).add(new Vector3(0, -0.45f, 0));
		system.particleSpeedMultiplier = 35;
		system.particleColorFirst = Color.white;
		system.particleColorSecond = Color.white;
		system.initialParticleSize = 15;
		system.endParticleSize = 0;
		system.particleLifetime = 5;

		SceneManager.GetActiveScene().AddObject(system);
		SceneManager.GetActiveScene().ShakeCamera(35, 35, 35);
		system.Init();
	}
	
}
