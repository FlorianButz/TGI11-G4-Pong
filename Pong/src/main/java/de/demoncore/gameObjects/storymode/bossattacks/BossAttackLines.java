package de.demoncore.gameObjects.storymode.bossattacks;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

import de.demoncore.audio.AudioSource;
import de.demoncore.game.GameObject;
import de.demoncore.game.SceneManager;
import de.demoncore.game.Translation;
import de.demoncore.gameObjects.storymode.StorymodePlayer;
import de.demoncore.utils.GameMath;
import de.demoncore.utils.Logger;
import de.demoncore.utils.Resources;

public class BossAttackLines extends GameObject {

	AudioSource source;

	public BossAttackLines() {
		super(0, 0, 1, 1);

		color = new Color(0, 0, 0, 0);
		collisionEnabled = false;

		// Update

		Logger.logInfo("Add attack " + getClass().getName());

		source = new AudioSource(this);
		source.setSpacial(false);
		source.SetVolume(0.7f);

		SceneManager.getActiveScene().addObject(source);

		Thread thread = new Thread() {
			public void run() {

				linesWidth = 0;

				source.Play(Resources.bossLaserCharge);

				for(int i = 50; i < 100; i++) {

					try {
						sleep(15);
					} catch (InterruptedException e) {
						return;
					}

					linesWidth = GameMath.Lerp(linesWidth, 100 * lineWidthMulti, 0.05f);
					linesColor = GameMath.lerpColor(new Color(0, 0, 0, 0), new Color(0.5f, 0.5f, 0.5f, 0.7f), ((float)i - 50f) / 50);
				}

				linesColor = Color.white;
				SceneManager.getActiveScene().ShakeCamera(10, 10, 250);

				testForPlayerDamage();
				source.Play(Resources.bossLaserAttack);

				for(int i = 50; i < 100; i++) {

					try {
						sleep(50);
					} catch (InterruptedException e) {
						return;
					}

					linesWidth = GameMath.Lerp(linesWidth, 0, 0.25f);
					linesColor = GameMath.lerpColor(Color.white, new Color(0, 0, 0, 0), ((float)i - 50f) / 50);
				}

				SceneManager.getActiveScene().destroyObject(getObj());
			};
		};
		thread.start();
	}

	void testForPlayerDamage() {
		if(line1Area.intersects(StorymodePlayer.getPlayerInstance().getBoundingBox()) ||
				line2Area.intersects(StorymodePlayer.getPlayerInstance().getBoundingBox())){
			Logger.logInfo("Player Damage");
			StorymodePlayer.getPlayerInstance().damage(1, this, Translation.get("deathReason.endbossLine"));
		}
	}

	BossAttackLines getObj() {
		return this;
	}

	Rectangle2D line1;
	Rectangle2D line2;

	Area line1Area;
	Area line2Area;

	Color linesColor = new Color(0, 0, 0, 0);

	float linesWidth = 100;
	float lineWidthMulti = 1f;

	@Override
	public void draw(Graphics2D g2d, int screenWidth, int screenHeight) {

		if(line1 != null && line2 != null) {
			g2d.setColor(linesColor);
			g2d.fill(line1Area);
			g2d.fill(line2Area);
		}
	}

	@Override
	public void update() {
		super.update();

		createAreas();
	}

	void createAreas() {
	}

}
