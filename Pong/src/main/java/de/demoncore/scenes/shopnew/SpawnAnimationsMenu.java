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

public class SpawnAnimationsMenu extends GUIMenu {


	int xSpacing = 100;
	float amountBtn = 1f;
	int buttonWidth = 325;
	
	int posY = -25;
	private GUIImageButton option1Button;
	private GUIImageButton option2Button;
	private GUIText currency;
	
	@Override
	protected List<GUIObject> addMenuContent() {
	
		List<GUIObject> guiObjects = new ArrayList<GUIObject>();
		
		GUIText title = new GUIText(75, 100, Translation.get("shop.spawns"), Resources.uiFont.deriveFont(45f), Color.white);
		title.alignment = GUIAlignment.TopLeft;
		title.SetTextAlignment(TextAlignment.Left);
		guiObjects.add(title);

		currency = new GUIText(-75, 100, Translation.literal("Pong Taler: " + ShopValues.shopData.getPlayerMoney()), Resources.uiFont.deriveFont(45f), Color.white);
		currency.alignment = GUIAlignment.TopRight;
		currency.SetTextAlignment(TextAlignment.Right);
		guiObjects.add(currency);
		
		option1Button = new GUIImageButton(getX(0), posY, buttonWidth, 650, 250, Resources.blocked_icon, Resources.blocked_icon, new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				if((ShopValues.shopData.unlockedSpawnAnimations & 0b00000001) != 0) {					
					ShopValues.shopData.activeSpawnAnimation = SpawnAnimations.None;
				}
			}
		});
		option1Button.alignment = GUIAlignment.Center;
		
		GUIText option1Text = new GUIText(getX(0), 400 + posY, Translation.get("spawns.none"), Resources.uiFont.deriveFont(45F), Color.white);
		option1Text.alignment = GUIAlignment.Center;
		guiObjects.add(option1Text);
		
		option2Button = new GUIImageButton(getX(1), posY, buttonWidth, 650, 250, Resources.latiku, Resources.latiku, new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				if((ShopValues.shopData.unlockedSpawnAnimations & 0b00000010) != 0) {					
					ShopValues.shopData.activeSpawnAnimation = SpawnAnimations.Latiku;
				}
			}
		});
		option2Button.alignment = GUIAlignment.Center;
		
		GUIText option2Text = new GUIText(getX(1), 400 + posY, Translation.get("spawns.latiku"), Resources.uiFont.deriveFont(45F), Color.white);
		option2Text.alignment = GUIAlignment.Center;
		guiObjects.add(option2Text);

		GUIButton buyOption1 = new GUIButton(getX(0), posY + 275, 250, 50, Translation.literal(Translation.get("shop.buy").Get() + " (" + SpawnAnimations.None.getNumVal() + " Pong Taler)"), Resources.uiFont.deriveFont(18F), null);
		buyOption1.alignment = GUIAlignment.Center;
		buyOption1.setButtonEvent(new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();

				if(SpawnAnimations.None.getNumVal() <= ShopValues.shopData.getPlayerMoney()) {
					ShopValues.shopData.addPlayerMoney(-SpawnAnimations.None.getNumVal());
				}else {
					return;
				}
				
				ShopValues.shopData.unlockedSpawnAnimations = (byte) (ShopValues.shopData.unlockedSpawnAnimations | 0b00000001);
				SceneManager.getActiveScene().destroyObject(buyOption1);
			}
		});
		
		GUIButton buyOption2 = new GUIButton(getX(1), posY + 275, 250, 50, Translation.literal(Translation.get("shop.buy").Get() + " (" + SpawnAnimations.Latiku.getNumVal() + " Pong Taler)"), Resources.uiFont.deriveFont(18F), new GUIButtonClickEvent() {
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

				if(SpawnAnimations.Latiku.getNumVal() <= ShopValues.shopData.getPlayerMoney()) {
					ShopValues.shopData.addPlayerMoney(-SpawnAnimations.Latiku.getNumVal());
				}else {
					return;
				}
				
				ShopValues.shopData.unlockedSpawnAnimations = (byte) (ShopValues.shopData.unlockedSpawnAnimations | 0b00000010);
				SceneManager.getActiveScene().destroyObject(buyOption2);
			}
		});
		
		guiObjects.add(option1Button);
		guiObjects.add(option2Button);
		
		if((ShopValues.shopData.unlockedSpawnAnimations & 0b00000001) == 0)
			guiObjects.add(buyOption1);
		if((ShopValues.shopData.unlockedSpawnAnimations & 0b00000010) == 0)
			guiObjects.add(buyOption2);

		option1Text.color = new Color(1, 1, 1, 0.75f);
		option2Text.color = new Color(1, 1, 1, 0.75f);

		buyOption1.normalColor = new Color(1, 1, 1, 0.15f);
		buyOption2.normalColor = new Color(1, 1, 1, 0.15f);
		
		option1Button.normalColor = new Color(1, 1, 1, 0.15f);
		option2Button.normalColor = new Color(1, 1, 1, 0.15f);
		
		option1Button.hoverColor = 	new Color(0, 0, 0, 0.25f);
		option2Button.hoverColor = 	new Color(0, 0, 0, 0.25f);
		
		return guiObjects;
	}
	
	@Override
	public void update() {
		super.update();
		
		if(option1Button != null &&
				option2Button != null) {

			clearCol();
			
			currency.SetText(Translation.literal("Pong Taler: " + ShopValues.shopData.getPlayerMoney()));
			
			switch (ShopValues.shopData.activeSpawnAnimation) {
			case None:
				option1Button.normalColor = new Color(1f, 0.8f, 0.1f, 0.5f);
				break;
			case Latiku:
				option2Button.normalColor = new Color(1f, 0.8f, 0.1f, 0.5f);
				break;

			default:
				break;
			}	
		}
	}
	
	void clearCol() {
		option1Button.normalColor = new Color(1, 1, 1, 0.15f);
		option2Button.normalColor = new Color(1, 1, 1, 0.15f);
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
