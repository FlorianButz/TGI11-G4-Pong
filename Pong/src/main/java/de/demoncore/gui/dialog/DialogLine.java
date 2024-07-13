package de.demoncore.gui.dialog;

import de.demoncore.game.TranslationComponent;

public class DialogLine {

	private String name;
	private TranslationComponent line;
	
	private DialogLine nextLine;
	
	public DialogLine(String name, TranslationComponent line, DialogLine nextLine) {
		this.name = name;
		this.line = line;
		this.nextLine = nextLine;
	}

	public String GetName() {
		return name;
	}

	public String GetLine() {
		return line.Get();
	}
	
	public DialogLine GetNextLine() {
		return nextLine;
	}
}
