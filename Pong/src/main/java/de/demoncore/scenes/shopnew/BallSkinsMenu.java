package de.demoncore.scenes.shopnew;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import de.demoncore.game.SceneManager;
import de.demoncore.game.Translation;
import de.demoncore.gui.GUIAlignment;
import de.demoncore.gui.GUIButton;
import de.demoncore.gui.GUIButtonClickEvent;
import de.demoncore.gui.GUIMenu;
import de.demoncore.gui.GUIObject;
import de.demoncore.gui.GUIText;
import de.demoncore.gui.TextAlignment;
import de.demoncore.scenes.shop.BallColors;
import de.demoncore.utils.Resources;

public class BallSkinsMenu extends GUIMenu {


	int xSpacing = 100;
	float amountBtn = 3f;
	int buttonWidth = 325;
	
	int posY = -25;
	private GUIButton option1Button;
	private GUIButton option2Button;
	private GUIButton option3Button;
	private GUIButton option4Button;
	
	@Override
	protected List<GUIObject> addMenuContent() {
	
		List<GUIObject> guiObjects = new ArrayList<GUIObject>();
		
		GUIText title = new GUIText(75, 50, Translation.literal("Shop"), Resources.uiFont.deriveFont(100F), Color.white);
		title.alignment = GUIAlignment.TopLeft;
		title.SetTextAlignment(TextAlignment.Left);
		guiObjects.add(title);
		
		option1Button = new GUIButton(getX(0), posY, buttonWidth, 650, Translation.literal(""), Resources.uiFont, new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				ShopValues.shopData.activeBallSkin = BallSkins.White;
			}
		});
		option1Button.alignment = GUIAlignment.Center;
		guiObjects.add(option1Button);
		
		GUIText option1Text = new GUIText(getX(0), 400 + posY, Translation.get("color.white"), Resources.uiFont.deriveFont(45F), Color.white);
		option1Text.alignment = GUIAlignment.Center;
		guiObjects.add(option1Text);


		option2Button = new GUIButton(getX(1), posY, buttonWidth, 650, Translation.literal(""), Resources.uiFont, new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				ShopValues.shopData.activeBallSkin = BallSkins.Red;
			}
		});
		option2Button.alignment = GUIAlignment.Center;
		guiObjects.add(option2Button);
		
		GUIText option2Text = new GUIText(getX(1), 400 + posY, Translation.get("color.red"), Resources.uiFont.deriveFont(45F), Color.white);
		option2Text.alignment = GUIAlignment.Center;
		guiObjects.add(option2Text);
		
		option3Button = new GUIButton(getX(2), posY, buttonWidth, 650, Translation.literal(""), Resources.uiFont, new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				ShopValues.shopData.activeBallSkin = BallSkins.Yellow;
			}
		});
		option3Button.alignment = GUIAlignment.Center;
		guiObjects.add(option3Button);
		
		GUIText option3Text = new GUIText(getX(2), 400 + posY, Translation.get("color.yellow"), Resources.uiFont.deriveFont(45F), Color.white);
		option3Text.alignment = GUIAlignment.Center;
		guiObjects.add(option3Text);
		
		option4Button = new GUIButton(getX(3), posY, buttonWidth, 650, Translation.literal(""), Resources.uiFont, new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				ShopValues.shopData.activeBallSkin = BallSkins.Rainbow;
			}
		});
		option4Button.alignment = GUIAlignment.Center;
		guiObjects.add(option4Button);
		
		GUIText option4Text = new GUIText(getX(3), 400 + posY, Translation.get("color.rgb"), Resources.uiFont.deriveFont(45F), Color.white);
		option4Text.alignment = GUIAlignment.Center;
		guiObjects.add(option4Text);

		
		option1Button.normalColor = new Color(1, 1, 1, 0.15f);
		option2Button.normalColor = new Color(1, 1, 1, 0.15f);
		option3Button.normalColor = new Color(1, 1, 1, 0.15f);
		option4Button.normalColor = new Color(1, 1, 1, 0.15f);
		
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
		return back;
	}
	
	int getX(int index) {
		return (int) ((int)(index * buttonWidth + index * xSpacing) - ((amountBtn / 2) * buttonWidth + (amountBtn / 2) * xSpacing));
	}
}
