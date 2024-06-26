package de.demoncore.scenes.shopnew;

import java.awt.Color;

import de.demoncore.game.SceneManager;
import de.demoncore.game.Translation;
import de.demoncore.gui.GUIAlignment;
import de.demoncore.gui.GUIButton;
import de.demoncore.gui.GUIButtonClickEvent;
import de.demoncore.gui.GUIImageButton;
import de.demoncore.gui.GUIText;
import de.demoncore.scenes.BaseScene;
import de.demoncore.scenes.shop.BallColors;
import de.demoncore.utils.Resources;

public class ShopScene extends BaseScene {

	int xSpacing = 100;
	int amountBtn = 4;
	int buttonWidth = 325;
	
	int posY = 100;
	
	@Override
	public void initializeScene() {
		super.initializeScene();
	
		GUIText title = new GUIText(0, 200, Translation.literal("Shop"), Resources.dialogFont.deriveFont(55F), Color.white);
		title.alignment = GUIAlignment.TopMiddle;
		addObject(title);
		
		GUIImageButton ballSkinsButton = new GUIImageButton(getX(0), posY, buttonWidth, 650, 150, Resources.cake, Resources.fullHeart, new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				SceneManager.loadScene(new BallColors());
			}
		});
		ballSkinsButton.alignment = GUIAlignment.Center;
		addObject(ballSkinsButton);
		
		GUIText ballSkinsText = new GUIText(getX(0), 400 + posY, Translation.literal("Test"), Resources.uiFont.deriveFont(45F), Color.white);
		ballSkinsText.alignment = GUIAlignment.Center;
		addObject(ballSkinsText);


		GUIImageButton ballTrailsButton = new GUIImageButton(getX(1), posY, buttonWidth, 650, 150, Resources.cake, Resources.fullHeart, new GUIButtonClickEvent() {
		});
		ballTrailsButton.alignment = GUIAlignment.Center;
		addObject(ballTrailsButton);
		
		GUIText ballTrailsText = new GUIText(getX(1), 400 + posY, Translation.literal("Test"), Resources.uiFont.deriveFont(45F), Color.white);
		ballTrailsText.alignment = GUIAlignment.Center;
		addObject(ballTrailsText);
		
		GUIImageButton pedalSkinsButton = new GUIImageButton(getX(2), posY, buttonWidth, 650, 150, Resources.cake, Resources.fullHeart, new GUIButtonClickEvent() {
		});
		pedalSkinsButton.alignment = GUIAlignment.Center;
		addObject(pedalSkinsButton);
		
		GUIText pedalSkinsText = new GUIText(getX(2), 400 + posY, Translation.literal("Test"), Resources.uiFont.deriveFont(45F), Color.white);
		pedalSkinsText.alignment = GUIAlignment.Center;
		addObject(pedalSkinsText);


		GUIImageButton backgroundsButton = new GUIImageButton(getX(3), posY, buttonWidth, 650, 150, Resources.cake, Resources.fullHeart, new GUIButtonClickEvent() {
		});
		backgroundsButton.alignment = GUIAlignment.Center;
		addObject(backgroundsButton);
		
		GUIText backgroundsText = new GUIText(getX(3), 400 + posY, Translation.literal("Test"), Resources.uiFont.deriveFont(45F), Color.white);
		backgroundsText.alignment = GUIAlignment.Center;
		addObject(backgroundsText);
		
		GUIImageButton powerupsButton = new GUIImageButton(getX(4), posY, buttonWidth, 650, 150, Resources.cake, Resources.fullHeart, new GUIButtonClickEvent() {
		});
		powerupsButton.alignment = GUIAlignment.Center;
		addObject(powerupsButton);
		
		GUIText powerupsText = new GUIText(getX(4), 400 + posY, Translation.literal("Test"), Resources.uiFont.deriveFont(45F), Color.white);
		powerupsText.alignment = GUIAlignment.Center;
		addObject(powerupsText);
	}
	
	int getX(int index) {
		return (index * buttonWidth + index * xSpacing) - ((amountBtn / 2) * buttonWidth + (amountBtn / 2) * xSpacing);
	}
	
}
