package de.demoncore.game;

public interface Damageable {
	
	public void damage(int amount, GameObject damageSource, TranslationComponent deathReason);
	public void heal(int amount);

	public int getHealth();
	public void setHealth(int health);
	
}
