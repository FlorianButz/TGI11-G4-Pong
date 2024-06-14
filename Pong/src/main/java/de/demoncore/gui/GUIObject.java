package de.demoncore.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import de.demoncore.actions.GameActionListener;
import de.demoncore.actions.KeyHandler;
import de.demoncore.game.GameObject;
import de.demoncore.game.SceneManager;
import de.demoncore.game.Settings;
import de.demoncore.utils.Logger;
import de.demoncore.utils.Vector3;

public class GUIObject extends GameObject {

	public GUIAlignment alignment = GUIAlignment.TopMiddle;
	public boolean doUIScale = true;
	
	GameActionListener l;
	
	public GUIObject(int posX, int posY, int width, int height) {
		super(posX, posY, width, height);

		l = new GameActionListener() {
			@Override
			public void onMouseDown(MouseEvent e) {
				super.onMouseDown(e);
				MouseDown(e);
			}
			
			@Override
			public void onMouseUp(MouseEvent e) {
				super.onMouseUp(e);
				MouseUp(e);
			}
			
			@Override
			public void onEscapePressed() {
				super.onEscapePressed();
				EscapePressed();
			}
		};
		
		KeyHandler.listeners.add(l);
		collisionEnabled = false;
	}
	
	@Override
	public Vector3 getScale() {
		return size;
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		KeyHandler.listeners.remove(l);
	}
	
	@Override
	public Vector3 getPosition() {	// Gibt die korrekte Position vom GameObject zurück
		return position.subtract(new Vector3(
				getScale().x * anchorPoint.x + localPosition.x,
				getScale().y * anchorPoint.y + localPosition.y
				));
	}
	
	public Vector3 getUIPosition(int screenWidth, int screenHeight) {
		
		switch(alignment)
		{
		
		case Center:
			return new Vector3(screenWidth / 2, screenHeight / 2).add(getPosition());
			
		case TopLeft:
			return new Vector3(0, 0).add(getPosition());
		case TopMiddle:
			return new Vector3(screenWidth / 2, 0).add(getPosition());
		case TopRight:
			return new Vector3(screenWidth / 2, screenHeight / 2).add(getPosition());
			
		case MiddleLeft:
			return new Vector3(0, screenHeight / 2).add(getPosition());
		case MiddleRight:
			return new Vector3(screenWidth, screenHeight / 2).add(getPosition());
			
		case DownLeft:
			return new Vector3(0, screenHeight).add(getPosition());
		case DownMiddle:
			return new Vector3(screenWidth / 2, screenHeight).add(getPosition());
		case DownRight:
			return new Vector3(screenWidth, screenHeight).add(getPosition());
			
		}
		
		return Vector3.zero();
	}

	protected boolean isHovering = false;

	public void MouseDown(MouseEvent e) {
		if(CheckIntersection(e.getX(), e.getY())) {
			
			for(GameObject o : SceneManager.getActiveScene().getSceneObjects()) {
				if(o instanceof GUIObject) {
					if(((GUIObject)o).CheckIntersection(e.getX(), e.getY())) {
						if(SceneManager.getActiveScene().getSceneObjects().indexOf(this) < SceneManager.getActiveScene().getSceneObjects().indexOf(o))
							if(o.enableRendering) return;
					}
				}
			}
			
			OnMouseDownUIObject(e);
		}
	}
	
	public void MouseUp(MouseEvent e) {
		if(CheckIntersection(e.getX(), e.getY())) {
			
			for(GameObject o : SceneManager.getActiveScene().getSceneObjects()) {
				if(o instanceof GUIObject) {
					if(((GUIObject)o).CheckIntersection(e.getX(), e.getY())) {
						if(SceneManager.getActiveScene().getSceneObjects().indexOf(this) < SceneManager.getActiveScene().getSceneObjects().indexOf(o)) {
							if(o.enableRendering) return;
						}
					}
				}
			}
			
			OnMouseUpUIObject(e);
		}
	}
	
	protected void EscapePressed() {
		
	}
	
	public void OnMouseDownUIObject(MouseEvent e) {
		
	}
	
	public void OnMouseUpUIObject(MouseEvent e) {
		
	}
	
	public void onMouseHoverOverUIObject() {
		isHovering = true;
	}
	
	public void OnMouseStopHoverOverUIObject() {
		isHovering = false;
	}
	
	Vector3 scaleUI(Vector3 vector) {
		
		Vector3 vec = vector;
		
		float sX = 0;
		float sY = 0;
		
		switch(alignment) {
		case TopLeft:
			sX = 0;
			sY = 0;
			break;
		case TopMiddle:
			sX = Gui.GetScreenDimensions().x / 2;
			sY = 0;
			break;
		case TopRight:
			sX = Gui.GetScreenDimensions().x;
			sY = 0;
			break;
			
		case MiddleLeft:
			sX = 0;
			sY = Gui.GetScreenDimensions().y / 2;
			break;
		case Center:
			sX = Gui.GetScreenDimensions().x / 2;
			sY = Gui.GetScreenDimensions().y / 2;
			break;
		case MiddleRight:
			sX = Gui.GetScreenDimensions().x;
			sY = Gui.GetScreenDimensions().y / 2;
			break;
			
		case DownLeft:
			sX = 0;
			sY = Gui.GetScreenDimensions().y;
			break;
		case DownMiddle:
			sX = Gui.GetScreenDimensions().x / 2;
			sY = Gui.GetScreenDimensions().y;
			break;
		case DownRight:
			sX = Gui.GetScreenDimensions().x;
			sY = Gui.GetScreenDimensions().y;
			break;
		}

		vec.x = vec.x - sX;
		vec.y = vec.y - sY;
		
		vec.x = vec.x * Settings.getUIScale();
		vec.y = vec.y * Settings.getUIScale();
		
		vec.x = vec.x + sX;
		vec.y = vec.y + sY;
		
		return vec;
	}
	
	public boolean CheckIntersection(int x, int y) {
		Vector3 screenPos = this.getUIPosition(
				(int)Gui.GetScreenDimensions().x,
				(int)Gui.GetScreenDimensions().y
				);
		
		screenPos = scaleUI(screenPos);
		
		Rectangle r1 = new Rectangle(
				(int)(screenPos.x),
				(int)(screenPos.y),
				(int)(this.getScale().x * Settings.getUIScale()),
				(int)(this.getScale().y * Settings.getUIScale()));
		
		Rectangle r2 = new Rectangle(x, y, 15, 15);
		return r1.intersects(r2);
	}
	
	@Override
	public void update() {
		super.update();
	}

	void CheckHover() {
		if(CheckIntersection(
				(int)(MouseInfo.getPointerInfo().getLocation().getX() - Gui.GetScreenLocation().x),
				(int)(MouseInfo.getPointerInfo().getLocation().getY()  - Gui.GetScreenLocation().y))) {
			
			for(GameObject o : SceneManager.getActiveScene().getSceneObjects()) {
				if(o instanceof GUIObject) {
					if(((GUIObject)o).CheckIntersection(
							(int)MouseInfo.getPointerInfo().getLocation().getX(),
							(int)MouseInfo.getPointerInfo().getLocation().getY())) {
						
						if(SceneManager.getActiveScene().getSceneObjects().indexOf(this) < SceneManager.getActiveScene().getSceneObjects().indexOf(o)) {
							if(o.enableRendering) {
								OnMouseStopHoverOverUIObject();
								return;
							}
						}
					}
				}
			}
			
			if(!isHovering) {
				onMouseHoverOverUIObject();
			}
		}
		else if(isHovering)
			OnMouseStopHoverOverUIObject();
	}
	
	@Override
	public void Draw(Graphics2D g2d, int screenWidth, int screenHeight) {
		g2d.setColor(color);
		
		CheckHover();
	}

	
	@Override
	public boolean CheckDistanceCulled(Rectangle viewport) {
		return false;
	}
}



