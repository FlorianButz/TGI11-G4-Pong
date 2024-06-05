package de.demoncore.gui;

import java.awt.Color;

public class GUITheme {

	public enum Theme {
		ButtonSecondary,
		TextSecondary
	}
	
	public static void LoadButtonTheme(GUIButton button, Theme theme) {
		switch(theme) {
		case ButtonSecondary:
			
			button.normalColor = new Color(0.5f, 0.5f, 0.5f);
			button.hoverColor = Color.black;
			button.hoverTextColor = new Color(0.5f, 0.5f, 0.5f);
			button.normalTextColor = Color.black;
			button.textHoverSize = 1.05f;
			
			break;
		}
	}
	
	public static void LoadButtonTheme(GUIToggle button, Theme theme) {
		switch(theme) {
		case ButtonSecondary:
			
			button.normalColor = new Color(0.5f, 0.5f, 0.5f);
			button.hoverColor = Color.black;
			button.hoverCheckmarkColor = new Color(0.5f, 0.5f, 0.5f);
			button.normalCheckmarkColor = Color.black;
			
			break;
		}
	}
	
	public static void LoadTextTheme(GUIText text, Theme theme) {
		switch(theme) {
		case TextSecondary:
			
			text.color = new Color(0.5f, 0.5f, 0.5f);
			
			break;
		}
	}
	
}
