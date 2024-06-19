package de.demoncore.scenes;

import java.awt.Color;

import de.demoncore.game.SceneManager;
import de.demoncore.game.Translation;
import de.demoncore.gui.GUIAlignment;
import de.demoncore.gui.GUIButton;
import de.demoncore.gui.GUIButtonClickEvent;
import de.demoncore.gui.GUIText;
import de.demoncore.gui.GUITheme;
import de.demoncore.gui.GUITheme.Theme;
import de.demoncore.utils.Resources;

public class DefaultScene extends BaseScene {

	@Override
	public void initializeScene() {
		super.initializeScene();
	
		GUIText t = new GUIText(0, -75, Translation.get("defscene.error"), Resources.uiFont.deriveFont(70F), Color.white);
		t.alignment = GUIAlignment.Center;
		t.color = Color.red;
		addObject(t);
		
		GUIText d = new GUIText(0, 0, Translation.get("defscene.error2"), Resources.uiFont.deriveFont(30F), Color.white);
		d.alignment = GUIAlignment.Center;
		GUITheme.LoadTextTheme(d, Theme.TextSecondary);
		addObject(d);
		
		GUIButton returnButton = new GUIButton(0, 100, 400, 65, Translation.get("defscene.return"), Resources.uiFont.deriveFont(25F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				SceneManager.loadScene(new MainMenu());
			}
		});

		returnButton.alignment = GUIAlignment.Center;
		
		GUITheme.LoadButtonTheme(returnButton, Theme.ButtonSecondary);
		addObject(returnButton);
	}
	
}
