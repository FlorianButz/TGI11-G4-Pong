package de.demoncore.gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import de.demoncore.game.GameLogic;
import de.demoncore.game.Translation;
import de.demoncore.utils.GameMath;
import de.demoncore.utils.Resources;

public class GuiCredidtsMenu extends GUIMenu{
	
	
	private GUITextArea guiText;

	public static Color regenbogen() {
		return GameMath.lerpColor(
				GameMath.lerpColor(Color.magenta, Color.green, (float) Math.sin(GameLogic.getInstance().getGameTime() *2)),
				Color.cyan, (float) Math.sin(GameLogic.getInstance().getGameTime() * 1));
	}
	
	@Override
	protected List<GUIObject> addMenuContent() {

		List<GUIObject> Objects = new ArrayList<GUIObject>();
		
		guiText = new GUITextArea(0, -650, Translation.literal("\r\n"
				+ "\bGrafikdesigner\r\n"
				+ "\r\n"
				+ "Jonathan Kugler\r\n"
				+ "Philip Hoeschle\r\n"
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
				+ "Florian Butz"), Resources.uiFont.deriveFont(40f),regenbogen());
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
