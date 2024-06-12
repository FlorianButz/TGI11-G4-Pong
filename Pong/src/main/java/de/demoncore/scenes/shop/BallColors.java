package de.demoncore.scenes.shop;

import java.awt.Color;

import de.demoncore.game.GameObject;
import de.demoncore.game.SceneManager;
import de.demoncore.game.Translation;
import de.demoncore.gui.GUIAlignment;
import de.demoncore.gui.GUIButton;
import de.demoncore.gui.GUIButtonClickEvent;
import de.demoncore.scenes.BaseScene;
import de.demoncore.utils.Resources;


public class BallColors extends AdvencedShopScene {

	GameObject ColorVorschau;
	

	
	
	
	@Override
	public void initializeScene() {
		
		ColorVorschau = new GameObject(-500, -300, 200, 200);
		addObject(ColorVorschau);
		
		Background = new GameObject(500, 0, 1000, 2000);
		addObject(Background);
		
		
		Background.color = Color.darkGray;
		
		
		GUIButton RED = new GUIButton(300, -400, 100, 100, Translation.get(""), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				
				ColorVorschau.color = Color.red;
			}
		});
		RED.alignment = GUIAlignment.Center;
		addObject(RED);
		
		GUIButton BLUE = new GUIButton(500, -400, 100, 100, Translation.get(""), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				
				ColorVorschau.color = Color.BLUE;
			}
		});
		BLUE.alignment = GUIAlignment.Center;
		addObject(BLUE);
		
		GUIButton ORANGE = new GUIButton(700, -400, 100, 100, Translation.get(""), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				
				ColorVorschau.color = Color.ORANGE;
			}
		});
		ORANGE.alignment = GUIAlignment.Center;
		addObject(ORANGE);
		
		GUIButton CYAN = new GUIButton(900, -400, 100, 100, Translation.get(""), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				
				ColorVorschau.color = Color.CYAN;
			}
		});
		CYAN.alignment = GUIAlignment.Center;
		addObject(CYAN);
		
		GUIButton GREEN = new GUIButton(100, -300, 100, 100, Translation.get(""), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				
				ColorVorschau.color = Color.GREEN;
			}
		});
		GREEN.alignment = GUIAlignment.Center;
		addObject(GREEN);
		
		GUIButton MAGENTA = new GUIButton(300, -300, 100, 100, Translation.get(""), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				
				ColorVorschau.color = Color.MAGENTA;
			}
		});
		MAGENTA.alignment = GUIAlignment.Center;
		addObject(MAGENTA);
		
		GUIButton PINK = new GUIButton(500, -300, 100, 100, Translation.get(""), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				
				ColorVorschau.color = Color.PINK;
			}
		});
		PINK.alignment = GUIAlignment.Center;
		addObject(PINK);
		
		GUIButton WHITE = new GUIButton(100, -400, 100, 100, Translation.get(""), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				
				ColorVorschau.color = Color.WHITE;
			}
		});
		WHITE.alignment = GUIAlignment.Center;
		addObject(WHITE);
		
		GUIButton YELLOW = new GUIButton(700, -300, 100, 100, Translation.get(""), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				
				ColorVorschau.color = Color.YELLOW;
			}
		});
		YELLOW.alignment = GUIAlignment.Center;
		addObject(YELLOW);
		
		
		
		
		
		
		
		
	
		
		
		super.initializeScene();
	}
	

	
}
