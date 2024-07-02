package de.demoncore.scenes.shopnew;

import java.io.Serializable;

public class ShopValuesSave implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6383494586351025640L;

	private int playerMoney = 0;
	
	public BallSkins activeBallSkin = BallSkins.White;
	public BallTrails activeBallTrail = BallTrails.Simple;
	public PedalSkins activePedalSkin = PedalSkins.White;
	public SpawnAnimations activeSpawnAnimation = SpawnAnimations.None;
	
	public int getPlayerMoney() {
		return playerMoney;
	}
	
	public void setPlayerMoney(int playerMoney) {
		this.playerMoney = playerMoney;
	}
	
	public void addPlayerMoney(int amount) {
		this.playerMoney += amount;
	}
}
