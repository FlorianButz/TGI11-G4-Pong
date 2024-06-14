package de.demoncore.gui;

import java.awt.Color;
import java.awt.Graphics2D;

public class GUIRectangle extends GUIObject {

	public GUIRectangle(int posX, int posY, int width, int height, Color color) {
		super(posX, posY, width, height);
		this.color = color;
	}

	@Override
	public void Draw(Graphics2D g2d, int screenWidth, int screenHeight) {
		super.Draw(g2d, screenWidth, screenHeight);
		
		g2d.fillRect((int)getUIPosition(screenWidth, screenHeight).x, (int)getUIPosition(screenWidth, screenHeight).y, (int)getScale().x, (int)getScale().y);
	}
	
}
