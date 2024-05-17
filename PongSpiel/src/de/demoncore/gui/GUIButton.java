package de.demoncore.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import de.demoncore.game.SceneManager;
import de.demoncore.utils.GameMath;

public class GUIButton extends GUIObject {

	public Color normalColor = Color.white;
	public Color hoverColor = Color.black;
	
	public Color currentColor = normalColor;
	
	public Color normalTextColor = Color.black;
	public Color hoverTextColor = Color.white;
	
	public Color currentTextColor = normalTextColor;

	public float colorTransitionSmoothing = 0.055f;
	public float sizeTransitionSmoothing = 0.075f;

	
	public float textNormalSize;
	public float textHoverSize = 5F;
	public float textCurrentSize;
	
	String text = "";
	Font font;
	
	public GUIButton(int posX, int posY, int width, int height, String text, Font font, Color fontColor) {
		super(posX, posY, width, height);
		
		this.text = text;
		this.color = fontColor;
		this.font = font;
		
		textNormalSize = font.getSize();
		textCurrentSize = textNormalSize;
	}
	
	@Override
	public void Draw(Graphics2D g2d, int screenWidth, int screenHeight) {
		super.Draw(g2d, screenWidth, screenHeight);

		g2d.setColor(currentColor);
		g2d.fillRect((int)GetUIPosition(screenWidth, screenHeight).x, (int)GetUIPosition(screenWidth, screenHeight).y, (int)size.x, (int)size.y);
		
		g2d.setStroke(new BasicStroke(4));
		g2d.setColor(normalColor);
		g2d.drawRect((int)GetUIPosition(screenWidth, screenHeight).x, (int)GetUIPosition(screenWidth, screenHeight).y, (int)size.x, (int)size.y);
		
		g2d.setFont(font.deriveFont(textCurrentSize));
		g2d.setColor(currentTextColor);
		
		Rectangle2D bounds = g2d.getFontMetrics().getStringBounds(text, g2d);
		g2d.drawString(text, (int)(GetUIPosition(screenWidth, screenHeight).x + this.size.x / 2 - bounds.getWidth() / 2),
				(int)(GetUIPosition(screenWidth, screenHeight).y + this.size.y / 2 +  textCurrentSize / 3)
				);
	}
	
	@Override
	public void OnMouseClickUIObject(MouseEvent e) {
		super.OnMouseClickUIObject(e);

		currentColor = normalColor;
		currentTextColor = normalTextColor;
		
		SceneManager.GetActiveScene().ShakeCamera(50, 1, 25);
	}
	
	@Override
	public void Update() {
		super.Update();

		if(this.isHovering) {
			currentColor = GameMath.LerpColor(currentColor, hoverColor, colorTransitionSmoothing);
			currentTextColor = GameMath.LerpColor(currentTextColor, hoverTextColor, colorTransitionSmoothing);
		
			textCurrentSize = GameMath.Lerp(textCurrentSize, textNormalSize + textHoverSize, sizeTransitionSmoothing);
		}
		else {
			
			currentColor = GameMath.LerpColor(currentColor, normalColor, colorTransitionSmoothing);
			currentTextColor = GameMath.LerpColor(currentTextColor, normalTextColor, colorTransitionSmoothing);
	
			textCurrentSize = GameMath.Lerp(textCurrentSize, textNormalSize, sizeTransitionSmoothing);
		}
	
	}
}
