package actions;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import game.GameLogic;

public class KeyHandler implements KeyListener {

	GameLogic gamelogic;

	public KeyHandler(GameLogic spiellogik) {
		gamelogic = spiellogik;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			gamelogic.keyLeftarrowpressed = true;
		
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			gamelogic.keyRightarrowpressed = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			gamelogic.keyLeftarrowpressed = false;
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			gamelogic.keyRightarrowpressed = false;
		}
		
	}

}
