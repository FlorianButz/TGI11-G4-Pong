package de.demoncore.gameObjects;

import de.demoncore.game.animator.Easing.EasingType;
import de.demoncore.game.PongSpawnEffect;
import de.demoncore.game.PongSpawnEffect.PongSpawnEffectListener;
import de.demoncore.game.animator.AnimatorOnCompleteEvent;
import de.demoncore.game.animator.AnimatorUpdateEvent;
import de.demoncore.game.animator.Vector3Animator;
import de.demoncore.sprites.SpriteObject;
import de.demoncore.utils.Resources;
import de.demoncore.utils.Vector3;

public class PongLatiku extends SpriteObject implements PongSpawnEffectListener {

	public PongLatiku() {
		super(0, 0, 100, 100, Resources.latiku);

		PongSpawnEffect.listeners.add(this);
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();

		PongSpawnEffect.listeners.remove(this);
	}
	
	public void spawnEffect() {
		Vector3Animator positionOut = new Vector3Animator(new Vector3(-35, -200), new Vector3(1250, -400), 1.75f, EasingType.InOutQuint);
		positionOut.setOnUpdate(new AnimatorUpdateEvent() {
		@Override
		public void onUpdate(Vector3 value) {
			super.onUpdate(value);
			setPosition(value);
		}
		});
		
		Vector3Animator positionIn = new Vector3Animator(new Vector3(-1250, -400), new Vector3(-35, -200), 2f, EasingType.InOutQuint);
		positionIn.setOnUpdate(new AnimatorUpdateEvent() {
		@Override
		public void onUpdate(Vector3 value) {
			super.onUpdate(value);
			setPosition(value);
			
			PongBall.getInstance().setPosition(getPosition().add(Vector3.one().multiply(100).add(new Vector3(-13, 0))));
		}
		});
		positionIn.setOnComplete(new AnimatorOnCompleteEvent() {
		@Override
		public void onComplete() {
			super.onComplete();
			positionOut.play();
			
			Vector3Animator ballPos = new Vector3Animator(PongBall.getInstance().getRawPosition(), Vector3.zero(), 0.5f, EasingType.InOutSine);
			ballPos.setOnUpdate(new AnimatorUpdateEvent() {
			@Override
			public void onUpdate(Vector3 value) {
				super.onUpdate(value);
				PongBall.getInstance().setPosition(value);
			}
			});
			ballPos.play();
		}
		});
		positionIn.play();
	}

	
	
}
