package de.demoncore.scenes.shopnew;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import de.demoncore.game.SceneManager;
import de.demoncore.game.Translation;
import de.demoncore.gameObjects.PongBall;
import de.demoncore.gameObjects.PongPlayer;
import de.demoncore.gui.GUIAlignment;
import de.demoncore.gui.GUIButton;
import de.demoncore.gui.GUIButtonClickEvent;
import de.demoncore.gui.GUIImageButton;
import de.demoncore.gui.GUIMenu;
import de.demoncore.gui.GUIObject;
import de.demoncore.gui.GUIRectangle;
import de.demoncore.gui.GUIText;
import de.demoncore.gui.Gui;
import de.demoncore.gui.TextAlignment;
import de.demoncore.utils.Resources;

public class PedalSkinsMenu extends GUIMenu {


	int xSpacing = 100;
	float amountBtn = 3f;
	int buttonWidth = 325;
	
	int posY = -25;
	private GUIImageButton option1Button;
	private GUIImageButton option2Button;
	private GUIImageButton option3Button;
	private GUIImageButton option4Button;
	private GUIText currency;
	
	@Override
	protected List<GUIObject> addMenuContent() {
	
		List<GUIObject> guiObjects = new ArrayList<GUIObject>();
		
		GUIText title = new GUIText(75, 100, Translation.get("shop.pedals"), Resources.uiFont.deriveFont(45f), Color.white);
		title.alignment = GUIAlignment.TopLeft;
		title.SetTextAlignment(TextAlignment.Left);
		guiObjects.add(title);

		currency = new GUIText(-75, 100, Translation.literal("Pong Taler: " + ShopValues.shopData.getPlayerMoney()), Resources.uiFont.deriveFont(45f), Color.white);
		currency.alignment = GUIAlignment.TopRight;
		currency.SetTextAlignment(TextAlignment.Right);
		guiObjects.add(currency);
		
		option1Button = new GUIImageButton(getX(0), posY, buttonWidth, 650, 450, Resources.shop_whitepedalicon, Resources.shop_whitepedalicon, new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				if((ShopValues.shopData.unlockedPedalSkins & 0b00000001) != 0) {					
					ShopValues.shopData.activePedalSkin = PedalSkins.White;
				}
			}
		});
		option1Button.alignment = GUIAlignment.Center;
		
		GUIText option1Text = new GUIText(getX(0), 400 + posY, Translation.get("pedals.white"), Resources.uiFont.deriveFont(45F), Color.white);
		option1Text.alignment = GUIAlignment.Center;
		guiObjects.add(option1Text);
		
		option2Button = new GUIImageButton(getX(1), posY, buttonWidth, 650,  450, Resources.shop_linespedalicon, Resources.shop_linespedalicon, new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				if((ShopValues.shopData.unlockedPedalSkins & 0b00000010) != 0) {					
					ShopValues.shopData.activePedalSkin = PedalSkins.Lines;
				}
			}
		});
		option2Button.alignment = GUIAlignment.Center;
		
		GUIText option2Text = new GUIText(getX(1), 400 + posY, Translation.get("pedals.lines"), Resources.uiFont.deriveFont(45F), Color.white);
		option2Text.alignment = GUIAlignment.Center;
		guiObjects.add(option2Text);
		
		option3Button = new GUIImageButton(getX(2), posY, buttonWidth, 650, 450, Resources.shop_wavespedalicon, Resources.shop_wavespedalicon, new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				if((ShopValues.shopData.unlockedPedalSkins & 0b00000100) != 0) {					
					ShopValues.shopData.activePedalSkin = PedalSkins.Waves;
				}
			}
		});
		option3Button.alignment = GUIAlignment.Center;
		
		GUIText option3Text = new GUIText(getX(2), 400 + posY, Translation.get("pedals.waves"), Resources.uiFont.deriveFont(45F), Color.white);
		option3Text.alignment = GUIAlignment.Center;
		guiObjects.add(option3Text);
		
		option4Button = new GUIImageButton(getX(3), posY, buttonWidth, 650, 450, Resources.shop_dotspedalicon, Resources.shop_dotspedalicon, new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				if((ShopValues.shopData.unlockedPedalSkins & 0b00001000) != 0) {					
					ShopValues.shopData.activePedalSkin = PedalSkins.Dots;
				}
			}
		});
		option4Button.alignment = GUIAlignment.Center;
		
		GUIText option4Text = new GUIText(getX(3), 400 + posY, Translation.get("pedals.dots"), Resources.uiFont.deriveFont(45F), Color.white);
		option4Text.alignment = GUIAlignment.Center;
		guiObjects.add(option4Text);

		GUIButton buyOption1 = new GUIButton(getX(0), posY + 275, 250, 50, Translation.literal(Translation.get("shop.buy").Get() + " (" + PedalSkins.White.getNumVal() + " Pong Taler)"), Resources.uiFont.deriveFont(18F), null);
		buyOption1.alignment = GUIAlignment.Center;
		buyOption1.setButtonEvent(new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();

				if(PedalSkins.White.getNumVal() <= ShopValues.shopData.getPlayerMoney()) {
					ShopValues.shopData.addPlayerMoney(-PedalSkins.White.getNumVal());
				}else {
					return;
				}
				
				ShopValues.shopData.unlockedPedalSkins = (byte) (ShopValues.shopData.unlockedPedalSkins | 0b00000001);
				SceneManager.getActiveScene().destroyObject(buyOption1);
			}
		});
		
		GUIButton buyOption2 = new GUIButton(getX(1), posY + 275, 250, 50, Translation.literal(Translation.get("shop.buy").Get() + " (" + PedalSkins.Lines.getNumVal() + " Pong Taler)"), Resources.uiFont.deriveFont(18F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
			}
		});
		buyOption2.alignment = GUIAlignment.Center;
		buyOption2.setButtonEvent(new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();

				if(PedalSkins.Lines.getNumVal() <= ShopValues.shopData.getPlayerMoney()) {
					ShopValues.shopData.addPlayerMoney(-PedalSkins.Lines.getNumVal());
				}else {
					return;
				}
				
				ShopValues.shopData.unlockedPedalSkins = (byte) (ShopValues.shopData.unlockedPedalSkins | 0b00000010);
				SceneManager.getActiveScene().destroyObject(buyOption2);
			}
		});
		
		GUIButton buyOption3 = new GUIButton(getX(2), posY + 275, 250, 50, Translation.literal(Translation.get("shop.buy").Get() + " (" + PedalSkins.Waves.getNumVal() + " Pong Taler)"), Resources.uiFont.deriveFont(18F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
			}
		});
		buyOption3.alignment = GUIAlignment.Center;
		buyOption3.setButtonEvent(new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();

				if(PedalSkins.Waves.getNumVal() <= ShopValues.shopData.getPlayerMoney()) {
					ShopValues.shopData.addPlayerMoney(-PedalSkins.Waves.getNumVal());
				}else {
					return;
				}
				
				ShopValues.shopData.unlockedPedalSkins = (byte) (ShopValues.shopData.unlockedPedalSkins | 0b00000100);
				SceneManager.getActiveScene().destroyObject(buyOption3);
			}
		});
		
		GUIButton buyOption4 = new GUIButton(getX(3), posY + 275, 250, 50, Translation.literal(Translation.get("shop.buy").Get() + " (" + PedalSkins.Dots.getNumVal() + " Pong Taler)"), Resources.uiFont.deriveFont(18F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
			}
		});
		buyOption4.alignment = GUIAlignment.Center;
		buyOption4.setButtonEvent(new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				
				if(PedalSkins.Dots.getNumVal() <= ShopValues.shopData.getPlayerMoney()) {
					ShopValues.shopData.addPlayerMoney(-PedalSkins.Dots.getNumVal());
				}else {
					return;
				}
				
				ShopValues.shopData.unlockedPedalSkins = (byte) (ShopValues.shopData.unlockedPedalSkins | 0b00001000);
				SceneManager.getActiveScene().destroyObject(buyOption4);
			}
		});
		
		
		guiObjects.add(option1Button);
		guiObjects.add(option2Button);
		guiObjects.add(option3Button);
		guiObjects.add(option4Button);
		
		if((ShopValues.shopData.unlockedPedalSkins & 0b00000001) == 0)
			guiObjects.add(buyOption1);
		if((ShopValues.shopData.unlockedPedalSkins & 0b00000010) == 0)
			guiObjects.add(buyOption2);
		if((ShopValues.shopData.unlockedPedalSkins & 0b00000100) == 0)
			guiObjects.add(buyOption3);
		if((ShopValues.shopData.unlockedPedalSkins & 0b00001000) == 0)
			guiObjects.add(buyOption4);

		option1Text.color = new Color(1, 1, 1, 0.75f);
		option2Text.color = new Color(1, 1, 1, 0.75f);
		option3Text.color = new Color(1, 1, 1, 0.75f);
		option4Text.color = new Color(1, 1, 1, 0.75f);

		buyOption1.normalColor = new Color(1, 1, 1, 0.15f);
		buyOption2.normalColor = new Color(1, 1, 1, 0.15f);
		buyOption3.normalColor = new Color(1, 1, 1, 0.15f);
		buyOption4.normalColor = new Color(1, 1, 1, 0.15f);
		
		option1Button.normalColor = new Color(1, 1, 1, 0.15f);
		option2Button.normalColor = new Color(1, 1, 1, 0.15f);
		option3Button.normalColor = new Color(1, 1, 1, 0.15f);
		option4Button.normalColor = new Color(1, 1, 1, 0.15f);
		
		option1Button.hoverColor = 	new Color(0, 0, 0, 0.25f);
		option2Button.hoverColor = 	new Color(0, 0, 0, 0.25f);
		option3Button.hoverColor  = new Color(0, 0, 0, 0.25f);
		option4Button.hoverColor = 	new Color(0, 0, 0, 0.25f);
		
		return guiObjects;
	}
	
	@Override
	public void update() {
		super.update();
		
		if(option1Button != null &&
				option2Button != null &&
				option3Button != null &&
				option4Button != null) {

			clearCol();
			
			currency.SetText(Translation.literal("Pong Taler: " + ShopValues.shopData.getPlayerMoney()));
			
			switch (ShopValues.shopData.activePedalSkin) {
			case White:
				option1Button.normalColor = new Color(1f, 0.8f, 0.1f, 0.5f);
				break;
			case Lines:
				option2Button.normalColor = new Color(1f, 0.8f, 0.1f, 0.5f);
				break;
			case Waves:
				option3Button.normalColor = new Color(1f, 0.8f, 0.1f, 0.5f);
				break;
			case Dots:
				option4Button.normalColor = new Color(1f, 0.8f, 0.1f, 0.5f);
				break;

			default:
				break;
			}	
		}
	}
	
	void clearCol() {
		option1Button.normalColor = new Color(1, 1, 1, 0.15f);
		option2Button.normalColor = new Color(1, 1, 1, 0.15f);
		option3Button.normalColor = new Color(1, 1, 1, 0.15f);
		option4Button.normalColor = new Color(1, 1, 1, 0.15f);
	}
	
	@Override
	protected GUIButton createBackButton() {
		GUIButton back = new GUIButton(0, -125, 800, 50, Translation.get("shop.back"), Resources.uiFont.deriveFont(25F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				HideMenu();
			}
		});
		back.alignment = GUIAlignment.DownMiddle;
		back.normalColor = new Color(1, 1, 1, 0.15f);
		return back;
	}
	
	int getX(int index) {
		return (int) ((int)(index * buttonWidth + index * xSpacing) - ((amountBtn / 2) * buttonWidth + (amountBtn / 2) * xSpacing));
	}
	
	@Override
	protected GUIObject createBackground() {
		GUIRectangle bg = new GUIRectangle(0, 0, (int)Gui.GetScreenDimensions().x + 1500, (int)Gui.GetScreenDimensions().y + 1500, new Color(0, 0, 0, 0f));
		bg.alignment = GUIAlignment.Center;
		bg.doUIScale = false;
		
		backgroundColor = Color.black;
		
		return bg;
	}
}
