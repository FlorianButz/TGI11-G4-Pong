package de.demoncore.gameObjects.storymode;

import java.awt.Color;
import java.awt.Graphics2D;

import de.demoncore.game.Damageable;
import de.demoncore.game.GameLogic;
import de.demoncore.game.GameObject;
import de.demoncore.game.SceneManager;
import de.demoncore.game.Settings;
import de.demoncore.game.Translation;
import de.demoncore.game.TranslationComponent;
import de.demoncore.gameObjects.ParticleSystem;
import de.demoncore.gameObjects.RigidBody;
import de.demoncore.gui.ValueBarRenderable;
import de.demoncore.utils.Logger;
import de.demoncore.utils.Vector3;

public class BaseEnemy extends RigidBody implements Damageable {

	public int attackSpeed = 85;
	public int damageAmount = 1;
	public float enemySpeed = 0.75f;
	public float stoppingDistance = 85f;
	public float chaseDistance = 500f;

	public int initialHealth = 5;
	public int health = 5;

	public ValueBarRenderable healthBar;

	public BaseEnemy(int posX, int posY) {
		super(posX, posY, 25, 25);

		color = Color.magenta;
		healthBar = new ValueBarRenderable(0, 0, 50, 10, 0, health);
		friction = 0.975f;

		Logger.logInfo("Spawning enemy: " + getClass().getName());
	}

	protected void setInitHealth(int health) {
		this.health = health;
		this.initialHealth = health;

		healthBar = new ValueBarRenderable(0, 0, 50, 10, 0, health);	
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		spawnParticles();
		SceneManager.getActiveScene().destroyObject(trail);
	}

	@Override
	public void draw(Graphics2D g2d, int screenWidth, int screenHeight) {
		super.draw(g2d, screenWidth, screenHeight);

		Vector3 worldPos = getPosition();
		g2d.setColor(color);
		g2d.fillRect((int)worldPos.x, (int)worldPos.y, (int)size.x, (int)size.y);

		if(health < initialHealth)
			healthBar.draw(g2d, screenWidth, screenHeight);

		if(Settings.getDebugMode()) {
			g2d.setColor(Color.red);
			g2d.drawOval((int)worldPos.x - (int)(chaseDistance / 1.5f), (int)worldPos.y - (int)(chaseDistance / 1.5f), (int)(chaseDistance * 1.5f) + (int)getScale().x, (int)(chaseDistance * 1.5f) + (int)getScale().y);
		}
	}

	private void spawnParticles() {
		ParticleSystem system = new ParticleSystem((int)getRawPosition().x, (int)getRawPosition().y);
		system.emitLoop = false;
		system.emitChunk = 35;
		system.initialParticleSpeedMax = Vector3.one().add(new Vector3(0, -0.45f, 0));
		system.initialParticleSpeedMin = Vector3.one().multiply(-1f).add(new Vector3(0, -0.45f, 0));
		system.particleSpeedMultiplier = 8;
		system.particleColorFirst = Color.lightGray;
		system.particleColorSecond = Color.gray;
		system.particleColorEnd = new Color(0, 0, 0, 0);
		system.initialParticleSize = 5.5f;
		system.endParticleSize = 0;
		system.particleLifetime = 75;
		system.particleLifetimeRandom = 45;

		system.particleColorFirst = Color.red;

		SceneManager.getActiveScene().addObject(system);
		SceneManager.getActiveScene().ShakeCamera(35, 35, 35);
		system.Init();
	}

	protected boolean isAttacking;
	private int timer = 0;
	private byte speed = 1;

	ParticleSystem trail;

	@Override
	public void update() {
		super.update();

		if(GameLogic.IsGamePaused()) return;

		// Partikel effekt fuer den gegner
		if (trail == null) {
			trail = new ParticleSystem((int) this.position.x, (int) this.position.y);

			trail.emitLoop = true;
			trail.particleSpawnArea = new Vector3(10, 10, 10);
			trail.particleGravity = 0;
			trail.initialParticleEmitCount = 0;
			trail.initialParticleEmitCountRandom = 0;
			trail.emitPause = 5;

			trail.emitChunk = 2;

			trail.particleColorFirst = color;
			trail.particleColorSecond = new Color(1, 1, 1, 0.2f);

			trail.initialParticleSpeedMin = Vector3.one().multiply(-1);
			trail.initialParticleSpeedMax = Vector3.one();
			trail.particleSpeedMultiplier = 0.25f;

			trail.particleLifetime = 25;
			trail.Init();
			SceneManager.getActiveScene().addObject(trail);
		}
		
		trail.setPosition(position);

		if(Vector3.Distance(StorymodePlayer.getPlayerInstance().getPosition(), getPosition()) <= chaseDistance) {
			if(Vector3.Distance(StorymodePlayer.getPlayerInstance().getPosition(), getPosition()) >= stoppingDistance) {
				Vector3 force = StorymodePlayer.getPlayerInstance().getPosition().subtract(getPosition()).normalized().multiply(enemySpeed);
				addForce(force);
				isAttacking = false;
			}else {
				//velocity = Vector3.Lerp(velocity, Vector3.zero(), 0.005f);

				if(!isAttacking) {
					isAttacking = true;
					timer = 0;
				}

				timer += speed;

				if((int)timer % attackSpeed == 0) {
					attackPlayer();
				}
			}
		}

		healthBar.setPosition(position.add(new Vector3(0, -25)));
		healthBar.setValue(health);
	}

	protected TranslationComponent getDeathReason() {
		return Translation.get("deathReason.baseEnemy");
	}

	protected void attackPlayer() {
		StorymodePlayer.getPlayerInstance().damage(damageAmount, this, getDeathReason());
	}

	@Override
	public void damage(int amount, GameObject damageSource, TranslationComponent deathReason) {
		health -= amount;

		StorymodePlayer.getPlayerInstance().addXP(5);

		if(health <= 0) SceneManager.getActiveScene().destroyObject(this);
	}
	
	@Override
	public void heal(int amount) {
		health += amount;
	}

	@Override
	public int getHealth() {
		return health;
	}

	@Override
	public void setHealth(int health) {
		this.health = health;
	}

}
