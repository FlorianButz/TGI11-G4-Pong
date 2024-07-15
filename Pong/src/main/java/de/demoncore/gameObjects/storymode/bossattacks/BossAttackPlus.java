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

public class BossAttackPlus extends BossAttackLines {
	
	public BossAttackPlus() {
		super();
		lineWidthMulti = 2.5f;
	}
	
	@Override
	void createAreas() {
		line1 = new Rectangle2D.Float(0 - 2000, 0 - linesWidth / 2, 4000, linesWidth);
		line2 = new Rectangle2D.Float(0 - linesWidth / 2, 0 - 2000, linesWidth, 4000);

		AffineTransform affine1 = new AffineTransform();
		AffineTransform affine2 = new AffineTransform();

		line1Area = new Area(line1);
		line2Area = new Area(line2);

		line1Area = line1Area.createTransformedArea(affine1);
		line2Area = line2Area.createTransformedArea(affine2);
	}
	
}
