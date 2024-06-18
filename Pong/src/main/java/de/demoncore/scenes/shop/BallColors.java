package de.demoncore.scenes.shop;

import java.awt.Color;

import de.demoncore.game.GameLogic;
import de.demoncore.game.GameObject;
import de.demoncore.game.SceneManager;
import de.demoncore.game.Translation;
import de.demoncore.gui.GUIAlignment;
import de.demoncore.gui.GUIButton;
import de.demoncore.gui.GUIButtonClickEvent;
import de.demoncore.scenes.BaseScene;
import de.demoncore.utils.GameMath;
import de.demoncore.utils.Resources;


public class BallColors extends AdvencedShopScene {

	GameObject ColorVorschau;
	GUIButton RAINBOW;
	
	
	@Override
	public void initializeScene() {
		
		
		ColorVorschau = new GameObject(500, 0, 200, 200);
		addObject(ColorVorschau);
		
		
		GUIButton WHITE = new GUIButton(-900, -400, 200, 300, Translation.get("Select"), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				
				ColorVorschau.color = Color.WHITE;
			}
		});
		WHITE.alignment = GUIAlignment.Center;
		addObject(WHITE);
		
		GUIButton RED = new GUIButton(-600, -400, 200, 300, Translation.get("Select"), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				
				ColorVorschau.color = Color.red;
			}
		});
		RED.alignment = GUIAlignment.Center;
		addObject(RED);
		
		GUIButton BLUE = new GUIButton(-300, -400, 200, 300, Translation.get("Select"), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				
				ColorVorschau.color = Color.BLUE;
			}
		});
		BLUE.alignment = GUIAlignment.Center;
		addObject(BLUE);
		
		GUIButton ORANGE = new GUIButton(0, -400, 200, 300, Translation.get("Select"), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				
				ColorVorschau.color = Color.ORANGE;
			}
		});
		ORANGE.alignment = GUIAlignment.Center;
		addObject(ORANGE);
		
		GUIButton CYAN = new GUIButton(300, -400, 200, 300, Translation.get("Select"), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				
				ColorVorschau.color = Color.CYAN;
			}
		});
		CYAN.alignment = GUIAlignment.Center;
		addObject(CYAN);
		
		GUIButton GREEN = new GUIButton(600, -400, 200, 300, Translation.get("Select"), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				
				ColorVorschau.color = Color.GREEN;
			}
		});
		GREEN.alignment = GUIAlignment.Center;
		addObject(GREEN);
		
		GUIButton MAGENTA = new GUIButton(900, -400, 200, 300, Translation.get("Select"), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				
				ColorVorschau.color = Color.MAGENTA;
			}
		});
		MAGENTA.alignment = GUIAlignment.Center;
		addObject(MAGENTA);
		
		GUIButton PINK = new GUIButton(0, 100, 200, 300, Translation.get("Select"), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				
				ColorVorschau.color = Color.PINK;
			}
		});
		PINK.alignment = GUIAlignment.Center;
		addObject(PINK);
		
		GUIButton YELLOW = new GUIButton(-300, 100, 200, 300, Translation.get("Select"), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				
				ColorVorschau.color = Color.YELLOW;
			}
		});
		YELLOW.alignment = GUIAlignment.Center;
		addObject(YELLOW);
		
		RAINBOW = new GUIButton(-600, 100, 200, 300, Translation.get("Select"), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				
				ColorVorschau.color = GameMath.lerpColor(GameMath.lerpColor(Color.cyan, Color.red, (float) Math.sin(GameLogic.getInstance().getGameTime() * 2)), Color.green, (float) Math.sin(GameLogic.getInstance().getGameTime() * 8));
			}
		});
		RAINBOW.alignment = GUIAlignment.Center;
		addObject(RAINBOW);
		
		
	
		
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
		
		
		
			
		RAINBOW.normalColor = GameMath.lerpColor(GameMath.lerpColor(Color.cyan, Color.red, (float) Math.sin(GameLogic.getInstance().getGameTime() * 2)), Color.green, (float) Math.sin(GameLogic.getInstance().getGameTime() * 4));
		RAINBOW.hoverColor = GameMath.lerpColor(GameMath.lerpColor(Color.cyan, Color.red, (float) Math.sin(GameLogic.getInstance().getGameTime() * 2)), Color.green, (float) Math.sin(GameLogic.getInstance().getGameTime() * 4)).darker().darker();
		
		
	
		
		//GameMath.lerpColor(GameMath.lerpColor(Color.cyan, Color.red, (float) Math.sin(GameLogic.getInstance().getGameTime() * 2)), Color.green, (float) Math.sin(GameLogic.getInstance().getGameTime() * 4))
		
		super.initializeScene();
		}
	
	public Color Farbe( ) {
		
		Color color = ColorVorschau.color;
		
		
		return color;
	}
	

	
}
