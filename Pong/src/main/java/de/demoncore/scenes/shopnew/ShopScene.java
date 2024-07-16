package de.demoncore.scenes.shopnew;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import de.demoncore.actions.KeyHandler;
import de.demoncore.game.SceneManager;
import de.demoncore.game.Settings;
import de.demoncore.game.Translation;
import de.demoncore.gui.GUIAlignment;
import de.demoncore.gui.GUIButton;
import de.demoncore.gui.GUIButtonClickEvent;
import de.demoncore.gui.GUIImageButton;
import de.demoncore.gui.GUIMenu;
import de.demoncore.gui.GUIObject;
import de.demoncore.gui.GUIText;
import de.demoncore.gui.GUITextArea;
import de.demoncore.gui.TextAlignment;
import de.demoncore.scenes.BaseScene;
import de.demoncore.scenes.MainMenu;
import de.demoncore.utils.Resources;

public class ShopScene extends BaseScene {

	int xSpacing = 150;
	float amountBtn = 3f;
	int buttonWidth = 325;
	
	int posY = -25;
	
	@Override
	public void initializeScene() {
		super.initializeScene();
	
		GUIText title = new GUIText(0, 125, Translation.literal("Shop"), Resources.dialogFont.deriveFont(125F), Color.white);
		title.alignment = GUIAlignment.TopMiddle;
		addObject(title);
		
		GUIMenu info = new GUIMenu() {
			@Override
			protected List<GUIObject> addMenuContent() {
				List<GUIObject> objects = new ArrayList<GUIObject>();

				GUIText infoText = new GUIText(0, 0, Translation.get("shop.info"), Resources.uiFont.deriveFont(35F), Color.white);
				infoText.alignment = GUIAlignment.Center;
				infoText.SetTextAlignment(TextAlignment.Center);
				objects.add(infoText);
				
				return objects;
			}
		};
		GUIImageButton infoBtn = new GUIImageButton(-125, 125, 75, 75, 100, Resources.info_icon, Resources.info_icon, new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				info.ShowMenu();
			}
		});
		infoBtn.alignment = GUIAlignment.TopRight;
		infoBtn.normalColor = new Color(0.5f, 0.5f, 0.5f, 0.25f);
		infoBtn.currentColor = infoBtn.normalColor;
		addObject(infoBtn);
		
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

		BallTrailsMenu t = new BallTrailsMenu();
		addObject(t);
		GUIImageButton ballTrailsButton = new GUIImageButton(getX(1), posY, buttonWidth, 650, 150, Resources.shop_particletrailicon, Resources.shop_particletrailicon, new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				t.ShowMenu();
			}
		});
		ballTrailsButton.alignment = GUIAlignment.Center;
		addObject(ballTrailsButton);
		
		GUIText ballTrailsText = new GUIText(getX(1), 400 + posY, Translation.get("shop.trails"), Resources.uiFont.deriveFont(45F), Color.white);
		ballTrailsText.alignment = GUIAlignment.Center;
		addObject(ballTrailsText);
		
		PedalSkinsMenu p = new PedalSkinsMenu();
		addObject(p);
		GUIImageButton pedalSkinsButton = new GUIImageButton(getX(2), posY, buttonWidth, 650, 150, Resources.shop_pedalskin, Resources.shop_pedalskin, new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				p.ShowMenu();
			}
		});
		pedalSkinsButton.alignment = GUIAlignment.Center;
		addObject(pedalSkinsButton);
		
		GUIText pedalSkinsText = new GUIText(getX(2), 400 + posY, Translation.get("shop.pedals"), Resources.uiFont.deriveFont(45F), Color.white);
		pedalSkinsText.alignment = GUIAlignment.Center;
		addObject(pedalSkinsText);
		
		SpawnAnimationsMenu sm = new SpawnAnimationsMenu();
		addObject(sm);
		GUIImageButton spawnAnimationButton = new GUIImageButton(getX(3), posY, buttonWidth, 650, 150, Resources.shop_ballspawn, Resources.shop_ballspawn, new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				sm.ShowMenu();
			}
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

		spawnAnimationButton.textNormalSize = 2;
		ballSkinsButton.textNormalSize = 2;
		pedalSkinsButton.textNormalSize = 2;
		ballTrailsButton.textNormalSize = 2;

		spawnAnimationButton.textHoverSize = 1.5f;
		ballSkinsButton.textHoverSize = 1.5f;
		pedalSkinsButton.textHoverSize = 1.5f;
		ballTrailsButton.textHoverSize = 1.5f;
		
		spawnAnimationButton.normalColor = new Color(1, 1, 1, 0.15f);
		ballSkinsButton.normalColor = new Color(1, 1, 1, 0.15f);
		pedalSkinsButton.normalColor = new Color(1, 1, 1, 0.15f);
		ballTrailsButton.normalColor = new Color(1, 1, 1, 0.15f);
	}
	
	@Override
	public void updateScene() {
		super.updateScene();
	
		if(KeyHandler.isCtrlPressed && KeyHandler.playerInput1.y != 0 && Settings.getDebugMode())
			ShopValues.shopData.addPlayerMoney(5);
	}
	
	int getX(int index) {
		return (int) ((int)(index * buttonWidth + index * xSpacing) - ((amountBtn / 2) * buttonWidth + (amountBtn / 2) * xSpacing));
	}
	
}
