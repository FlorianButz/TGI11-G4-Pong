package de.demoncore.gameObjects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Timer;
import java.util.TimerTask;

import de.demoncore.audio.AudioSource;
import de.demoncore.game.SceneManager;
import de.demoncore.sprites.SpriteObject;
import de.demoncore.utils.Logger;
import de.demoncore.utils.Resources;
import de.demoncore.utils.Vector3;

public class PowerupBase extends SpriteObject {
	
	public long powerupDuration = 5000l;
	
	public PowerupBase(int posX, int posY) {
		super(posX, posY, 50, 50, Resources.powerup);
		
		collisionEnabled = false;
		
		setScale(Vector3.zero());
	}
	
	boolean isUsed = false;
	private TimerTask powerupTask;
	
	@Override
	public void draw(Graphics2D g2d, int screenWidth, int screenHeight) {
		
		g2d.setXORMode(PongBall.regenbogen());
		
		super.draw(g2d, screenWidth, screenHeight);
		g2d.dispose();
	}
	
	@Override
	public void update() {
		super.update();

		size = Vector3.lerp(size, Vector3.one().multiply(75), 0.15f);
		
		if(PongBall.getInstance().getBoundingBox().intersects(getBoundingBox()) && !isUsed) {
			isUsed = true;
			enableRendering = false;
			trigPowerup();
		}
	}
	
	boolean wasDestroyed = false;
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		wasDestroyed = true;
	}
	
	private void trigPowerup() {
		Timer t = new Timer();
		
		spawnParticles();
		AudioSource source = new AudioSource();
		source.setSpacial(false);
		SceneManager.getActiveScene().addObject(source);
		source.Play(Resources.pongPowerup);
		
		startPowerup();
		powerupTask = new TimerTask() {
			public void run() {
				if(!wasDestroyed)
					stopPowerup();
			};
		};
		t.schedule(powerupTask, powerupDuration);
	}
	
	private void spawnParticles() {
		ParticleSystem system = new ParticleSystem((int)getRawPosition().x, (int)getRawPosition().y);
		system.emitLoop = false;
		system.emitChunk = 35;
		system.initialParticleSpeedMax = Vector3.one().add(new Vector3(0, -0.45f, 0));
		system.initialParticleSpeedMin = Vector3.one().multiply(-1f).add(new Vector3(0, -0.45f, 0));
		system.particleSpeedMultiplier = 12;
		system.particleColorFirst = Color.lightGray;
		system.particleColorSecond = Color.gray;
		system.particleColorEnd = new Color(0, 0, 0, 0);
		system.initialParticleSize = 6.5f;
		system.endParticleSize = 0;
		system.particleLifetime = 75;
		system.particleLifetimeRandom = 45;

		system.particleColorFirst = Color.red;

		SceneManager.getActiveScene().addObject(system);
		SceneManager.getActiveScene().ShakeCamera(35, 35, 35);
		system.Init();
	}
	
	protected void startPowerup() {
		Logger.logInfo("Start powerup: " + getClass().getName());
	}
	
	protected void stopPowerup() {		
		Logger.logInfo("Stop powerup: " + getClass().getName());
	}
	
	@Override
	public Rectangle getBoundingBox() {
		Rectangle r = super.getBoundingBox();
		r.grow(25, 25);
		return r;
	}
}
