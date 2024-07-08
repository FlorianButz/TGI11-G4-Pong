package de.demoncore.gui;

import java.awt.Color;
import java.awt.Graphics2D;

public class ValueBarRenderable extends Renderable {

	protected float value = 0;

	public Color fillColor;
	public Color borderColor;
	
	public float borderSize = 1;
	
	public ValueBarRenderable(int posX, int posY, int width, int height, float minValue, float maxValue) {
		super(posX, posY, width, height);
		value = minValue;
	}
	
	@Override
	public void draw(Graphics2D g2d, int screenWidth, int screenHeight) {
		g2d.setColor(color);
		g2d.fillRect((int)getPosition().x, (int)getPosition().y, (int)getScale().x, (int)getScale().y);
	}
	
	public float getValue() {
		return value;
	}
	
	public void setValue(float value) {
		this.value = value;
	}

}
