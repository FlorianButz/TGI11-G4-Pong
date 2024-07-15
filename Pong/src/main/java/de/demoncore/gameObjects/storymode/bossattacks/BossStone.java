package de.demoncore.gameObjects.storymode.bossattacks;

import java.awt.Color;
import java.awt.Graphics2D;

import de.demoncore.game.Damageable;
import de.demoncore.game.GameLogic;
import de.demoncore.game.GameObject;
import de.demoncore.game.SceneManager;
import de.demoncore.game.TranslationComponent;
import de.demoncore.game.animator.AnimatorOnCompleteEvent;
import de.demoncore.game.animator.AnimatorUpdateEvent;
import de.demoncore.game.animator.Easing.EasingType;
import de.demoncore.game.animator.SpriteAnimator;
import de.demoncore.gui.dialog.Dialog;
import de.demoncore.scenes.storymode.EndbossFight;
import de.demoncore.sprites.Sprite;
import de.demoncore.sprites.SpriteObject;
import de.demoncore.utils.GameMath;
import de.demoncore.utils.Resources;

public class BossStone extends SpriteObject implements Damageable {

	SpriteAnimator anim;
	
	public BossStone(int posX, int posY) {
		super(posX, posY, 75 * 5, 75 * 5, Resources.bossIdle);
		
		Sprite[] values = new Sprite[] {
				Resources.bossIdle,
				Resources.bossIdle,
				Resources.bossIdle,
				Resources.bossIdle,
				Resources.bossIdle,
				Resources.bossIdle,
				Resources.bossIdle,
				Resources.bossIdle,
				Resources.bossIdle,
				Resources.bossIdle,
				Resources.bossIdle,
				Resources.bossIdle,
				Resources.bossIdle,
				Resources.bossIdle,
				Resources.bossIdle,
				Resources.bossIdle,
				Resources.bossIdle,
				Resources.bossIdle,
				Resources.bossIdle,
				Resources.bossEye1,
				Resources.bossEye2,
				Resources.bossEye1
		};
		
		anim = new SpriteAnimator(values, 5f, EasingType.Linear);
		anim.setOnComplete(new AnimatorOnCompleteEvent() {
		@Override
		public void onComplete() {
			super.onComplete();
			anim.play();
		}
		});
		anim.setOnUpdate(new AnimatorUpdateEvent() {
		@Override
		public void onUpdate(Sprite value) {
			super.onUpdate(value);
			sprite = value;
		}
		});
		
		anim.play();
		
		health = maxHealth;
		
		Dialog dialog = new Dialog(Resources.endbossDialog) {
			@Override
			public void onDestroy() {
				super.onDestroy();
				EndbossFight.getInstance().startEndbossFight();
			
				GameLogic.getInstance().doPauseGameOnDialog = true;
			}
			
			@Override
			public void showDialog() {
				GameLogic.getInstance().doPauseGameOnDialog = false;
				super.showDialog();
			}
		};
		SceneManager.getActiveScene().addObject(dialog);
		dialog.showDialog();
	}
	
	@Override
	public void update() {
		super.update();
	
		currentColor = GameMath.lerpColor(currentColor, Color.white, 0.2f);
	}
	
	Color currentColor = Color.white;

	@Override
	public void draw(Graphics2D g2d, int screenWidth, int screenHeight) {
		
		Color newColor = new Color(255 - currentColor.getRed(),
				255 - currentColor.getGreen(),
				255 - currentColor.getBlue(), 0);
	    g2d.setXORMode(newColor);
	    
		super.draw(g2d, screenWidth, screenHeight);

		g2d.dispose();
	}
	
	int health = 0;
	int maxHealth = 200;
	
	public int getMaxHealth() {
		return maxHealth;
	}
	
	@Override
	public void damage(int amount, GameObject damageSource, TranslationComponent deathReason) {
		this.health -= amount;
		
		currentColor = Color.red;
		
		if(this.health <= 0) {
			EndbossFight.getInstance().endEndbossFight();
			collisionEnabled = false;
		}
	}

	@Override
	public void heal(int amount) {
		this.health += health;
	}

	@Override
	public int getHealth() {
		return this.health;
	}

	@Override
	public void setHealth(int health) {
		this.health = health;
	}

	
}
