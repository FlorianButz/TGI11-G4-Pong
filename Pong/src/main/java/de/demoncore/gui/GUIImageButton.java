package de.demoncore.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import de.demoncore.game.Translation;
import de.demoncore.rendering.Draw;
import de.demoncore.sprites.Sprite;
import de.demoncore.utils.GameMath;
import de.demoncore.utils.Resources;
import de.demoncore.utils.Vector3;

public class GUIImageButton extends GUIButton {

	Sprite normalSprite;
	Sprite hoverSprite;
	
	public GUIImageButton(int posX, int posY, int width, int height, Sprite normalSprite, Sprite hoverSprite, GUIButtonClickEvent e) {
		super(posX, posY, width, height, Translation.literal(""), Resources.uiFont, e);	
		this.normalSprite = normalSprite;
		this.hoverSprite = hoverSprite;
		
		textCurrentSize = 1f;
		textHoverSize = 0.35f;
		textNormalSize = 0.8f;
		
		hoverTextColor = Color.white;
	}
	
	@Override
	public void Draw(Graphics2D g2d, int screenWidth, int screenHeight) {
		super.Draw(g2d, screenWidth, screenHeight);

		g2d.setColor(currentColor);
		g2d.fillRect((int)GetUIPosition(screenWidth, screenHeight).x, (int)GetUIPosition(screenWidth, screenHeight).y, (int)size.x, (int)size.y);
		
		g2d.setStroke(new BasicStroke(4));
		g2d.setColor(normalColor);
		g2d.drawRect((int)GetUIPosition(screenWidth, screenHeight).x, (int)GetUIPosition(screenWidth, screenHeight).y, (int)size.x, (int)size.y);
		
		g2d.setColor(currentTextColor);

		this.size.x = currentButtonWidth;
		this.size.y = currentButtonWidth;

		Color tint = new Color(
				currentTextColor.getRed(),
				currentTextColor.getGreen(),
				currentTextColor.getBlue(),
				0);
		
		Vector3 pos = GetUIPosition(screenWidth, screenHeight)
				.add(new Vector3(
				GetScale().x * anchorPoint.x + localPosition.x,
				GetScale().y * anchorPoint.y + localPosition.y
				))
				.subtract(new Vector3(
					GetScale().x * textCurrentSize * anchorPoint.x + localPosition.x,
					GetScale().y * textCurrentSize * anchorPoint.y + localPosition.y
				));;
		
		if(!isHovering) {
			g2d.drawImage(normalSprite.getTexture(),
					(int)pos.x,
					(int)pos.y,
					(int)(size.x * textCurrentSize),
					(int)(size.y * textCurrentSize),
					null);
		}else {
			g2d.drawImage(hoverSprite.getTexture(),
					(int)pos.x,
					(int)pos.y,
					(int)(size.x * textCurrentSize),
					(int)(size.y * textCurrentSize),
					null);
		}
		
		float fps = Draw.GetFramesPerSecond();
		
		if(this.isHovering) {
			currentColor = GameMath.LerpColor(currentColor, hoverColor, colorTransitionSmoothing / fps);
			currentTextColor = GameMath.LerpColor(currentTextColor, hoverTextColor, colorTransitionSmoothing / fps);

			textCurrentSize = GameMath.Lerp(textCurrentSize, textHoverSize, sizeTransitionSmoothing / fps);
			
			currentButtonWidth = GameMath.Lerp(currentButtonWidth, normalButtonWidth + hoverButtonWidth, sizeTransitionSmoothing / fps);
		}
		else {
			
			currentColor = GameMath.LerpColor(currentColor, normalColor, colorTransitionSmoothing / fps);
			currentTextColor = GameMath.LerpColor(currentTextColor, normalTextColor, colorTransitionSmoothing / fps);

			textCurrentSize = GameMath.Lerp(textCurrentSize, textNormalSize, sizeTransitionSmoothing / fps);
			
			currentButtonWidth = GameMath.Lerp(currentButtonWidth, normalButtonWidth, sizeTransitionSmoothing / fps);
		}
	}
}
