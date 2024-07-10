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
		
		GUITextArea guiText = new GUITextArea(0, -650, Translation.literal("\r\n"
				+ "\bGrafikdesigner\r\n"
				+ "\r\n"
				+ "Jonathan Kugler\r\n"
				+ "Philip Höschle\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "\bProgrammierer\r\n"
				+ "\r\n"
				+ "Florian Butz\r\n"
				+ "Marcel Firlus\r\n"
				+ "Tim Hilzinger\r\n"
				+ "\r\n"
				+ "\bMusik\r\n"
				+ "\r\n"
				+ "Florian Butz"), Resources.uiFont.deriveFont(40f),Color.cyan);
		guiText.alignment = GUIAlignment.Center;
		guiText.SetTextAlignment(TextAlignment.Center);
		Objects.add(guiText);
	
		return Objects;
	}

}
