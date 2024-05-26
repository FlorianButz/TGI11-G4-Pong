package de.demoncore.actions;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import de.demoncore.game.GameLogic;
import de.demoncore.game.SceneManager;
import de.demoncore.gameObjects.ParticleSystem;
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
		
		for(GameActionListener listener : new ArrayList<GameActionListener>(listeners)) {
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
				listener.OnEscapePressed();
			if(e.getKeyCode() == KeyEvent.VK_E)
				listener.OnInteractionKeyPressed();
			if(e.getKeyCode() == KeyEvent.VK_SPACE)
				listener.OnSpaceKeyPressed();
		}
		
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			playerInput = playerInput.subtract(new Vector3(1, 0, 0));
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			playerInput = playerInput.add(new Vector3(1, 0, 0));
		}
		
		else if (e.getKeyCode() == KeyEvent.VK_UP) {
			playerInput = playerInput.subtract(new Vector3(0, 1, 0));
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			playerInput = playerInput.add(new Vector3(0, 1, 0));
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(pressedKeys.contains(e.getKeyCode())) { pressedKeys.remove(pressedKeys.indexOf(e.getKeyCode())); } // Entfernt die Sperre
		
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			playerInput = playerInput.add(new Vector3(1, 0, 0));
		
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			playerInput = playerInput.subtract(new Vector3(1, 0, 0));
		}
		
		else if (e.getKeyCode() == KeyEvent.VK_UP) {
			playerInput = playerInput.add(new Vector3(0, 1, 0));
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			playerInput = playerInput.subtract(new Vector3(0, 1, 0));
		}
	}

	public void updateMousePos() {
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		ArrayList<GameActionListener> l = new ArrayList<GameActionListener>(listeners);
		for(GameActionListener g : l) {
			g.OnMouseDown(e);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		ArrayList<GameActionListener> l = new ArrayList<GameActionListener>(listeners);
		for(GameActionListener g : l) {
			g.OnMouseUp(e);
		}
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
