package de.demoncore.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.StringTokenizer;

public class GUITextArea extends GUIText {

	public int maxLetterInOneLine = 200;
	public int newLineSpacing = 20;
	
	public GUITextArea(int posX, int posY, String text, Font font, Color fontColor) {
		super(posX, posY, text, font, fontColor);
	}

	@Override
	public void Draw(Graphics2D g2d, int screenWidth, int screenHeight) {
		g2d.setFont(font);
		g2d.setColor(color);
		
		size.x = g2d.getFontMetrics().stringWidth(text);
		size.y = g2d.getFontMetrics().getMaxAscent() +
				g2d.getFontMetrics().getMaxDescent() +
				g2d.getFontMetrics().getMaxAdvance() / 2;
		
		String newText = AddLinebreaks(text, maxLetterInOneLine);

		Rectangle2D bounds = g2d.getFontMetrics().getStringBounds(text, g2d);
		
		int counter = 0;
	    for (String line : newText.split("\n")) {
	    	g2d.drawString(line, GetUIPosition(screenWidth, screenHeight).x,
	    			(float) (GetUIPosition(screenWidth, screenHeight).y + bounds.getHeight() + (g2d.getFontMetrics().getHeight() + newLineSpacing) * counter));
	    	counter++;
	    }
	}
	
	public String AddLinebreaks(String input, int maxLineLength) {
	    StringTokenizer tok = new StringTokenizer(input, " ");
	    StringBuilder output = new StringBuilder(input.length());
	    int lineLen = 0;
	    while (tok.hasMoreTokens()) {
	        String word = tok.nextToken();
	        output.append(" ");
	        if (lineLen + word.length() > maxLineLength) {
	            output.append("\n");
	            lineLen = 0;
	        }
	        output.append(word);
	        lineLen += word.length();
	    }
	    return output.toString();
	}
}