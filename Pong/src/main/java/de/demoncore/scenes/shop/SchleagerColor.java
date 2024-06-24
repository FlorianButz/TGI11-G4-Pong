package de.demoncore.scenes.shop;

import java.awt.Color;

import de.demoncore.game.GameLogic;
import de.demoncore.game.GameObject;
import de.demoncore.game.Translation;
import de.demoncore.gui.GUIAlignment;
import de.demoncore.gui.GUIButton;
import de.demoncore.gui.GUIButtonClickEvent;
import de.demoncore.utils.GameMath;
import de.demoncore.utils.Resources;

public class SchleagerColor extends AdvencedShopScene {

	GameObject ColorVorschau;
	
	@Override
	public void initializeScene() {
	
		ColorVorschau = new GameObject(0, -125, 50, 50);
		addObject(ColorVorschau);
		
		
		GUIButton btn_1 = new GUIButton(-600, -400, 200, 300, Translation.get("Select"), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				
				ColorVorschau.color = Color.WHITE;
			}
		});
		btn_1.alignment = GUIAlignment.Center;
		addObject(btn_1);
		
		GUIButton btn_2 = new GUIButton(-300, -400, 200, 300, Translation.get("Select"), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				
				ColorVorschau.color = Color.MAGENTA;
			}
		});
		btn_2.alignment = GUIAlignment.Center;
		addObject(btn_2);
		
		GUIButton btn_3 = new GUIButton(0, -400, 200, 300, Translation.get("Select"), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				
				ColorVorschau.color = Color.PINK;
			}
		});
		btn_3.alignment = GUIAlignment.Center;
		addObject(btn_3);
		
		GUIButton btn_4 = new GUIButton(300, -400, 200, 300, Translation.get("Select"), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				
				ColorVorschau.color = Color.RED;
			}
		});
		btn_4.alignment = GUIAlignment.Center;
		addObject(btn_4);
		
		GUIButton btn_5 = new GUIButton(600, -400, 200, 300, Translation.get("Select"), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				
				ColorVorschau.color = Color.ORANGE;
			}
		});
		btn_5.alignment = GUIAlignment.Center;
		addObject(btn_5);
		
		GUIButton btn_6 = new GUIButton(-600, 100, 200, 300, Translation.get("Select"), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				
				ColorVorschau.color = Color.YELLOW;
			}
		});
		btn_6.alignment = GUIAlignment.Center;
		addObject(btn_6);
		
		GUIButton btn_7 = new GUIButton(-300, 100, 200, 300, Translation.get("Select"), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				
				ColorVorschau.color = Color.GREEN;
			}
		});
		btn_7.alignment = GUIAlignment.Center;
		addObject(btn_7);
		
		GUIButton btn_8 = new GUIButton(0, 100, 200, 300, Translation.get("Select"), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				
				ColorVorschau.color = Color.CYAN;
			}
		});
		btn_8.alignment = GUIAlignment.Center;
		addObject(btn_8);
		
		GUIButton btn_9 = new GUIButton(300, 100, 200, 300, Translation.get("Select"), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				
				ColorVorschau.color = Color.BLUE;
			}
		});
		btn_9.alignment = GUIAlignment.Center;
		addObject(btn_9);
		
		GUIButton btn_10 = new GUIButton(600, 100, 200, 300, Translation.get("Select"), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				
				ColorVorschau.color = Color.WHITE;
			}
		});
		btn_10.alignment = GUIAlignment.Center;
		addObject(btn_10);
	
		

		
		super.initializeScene();
	}
	
	public Color Farbe( ) {
		
		Color color = ColorVorschau.color;
		
		
		return color;
	}
	
	
	
	
	
	
	
	
}
