package de.demoncore.gameObjects.storymode;

import java.awt.Graphics2D;

import de.demoncore.audio.AudioSource;
import de.demoncore.game.Damageable;
import de.demoncore.game.GameLogic;
import de.demoncore.game.GameObject;
import de.demoncore.game.SceneManager;
import de.demoncore.game.Translation;
import de.demoncore.gameObjects.ParticleSystem;
import de.demoncore.gameObjects.RigidBody;
import de.demoncore.rendering.Draw;
import de.demoncore.sprites.Sprite;
import de.demoncore.utils.Logger;
import de.demoncore.utils.Resources;
import de.demoncore.utils.Vector3;

public class Arrow extends RigidBody {

	public float speed = 1f;
	public float speedIncrease = 4f;
	public Sprite sprite;
	int damage = 1;
	
	public Arrow(int posX, int posY, int damage) {
		super(posX, posY, (int)(5 * 2.5f), (int)(17 * 2.5f));
		sprite = Resources.arrow;
		
		this.damage = damage;
		
		collisionEnabled = false;
	}
	
	@Override
	public void draw(Graphics2D g2d, int screenWidth, int screenHeight) {
		float currentAngle = (float)Math.toRadians(getAngle(getPosition().add(getScale().multiply(0.5f)), StorymodePlayer.getPlayerInstance().getRawPosition()));
		
		g2d.rotate(currentAngle,
				getPosition().getX() + getScale().getX() / 2,
				getPosition().getY() + getScale().getY() / 2);

		g2d.drawImage(sprite.getTexture(),
				getPosition().getX() + getScale().getX() / 2 - (getScale().getX()) / 2,
				getPosition().getY() + getScale().getY() / 2 - (getScale().getY()) / 2,
				getScale().getX(), getScale().getY(), null);

		g2d.rotate(-currentAngle,
				getPosition().getX() + getScale().getX() / 2,
				getPosition().getY() + getScale().getY() / 2);
	}
	
	Vector3 velVector = Vector3.zero();
	
	@Override
	protected void onCollision(GameObject thisObject, GameObject otherObject) {
		if(otherObject instanceof Damageable) {
			((Damageable)otherObject).damage(damage, this, Translation.get("deathReason.arrow"));
		}
		SceneManager.getActiveScene().destroyObject(this);
		
		spawnParticles();
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
	public void update() {
		if(GameLogic.IsGamePaused()) return;
		
		super.update();
		
		speed += speedIncrease / Draw.GetFramesPerSecond();
		
		velVector = Vector3.Lerp(velVector, StorymodePlayer.getPlayerInstance().getPosition().add(StorymodePlayer.getPlayerInstance().getScale().multiply(0.5f)).subtract(position).normalized().multiply(speed), 0.05f);
		addForce(velVector);
	}
	
	public float getAngle(Vector3 start, Vector3 target) {
		float angle = (float) Math.toDegrees(Math.atan2(target.y - start.y, target.x - start.x));

		return angle + 90;
	}
}
