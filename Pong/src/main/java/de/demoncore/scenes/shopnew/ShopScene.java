package de.demoncore.scenes.shopnew;

import java.awt.Color;

import de.demoncore.game.SceneManager;
import de.demoncore.game.Settings;
import de.demoncore.game.Translation;
import de.demoncore.gui.GUIAlignment;
import de.demoncore.gui.GUIButton;
import de.demoncore.gui.GUIButtonClickEvent;
import de.demoncore.gui.GUIImageButton;
import de.demoncore.gui.GUIText;
import de.demoncore.gui.GUITheme;
import de.demoncore.gui.GUITheme.Theme;
import de.demoncore.scenes.BaseScene;
import de.demoncore.scenes.MainMenu;
import de.demoncore.scenes.shop.BallColors;
import de.demoncore.utils.Resources;

public class ShopScene extends BaseScene {

	int xSpacing = 100;
	float amountBtn = 3f;
	int buttonWidth = 325;
	
	int posY = -25;
	
	@Override
	public void initializeScene() {
		super.initializeScene();
	
		GUIText title = new GUIText(0, 125, Translation.literal("Shop"), Resources.dialogFont.deriveFont(125F), Color.white);
		title.alignment = GUIAlignment.TopMiddle;
		addObject(title);
		
		BallSkinsMenu s = new BallSkinsMenu();
		addObject(s);
		GUIImageButton ballSkinsButton = new GUIImageButton(getX(0), posY, buttonWidth, 650, 150, Resources.shop_ballskin, Resources.shop_ballskin, new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				s.ShowMenu();
			}
		});
		ballSkinsButton.alignment = GUIAlignment.Center;
		addObject(ballSkinsButton);
		
		GUIText ballSkinsText = new GUIText(getX(0), 400 + posY, Translation.get("shop.ballskins"), Resources.uiFont.deriveFont(45F), Color.white);
		ballSkinsText.alignment = GUIAlignment.Center;
		addObject(ballSkinsText);


		GUIImageButton ballTrailsButton = new GUIImageButton(getX(1), posY, buttonWidth, 650, 150, Resources.shop_balltrail, Resources.shop_balltrail, new GUIButtonClickEvent() {
		});
		ballTrailsButton.alignment = GUIAlignment.Center;
		addObject(ballTrailsButton);
		
		GUIText ballTrailsText = new GUIText(getX(1), 400 + posY, Translation.get("shop.trails"), Resources.uiFont.deriveFont(45F), Color.white);
		ballTrailsText.alignment = GUIAlignment.Center;
		addObject(ballTrailsText);
		
		GUIImageButton pedalSkinsButton = new GUIImageButton(getX(2), posY, buttonWidth, 650, 150, Resources.shop_pedalskin, Resources.shop_pedalskin, new GUIButtonClickEvent() {
		});
		pedalSkinsButton.alignment = GUIAlignment.Center;
		addObject(pedalSkinsButton);
		
		GUIText pedalSkinsText = new GUIText(getX(2), 400 + posY, Translation.get("shop.pedals"), Resources.uiFont.deriveFont(45F), Color.white);
		pedalSkinsText.alignment = GUIAlignment.Center;
		addObject(pedalSkinsText);
		
		GUIImageButton spawnAnimationButton = new GUIImageButton(getX(3), posY, buttonWidth, 650, 150, Resources.shop_ballspawn, Resources.shop_ballspawn, new GUIButtonClickEvent() {
		});
		spawnAnimationButton.alignment = GUIAlignment.Center;
		addObject(spawnAnimationButton);
		
		GUIText spawnAnimationText = new GUIText(getX(3), 400 + posY, Translation.get("shop.spawns"), Resources.uiFont.deriveFont(45F), Color.white);
		spawnAnimationText.alignment = GUIAlignment.Center;
		addObject(spawnAnimationText);

		GUIButton back = new GUIButton(0, -125, 800, 50, Translation.get("shop.back"), Resources.uiFont.deriveFont(25F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				SceneManager.loadScene(new MainMenu());
				ShopValues.SaveAllSettings();
			}
		});
		back.alignment = GUIAlignment.DownMiddle;
		addObject(back);
		
		spawnAnimationButton.normalColor = new Color(1, 1, 1, 0.15f);
		ballSkinsButton.normalColor = new Color(1, 1, 1, 0.15f);
		pedalSkinsButton.normalColor = new Color(1, 1, 1, 0.15f);
		ballTrailsButton.normalColor = new Color(1, 1, 1, 0.15f);
	}
	
	int getX(int index) {
		return (int) ((int)(index * buttonWidth + index * xSpacing) - ((amountBtn / 2) * buttonWidth + (amountBtn / 2) * xSpacing));
	}
	
}
