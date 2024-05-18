package de.demoncore.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;


public class GUIText extends GUIObject {
	
	String text = "";
	Font font;
	
	public GUIText(int posX, int posY, String text, Font font, Color fontColor) {
		super(posX, posY, 250, 250);

		this.text = text;
		this.color = fontColor;
		this.font = font;
	}

	@Override
	public void Draw(Graphics2D g2d, int screenWidth, int screenHeight) {
		
		g2d.setFont(font);
		
		size.x = g2d.getFontMetrics().stringWidth(text);
		size.y = g2d.getFontMetrics().getMaxAscent() +
				g2d.getFontMetrics().getMaxDescent() +
				g2d.getFontMetrics().getMaxAdvance() / 2;
		
		super.Draw(g2d, screenWidth, screenHeight);
	
		Rectangle2D bounds = g2d.getFontMetrics().getStringBounds(text, g2d);
		g2d.drawString(text, GetUIPosition(screenWidth, screenHeight).x, (float) (GetUIPosition(screenWidth, screenHeight).y + bounds.getHeight()));
	}
	
	
	public void SetText(String text) {
		this.text = text;
	}
}
