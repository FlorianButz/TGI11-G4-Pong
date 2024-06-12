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
		
		GUIButton GREEN = new GUIButton(100, -200, 100, 100, Translation.get(""), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				
				ColorVorschau.color = Color.GREEN;
			}
		});
		GREEN.alignment = GUIAlignment.Center;
		addObject(GREEN);
		
		GUIButton MAGENTA = new GUIButton(300, -200, 100, 100, Translation.get(""), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				
				ColorVorschau.color = Color.MAGENTA;
			}
		});
		MAGENTA.alignment = GUIAlignment.Center;
		addObject(MAGENTA);
		
		GUIButton PINK = new GUIButton(500, -200, 100, 100, Translation.get(""), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
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
		
		GUIButton YELLOW = new GUIButton(700, -200, 100, 100, Translation.get(""), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				
				ColorVorschau.color = Color.YELLOW;
			}
		});
		YELLOW.alignment = GUIAlignment.Center;
		addObject(YELLOW);
		
		
		//Buttons die Farbe geben
		RED.normalColor = Color.red;				
		YELLOW.normalColor = Color.yellow;
		GREEN.normalColor = Color.green;
		ORANGE.normalColor = Color.orange;
		BLUE.normalColor = Color.blue;
		CYAN.normalColor = Color.cyan;
		PINK.normalColor = Color.pink;
		MAGENTA.normalColor = Color.magenta;
		
		//Hover Farbe etwas verdunkeln
		RED.hoverColor = Color.red.darker().darker();
		YELLOW.hoverColor = Color.yellow.darker().darker(); //.darker() 2 mal damit es passt sonnst wärs zu hell
		GREEN.hoverColor = Color.green.darker().darker();
		BLUE.hoverColor = Color.blue.darker().darker();
		ORANGE.hoverColor = Color.orange.darker().darker();
		CYAN.hoverColor = Color.cyan.darker().darker();
		PINK.hoverColor = Color.pink.darker().darker();
		MAGENTA.hoverColor = Color.magenta.darker().darker();
		WHITE.hoverColor = Color.white.darker().darker();
		
		super.initializeScene();
	}
	

	
}
