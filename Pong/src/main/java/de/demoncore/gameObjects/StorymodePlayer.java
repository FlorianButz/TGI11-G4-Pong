package de.demoncore.gameObjects;

import java.awt.Color;
import java.awt.Graphics2D;

import de.demoncore.actions.KeyHandler;
import de.demoncore.game.GameLogic;
import de.demoncore.game.SceneManager;
import de.demoncore.game.animator.AnimatorOnCompleteEvent;
import de.demoncore.game.animator.AnimatorUpdateEvent;
import de.demoncore.game.animator.SpriteAnimator;
import de.demoncore.game.animator.Easing.EasingType;
import de.demoncore.gui.GUIAlignment;
import de.demoncore.gui.GUIHealthbar;
import de.demoncore.sprites.Sprite;
import de.demoncore.utils.Resources;
import de.demoncore.utils.Vector3;

public class StorymodePlayer extends RigidBody {

	public float playerAcceleration = 7.75f; // Die Geschwindigkeitszunahme vom Spieler
	
	ParticleSystem trail;
	SpriteAnimator walkAnim;
	
	public GUIHealthbar health;
	
	private static StorymodePlayer instance;
	
	public static StorymodePlayer getPlayerInstance() {
		return instance;
	}
	
	public StorymodePlayer(int x, int y) {
		super(x, y, 30, 45);
		instance = this;
		
		health = new GUIHealthbar(65, 85, 45, 6);
		health.alignment = GUIAlignment.TopLeft;
	
		SceneManager.getActiveScene().addObject(health);
	
		activeImage = Resources.playerIdle;
		
		walkAnim = new SpriteAnimator(new Sprite[] {Resources.playerWalk1, Resources.playerIdle, Resources.playerWalk2, Resources.playerIdle}, 0.15f, EasingType.Linear);
		walkAnim.setOnUpdate(new AnimatorUpdateEvent() {
		@Override
		public void onUpdate(Sprite value) {
			activeImage = value;
		}
		});
		walkAnim.setOnComplete(new AnimatorOnCompleteEvent() {
		@Override
		public void onComplete() {
			activeImage = Resources.playerIdle;
		}
		});
	}
	
	Sprite activeImage;
	
	@Override
	public void Draw(Graphics2D g2d, int screenWidth, int screenHeight) {
		if(activeImage != null)
			g2d.drawImage(activeImage.getTexture(), (int)getPosition().x, (int)getPosition().y, (int)getScale().x, (int)getScale().y, null);
	}
	
	@Override
	public void update() {
		
		if(GameLogic.IsGamePaused()) return;
		
		// Partikel effekt fuer den spieler
		if(trail == null) {
			trail = new ParticleSystem((int)this.position.x, (int)this.position.y);
			
			trail.emitLoop = true;
			trail.particleSpawnArea = new Vector3(10, 10, 10);
			trail.particleGravity = 0;
			trail.initialParticleEmitCount = 0;
			trail.initialParticleEmitCountRandom = 0;
			trail.emitPause = 5;
			
			trail.emitChunk = 2;

			trail.particleColorFirst = new Color(1, 1, 1, 0.2f);
			trail.particleColorSecond = new Color(1, 1, 1, 0.2f);
			
			trail.initialParticleSpeedMin = Vector3.one().multiply(-1);
			trail.initialParticleSpeedMax = Vector3.one();
			trail.particleSpeedMultiplier = 0.25f;
			
			trail.particleLifetime = 25;
			trail.Init();
			SceneManager.getActiveScene().addObject(trail);
		}
		
		if(Math.abs(velocity.Magnitude()) >= 0.1) {
			trail.emitLoop = true;
			walkAnim.play();
		}
		else {
			trail.emitLoop = false;
			walkAnim.stop();
		}
		
		AddForce(KeyHandler.playerInput1.normalized().multiply(playerAcceleration));
		
		trail.setPosition(this.position);
		
		super.update();
	}
	
}
