package de.demoncore.scenes;

import java.awt.Color;

import de.demoncore.game.SceneManager;
import de.demoncore.gui.GUIAlignment;
import de.demoncore.gui.GUIButton;
import de.demoncore.gui.GUIButtonClickEvent;
import de.demoncore.gui.GUIText;
import de.demoncore.gui.GUITheme;
import de.demoncore.gui.GUITheme.Theme;
import de.demoncore.utils.Resources;

public class DefaultScene extends BaseScene {

	public DefaultScene() {
		
		GUIText t = new GUIText(0, -75, "Etwas ist schief gelaufen...", Resources.uiFont.deriveFont(70F), Color.white);
		t.alignment = GUIAlignment.Center;
		t.color = Color.red;
		AddObject(t);
		
		GUIText d = new GUIText(0, 0, "Das Level konnte nicht geladen werden.", Resources.uiFont.deriveFont(30F), Color.white);
		d.alignment = GUIAlignment.Center;
		GUITheme.LoadTextTheme(d, Theme.TextSecondary);
		AddObject(d);
		
		GUIButton returnButton = new GUIButton(0, 100, 400, 65, "Zurueck zum Start", Resources.uiFont.deriveFont(25F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				SceneManager.LoadScene(new MainMenu());
			}
		});

		returnButton.alignment = GUIAlignment.Center;
		
		GUITheme.LoadButtonTheme(returnButton, Theme.ButtonSecondary);
		AddObject(returnButton);
	}
	
}
