package de.demoncore.gui;

import java.awt.Graphics2D;

import de.demoncore.utils.GameMath;
import de.demoncore.utils.Resources;

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
}
