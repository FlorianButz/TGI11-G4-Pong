package de.demoncore.gameObjects.storymode;

import java.awt.Color;
import java.awt.Graphics2D;

import de.demoncore.game.Damagable;
import de.demoncore.game.GameLogic;
import de.demoncore.game.GameObject;
import de.demoncore.game.SceneManager;
import de.demoncore.gameObjects.ParticleSystem;
import de.demoncore.gameObjects.RigidBody;
import de.demoncore.gui.ValueBarRenderable;
import de.demoncore.utils.Vector3;

public class BaseEnemy extends RigidBody implements Damagable {

	public int attackSpeed = 85;
	public int damageAmount = 1;
	public float enemySpeed = 2.75f;
	public float stoppingDistance = 85f;
	
	public int initialHealth = 5;
	public int health = 5;
	
	public ValueBarRenderable healthBar;
	
	public BaseEnemy(int posX, int posY) {
		super(posX, posY, 25, 25);
	
		color = Color.magenta;
		healthBar = new ValueBarRenderable(0, 0, 50, 10, 0, health);
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
	
		spawnParticles();
		StorymodePlayer.getPlayerInstance().addXP(25);
	}
	
	@Override
	public void draw(Graphics2D g2d, int screenWidth, int screenHeight) {
		super.draw(g2d, screenWidth, screenHeight);
		
		Vector3 worldPos = getPosition();
		g2d.setColor(color);
		g2d.fillRect((int)worldPos.x, (int)worldPos.y, (int)size.x, (int)size.y);
		
		if(health < initialHealth)
			healthBar.draw(g2d, screenWidth, screenHeight);
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
	int timer = 0;
	byte speed = 1;
	
	@Override
	public void update() {
		super.update();
		
		if(GameLogic.IsGamePaused()) return;
	
		if(Vector3.Distance(StorymodePlayer.getPlayerInstance().getPosition(), getPosition()) >= stoppingDistance) {
			Vector3 force = StorymodePlayer.getPlayerInstance().getPosition().subtract(getPosition()).normalized().multiply(enemySpeed);
			addForce(force);
			isAttacking = false;
		}else {
			velocity = Vector3.Lerp(velocity, Vector3.zero(), 0.03f);
		
			if(!isAttacking) {
				isAttacking = true;
				timer = 0;
			}
			
			timer += speed;
			
			if((int)timer % attackSpeed == 0) {
				StorymodePlayer.getPlayerInstance().damage(damageAmount, this);
			}
		}
		
		healthBar.setPosition(position.add(new Vector3(0, -25)));
		healthBar.setValue(health);
	}

	@Override
	public void damage(int amount, GameObject damageSource) {
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
