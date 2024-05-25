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
import de.demoncore.utils.Vector3;

public class GUIObject extends GameObject {

	public GUIAlignment alignment = GUIAlignment.TopMiddle;
	
	GameActionListener l;
	
	public GUIObject(int posX, int posY, int width, int height) {
		super(posX, posY, width, height);

		l = new GameActionListener() {
			@Override
			public void OnMouseDown(MouseEvent e) {
				super.OnMouseDown(e);
				MouseDown(e);
			}
			
			@Override
			public void OnMouseUp(MouseEvent e) {
				super.OnMouseUp(e);
				MouseUp(e);
			}
			
			@Override
			public void OnEscapePressed() {
				super.OnEscapePressed();
				EscapePressed();
			}
		};
		
		KeyHandler.listeners.add(l);
		collisionEnabled = false;
	}
	
	@Override
	public void OnDestroy() {
		super.OnDestroy();
		KeyHandler.listeners.remove(l);
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

	protected boolean isHovering = false;

	public void MouseDown(MouseEvent e) {
		if(CheckIntersection(e.getX(), e.getY())) {
			
			for(GameObject o : SceneManager.GetActiveScene().GetSceneObjects()) {
				if(o instanceof GUIObject) {
					if(((GUIObject)o).CheckIntersection(e.getX(), e.getY())) {
						if(SceneManager.GetActiveScene().GetSceneObjects().indexOf(this) < SceneManager.GetActiveScene().GetSceneObjects().indexOf(o))
							if(o.enableRendering) return;
					}
				}
			}
			
			OnMouseDownUIObject(e);
		}
	}
	
	public void MouseUp(MouseEvent e) {
		if(CheckIntersection(e.getX(), e.getY())) {
			
			for(GameObject o : SceneManager.GetActiveScene().GetSceneObjects()) {
				if(o instanceof GUIObject) {
					if(((GUIObject)o).CheckIntersection(e.getX(), e.getY())) {
						if(SceneManager.GetActiveScene().GetSceneObjects().indexOf(this) < SceneManager.GetActiveScene().GetSceneObjects().indexOf(o)) {
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
	}

	void CheckHover() {
		if(CheckIntersection(
				(int)(MouseInfo.getPointerInfo().getLocation().getX() - Gui.GetScreenLocation().x),
				(int)(MouseInfo.getPointerInfo().getLocation().getY()  - Gui.GetScreenLocation().y))) {
			
			for(GameObject o : SceneManager.GetActiveScene().GetSceneObjects()) {
				if(o instanceof GUIObject) {
					if(((GUIObject)o).CheckIntersection(
							(int)MouseInfo.getPointerInfo().getLocation().getX(),
							(int)MouseInfo.getPointerInfo().getLocation().getY())) {
						
						if(SceneManager.GetActiveScene().GetSceneObjects().indexOf(this) < SceneManager.GetActiveScene().GetSceneObjects().indexOf(o)) {
							if(o.enableRendering) {
								OnMouseStopHoverOverUIObject();
								return;
							}
						}
					}
				}
			}
			
			if(!isHovering) {
				OnMouseHoverOverUIObject();
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



