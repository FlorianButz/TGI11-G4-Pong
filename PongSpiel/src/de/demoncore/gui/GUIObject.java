package de.demoncore.gui;

import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import de.demoncore.actions.GameActionListener;
import de.demoncore.actions.KeyHandler;
import de.demoncore.game.GameObject;
import de.demoncore.utils.Vector3;

public class GUIObject extends GameObject implements GameActionListener {

	public GUIAlignment alignment = GUIAlignment.TopMiddle;
	
	public GUIObject(int posX, int posY, int width, int height) {
		super(posX, posY, width, height);
	
		this.renderSpecial = true;

		KeyHandler.listeners.add(this);
	}
	
	@Override
	public void OnDestroy() {
		super.OnDestroy();
		KeyHandler.listeners.remove(this);
	}
	
	public Vector3 GetUIPosition(int screenWidth, int screenHeight) {
		
		switch(alignment)
		{
		
		case Center:
			return GetPosition().add(new Vector3(screenWidth / 2, screenHeight / 2));
			
		case TopLeft:
			return GetPosition().add(new Vector3(0, 0));
		case TopMiddle:
			return GetPosition().add(new Vector3(screenWidth / 2, 0));
		case TopRight:
			return GetPosition().add(new Vector3(screenWidth, 0));
			
		case MiddleLeft:
			return GetPosition().add(new Vector3(0, screenHeight / 2));
		case MiddleRight:
			return GetPosition().add(new Vector3(screenWidth, screenHeight / 2));
			
		case DownLeft:
			return GetPosition().add(new Vector3(0, screenHeight));
		case DownMiddle:
			return GetPosition().add(new Vector3(screenWidth / 2, screenHeight));
		case DownRight:
			return GetPosition().add(new Vector3(screenWidth, screenHeight));
			
		}
		
		return Vector3.zero();
	}
	
	public void Draw(Graphics2D g2d, int screenWidth, int screenHeight) {
		g2d.setColor(color);
		//g2d.fillRect((int)GetUIPosition(screenWidth, screenHeight).x, (int)GetUIPosition(screenWidth, screenHeight).y, (int)size.x, (int)size.y);
	}

	protected boolean isHovering = false;

	@Override
	public void OnMouseDown(MouseEvent e) {
		if(CheckIntersection(e.getX(), e.getY())) {
			OnMouseClickUIObject(e);
		}
	}
	
	public void OnMouseClickUIObject(MouseEvent e) {
		
	}
	
	public void OnMouseHoverOverUIObject() {
		isHovering = true;
	}
	
	public void OnMouseStopHoverOverUIObject() {
		isHovering = false;
	}
	
	public boolean CheckIntersection(int x, int y) {
		Vector3 screenPos = this.GetUIPosition(
				(int)Gui.GetScreenDimensions().x,
				(int)Gui.GetScreenDimensions().y
				);
		
		Rectangle r1 = new Rectangle(
				(int)screenPos.x,
				(int) ((int) screenPos.y),
				(int)this.size.x,
				(int)this.size.y);
		
		Rectangle r2 = new Rectangle(x, y, 15, 15);
		return r1.intersects(r2);
	}
	
	@Override
	public void Update() {
		super.Update();
		
		if(CheckIntersection(
				(int)(MouseInfo.getPointerInfo().getLocation().getX() - Gui.GetScreenLocation().x),
				(int)(MouseInfo.getPointerInfo().getLocation().getY()  - Gui.GetScreenLocation().y))) {
			if(!isHovering)
				OnMouseHoverOverUIObject();
		}
		else if(isHovering)
			OnMouseStopHoverOverUIObject();
	}
}



