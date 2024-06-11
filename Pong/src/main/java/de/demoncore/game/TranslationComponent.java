package de.demoncore.game;

public class TranslationComponent {

	public String translationId = "";
	public boolean isLiteral = false;
	
	public TranslationComponent(String id) {
		this.translationId = id;
	}
	
	public String Get() {
		return Translation.get(this);
	}
	
}
