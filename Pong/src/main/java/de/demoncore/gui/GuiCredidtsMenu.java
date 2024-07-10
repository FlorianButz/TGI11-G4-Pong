package de.demoncore.gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import de.demoncore.game.Translation;
import de.demoncore.utils.Resources;

public class GuiCredidtsMenu extends GUIMenu{
	
	@Override
	protected List<GUIObject> addMenuContent() {

		List<GUIObject> Objects = new ArrayList<GUIObject>();
		
		GUITextArea guiText = new GUITextArea(0, 0, Translation.literal("Grafikdesign - Jonatahn\r\n"
				+ "aowdkpwo"), Resources.uiFont.deriveFont(40f),Color.cyan);
		guiText.alignment = GUIAlignment.Center;
		guiText.SetTextAlignment(TextAlignment.Center);
		Objects.add(guiText);
	
		return Objects;
	}

}
