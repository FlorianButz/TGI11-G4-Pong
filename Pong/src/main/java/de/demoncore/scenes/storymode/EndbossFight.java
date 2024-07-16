package de.demoncore.scenes.storymode;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import de.demoncore.audio.AudioSource;
import de.demoncore.audio.MusicManager;
import de.demoncore.game.GameLogic;
import de.demoncore.game.GameObject;
import de.demoncore.game.SceneManager;
import de.demoncore.gameObjects.ParticleSystem;
import de.demoncore.gameObjects.PauseMenu;
import de.demoncore.gameObjects.storymode.Cake;
import de.demoncore.gameObjects.storymode.StorymodePlayer;
import de.demoncore.gameObjects.storymode.bossattacks.BossAttackBoneThrow;
import de.demoncore.gameObjects.storymode.bossattacks.BossAttackBorderBreak;
import de.demoncore.gameObjects.storymode.bossattacks.BossAttackCross;
import de.demoncore.gameObjects.storymode.bossattacks.BossAttackHorizontalParallel;
import de.demoncore.gameObjects.storymode.bossattacks.BossAttackPlus;
import de.demoncore.gameObjects.storymode.bossattacks.BossAttackVerticalParallel;
import de.demoncore.gameObjects.storymode.bossattacks.BossStone;
import de.demoncore.gui.GUIValueBar;
import de.demoncore.gui.dialog.Dialog;
import de.demoncore.scenes.BaseScene;
import de.demoncore.utils.GameMath;
import de.demoncore.utils.Logger;
import de.demoncore.utils.Resources;
import de.demoncore.utils.Vector3;

public class EndbossFight extends BaseScene {

	private GUIValueBar bossBar;

	private Random rng = new Random();
	static EndbossFight instance;

	public static EndbossFight getInstance() {
		return instance;
	}

	@Override
	public void initializeScene() {
		super.initializeScene();

		instance = this;

		addObject(new GameObject(0, 0, 1500, 1500) {
			@Override
			public void update() {
				super.update();
				collisionEnabled = false;
			}

			@Override
			public void draw(Graphics2D g2d, int screenWidth, int screenHeight) {
				Vector3 worldPos = getPosition();

				g2d.setColor(new Color(1, 1, 1, 0.05f));
				g2d.fillRect(worldPos.getX(), worldPos.getY(), size.getX(), size.getY());

				g2d.setColor(new Color(0.6f, 0.6f, 0.6f, 1f));
				g2d.setStroke(new BasicStroke(25));
				g2d.drawRect(worldPos.getX(), worldPos.getY(), size.getX(), size.getY());				
			}
		});

		GameObject w1 = new GameObject(1500 / 2, 0, 25, 1500);
		GameObject w2 = new GameObject(0, 1500 / 2, 1500, 25);
		GameObject w3 = new GameObject(-1500 / 2, 0, 25, 1500);
		GameObject w4 = new GameObject(0, -1500 / 2, 1500, 25);

		w1.enableRendering = false;
		w2.enableRendering = false;
		w3.enableRendering = false;
		w4.enableRendering = false;

		addObject(w1);
		addObject(w2);
		addObject(w3);
		addObject(w4);

		addObject(new StorymodePlayer(0, 350));
		addObject(new PauseMenu());

		bossStone = new BossStone(0, 0);
		addObject(bossStone);

		bossBar = new GUIValueBar(0, 90, 850, 25, 0, bossStone.getMaxHealth()) {
			@Override
			public void draw(Graphics2D g2d, int screenWidth, int screenHeight) {
				super.draw(g2d, screenWidth, screenHeight);
				String text = "The Rock";

				Graphics2D g = (Graphics2D) g2d.create();

				g.setFont(Resources.uiFont.deriveFont(35f));
				Rectangle2D bounds = g.getFontMetrics().getStringBounds(text, g);

				g.setColor(Color.white);
				g.drawString(text,
						getUIPosition(screenWidth, screenHeight).x + (int) (size.x / 2) - (int) (bounds.getMaxX() / 2),
						(float) (getUIPosition(screenWidth, screenHeight).y - bounds.getMaxY() / 2 - 25));
			}
		};
		addObject(bossBar);

		cameraPosition = new Vector3(0, -75);
	}

	int timeCounter = 0;
	boolean isFighting = false;

	float curCamZoom = 1.25f;

	int cakeCounter = 650;

	@Override
	public void updateScene() {
		if(GameLogic.IsGamePaused()) return;

		cameraZoom = GameMath.Lerp(cameraZoom, curCamZoom, 0.025f);

		if(bossStone.getHealth() <= 0) {
			cameraPosition = Vector3.Lerp(cameraPosition, StorymodePlayer.getPlayerInstance().getRawPosition(), 0.075f);
		}
		else {
			if(!isFighting) {
				cameraPosition = Vector3.Lerp(cameraPosition, new Vector3(0, 100), 0.025f);
			}else {
				cameraPosition = Vector3.Lerp(cameraPosition, new Vector3(0, -75), 0.025f);
			}
		}
		
		super.updateScene();

		if(isFighting)
			timeCounter++;

		if(isFighting)
			cakeCounter--;

		if(cakeCounter <= 0) {
			createCake();
			cakeCounter = (int)(Math.random() * 500 + 300);
		}

		if(timeCounter >= 150) {
			timeCounter = 0;
			attack();
		}

		bossBar.silentSetValue(bossStone.getHealth());
	}

	private void createCake() {
		Vector3 position;

		do {
			position = new Vector3(GameMath.RandomRange(-600, 600), GameMath.RandomRange(-600, 600));
		}while(bossStone.getBoundingBox().contains(position.getX(), position.getY()));

		addObject(new Cake(position.getX(), position.getY()));
	}

	GameObject activeAttack;

	private BossStone bossStone;

	void attack(){

		int attack = rng.nextInt(0, 10);
		Logger.logInfo("Attack " + attack);

		if(attack == 0) {
			activeAttack = new BossAttackPlus();
			addObject(activeAttack);
		}else if(attack == 1) {

			Thread t = new Thread() {
				public void run() {
					activeAttack = new BossAttackCross();
					addObject(activeAttack);

					try {
						sleep(500);
					} catch (InterruptedException e) {
					}

					activeAttack = new BossAttackCross();
					addObject(activeAttack);

					try {
						sleep(500);
					} catch (InterruptedException e) {
					}

					activeAttack = new BossAttackCross();
					addObject(activeAttack);

					try {
						sleep(500);
					} catch (InterruptedException e) {
					}
				};
			};
			t.start();

		}else if(attack == 2) {
			activeAttack = new BossAttackHorizontalParallel();
			addObject(activeAttack);
		}else if(attack == 3) {
			activeAttack =new BossAttackVerticalParallel();
			addObject(activeAttack);
		}else if(attack == 4) {
			if(activeAttack instanceof BossAttackBoneThrow)
				return;

			activeAttack = new BossAttackBoneThrow();
			addObject(activeAttack);
		}else if(attack == 5) {
			addObject(new BossAttackBorderBreak());
		}
	}

	public void endEndbossFight() {
		isFighting = false;

		StorymodeMain.saveData.addCompletedDungeon(-1);
		
		MusicManager.ForcePlayMusic(MusicManager.endboss_win, false);

		Dialog dialog = new Dialog(Resources.endbossDialogEnd) {
			@Override
			public void onDestroy() {
				super.onDestroy();
				destroyBoss();
				curCamZoom = 1.2f;

				AudioSource source = new AudioSource();
				source.setSpacial(false);
				source.SetVolume(1f);
				SceneManager.getActiveScene().addObject(source);
				source.Play(Resources.bossDeath);
			}
		};
		SceneManager.getActiveScene().addObject(dialog);
		dialog.showDialog();

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				if(SceneManager.getActiveScene() instanceof EndbossFight) {
					SceneManager.loadScene(new StorymodeMain());
				}
			}
		}, 20000);
	}

	protected void destroyBoss() {
		ParticleSystem s = new ParticleSystem(bossStone.getPosition().getX() + bossStone.getScale().getX() / 2,
				bossStone.getPosition().getY() + bossStone.getScale().getY() / 2);

		s.emitChunk = 500;
		s.initialParticleSpeedMax = new Vector3(10, 10);
		s.initialParticleSpeedMin = new Vector3(-10, -10);
		s.particleColorEnd = new Color(0, 0, 0, 0);
		s.particleSpeedMultiplier = 0.45f;
		s.particleGravity = 0.025f;
		s.particleLifetime = 750;
		s.particleLifetimeRandom = 150;
		s.initialParticleSize = 35;
		s.initialParticleSizeRandom = 50;
		s.endParticleSize = 0;

		SceneManager.getActiveScene().addObject(s);
		s.Init();

		destroyObject(bossStone);
	}

	public void startEndbossFight() {
		isFighting = true;
		curCamZoom = 0.525f;
	}
}
