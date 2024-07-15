package de.demoncore.gameObjects.storymode.bossattacks;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.util.Timer;
import java.util.TimerTask;

import de.demoncore.game.GameObject;
import de.demoncore.game.SceneManager;
import de.demoncore.game.Translation;
import de.demoncore.gameObjects.storymode.StorymodePlayer;
import de.demoncore.utils.GameMath;
import de.demoncore.utils.Logger;

public class BossAttackCross extends GameObject {

	public BossAttackCross() {
		super(0, 0, 1, 1);

		color = new Color(0, 0, 0, 0);
		
		// Update

		Thread thread = new Thread() {
			public void run() {

				linesWidth = 50;

				for(int i = 50; i < 100; i++) {

					try {
						sleep(15);
					} catch (InterruptedException e) {
						return;
					}

					linesWidth = i * 2;
					crossColor = GameMath.lerpColor(new Color(0, 0, 0, 0), new Color(0.7f, 0.7f, 0.7f, 0.7f), ((float)i - 50f) / 50);
				}

				crossColor = Color.white;
				SceneManager.getActiveScene().ShakeCamera(10, 10, 250);
				
				testDamageCross();
				
				for(int i = 50; i < 100; i++) {

					try {
						sleep(50);
					} catch (InterruptedException e) {
						return;
					}

					linesWidth = 50 - (i - 50);
					
					crossColor = GameMath.lerpColor(Color.white, new Color(0, 0, 0, 0), ((float)i - 50f) / 50);
				}

				SceneManager.getActiveScene().destroyObject(getObj());
			};
		};
		thread.start();
	}

	void testDamageCross() {      	
		if(cross1Area.intersects(StorymodePlayer.getPlayerInstance().getBoundingBox()) ||
				cross2Area.intersects(StorymodePlayer.getPlayerInstance().getBoundingBox())){
			Logger.logInfo("Player Damage");
			StorymodePlayer.getPlayerInstance().damage(1, this, Translation.get("deathReason.endbossLine"));
		}
	}

	BossAttackCross getObj() {
		return this;
	}


	Rectangle2D cross1 = new Rectangle2D.Float(0 - 50, 0 - 2000, 100, 4000);
	Rectangle2D cross2 = new Rectangle2D.Float(0 - 2000, 0 - 50, 4000, 100);

	Area cross1Area;
	Area cross2Area;

	Color crossColor = new Color(0, 0, 0, 0);

	float linesWidth = 100;

	@Override
	public void draw(Graphics2D g2d, int screenWidth, int screenHeight) {
		g2d.setColor(crossColor);
		g2d.fill(cross1Area);
		g2d.fill(cross2Area);
	}

	@Override
	public void update() {
		super.update();

		cross1 = new Rectangle2D.Float(0 - linesWidth / 2, 0 - 2000, linesWidth, 4000);
		cross2 = new Rectangle2D.Float(0 - 2000, 0 - linesWidth / 2, 4000, linesWidth);

		AffineTransform affine3 = new AffineTransform();
		affine3.rotate(Math.toRadians(45), 0, 0);
		AffineTransform affine4 = new AffineTransform();
		affine4.rotate(Math.toRadians(45), 0, 0);

		cross1Area = new Area(cross1);
		cross2Area = new Area(cross2);

		cross1Area = cross1Area.createTransformedArea(affine3);
		cross2Area = cross2Area.createTransformedArea(affine4);
	}
}
