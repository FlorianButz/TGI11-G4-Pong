package de.demoncore.gameObjects.storymode;

import java.awt.Color;

import de.demoncore.game.SceneManager;
import de.demoncore.game.animator.AnimatorOnCompleteEvent;
import de.demoncore.game.animator.AnimatorUpdateEvent;
import de.demoncore.game.animator.SpriteAnimator;
import de.demoncore.game.animator.Easing.EasingType;
import de.demoncore.gameObjects.ParticleSystem;
import de.demoncore.sprites.Sprite;
import de.demoncore.sprites.SpriteObject;
import de.demoncore.utils.Resources;
import de.demoncore.utils.Vector3;

public class Campfire extends SpriteObject {

	public Campfire(int posX, int posY) {
		super(posX, posY, (int)(22 * 2.5f), (int)(29 * 2.5f), Resources.campfire1);
		

		Sprite[] values = new Sprite[] {
				Resources.campfire1,
				Resources.campfire2
		};
		
		SpriteAnimator anim = new SpriteAnimator(values, 0.5f, EasingType.Linear);
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
		
		ParticleSystem trail = new ParticleSystem((int) this.position.x, (int) this.position.y);

		trail.emitLoop = true;
		trail.particleSpawnArea = new Vector3(15, 15, 15);
		trail.particleGravity = -0.2f;
		trail.initialParticleEmitCount = 0;
		trail.initialParticleEmitCountRandom = 0;
		trail.emitPause = 5;

		trail.emitChunk = 2;

		trail.particleColorFirst = new Color(1, 1, 1, 0.75f);
		trail.particleColorSecond = new Color(0.5f, 0.5f, 0.5f, 0.3f);

		trail.initialParticleSpeedMin = Vector3.one().multiply(-0.15f);
		trail.initialParticleSpeedMax = Vector3.one().multiply(0.15f);
		trail.particleSpeedMultiplier = 0.25f;
		trail.initialParticleSize = 10;
		trail.endParticleSize = 0;
		
		trail.particleLifetime = 50;
		trail.particleLifetimeRandom = 40;
		trail.Init();
		SceneManager.getActiveScene().addObject(trail);	
	}

}
