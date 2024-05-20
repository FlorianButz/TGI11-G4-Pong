package de.demoncore.actions;

import java.awt.event.MouseEvent;

public interface GameActionListener {
	public void OnMouseDown(MouseEvent e);
	public void OnMouseUp(MouseEvent e);
	
	public void OnEscapePressed();
}
