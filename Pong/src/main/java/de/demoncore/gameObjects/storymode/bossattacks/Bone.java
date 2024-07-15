package de.demoncore.gameObjects.storymode.bossattacks;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import de.demoncore.audio.AudioSource;
import de.demoncore.game.Damageable;
import de.demoncore.game.GameLogic;
import de.demoncore.game.GameObject;
import de.demoncore.game.SceneManager;
import de.demoncore.game.Translation;
import de.demoncore.gameObjects.ParticleSystem;
import de.demoncore.gameObjects.RigidBody;
import de.demoncore.sprites.Sprite;
import de.demoncore.utils.Logger;
import de.demoncore.utils.Resources;
import de.demoncore.utils.Vector3;

public class Bone extends RigidBody {

	public Vector3 initialSpeed;
	private float rotSpeed = 5;
	
	Sprite sprite;
	
	public Bone(int posX, int posY, Vector3 initialSpeed) {
		super(posX, posY, 7 * 5, 15 * 5);
		
		sprite = Math.random() > 0.5 ? Resources.bone1 : Resources.bone2;
		
		rotSpeed *= (float)Math.random() + 0.15f;
		
		setScale(Vector3.zero());
		this.initialSpeed = initialSpeed;
	}
	
	@Override
	public void draw(Graphics2D g2d, int screenWidth, int screenHeight) {
		Vector3 worldPos = getPosition();
		g2d.drawImage(sprite.getTexture(),
				(int)worldPos.x,
				(int)worldPos.y,
				(int)size.x,
				(int)size.y,
				null);
	}
	
	@Override
	public void update() {
		if(GameLogic.IsGamePaused()) return;
		
		super.update();
		
		rotationZ += rotSpeed;
		velocity = initialSpeed;
		
		setScale(Vector3.Lerp(getScale(), new Vector3(7 * 3.5f, 13 * 3.5f), 0.1f));
	}
	
	private void spawnParticles() {
		ParticleSystem s = new ParticleSystem(getPosition().getX() + getScale().getX() / 2, getPosition().getY() + getScale().getY() / 2);
		
		s.emitChunk = 30;
		s.initialParticleSpeedMax = new Vector3(10, 10);
		s.initialParticleSpeedMin = new Vector3(-10, -10);
		s.particleSpeedMultiplier = 0.35f;
		s.particleGravity = 0.1f;
		s.particleLifetime = 65;
		s.particleLifetimeRandom = 50;
		
		SceneManager.getActiveScene().addObject(s);
		s.Init();
		
		AudioSource source = new AudioSource();
		source.setSpacial(false);
		SceneManager.getActiveScene().addObject(source);
		source.Play(Resources.playerAttackNormal);
	}

	
	@Override
	protected void onCollision(GameObject thisObject, GameObject otherObject) {
		if(otherObject instanceof Bone || otherObject instanceof BossStone) return;
		
		if(otherObject instanceof Damageable) {
			((Damageable) otherObject).damage(1, this, Translation.get("deathReason.bone"));
		}
		
		spawnParticles();
		SceneManager.getActiveScene().destroyObject(this);
	}
	
	@Override
	public Rectangle getBoundingBox() {
		Rectangle r = new Rectangle();
		
		int sizeX = (int)(size.y + boundingMargin);
		int sizeY = (int)(size.y + boundingMargin);
		
		r.x = (int)(position.x - sizeX / 2);
		r.y = (int)(position.y - sizeY / 2);
		r.width = sizeX;
		r.height = sizeY;
		
		return r;
	}
}
