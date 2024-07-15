package de.demoncore.gameObjects.storymode.bossattacks;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import de.demoncore.audio.AudioSource;
import de.demoncore.game.GameObject;
import de.demoncore.game.SceneManager;
import de.demoncore.game.Translation;
import de.demoncore.gameObjects.ParticleSystem;
import de.demoncore.gameObjects.storymode.StorymodePlayer;
import de.demoncore.utils.GameMath;
import de.demoncore.utils.Logger;
import de.demoncore.utils.Resources;
import de.demoncore.utils.Vector3;

public class BossAttackBorderBreak extends GameObject {

	AudioSource source;

	public BossAttackBorderBreak() {
		super(0, 0, 1500, 1500);
		collisionEnabled = false;

		source = new AudioSource(this);
		source.setSpacial(false);
		SceneManager.getActiveScene().addObject(source);

		source.SetVolume(0.35f);

		color = new Color(0.6f, 0.6f, 0.6f, 1f);
		collisionEnabled = false;
		
		source.Play(Resources.bossBigAttack);
		checkPlayerCollision();
		spawnParticles();
		SceneManager.getActiveScene().ShakeCamera(20, 18, 150);

		Logger.logInfo("Add attack " + getClass().getName());
	}

	float borderThickness = 0f;
	float borderEnd = 350f;
	
	private void spawnParticles() {
		ParticleSystem s = new ParticleSystem(0, 0);
		
		s.particleSpawnArea = new Vector3(1500 / 2, 1500 / 2);
		s.emitChunk = 150;
		s.initialParticleSpeedMax = new Vector3(10, 10);
		s.initialParticleSpeedMin = new Vector3(-10, -10);
		s.particleSpeedMultiplier = 0.35f;
		s.particleGravity = 0.45f;
		s.particleLifetime = 65;
		s.particleLifetimeRandom = 50;
		s.particleColorFirst = Color.white;
		s.particleColorSecond = Color.gray;
		s.particleColorEnd = Color.black;
		
		SceneManager.getActiveScene().addObject(s);
		s.Init();
	}

	
	@Override
	public void update() {
		super.update();

		color = GameMath.lerpColor(color, new Color(0, 0, 0, 0), 0.05f);
		borderThickness = GameMath.Lerp(borderThickness, borderEnd, 0.075f);
	}
	
	void checkPlayerCollision() {
		Rectangle2D r2d = new Rectangle2D.Float(-1100/2, -1100/2, 1100, 1100);
		
		if(!r2d.intersects(StorymodePlayer.getPlayerInstance().getBoundingBox()))
			StorymodePlayer.getPlayerInstance().damage(2, this, Translation.get("deathReason.movingWalls"));
	}
	
	@Override
	public void draw(Graphics2D g2d, int screenWidth, int screenHeight) {
		Vector3 worldPos = getPosition();

		g2d.setColor(color);
		g2d.setStroke(new BasicStroke(borderThickness));
		g2d.drawRect(worldPos.getX(), worldPos.getY(), size.getX(), size.getY());				
	}

	BossAttackBorderBreak getObj() {
		return this;
	}

}
