package de.demoncore.gui.dialogue;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import de.demoncore.game.GameObject;
import de.demoncore.game.SceneManager;
import de.demoncore.gui.GUIAlignment;
import de.demoncore.gui.GUIButton;
import de.demoncore.gui.GUIButtonClickEvent;
import de.demoncore.gui.GUIObject;
import de.demoncore.gui.GUIRectangle;
import de.demoncore.gui.GUIText;
import de.demoncore.gui.GUITextArea;
import de.demoncore.gui.GUITheme;
import de.demoncore.gui.GUITheme.Theme;
import de.demoncore.gui.Gui;
import de.demoncore.utils.Resources;

public class Dialogue extends GameObject {
	
	List<GUIObject> content = new ArrayList<GUIObject>();

	public Dialogue() {
		super(0, 0, 0, 0);
		
		content.add(CreateBackground());
		
		GUIText name = new GUIText(0, -415, "Test Name", Resources.dialogFont.deriveFont(75F), new Color(1f, 1f, 1f, 1f));
		name.alignment = GUIAlignment.DownMiddle;
		content.add(name);
		
		GUIButton skipBtn = new GUIButton(-150, -415, 100, 35, "Skip >", Resources.uiFont.deriveFont(20F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				DestroyDialogue();
			}
		});
		skipBtn.alignment = GUIAlignment.DownRight;
		GUITheme.LoadButtonTheme(skipBtn, Theme.ButtonSecondary);
		content.add(skipBtn);
		
		GUITextArea dialogueText = new GUITextArea(25, -300, "Test text. text. text. text. text. text. text. text. text. text. text. text. text. text. text. text. text. text. text. text. text. text. text. text. text. text. text. text. text. text. text. text. text. text. text. text. text. text. text. text. text. text.  ",
				Resources.uiFont.deriveFont(35F), new Color(1f, 1f, 1f, 1f));
		dialogueText.alignment = GUIAlignment.DownLeft;
		dialogueText.centerText = false;
		dialogueText.maxLetterInOneLine = 75;
		GUITheme.LoadTextTheme(dialogueText, Theme.TextSecondary);
		content.add(dialogueText);
		
		CreateDialogue();
	}
	
	void CreateDialogue() {
		for(GUIObject o : content) {
			SceneManager.GetActiveScene().AddObject(o);
		}
	}
	
	void DestroyDialogue() {
		SceneManager.GetActiveScene().DestroyObject(this);
		
		for(GUIObject o : content) {
			SceneManager.GetActiveScene().DestroyObject(o);
		}
	}
	
	protected GUIObject CreateBackground() {
		GUIRectangle bg = new GUIRectangle(0, -350/2, (int)Gui.GetScreenDimensions().x, (int)350, new Color(0.075f, 0.075f, 0.075f, 0.65f));
		bg.alignment = GUIAlignment.DownMiddle;
		return bg;
	}
	
}
