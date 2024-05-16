package actions;

import game.GameLogic;
import gui.Gui;

public class Main {

	public static void main(String[] args) {
		GameLogic spiellogik = new GameLogic();
		new Gui(spiellogik);
	}

}
