package de.demoncore.gui;

import java.awt.Color;
import java.awt.Graphics2D;

import de.demoncore.game.SceneManager;
import de.demoncore.gameObjects.ParticleSystem;
import de.demoncore.gameObjects.storymode.StorymodePlayer;
import de.demoncore.utils.GameMath;
import de.demoncore.utils.Resources;
import de.demoncore.utils.Vector3;

public class GUIHealthbar extends GUIObject {

	int heartCount = 0;
	int health = 0;
	int spacing = 15;
	
	public GUIHealthbar(int posX, int posY, int size, int heartCount) {
		super(posX, posY, 0, size);
		this.heartCount = heartCount;
		this.health = heartCount;
	}
	
	int sw = 0, sh = 0;
	
	@Override
	public void draw(Graphics2D g2d, int screenWidth, int screenHeight) {
		super.draw(g2d, screenWidth, screenHeight);		
		
		for(int i = 0; i < heartCount; i++) {
			if(i >= health) {
				g2d.drawImage(Resources.brokenHeart.getTexture(),
						(int)getUIPosition(screenWidth, screenHeight).x - (int)size.y / 2 + (int)(size.y * i + spacing * i),
						(int)getUIPosition(screenWidth, screenHeight).y - (int)size.y / 2,
						(int)size.y, (int)size.y, null);
			}
			else {
				g2d.drawImage(Resources.fullHeart.getTexture(),
						(int)getUIPosition(screenWidth, screenHeight).x - (int)size.y / 2 + (int)(size.y * i + spacing * i),
						(int)getUIPosition(screenWidth, screenHeight).y - (int)size.y / 2,
						(int)size.y, (int)size.y, null);
			}
		}
		
		sw = screenWidth;
		sh = screenHeight;
	}

	public int GetHealth() {
		return health;
	}
	
	public void Heal(int healFactor) {
		health = GameMath.Clamp(health + healFactor, 0, heartCount);
	}
	
	public void Damage(int damage) {
//		int healthBefore = health;
		health = GameMath.Clamp(health - damage, 0, heartCount);
		
		SceneManager.getActiveScene().ShakeCamera(16, 2, 20);
		spawnParticles();
		
//		for(int i = healthBefore; i > healthBefore - health; i--) {
//			GUIParticleSystem damageSystem = new GUIParticleSystem(
//					(int)GetUIPosition(sw,sh).x - (int)size.y / 2 + (int)(size.y * i + spacing * i),
//					(int)GetUIPosition(sw,sh).y - (int)size.y / 2);
//			System.out.println(i);
//			System.out.println(healthBefore - health);
//			System.out.println(healthBefore);
//			damageSystem.alignment = GUIAlignment.TopLeft;
//			SceneManager.GetActiveScene().AddObject(damageSystem);
//			damageSystem.Init();
//		}
	}
	
	private void spawnParticles() {
		ParticleSystem system = new ParticleSystem((int)StorymodePlayer.getPlayerInstance().getRawPosition().x, (int)StorymodePlayer.getPlayerInstance().getRawPosition().y);
		system.emitLoop = false;
		system.emitChunk = 50;
		system.initialParticleSpeedMax = Vector3.one().add(new Vector3(0, -0.45f, 0));
		system.initialParticleSpeedMin = Vector3.one().multiply(-1f).add(new Vector3(0, -0.45f, 0));
		system.particleSpeedMultiplier = 10;
		system.particleColorFirst = Color.lightGray;
		system.particleColorSecond = Color.gray;
		system.particleColorEnd = new Color(0, 0, 0, 0);
		system.initialParticleSize = 4;
		system.endParticleSize = 0;
		system.particleLifetime = 75;
		system.particleLifetimeRandom = 45;

		SceneManager.getActiveScene().addObject(system);
		SceneManager.getActiveScene().ShakeCamera(35, 35, 35);
		system.Init();
	}

}
