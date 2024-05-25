package de.demoncore.gui.dialogue;

public class DialogueLine {

	private String name;
	private String line;
	
	private DialogueLine nextLine;
	
	public DialogueLine(String name, String line, DialogueLine nextLine) {
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
	
	public DialogueLine GetNextLine() {
		return nextLine;
	}
}
