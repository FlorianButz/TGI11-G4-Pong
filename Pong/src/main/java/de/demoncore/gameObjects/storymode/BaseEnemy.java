package de.demoncore.gameObjects.storymode;

import java.awt.Color;
import java.awt.Graphics2D;

import de.demoncore.gameObjects.RigidBody;
import de.demoncore.gui.ValueBarRenderable;
import de.demoncore.utils.Vector3;

public class BaseEnemy extends RigidBody {

	public int attackSpeed = 85;
	public int damageAmount = 1;
	public float enemySpeed = 2.75f;
	public float stoppingDistance = 85f;
	
	public int health = 5;
	
	public ValueBarRenderable healthBar;
	
	public BaseEnemy(int posX, int posY) {
		super(posX, posY, 25, 25);
	
		color = Color.red;
		healthBar = new ValueBarRenderable(0, 0, 100, 25, 0, health);
	}
	
	@Override
	public void draw(Graphics2D g2d, int screenWidth, int screenHeight) {
		super.draw(g2d, screenWidth, screenHeight);
		
		Vector3 worldPos = getPosition();
		g2d.setColor(color);
		g2d.fillRect((int)worldPos.x, (int)worldPos.y, (int)size.x, (int)size.y);
		
		healthBar.draw(g2d, screenWidth, screenHeight);
	}
	
	protected boolean isAttacking;
	int timer = 0;
	byte speed = 1;
	
	@Override
	public void update() {
		super.update();
	
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
				StorymodePlayer.getPlayerInstance().health.Damage(damageAmount);
			}
		}
		
		healthBar.setPosition(getPosition().add(new Vector3(0, 100)));
	}
	
}
