package de.demoncore.gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import de.demoncore.game.Translation;
import de.demoncore.game.TranslationComponent;
import de.demoncore.utils.Resources;

public class MessagePopup extends GUIMenu {

	TranslationComponent text;

	public int maxLettersInOneLine = 135;
	public float textSize = 50;
	public Color textColor = Color.white;
	
	public MessagePopup(TranslationComponent text) {
		this.text = text;
	}
	
	@Override
	protected List<GUIObject> addMenuContent() {
		List<GUIObject> guiObjects = new ArrayList<GUIObject>();
		
		GUITextArea textObject = new GUITextArea(0, -150, text, Resources.uiFont.deriveFont(textSize), textColor);
		textObject.maxLetterInOneLine = maxLettersInOneLine;
		textObject.alignment = GUIAlignment.Center;
		textObject.textAlignment = TextAlignment.Center;
		guiObjects.add(textObject);
		
		return guiObjects;
	}
	
	@Override
	protected GUIButton createBackButton() {
		GUIButton back = new GUIButton(0, 225, 250, 50, Translation.literal("OK"), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
		@Override
		public void ButtonClick() {
			HideMenu();
			super.ButtonClick();
		}
		});
		back.alignment = GUIAlignment.Center;
		
		return back;
	}
}
