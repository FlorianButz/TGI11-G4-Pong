package de.demoncore.actions;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import de.demoncore.game.GameLogic;
import de.demoncore.game.ParticleSystem;
import de.demoncore.game.SceneManager;
import de.demoncore.utils.Vector3;

public class KeyHandler implements KeyListener, MouseListener {

	public static List<GameActionListener> listeners = new ArrayList<GameActionListener>();
	
	GameLogic gameLogic;
	List<Integer> pressedKeys = new ArrayList<Integer>();

	public Vector3 mouse = Vector3.zero();
	
	public static Vector3 playerInput = Vector3.zero(); // Der Input von dem Spieler (welche taste gedrueckt) in form eines Vektors der bei Start auf 0 ist
	
	public KeyHandler(GameLogic gameLogic) {
		this.gameLogic = gameLogic;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(pressedKeys.contains(e.getKeyCode())) return; // Sperrt die Tastatur wenn gespammt wird
		pressedKeys.add(e.getKeyCode()); // Sperre
		
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			playerInput = playerInput.subtract(Vector3.one());
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			playerInput = playerInput.add(Vector3.one());
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(pressedKeys.contains(e.getKeyCode())) { pressedKeys.remove(pressedKeys.indexOf(e.getKeyCode())); } // Entfernt die Sperre
		
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			playerInput = playerInput.add(Vector3.one());
		
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			playerInput = playerInput.subtract(Vector3.one());
		}
	}

	public void updateMousePos() {
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		for(GameActionListener g : listeners) {
			g.OnMouseDown(e);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
