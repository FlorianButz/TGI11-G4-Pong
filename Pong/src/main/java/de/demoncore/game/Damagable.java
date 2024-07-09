package de.demoncore.game;

public interface Damagable {
	
	public void damage(int amount, GameObject damageSource);
	public void heal(int amount);
	
	public int getHealth();
	
}
