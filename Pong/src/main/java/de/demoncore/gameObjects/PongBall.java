package de.demoncore.gameObjects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.demoncore.audio.AudioSource;
import de.demoncore.game.GameLogic;
import de.demoncore.game.GameObject;
import de.demoncore.game.PointSystem;
import de.demoncore.game.PongSpawnEffect;
import de.demoncore.game.SceneManager;
import de.demoncore.utils.GameMath;
import de.demoncore.utils.Logger;
import de.demoncore.utils.Resources;
import de.demoncore.utils.Vector3;
import de.demoncore.scenes.shop.BallColors;

public class PongBall extends GameObject {

	private PongPlayer player1, player2;
	public float speed = 9f;
	private Vector3 velocity = Vector3.one();

	private AudioSource sfxSource;

	private static PongBall instance;

	boolean isIntersectionWithPlayer = false;
	boolean isMoving = false;
	
	BallColors Farbe = new BallColors();

	List<Vector3> positions = new ArrayList<Vector3>(Collections.nCopies(10, Vector3.zero()));

	public static PongBall getInstance() {
		return instance;
	}

	public PongBall(int posX, int posY, PongPlayer player1, PongPlayer player2) {
		super(posX, posY, 25, 25);

		instance = this;

		this.player1 = player1;
		this.player2 = player2;
		collisionEnabled = false;

		sfxSource = new AudioSource(this).setSpacial(false);
		SceneManager.getActiveScene().addObject(sfxSource);
		sfxSource.SetVolume(0.65f);
		
		PongSpawnEffect.callEffect();
		moveTimer();
	}

	void moveTimer() {
		isMoving = false;

		Thread timer = new Thread("ball.timer") {
			public void run() {
				try {
					Thread.currentThread().sleep(3000);
				} catch (InterruptedException e) {
					Logger.logError("Ball sleep Fehler", e);
				}
				
				isMoving = true;
			};
		};
		timer.start();
	}

	@Override
	public void draw(Graphics2D g2d, int screenWidth, int screenHeight) {
		Vector3 worldPos = getPosition();
		g2d.setColor(color);
		g2d.fillRect((int)worldPos.x, (int)worldPos.y, (int)size.x, (int)size.y);

		if(isMoving) {
			g2d.setColor(new Color(1, 1, 1, 0.25f));
			g2d.fillRect((int)positions.get(2).x, (int)positions.get(2).y, (int)size.x, (int)size.y);
			g2d.setColor(new Color(1, 1, 1, 0.05f));
			g2d.fillRect((int)positions.get(4).x, (int)positions.get(4).y, (int)size.x, (int)size.y);
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		SceneManager.getActiveScene().destroyObject(sfxSource);
	}

	@Override
	public void update() {
		super.update();
		
		color = /*Farbe.Farbe();*/ GameMath.lerpColor(Color.pink, Color.DARK_GRAY, (float) Math.sin(GameLogic.getInstance().getGameTime() * 1));

		if(GameLogic.IsGamePaused() || !isMoving) return;
			
		if (speed < 30f) {
			speed = speed + 0.01f;	  //Linear
			//speed = speed * 1.001f; //Exponentiel
		}
		
		System.out.println(speed);
		
		position = position.add(velocity.multiply(speed));

		if(player1.getBoundingBox().intersects(getBoundingBox()) || player2.getBoundingBox().intersects(getBoundingBox())) {
			if(!isIntersectionWithPlayer) {
				isIntersectionWithPlayer = true;

				Logger.logMessage("Intersektion Ball mit Spieler", this);
				sfxSource.Play(Resources.pongPlayerHitPedal);

				velocity = velocity.reflect(getPlayerNormal());
			}
		}else {
			isIntersectionWithPlayer = false;
		}

		if (isNotFullyIntersecting(getBoundingBox(), SceneManager.getActiveScene().getRawCameraViewport())) {
			Logger.logMessage("Intersektion Ball mit Wand", this);
			sfxSource.Play(Resources.pongPlayerHitWall);

			velocity = velocity.reflect(getWallNormal());
		}

		Vector3 worldPos = getPosition();
		positions.add(0, worldPos);
	}

	private Vector3 getPlayerNormal() {
		Vector3 normal = new Vector3(-1, 0);

		if (getPosition().x	<= 0) normal = normal.multiply(-1f);
		
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
		if (getPosition().x - getScale().x < -SceneManager.getActiveScene().getRawCameraViewport().width / 2) {
			normal.x = -1;
			collisonWithGoal(false);
		}

		// Check collision with the right wall
		if (getPosition().x + getScale().x > SceneManager.getActiveScene().getRawCameraViewport().width / 2) {
			normal.x = 1;
			collisonWithGoal(true);
		}

		// Check collision with the top wall
		if (getPosition().y - getScale().y < -SceneManager.getActiveScene().getRawCameraViewport().height / 2) {
			normal.y = 1;
		}

		// Check collision with the bottom wall
		if (getPosition().y + getScale().y > SceneManager.getActiveScene().getRawCameraViewport().height / 2) {
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
		setPosition(Vector3.zero());
		positions = new ArrayList<Vector3>(Collections.nCopies(50, Vector3.zero()));

		PongSpawnEffect.callEffect();
		speed = 9f;
		moveTimer();		
	}

	private void spawnParticles() {
		ParticleSystem system = new ParticleSystem((int)getPosition().x, (int)getPosition().y);
		system.emitLoop = false;
		system.emitChunk = 450;
		system.initialParticleSpeedMax = Vector3.one().add(new Vector3(0, -0.45f, 0));
		system.initialParticleSpeedMin = Vector3.one().multiply(-1f).add(new Vector3(0, -0.45f, 0));
		system.particleSpeedMultiplier = 35;
		system.particleColorFirst = Color.lightGray;
		system.particleColorSecond = Color.gray;
		system.particleColorEnd = new Color(0, 0, 0, 0);
		system.initialParticleSize = 15;
		system.endParticleSize = 0;
		system.particleLifetime = 5;

		SceneManager.getActiveScene().addObject(system);
		SceneManager.getActiveScene().ShakeCamera(35, 35, 35);
		system.Init();
	}

}
