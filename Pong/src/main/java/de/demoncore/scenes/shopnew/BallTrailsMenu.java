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
import de.demoncore.gui.GUIMenu;
import de.demoncore.gui.GUIObject;
import de.demoncore.gui.GUIRectangle;
import de.demoncore.gui.GUIText;
import de.demoncore.gui.Gui;
import de.demoncore.gui.TextAlignment;
import de.demoncore.utils.Resources;

public class BallTrailsMenu extends GUIMenu {


	int xSpacing = 100;
	float amountBtn = 3f;
	int buttonWidth = 325;
	
	int posY = -25;
	private GUIButton option1Button;
	private GUIButton option2Button;
	private GUIButton option3Button;
	private GUIButton option4Button;
	private GUIRectangle option1Preview;
	private GUIRectangle option2Preview;
	private GUIRectangle option3Preview;
	private GUIRectangle option4Preview;
	private GUIText currency;
	
	@Override
	protected List<GUIObject> addMenuContent() {
	
		List<GUIObject> guiObjects = new ArrayList<GUIObject>();
		
		GUIText title = new GUIText(75, 100, Translation.get("shop.trails"), Resources.uiFont.deriveFont(45f), Color.white);
		title.alignment = GUIAlignment.TopLeft;
		title.SetTextAlignment(TextAlignment.Left);
		guiObjects.add(title);

		currency = new GUIText(-75, 100, Translation.literal("Pong Taler: " + ShopValues.shopData.getPlayerMoney()), Resources.uiFont.deriveFont(45f), Color.white);
		currency.alignment = GUIAlignment.TopRight;
		currency.SetTextAlignment(TextAlignment.Right);
		guiObjects.add(currency);
		
		option1Button = new GUIButton(getX(0), posY, buttonWidth, 650, Translation.literal(""), Resources.uiFont, new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				if((ShopValues.shopData.unlockedBallTrails & 0b00000001) != 0) {					
					ShopValues.shopData.activeBallTrail = BallTrails.None;
				}
			}
		});
		option1Button.alignment = GUIAlignment.Center;
		
		GUIText option1Text = new GUIText(getX(0), 400 + posY, Translation.get("trails.none"), Resources.uiFont.deriveFont(45F), Color.white);
		option1Text.alignment = GUIAlignment.Center;
		guiObjects.add(option1Text);

		option1Preview = new GUIRectangle(getX(0), 0, 150, 150, Color.white);
		
		option2Button = new GUIButton(getX(1), posY, buttonWidth, 650, Translation.literal(""), Resources.uiFont, new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				if((ShopValues.shopData.unlockedBallTrails & 0b00000010) != 0) {					
					ShopValues.shopData.activeBallTrail = BallTrails.Simple;
				}
			}
		});
		option2Button.alignment = GUIAlignment.Center;
		
		GUIText option2Text = new GUIText(getX(1), 400 + posY, Translation.get("trails.simple"), Resources.uiFont.deriveFont(45F), Color.white);
		option2Text.alignment = GUIAlignment.Center;
		guiObjects.add(option2Text);
		
		option2Preview = new GUIRectangle(getX(1), 0, 150, 150, Color.white);
		
		option3Button = new GUIButton(getX(2), posY, buttonWidth, 650, Translation.literal(""), Resources.uiFont, new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				if((ShopValues.shopData.unlockedBallTrails & 0b00000100) != 0) {					
					ShopValues.shopData.activeBallTrail = BallTrails.Particles;
				}
			}
		});
		option3Button.alignment = GUIAlignment.Center;
		
		GUIText option3Text = new GUIText(getX(2), 400 + posY, Translation.get("trails.particles"), Resources.uiFont.deriveFont(45F), Color.white);
		option3Text.alignment = GUIAlignment.Center;
		guiObjects.add(option3Text);

		option3Preview = new GUIRectangle(getX(2), 0, 150, 150, Color.white);
		
		option4Button = new GUIButton(getX(3), posY, buttonWidth, 650, Translation.literal(""), Resources.uiFont, new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				if((ShopValues.shopData.unlockedBallTrails & 0b00001000) != 0) {
					ShopValues.shopData.activeBallTrail = BallTrails.Beam;
				}
			}
		});
		option4Button.alignment = GUIAlignment.Center;
		
		GUIText option4Text = new GUIText(getX(3), 400 + posY, Translation.get("trails.beam"), Resources.uiFont.deriveFont(45F), Color.white);
		option4Text.alignment = GUIAlignment.Center;
		guiObjects.add(option4Text);

		option4Preview = new GUIRectangle(getX(3), 0, 150, 150, Color.white);

		GUIButton buyOption1 = new GUIButton(getX(0), posY + 275, 250, 50, Translation.literal(Translation.get("shop.buy").Get() + " (" + BallSkins.White.getNumVal() + " Pong Taler)"), Resources.uiFont.deriveFont(18F), null);
		buyOption1.alignment = GUIAlignment.Center;
		buyOption1.setButtonEvent(new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();

				if(BallTrails.None.getNumVal() <= ShopValues.shopData.getPlayerMoney()) {
					ShopValues.shopData.addPlayerMoney(-BallTrails.None.getNumVal());
				}else {
					return;
				}
				
				ShopValues.shopData.unlockedBallTrails = (byte) (ShopValues.shopData.unlockedBallTrails | 0b00000001);
				SceneManager.getActiveScene().destroyObject(buyOption1);
			}
		});
		
		GUIButton buyOption2 = new GUIButton(getX(1), posY + 275, 250, 50, Translation.literal(Translation.get("shop.buy").Get() + " (" + BallSkins.Red.getNumVal() + " Pong Taler)"), Resources.uiFont.deriveFont(18F), new GUIButtonClickEvent() {
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

				if(BallTrails.Simple.getNumVal() <= ShopValues.shopData.getPlayerMoney()) {
					ShopValues.shopData.addPlayerMoney(-BallTrails.Simple.getNumVal());
				}else {
					return;
				}
				
				ShopValues.shopData.unlockedBallTrails = (byte) (ShopValues.shopData.unlockedBallTrails | 0b00000010);
				SceneManager.getActiveScene().destroyObject(buyOption2);
			}
		});
		
		GUIButton buyOption3 = new GUIButton(getX(2), posY + 275, 250, 50, Translation.literal(Translation.get("shop.buy").Get() + " (" + BallSkins.Yellow.getNumVal() + " Pong Taler)"), Resources.uiFont.deriveFont(18F), new GUIButtonClickEvent() {
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

				if(BallTrails.Particles.getNumVal() <= ShopValues.shopData.getPlayerMoney()) {
					ShopValues.shopData.addPlayerMoney(-BallTrails.Particles.getNumVal());
				}else {
					return;
				}
				
				ShopValues.shopData.unlockedBallTrails = (byte) (ShopValues.shopData.unlockedBallTrails | 0b00000100);
				SceneManager.getActiveScene().destroyObject(buyOption3);
			}
		});
		
		GUIButton buyOption4 = new GUIButton(getX(3), posY + 275, 250, 50, Translation.literal(Translation.get("shop.buy").Get() + " (" + BallSkins.Rainbow.getNumVal() + " Pong Taler)"), Resources.uiFont.deriveFont(18F), new GUIButtonClickEvent() {
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
				
				if(BallTrails.Beam.getNumVal() <= ShopValues.shopData.getPlayerMoney()) {
					ShopValues.shopData.addPlayerMoney(-BallTrails.Beam.getNumVal());
				}else {
					return;
				}
				
				ShopValues.shopData.unlockedBallTrails = (byte) (ShopValues.shopData.unlockedBallTrails | 0b00001000);
				SceneManager.getActiveScene().destroyObject(buyOption4);
			}
		});
		
		guiObjects.add(option1Preview);
		guiObjects.add(option2Preview);
		guiObjects.add(option3Preview);
		guiObjects.add(option4Preview);
		
		guiObjects.add(option1Button);
		guiObjects.add(option2Button);
		guiObjects.add(option3Button);
		guiObjects.add(option4Button);
		
		if((ShopValues.shopData.unlockedBallTrails & 0b00000001) == 0)
			guiObjects.add(buyOption1);
		if((ShopValues.shopData.unlockedBallTrails & 0b00000010) == 0)
			guiObjects.add(buyOption2);
		if((ShopValues.shopData.unlockedBallTrails & 0b00000100) == 0)
			guiObjects.add(buyOption3);
		if((ShopValues.shopData.unlockedBallTrails & 0b00001000) == 0)
			guiObjects.add(buyOption4);
		
		option1Preview.alignment = GUIAlignment.Center;
		option2Preview.alignment = GUIAlignment.Center;
		option3Preview.alignment = GUIAlignment.Center;
		option4Preview.alignment = GUIAlignment.Center;
		option1Preview.collisionEnabled = false;
		option2Preview.collisionEnabled = false;
		option3Preview.collisionEnabled = false;
		option4Preview.collisionEnabled = false;

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

			option1Preview.color = Color.white;
			option2Preview.color = Color.red;
			option3Preview.color = Color.yellow;
			option4Preview.color = PongBall.regenbogen();
			
			clearCol();
			
			currency.SetText(Translation.literal("Pong Taler: " + ShopValues.shopData.getPlayerMoney()));
			
			switch (ShopValues.shopData.activeBallSkin) {
			case White:
				option1Button.normalColor = new Color(1f, 0.8f, 0.1f, 0.5f);
				break;
			case Red:
				option2Button.normalColor = new Color(1f, 0.8f, 0.1f, 0.5f);
				break;
			case Yellow:
				option3Button.normalColor = new Color(1f, 0.8f, 0.1f, 0.5f);
				break;
			case Rainbow:
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
