package de.demoncore.gameObjects;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import de.demoncore.game.Translation;
import de.demoncore.gui.GUIAlignment;
import de.demoncore.gui.GUIMenu;
import de.demoncore.gui.GUIObject;
import de.demoncore.gui.GUIText;
import de.demoncore.utils.Resources;

public class TestMenu extends GUIMenu {

	public TestMenu() {
		backgroundColor = Color.green;
	}
	
	@Override
	protected List<GUIObject> AddMenuContent() {
		List<GUIObject> content = new ArrayList<GUIObject>();
		
		GUIText text = new GUIText(0, 0, Translation.Literal("Test"), Resources.uiFont.deriveFont(45F), Color.white);
		text.alignment = GUIAlignment.Center;
		content.add(text);
		
		return content;
	}
}
