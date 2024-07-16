package de.demoncore.gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import de.demoncore.game.GameLogic;
import de.demoncore.game.Translation;
import de.demoncore.utils.GameMath;
import de.demoncore.utils.Resources;

public class GuiCreditsMenu extends GUIMenu{
	
	
	private GUITextArea guiText;

	public static Color regenbogen() {
		return GameMath.lerpColor(
				GameMath.lerpColor(Color.magenta, Color.green, (float) Math.sin(GameLogic.getInstance().getGameTime() *0.5)),
				Color.cyan, (float) Math.sin(GameLogic.getInstance().getGameTime() * 0.3));
	}
	
	@Override
	protected List<GUIObject> addMenuContent() {

		List<GUIObject> Objects = new ArrayList<GUIObject>();
		
		guiText = new GUITextArea(0, -550, Translation.literal("\r\n"
				+ "\bGrafikdesigner\r\n"
				+ "Jonathan Kugler\r\n"
				+ "Philip Hoeschle\r\n"
				+ "\r\n"
				+ "\bProgrammierer\r\n"
				+ "LD - Florian Karl Butz\r\n"
				+ "FE - Marcel Firlus\r\n"
				+ "FE - Tim Hilzinger\r\n"
				+ "BE - Philip Hoeschle\r\n"
				+ "BE - Jonathan Kugler\r\n"
				+ "\r\n"
				+ "\bMusik\r\n"
				+ "Produziert und Komponiert von Florian Karl Butz"), Resources.uiFont.deriveFont(40f),regenbogen());
		guiText.alignment = GUIAlignment.Center;
		guiText.SetTextAlignment(TextAlignment.Center);
		Objects.add(guiText);
	
		return Objects;
	}

	@Override
	public void update() {
		super.update();

		if(guiText != null)
			guiText.color = regenbogen();
	}
}
