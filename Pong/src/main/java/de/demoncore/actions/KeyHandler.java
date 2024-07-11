package de.demoncore.actions;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import de.demoncore.game.GameLogic;
import de.demoncore.utils.Vector3;

public class KeyHandler implements KeyListener, MouseListener {

	public static List<GameActionListener> listeners = new ArrayList<GameActionListener>();
	
	GameLogic gameLogic;
	List<Integer> pressedKeys = new ArrayList<Integer>();

	public Vector3 mouse = Vector3.zero();
	
	public static Vector3 playerInput1 = Vector3.zero(); // Der Input von dem Spieler (welche taste gedrueckt) in form eines Vektors der bei Start auf 0 ist
	public static Vector3 playerInput2 = Vector3.zero(); // Der Input von dem Spieler 2 (welche taste gedrueckt) in form eines Vektors der bei Start auf 0 ist
	
	public static boolean isCtrlPressed = false;
	
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
		
		isCtrlPressed = pressedKeys.contains(KeyEvent.VK_CONTROL);
		
		for(GameActionListener listener : new ArrayList<GameActionListener>(listeners)) {
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
				listener.onEscapePressed();
			if(e.getKeyCode() == KeyEvent.VK_E)
				listener.onInteractionKeyPressed();
			if(e.getKeyCode() == KeyEvent.VK_SPACE)
				listener.onSpaceKeyPressed();
		}
		
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			playerInput2 = playerInput2.subtract(new Vector3(1, 0, 0));
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			playerInput2 = playerInput2.add(new Vector3(1, 0, 0));
		}
		
		else if (e.getKeyCode() == KeyEvent.VK_UP) {
			playerInput2 = playerInput2.subtract(new Vector3(0, 1, 0));
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			playerInput2 = playerInput2.add(new Vector3(0, 1, 0));
		}
		
		if (e.getKeyCode() == KeyEvent.VK_A) {
			playerInput1 = playerInput1.subtract(new Vector3(1, 0, 0));
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			playerInput1 = playerInput1.add(new Vector3(1, 0, 0));
		}
		
		else if (e.getKeyCode() == KeyEvent.VK_W) {
			playerInput1 = playerInput1.subtract(new Vector3(0, 1, 0));
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			playerInput1 = playerInput1.add(new Vector3(0, 1, 0));
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		isCtrlPressed = pressedKeys.contains(KeyEvent.VK_CONTROL) ? false : true;
		
		if(pressedKeys.contains(e.getKeyCode())) { pressedKeys.remove(pressedKeys.indexOf(e.getKeyCode())); } // Entfernt die Sperre
		
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			playerInput2 = playerInput2.add(new Vector3(1, 0, 0));
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			playerInput2 = playerInput2.subtract(new Vector3(1, 0, 0));
		}
		
		else if (e.getKeyCode() == KeyEvent.VK_UP) {
			playerInput2 = playerInput2.add(new Vector3(0, 1, 0));
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			playerInput2 = playerInput2.subtract(new Vector3(0, 1, 0));
		}
		
		if (e.getKeyCode() == KeyEvent.VK_A) {
			playerInput1 = playerInput1.add(new Vector3(1, 0, 0));
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			playerInput1 = playerInput1.subtract(new Vector3(1, 0, 0));
		}
		
		else if (e.getKeyCode() == KeyEvent.VK_W) {
			playerInput1 = playerInput1.add(new Vector3(0, 1, 0));
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			playerInput1 = playerInput1.subtract(new Vector3(0, 1, 0));
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
			g.onMouseDown(e);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		ArrayList<GameActionListener> l = new ArrayList<GameActionListener>(listeners);
		for(GameActionListener g : l) {
			g.onMouseUp(e);
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
