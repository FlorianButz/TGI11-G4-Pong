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

public class BossAttackHorizontalParallel extends GameObject {

	public BossAttackHorizontalParallel() {
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
					plusColor = GameMath.lerpColor(new Color(0, 0, 0, 0), new Color(0.7f, 0.7f, 0.7f, 0.7f), ((float)i - 50f) / 50);
				}

				plusColor = Color.white;
				SceneManager.getActiveScene().ShakeCamera(10, 10, 250);
				
				testDamagePlus();
				
				for(int i = 50; i < 100; i++) {

					try {
						sleep(50);
					} catch (InterruptedException e) {
						return;
					}

					linesWidth = 50 - (i - 50);
					
					plusColor = GameMath.lerpColor(Color.white, new Color(0, 0, 0, 0), ((float)i - 50f) / 50);
				}

				SceneManager.getActiveScene().destroyObject(getObj());
			};
		};
		thread.start();
	}

	void testDamagePlus() {
		if(plus1Area.intersects(StorymodePlayer.getPlayerInstance().getBoundingBox()) ||
				plus2Area.intersects(StorymodePlayer.getPlayerInstance().getBoundingBox())){
			Logger.logInfo("Player Damage");
			StorymodePlayer.getPlayerInstance().damage(1, this, Translation.get("deathReason.endbossLine"));
		}
	}

	BossAttackHorizontalParallel getObj() {
		return this;
	}

	Rectangle2D plus1 = new Rectangle2D.Float(0 - 50, 0 - 2000, 100, 4000);
	Rectangle2D plus2 = new Rectangle2D.Float(0 - 2000, 0 - 50, 4000, 100);

	Area plus1Area;
	Area plus2Area;

	Color plusColor = new Color(0, 0, 0, 0);

	float linesWidth = 100;

	@Override
	public void draw(Graphics2D g2d, int screenWidth, int screenHeight) {
		g2d.setColor(plusColor);
		g2d.fill(plus1Area);
		g2d.fill(plus2Area);
	}

	@Override
	public void update() {
		super.update();

		plus1 = new Rectangle2D.Float(0 - linesWidth / 2, 0 - 2000, linesWidth, 4000);
		plus2 = new Rectangle2D.Float(0 - 2000, 0 - linesWidth / 2, 4000, linesWidth);

		AffineTransform affine1 = new AffineTransform();
		AffineTransform affine2 = new AffineTransform();

		plus1Area = new Area(plus1);
		plus2Area = new Area(plus2);

		plus1Area = plus1Area.createTransformedArea(affine1);
		plus2Area = plus2Area.createTransformedArea(affine2);
	}
}
