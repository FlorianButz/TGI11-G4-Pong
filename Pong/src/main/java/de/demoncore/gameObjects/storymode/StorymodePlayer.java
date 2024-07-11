package de.demoncore.gameObjects.storymode;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.color.ColorSpace;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.demoncore.actions.GameActionListener;
import de.demoncore.actions.KeyHandler;
import de.demoncore.game.Damagable;
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
import de.demoncore.gui.GUIValueBar;
import de.demoncore.rendering.Draw;
import de.demoncore.scenes.shopnew.BallTrails;
import de.demoncore.scenes.shopnew.ShopValues;
import de.demoncore.scenes.storymode.StorymodeMain;
import de.demoncore.scenes.storymode.StorymodeSaveData;
import de.demoncore.sprites.Sprite;
import de.demoncore.utils.GameMath;
import de.demoncore.utils.Resources;
import de.demoncore.utils.Vector3;

public class StorymodePlayer extends RigidBody implements Damagable {

	public float playerAcceleration = 7.75f; // Die Geschwindigkeitszunahme vom Spieler

	ParticleSystem trail;
	SpriteAnimator walkAnim;

	public GUIHealthbar health;

	private GUIValueBar levelBar;
	private GUIValueBar staminaBar;

	private float stamina = 100;
	private final float staminaSubtraction = 0.35f;
	private final float staminaRecharge = 0.65f;
	private float staminaTimer = 0;

	private Vector3 normalSize;
	private Vector3 ballSize;

	private float playerXP = 0;
	private final int playerXPForOneLevel = 100;

	private int damageAmount = 2;
	private int radialDamageAmount = 4;

	private static StorymodePlayer instance;

	List<Vector3> positions = new ArrayList<Vector3>(Collections.nCopies(10, getPosition()));
	
	public float getPlayerXP() {
		System.out.println(playerXP);
		return playerXP;
	}
	
	public void setPlayerXP(float xp) {
		playerXP = xp;
	}
	
	public static StorymodePlayer getPlayerInstance() {
		return instance;
	}

	public void setPermPosition(Vector3 position) {
		setPosition(position);
		lastPosition = position;
	}
	
	public StorymodePlayer(int x, int y) {
		super(x, y, 30, 45);
		instance = this;
		
		normalSize = size;
		ballSize = new Vector3(normalSize.y * 0.6f, normalSize.y * 0.6f);

		health = new GUIHealthbar(65, 85, 45, 6);
		health.alignment = GUIAlignment.TopLeft;

		friction = 0.8f;

		SceneManager.getActiveScene().addObject(health);

		setHealth(StorymodeMain.saveData.playerHealth);
		setPlayerXP(StorymodeMain.saveData.playerXP);
		
		levelBar = new GUIValueBar(0, 90, 850, 25, 0, 100) {
			@Override
			public void draw(Graphics2D g2d, int screenWidth, int screenHeight) {
				super.draw(g2d, screenWidth, screenHeight);
				String text = "Level " + (int) ((float) playerXP / (float) playerXPForOneLevel) + " (" + playerXP
						+ "xp)";

				Graphics2D g = (Graphics2D) g2d.create();

				g.setFont(Resources.uiFont.deriveFont(35f));
				Rectangle2D bounds = g.getFontMetrics().getStringBounds(text, g);

				// Composite comp = g2d.getComposite();
				// g2d.setComposite(new InvertedComposite());

				g.drawString(text,
						getUIPosition(screenWidth, screenHeight).x + (int) (size.x / 2) - (int) (bounds.getMaxX() / 2),
						(float) (getUIPosition(screenWidth, screenHeight).y - bounds.getMaxY() / 2 - 25));

				// g2d.setComposite(comp);
			}
		};
		staminaBar = new GUIValueBar(0, 125, 750, 10, 0, 100);

		staminaBar.color = Color.black;
		staminaBar.fillColor = Color.white;
		staminaBar.borderColor = Color.white;
		staminaBar.borderSize = 1.75f;

		levelBar.color = Color.gray;
		levelBar.fillColor = Color.white;
		levelBar.borderColor = Color.white;
		levelBar.borderSize = 3.5f;

		SceneManager.getActiveScene().addObject(levelBar);
		SceneManager.getActiveScene().addObject(staminaBar);

		activeImage = Resources.playerIdle;

		walkAnim = new SpriteAnimator(new Sprite[] { Resources.playerWalk1, Resources.playerIdle, Resources.playerWalk2,
				Resources.playerIdle }, 0.15f, EasingType.Linear);
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
		Vector3 intersectionMiddle = new Vector3((float) intersection.getCenterX(), (float) intersection.getCenterY());

		Vector3 positionAdd = Vector3.zero();
		positionAdd.x = (this.position.x - intersectionMiddle.x);
		positionAdd.y = (this.position.y - intersectionMiddle.y);
		positionAdd = positionAdd.normalized();

		ballVelocity = ballVelocity.reflect(positionAdd);
	}

	@Override
	protected void onCollision(GameObject thisObject, GameObject otherObject) {
		super.onCollision(thisObject, otherObject);

		if (isBallForm) {
			if (otherObject instanceof Damagable) {
				((Damagable) otherObject).damage(damageAmount, this);
			}
		}
		
	}

	void setTexture(Sprite texture, boolean isBall) {
		if (isBallForm && isBall) {
			activeImage = texture;
		} else if (!isBallForm && !isBall) {
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
		
		if(stamina >= 75 && !GameLogic.IsGamePaused() && !isBallForm && KeyHandler.isCtrlPressed) {
			radiusAttack();
		}
		
		else if (!GameLogic.IsGamePaused() || (isBallForm == false && stamina == 0) && !isShockwaveVisible) {
			isBallForm = !isBallForm;

			ballVelocity = Vector3.zero();
			
			positions = new ArrayList<Vector3>(Collections.nCopies(10, getPosition()));
			
			if (!isBallForm) {
				setTexture(Resources.playerIdle, false);
				trail.emitLoop = false;
			} else {
				ballVelocity = velocity.multiply(1 / playerAcceleration).multiply(ballSpeed);
				ballVelocity.clamp(Vector3.zero(), Vector3.one().multiply(ballSpeed));

				setTexture(Resources.ball, true);
				trail.emitLoop = true;
			}
		}
	}
	
	boolean isShockwaveVisible = false;
	float shockwaveScale = 0;
	
	private void radiusAttack() {
		stamina = 0;
		
		ParticleSystem s = new ParticleSystem(getPosition().getX() + getScale().getX() / 2, getPosition().getY() + getScale().getY() / 2);
		
		s.emitChunk = 500;
		s.initialParticleSpeedMax = new Vector3(10, 10);
		s.initialParticleSpeedMin = new Vector3(-10, -10);
		s.particleSpeedMultiplier = 0.25f;
		s.particleGravity = 1f;
		s.particleLifetime = 150;
		s.particleLifetimeRandom = 150;
		
		SceneManager.getActiveScene().addObject(s);
		s.Init();
		
		isShockwaveVisible = true;
		
		Thread thread = new Thread() {
			public void run() {
				shockwaveScale = 0;
				
				try {
					for(int i = 1; i < 750; i++) {
						sleep(1);
						shockwaveScale += 5f * ((1f / i) * 15);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				isShockwaveVisible = false;
			};
		};
		thread.start();
		
		SceneManager.getActiveScene().ShakeCamera(20, 5, 100);
		
		for(GameObject e : new ArrayList<GameObject>(SceneManager.getActiveScene().getSceneObjects())) {
			if(e instanceof BaseEnemy) {
				if(Vector3.Distance(getPosition(), e.getPosition()) <= 400) {
					((BaseEnemy) e).damage(radialDamageAmount, this);
				}
			}
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		KeyHandler.listeners.remove(listener);

		SceneManager.getActiveScene().destroyObject(levelBar);
		SceneManager.getActiveScene().destroyObject(staminaBar);
	}

	Sprite activeImage;

	private GameActionListener listener;

	@Override
	public void draw(Graphics2D g2d, int screenWidth, int screenHeight) {
		
		if (isBallForm) {			
			Color newColor = new Color(255 - color.getRed(), 255 - color.getGreen(), 255 - color.getBlue(),
					0 /* alpha needs to be zero */);
			g2d.setXORMode(newColor);
		}

		if (activeImage != null)
			g2d.drawImage(activeImage.getTexture(), getPosition().getX(), getPosition().getY(), getScale().getX(),
				getScale().getY(), null);

		g2d.setPaintMode();
		
		if(isBallForm) {
			
			if(ShopValues.shopData.activeBallTrail == BallTrails.Simple) {
				g2d.setColor(new Color(1, 1, 1, 0.25f));
				g2d.fillRect((int)positions.get(1).x, (int)positions.get(1).y, (int)size.x, (int)size.y);
				g2d.setColor(new Color(1, 1, 1, 0.05f));
				g2d.fillRect((int)positions.get(3).x, (int)positions.get(3).y, (int)size.x, (int)size.y);
			}
			else if(ShopValues.shopData.activeBallTrail == BallTrails.Beam) {
				for(int i = 1; i < positions.size(); i++) {
					g2d.setStroke(new BasicStroke(10f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
					g2d.setColor(GameMath.lerpColor(color, new Color(0, 0, 0, 0), (float)(i) / positions.size()));
					g2d.drawLine((int)positions.get(GameMath.Clamp(i / 8 -1, 0, positions.size())).x + (int)getScale().x / 2,
							(int)positions.get(GameMath.Clamp(i / 8 -1, 0, positions.size())).y + (int)getScale().y / 2,
							(int)positions.get(GameMath.Clamp(i / 8, 0, positions.size())).x + (int)getScale().x / 2,
							(int)positions.get(GameMath.Clamp(i / 8, 0, positions.size())).y + (int)getScale().y / 2);
				}
			}else if(ShopValues.shopData.activeBallTrail == BallTrails.Particles) {
				trail.emitLoop = true;
			}
		}
		
		if(isShockwaveVisible) {
			g2d.setStroke(new BasicStroke(5));
			g2d.setColor(new Color(1, 1, 1, GameMath.clamp(GameMath.RemapValue(shockwaveScale, 0, 500, 1, 0), 0, 1)));
			g2d.drawOval(getPosition().getX() + getScale().getX() / 2 - (int)shockwaveScale / 2, getPosition().getY() + getScale().getY() / 2 - (int)shockwaveScale / 2, (int)shockwaveScale, (int)shockwaveScale);
		}
	}

	@Override
	public void update() {
		
		if (GameLogic.IsGamePaused())
			return;

		size = Vector3.Lerp(size, isBallForm ? ballSize : normalSize, 0.15f);

		// Partikel effekt fuer den spieler
		if (trail == null) {
			trail = new ParticleSystem((int) this.position.x, (int) this.position.y);

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

		if (Math.abs(velocity.Magnitude()) >= 0.1) {
			trail.emitLoop = true;
			walkAnim.play();
		} else {
			trail.emitLoop = false;
			walkAnim.stop();
		}

		if (!isBallForm) {
			trail.particleColorFirst = new Color(1, 1, 1, 0.2f);
			trail.particleColorSecond = new Color(1, 1, 1, 0.2f);

			color = Color.white;

			addForce(KeyHandler.playerInput1.normalized().multiply(playerAcceleration));
		} else {
			trail.particleColorFirst = new Color(1, 1, 1, 0.5f);
			trail.particleColorSecond = new Color(1, 1, 1, 0.8f);

			switch (ShopValues.shopData.activeBallSkin) {
			case White:
				color = Color.white;
				break;
			case Red:
				color = Color.red;
				break;
			case Yellow:
				color = Color.YELLOW;
				break;
			case Rainbow:
				color = regenbogen();
				break;

			default:
				break;
			}

			addForce(ballVelocity);
		}

		if (isBallForm) {
			stamina -= staminaSubtraction;
			staminaTimer = 45;
		} else {
			staminaTimer -= 0.5f;
			if (staminaTimer <= 0) {
				stamina += staminaRecharge;
			}
		}

		stamina = GameMath.clamp(stamina, 0, 100);
		staminaBar.silentSetValue(stamina);

		levelBar.setValue(playerXP % playerXPForOneLevel);

		if (stamina == 0 && isBallForm) {
			spaceKeyDown();
		}

		trail.setPosition(this.position);
		
		Vector3 worldPos = getPosition();
		positions.add(0, worldPos);
		
		super.update();
	}

	public static Color regenbogen() {
		return GameMath.lerpColor(
				GameMath.lerpColor(Color.magenta, Color.green,
						(float) Math.sin(GameLogic.getInstance().getGameTime() * 2)),
				Color.cyan, (float) Math.sin(GameLogic.getInstance().getGameTime() * 1));
	}

	@Override
	public void damage(int amount, GameObject damageSource) {
		if (isBallForm && damageSource instanceof BaseEnemy)
			return;
		health.damage(amount);
	}

	@Override
	public void heal(int amount) {
		health.heal(amount);
	}

	@Override
	public int getHealth() {
		return health.getHealth();
	}

	public void addXP(int i) {
		playerXP += i;
	}

	@Override
	public void setHealth(int health) {
		this.health.setHealth(health);
	}

}
