package de.demoncore.gui.dialog;

public class DialogLine {

	private String name;
	private String line;
	
	private DialogLine nextLine;
	
	public DialogLine(String name, String line, DialogLine nextLine) {
		this.name = name;
		this.line = line;
		this.nextLine = nextLine;
	}

	public String GetName() {
		return name;
	}

	public String GetLine() {
		return line;
	}
	
	public DialogLine GetNextLine() {
		return nextLine;
	}
}
