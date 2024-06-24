package de.demoncore.scenes.shop;

import de.demoncore.game.Translation;
import de.demoncore.gui.GUIAlignment;
import de.demoncore.gui.GUIButton;
import de.demoncore.gui.GUIButtonClickEvent;
import de.demoncore.utils.Resources;

public class Background extends AdvencedShopScene {

	
	
	@Override
	public void initializeScene() {
		
		
		
		GUIButton btn_1 = new GUIButton(-600, -400, 200, 300, Translation.get("Select"), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				
			
			}
		});
		btn_1.alignment = GUIAlignment.Center;
		addObject(btn_1);
		
		GUIButton btn_2 = new GUIButton(-300, -400, 200, 300, Translation.get("Select"), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				
			
			}
		});
		btn_2.alignment = GUIAlignment.Center;
		addObject(btn_2);
		
		GUIButton btn_3 = new GUIButton(0, -400, 200, 300, Translation.get("Select"), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				
			
			}
		});
		btn_3.alignment = GUIAlignment.Center;
		addObject(btn_3);
		
		GUIButton btn_4 = new GUIButton(300, -400, 200, 300, Translation.get("Select"), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				
			
			}
		});
		btn_4.alignment = GUIAlignment.Center;
		addObject(btn_4);
		
		GUIButton btn_5 = new GUIButton(600, -400, 200, 300, Translation.get("Select"), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				
			
			}
		});
		btn_5.alignment = GUIAlignment.Center;
		addObject(btn_5);
		
		GUIButton btn_6 = new GUIButton(-600, 100, 200, 300, Translation.get("Select"), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				
				
			}
		});
		btn_6.alignment = GUIAlignment.Center;
		addObject(btn_6);
		
		GUIButton btn_7 = new GUIButton(-300, 100, 200, 300, Translation.get("Select"), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				
				
			}
		});
		btn_7.alignment = GUIAlignment.Center;
		addObject(btn_7);
		
		GUIButton btn_8 = new GUIButton(0, 100, 200, 300, Translation.get("Select"), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				
				
			}
		});
		btn_8.alignment = GUIAlignment.Center;
		addObject(btn_8);
		
		GUIButton btn_9 = new GUIButton(300, 100, 200, 300, Translation.get("Select"), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				
				
			}
		});
		btn_9.alignment = GUIAlignment.Center;
		addObject(btn_9);
		
		GUIButton btn_10 = new GUIButton(600, 100, 200, 300, Translation.get("Select"), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				
				
			}
		});
		btn_10.alignment = GUIAlignment.Center;
		addObject(btn_10);
	
	
		
		
		
		
		
		
		
		
		
		
		
		
		super.initializeScene();
	}
	

	
}
