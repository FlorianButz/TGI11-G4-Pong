package de.demoncore.gameObjects.storymode.bossattacks;

import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

import de.demoncore.gameObjects.storymode.StorymodePlayer;

public class BossAttackCross extends BossAttackLines {

	private int xPos;
	private int yPos;

	public BossAttackCross() {
		super();
		xPos = StorymodePlayer.getPlayerInstance().getPosition().getX();
		yPos = StorymodePlayer.getPlayerInstance().getPosition().getY();
	}
	
	@Override
	void createAreas() {		
		line1 = new Rectangle2D.Float(xPos - linesWidth / 2, yPos - 2000, linesWidth, 4000);
		line2 = new Rectangle2D.Float(xPos - 2000, yPos - linesWidth / 2, 4000, linesWidth);

		AffineTransform affine3 = new AffineTransform();
		affine3.rotate(Math.toRadians(45), xPos, yPos);
		AffineTransform affine4 = new AffineTransform();
		affine4.rotate(Math.toRadians(45), xPos, yPos);

		line1Area = new Area(line1);
		line2Area = new Area(line2);

		line1Area = line1Area.createTransformedArea(affine3);
		line2Area = line2Area.createTransformedArea(affine4);
	}
	
}
