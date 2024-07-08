package de.demoncore.gameObjects.storymode;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import de.demoncore.actions.GameActionListener;
import de.demoncore.actions.KeyHandler;
import de.demoncore.game.GameLogic;
import de.demoncore.game.GameObject;
import de.demoncore.game.SceneManager;
import de.demoncore.game.animator.AnimatorOnCompleteEvent;
import de.demoncore.game.animator.AnimatorUpdateEvent;
import de.demoncore.game.animator.Easing.EasingType;
import de.demoncore.game.animator.SpriteAnimator;
import de.demoncore.gameObjects.ParticleSystem;
import de.demoncore.gameObjects.RigidBody;
import de.demoncore.gui.GUIAlignment;
import de.demoncore.gui.GUIHealthbar;
import de.demoncore.sprites.Sprite;
import de.demoncore.utils.Logger;
import de.demoncore.utils.Resources;
import de.demoncore.utils.Vector3;

public class StorymodePlayer extends RigidBody {

	public float playerAcceleration = 7.75f; // Die Geschwindigkeitszunahme vom Spieler

	ParticleSystem trail;
	SpriteAnimator walkAnim;

	public GUIHealthbar health;

	private Vector3 normalSize;
	private Vector3 ballSize;

	private static StorymodePlayer instance;

	public static StorymodePlayer getPlayerInstance() {
		return instance;
	}

	public StorymodePlayer(int x, int y) {
		super(x, y, 30, 45);
		instance = this;

		normalSize = size;
		ballSize = new Vector3(normalSize.y * 0.75f, normalSize.y * 0.75f);

		health = new GUIHealthbar(65, 85, 45, 6);
		health.alignment = GUIAlignment.TopLeft;

		friction = 0.8f;

		SceneManager.getActiveScene().addObject(health);

		activeImage = Resources.playerIdle;

		walkAnim = new SpriteAnimator(new Sprite[] {Resources.playerWalk1, Resources.playerIdle, Resources.playerWalk2, Resources.playerIdle}, 0.15f, EasingType.Linear);
		walkAnim.setOnUpdate(new AnimatorUpdateEvent() {
			@Override
			public void onUpdate(Sprite value) {
				setTexture(value, false);
			}
		});
		walkAnim.setOnComplete(new AnimatorOnCompleteEvent() {
			@Override
			public void onComplete() {
				setTexture(Resources.playerIdle, false);
			}
		});


		listener = new GameActionListener() {
			public void onSpaceKeyPressed() {
				spaceKeyDown();
			};
		};

	}
	
	@Override
	protected void onCollision(Rectangle thisObject, Rectangle otherObject) {
		super.onCollision(thisObject, otherObject);
	
		Rectangle intersection = thisObject.intersection(otherObject);					
		Vector3 intersectionMiddle = new Vector3((float)intersection.getCenterX(), (float)intersection.getCenterY());
		
		Vector3 positionAdd = Vector3.zero();
		positionAdd.x = (this.position.x - intersectionMiddle.x);
		positionAdd.y = (this.position.y - intersectionMiddle.y);
		positionAdd = positionAdd.normalized();
		
		ballVelocity = ballVelocity.reflect(positionAdd);
	}

	void setTexture(Sprite texture, boolean isBall) {
		if(isBallForm && isBall) {
			activeImage = texture;
		}else if(!isBallForm && !isBall) {
			activeImage = texture;
		}
	}

	@Override
	public void onAddToScene() {
		super.onAddToScene();
		KeyHandler.listeners.add(listener);
	}

	boolean isBallForm = false;
	public Vector3 ballVelocity = Vector3.zero();
	public float ballSpeed = 3;

	public void spaceKeyDown() {
		if(!GameLogic.IsGamePaused())
		
		isBallForm = !isBallForm;

		ballVelocity = Vector3.zero();
		
		if(!isBallForm) {
			setTexture(Resources.playerIdle, false);
			trail.emitLoop = false;
		}else {
			ballVelocity = velocity.multiply(1 / playerAcceleration).multiply(ballSpeed);
			
			setTexture(Resources.ball, true);
			trail.emitLoop = true;
		}
	}
	
	

	@Override
	public void onDestroy() {
		super.onDestroy();

		KeyHandler.listeners.remove(listener);
	}

	Sprite activeImage;

	private GameActionListener listener;

	@Override
	public void draw(Graphics2D g2d, int screenWidth, int screenHeight) {
		if(activeImage != null)
			g2d.drawImage(activeImage.getTexture(), (int)getPosition().x, (int)getPosition().y, (int)getScale().x, (int)getScale().y, null);
	}

	@Override
	public void update() {

		if(GameLogic.IsGamePaused()) return;

		size = Vector3.Lerp(size, isBallForm ? ballSize : normalSize, 0.15f);

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

		if(!isBallForm) {
			trail.particleColorFirst = new Color(1, 1, 1, 0.2f);
			trail.particleColorSecond = new Color(1, 1, 1, 0.2f);

			
			addForce(KeyHandler.playerInput1.normalized().multiply(playerAcceleration));
		}
		else {
			trail.particleColorFirst = new Color(1, 1, 1, 0.5f);
			trail.particleColorSecond = new Color(1, 1, 1, 0.8f);
			
			addForce(ballVelocity);
		}
		
			
		
		trail.setPosition(this.position);

		super.update();
	}

}
