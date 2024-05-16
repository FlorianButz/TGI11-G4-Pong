package de.demoncore.main;

import de.demoncore.game.GameLogic;
import de.demoncore.gui.Gui;

public class Main {

	public static void main(String[] args) {
		GameLogic gl = new GameLogic();
		new Gui(gl);
	}
	
}
