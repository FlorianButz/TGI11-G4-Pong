package de.demoncore.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import de.demoncore.game.GameLogic;
import de.demoncore.game.SceneManager;
import de.demoncore.rendering.Draw;
import de.demoncore.utils.GameMath;

public class ValueBarRenderable extends Renderable {

	protected float value = 0;
	protected float minValue = 0;
	protected float maxValue = 0;

	float displayValue;
	
	public Color fillColor;
	public Color borderColor;
	
	public float borderSize = 1;
	
	public ValueBarRenderable(int posX, int posY, int width, int height, float minValue, float maxValue) {
		super(posX, posY, width, height);
		value = maxValue;
		this.minValue = minValue;
		this.maxValue = maxValue;
		
		color = Color.red;
		borderColor = Color.darkGray;
		fillColor = Color.green;
		
		displayValue = value;
	}
	
	@Override
	public void draw(Graphics2D g2d, int screenWidth, int screenHeight) {
		
		displayValue = GameMath.Lerp(displayValue, value, 3.5f / Draw.GetFramesPerSecond());
		
		g2d.setColor(color);
		g2d.fillRect((int)getPosition().x, (int)getPosition().y, (int)getScale().x, (int)getScale().y);
		
		g2d.setColor(fillColor);
		g2d.fillRect((int)getPosition().x, (int)getPosition().y, (int)(getScale().x * GameMath.RemapValue(displayValue, minValue, maxValue, 0f, 1f)), (int)getScale().y);

		g2d.setStroke(new BasicStroke(borderSize));
		g2d.setColor(borderColor);
		g2d.drawRect((int)getPosition().x, (int)getPosition().y, (int)getScale().x, (int)getScale().y);
	}
	
	public float getValue() {
		return value;
	}
	
	public void setValue(float value) {
		if(value != this.value) {
			this.value = value;
			
			SceneManager.getActiveScene().ShakeCamera(20, 10, 40);
		}
	}

}
