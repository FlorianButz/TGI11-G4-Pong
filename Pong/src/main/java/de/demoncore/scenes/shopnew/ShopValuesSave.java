package de.demoncore.scenes.shopnew;

import java.io.Serializable;

public class ShopValuesSave implements Serializable {

	private static final long serialVersionUID = -6383494586351025640L;

	private int playerPongBucks = 0;
	
	public BallSkins activeBallSkin = BallSkins.White;
	public BallTrails activeBallTrail = BallTrails.Simple;
	public PedalSkins activePedalSkin = PedalSkins.White;
	public SpawnAnimations activeSpawnAnimation = SpawnAnimations.None;

	public byte unlockedBallSkins = 0b00000001;
	public byte unlockedBallTrails = 0b00000011;
	public byte unlockedPedalSkins = 0b00000001;
	public byte unlockedSpawnAnimations = 0b00000001;
	
	public int getPlayerMoney() {
		return playerPongBucks;
	}
	
	public void setPlayerMoney(int playerMoney) {
		this.playerPongBucks = playerMoney;
	}
	
	public void addPlayerMoney(int amount) {
		this.playerPongBucks += amount;
	}
}
