package de.demoncore.scenes.shop;

import java.awt.Color;
import java.nio.channels.SelectableChannel;

import de.demoncore.game.GameLogic;
import de.demoncore.game.GameObject;
import de.demoncore.game.SceneManager;
import de.demoncore.game.Translation;
import de.demoncore.game.animator.AnimatorUpdateEvent;
import de.demoncore.gui.GUIAlignment;
import de.demoncore.gui.GUIButton;
import de.demoncore.gui.GUIButtonClickEvent;
import de.demoncore.scenes.BaseScene;
import de.demoncore.utils.GameMath;
import de.demoncore.utils.Resources;


public class BallColors extends AdvencedShopScene {

	GameObject ColorVorschau;
	GUIButton RAINBOW;
	GUIButton BLACK_WHITE;
	int selectetColor;
	
	@Override
	public void initializeScene() {


		ColorVorschau = new GameObject(0, -125, 50, 50);
		addObject(ColorVorschau);



		GUIButton WHITE = new GUIButton(-600, -400, 200, 300, Translation.get("White"), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				System.out.println("White");
				selectetColor = 1;
			}
		});
		WHITE.alignment = GUIAlignment.Center;
		addObject(WHITE);

		GUIButton MAGENTA = new GUIButton(-300, -400, 200, 300, Translation.get("Pink"), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				System.out.println("Magenta");
				selectetColor = 2;
			}
		});
		MAGENTA.alignment = GUIAlignment.Center;
		addObject(MAGENTA);

		GUIButton PINK = new GUIButton(0, -400, 200, 300, Translation.get("Select"), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				System.out.println("Pink");
				selectetColor = 3;
			}
		});
		PINK.alignment = GUIAlignment.Center;
		addObject(PINK);

		GUIButton RED = new GUIButton(300, -400, 200, 300, Translation.get("Select"), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				System.out.println("Red");
				selectetColor = 4;
			}
		});
		RED.alignment = GUIAlignment.Center;
		addObject(RED);

		GUIButton ORANGE = new GUIButton(600, -400, 200, 300, Translation.get("Select"), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				System.out.println("Orange");
				selectetColor = 5;
			}
		});
		ORANGE.alignment = GUIAlignment.Center;
		addObject(ORANGE);

		GUIButton YELLOW = new GUIButton(-600, 100, 200, 300, Translation.get("Select"), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				System.out.println("Yellow");
				selectetColor = 6;
			}
		});
		YELLOW.alignment = GUIAlignment.Center;
		addObject(YELLOW);

		GUIButton GREEN = new GUIButton(-300, 100, 200, 300, Translation.get("Select"), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				System.out.println("Green");
				selectetColor = 7;
			}
		});
		GREEN.alignment = GUIAlignment.Center;
		addObject(GREEN);

		GUIButton CYAN = new GUIButton(0, 100, 200, 300, Translation.get("Select"), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				System.out.println("Cyan");
				selectetColor = 8;
			}
		});
		CYAN.alignment = GUIAlignment.Center;
		addObject(CYAN);

		GUIButton BLUE = new GUIButton(300, 100, 200, 300, Translation.get("Select"), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				System.out.println("Blue");
				selectetColor = 9;
			}
		});
		BLUE.alignment = GUIAlignment.Center;
		addObject(BLUE);

		RAINBOW = new GUIButton(600, 100, 200, 300, Translation.get("Select (Rainbow)"), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				selectetColor = 10;
				System.out.println("Rainbow");
			}
		});
		RAINBOW.alignment = GUIAlignment.Center;
		addObject(RAINBOW);

		BLACK_WHITE = new GUIButton(-600, 600, 200, 300, Translation.get("Select (Black/White)"), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				selectetColor = 11;
				System.out.println("Black White");
			}
		});
		BLACK_WHITE.alignment = GUIAlignment.Center;
		addObject(BLACK_WHITE);





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
		YELLOW.hoverColor = Color.yellow.darker().darker(); //.darker() 2 mal damit es passt sonnst w�rs zu hell
		GREEN.hoverColor = Color.green.darker().darker();
		BLUE.hoverColor = Color.blue.darker().darker();
		ORANGE.hoverColor = Color.orange.darker().darker();
		CYAN.hoverColor = Color.cyan.darker().darker();
		PINK.hoverColor = Color.pink.darker().darker();
		MAGENTA.hoverColor = Color.magenta.darker().darker();
		WHITE.hoverColor = Color.white.darker().darker();



		




		//GameMath.lerpColor(Color.DARK_GRAY, Color.WHITE, (float) Math.sin(GameLogic.getInstance().getGameTime() * 1));
		//GameMath.lerpColor(GameMath.lerpColor(Color.cyan, Color.red, (float) Math.sin(GameLogic.getInstance().getGameTime() * 2)), Color.green, (float) Math.sin(GameLogic.getInstance().getGameTime() * 4))

		super.initializeScene();
	}

	public Color Farbe( ) {

		Color color = ColorVorschau.color;


		return color;
	}

	@Override
	public void updateScene() {
		RAINBOW.normalColor = GameMath.lerpColor(GameMath.lerpColor(Color.cyan, Color.red, (float) Math.sin(GameLogic.getInstance().getGameTime() * 2)), Color.green, (float) Math.sin(GameLogic.getInstance().getGameTime() * 2));
		RAINBOW.hoverColor = GameMath.lerpColor(GameMath.lerpColor(Color.cyan, Color.red, (float) Math.sin(GameLogic.getInstance().getGameTime() * 2)), Color.green, (float) Math.sin(GameLogic.getInstance().getGameTime() * 2)).darker().darker();
		BLACK_WHITE.normalColor = GameMath.lerpColor(Color.DARK_GRAY, Color.WHITE, (float) Math.sin(GameLogic.getInstance().getGameTime() * 1));
		BLACK_WHITE.hoverColor = GameMath.lerpColor(Color.DARK_GRAY, Color.WHITE, (float) Math.sin(GameLogic.getInstance().getGameTime() * 1)).darker().darker();

		switch (selectetColor) {
		case 1:
			ColorVorschau.color = Color.WHITE;;
			break;

		case 2:
			ColorVorschau.color = Color.MAGENTA;
			break;
			
		case 3:
			ColorVorschau.color = Color.PINK;
			break;
			
		case 4:
			ColorVorschau.color = Color.RED;
			break;
			
		case 5:
			ColorVorschau.color = Color.ORANGE;
			break;
			
		case 6:
			ColorVorschau.color = Color.YELLOW;
			break;
			
		case 7:
			ColorVorschau.color = Color.GREEN;
			break;
			
		case 8:
			ColorVorschau.color = Color.CYAN;
			break;
			
		case 9:
			ColorVorschau.color = Color.BLUE;
			break;
			
		case 10:
			ColorVorschau.color = RAINBOW.currentColor;
			break;
			
		case 11:
			ColorVorschau.color = BLACK_WHITE.currentColor;
			break;
			
		case 12:
			ColorVorschau.color = Color.WHITE;
			break;
			
		case 13:
			ColorVorschau.color = Color.WHITE;
			break;
		}
		
		
		super.updateScene();
	}

}
