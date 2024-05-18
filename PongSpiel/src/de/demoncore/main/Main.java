package de.demoncore.main;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import de.demoncore.game.GameLogic;
import de.demoncore.game.Settings;
import de.demoncore.gui.Gui;
import de.demoncore.utils.Resources;

public class Main {

	public static String version = "0.0.1a";
	
	public static void main(String[] args) {
		Resources.LoadResources();
		Settings.LoadAllSettings();
		
		GameLogic gl = new GameLogic();
		new Gui(gl);	
	}
	
}
