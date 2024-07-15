package de.demoncore.gameObjects.storymode.bossattacks;

import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

public class BossAttackVerticalParallel extends BossAttackLines {
	
	@Override
	void createAreas() {
		line1 = new Rectangle2D.Float(250 - linesWidth / 2, -2000, linesWidth, 4000);
		line2 = new Rectangle2D.Float(-250 - linesWidth / 2, -2000, linesWidth, 4000);

		AffineTransform affine1 = new AffineTransform();
		AffineTransform affine2 = new AffineTransform();

		line1Area = new Area(line1);
		line2Area = new Area(line2);

		line1Area = line1Area.createTransformedArea(affine1);
		line2Area = line2Area.createTransformedArea(affine2);
	}
	
}
