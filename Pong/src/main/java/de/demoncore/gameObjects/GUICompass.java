package de.demoncore.gameObjects;

import java.awt.Graphics2D;

import de.demoncore.gameObjects.storymode.StorymodePlayer;
import de.demoncore.gui.GUIAlignment;
import de.demoncore.gui.GUIObject;
import de.demoncore.utils.Resources;
import de.demoncore.utils.Vector3;

public class GUICompass extends GUIObject {

	public GUICompass() {
		super(-175, 175, 250, 250);
	
		alignment = GUIAlignment.TopRight;
	}
	
	@Override
	public void draw(Graphics2D g2d, int screenWidth, int screenHeight) {
		super.draw(g2d, screenWidth, screenHeight);
		
		g2d.drawImage(Resources.compassEmpty.getTexture(), getUIPosition(screenWidth, screenHeight).getX(),
				getUIPosition(screenWidth, screenHeight).getY(),
				getScale().getX(), getScale().getY(), null);
		
		g2d.rotate(Math.toRadians(getAngle(StorymodePlayer.getPlayerInstance().getRawPosition(), Vector3.zero())), 
				getUIPosition(screenWidth, screenHeight).getX() + getScale().getX() / 2,
				getUIPosition(screenWidth, screenHeight).getY() + getScale().getY() / 2);
		
		g2d.drawImage(Resources.compassNeedle.getTexture(),
				getUIPosition(screenWidth, screenHeight).getX() + getScale().getX() / 2 - (3 * 8) / 2,
				getUIPosition(screenWidth, screenHeight).getY() + getScale().getY() / 2 - (13 * 8) / 2,
				3 * 8, 13 * 8, null);
		
		g2d.rotate(Math.toRadians(-getAngle(StorymodePlayer.getPlayerInstance().getRawPosition(), Vector3.zero())), 
				getUIPosition(screenWidth, screenHeight).getX() + getScale().getX() / 2,
				getUIPosition(screenWidth, screenHeight).getY() + getScale().getY() / 2);
	}	
	
	public float getAngle(Vector3 start, Vector3 target) {
	    float angle = (float) Math.toDegrees(Math.atan2(target.y - start.y, target.x - start.x));

	    if(angle < 0){
	        angle += 360;
	    }

	    return angle + 90;
	}
	
}
