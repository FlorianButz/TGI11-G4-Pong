package de.demoncore.scenes;

import java.awt.Color;
import java.awt.Font;

import org.w3c.dom.ls.LSResourceResolver;

import de.demoncore.audio.MusicManager;
import de.demoncore.game.SceneManager;
import de.demoncore.game.Translation;
import de.demoncore.game.animator.AnimatorOnCompleteEvent;
import de.demoncore.game.animator.AnimatorUpdateEvent;
import de.demoncore.game.animator.Easing.EasingType;
import de.demoncore.game.animator.Vector3Animator;
import de.demoncore.gameObjects.ParticleSystem;
import de.demoncore.gameObjects.SettingsMenu;
import de.demoncore.gui.GUIAlignment;
import de.demoncore.gui.GUIButton;
import de.demoncore.gui.GUIButtonClickEvent;
import de.demoncore.gui.GUIImageButton;
import de.demoncore.gui.GUIMenu;
import de.demoncore.gui.GUIText;
import de.demoncore.gui.GUIToggle;
import de.demoncore.main.Main;
import de.demoncore.scenes.shop.ShopScene;
import de.demoncore.scenes.storymode.Dungeon;
import de.demoncore.sprites.SpriteObject;
import de.demoncore.utils.Resources;
import de.demoncore.utils.Vector3;

public class MainMenu extends BaseScene {
	
	@Override
	public void initializeScene() {
		super.initializeScene();
		
		MusicManager.PlayMusic(Resources.sneakySnitch);
		
		ParticleSystem bgSys = new ParticleSystem(0, 0);	// Neues partikelsystem wird definiert
		bgSys.particleSpawnArea = new Vector3(1000, 1000);
		bgSys.emitLoop = true;
		bgSys.particleColorFirst = new Color(1, 1, 1, 0.085f);
		bgSys.particleColorSecond = new Color(1, 1, 1, 0.04f);
		bgSys.emitPause = 15;
		bgSys.emitChunk = 2;
		bgSys.initialParticleSize = 15;
		bgSys.particleLifetime += 2500;
		bgSys.initialParticleSpeedMax = new Vector3(0.25f, 0.25f);
		bgSys.initialParticleSpeedMin = new Vector3(-0.25f, -0.25f);
		bgSys.particleGravity = 0;
		bgSys.endParticleSize = 0;
		bgSys.Init();
		addObject(bgSys);	// Füge partikelsystem zum level hinzu
		
		GUIText title = new GUIText(0, 175, Translation.literal(Main.gameName), Resources.dialogFont.deriveFont(Font.PLAIN, 125F), Color.WHITE);	// Titel text
		addObject(title);
		
		Vector3Animator anim = new Vector3Animator(Vector3.one().multiply(150), Vector3.one().multiply(160), 5, EasingType.Linear); // Erstelle neue animation für den Titel text
		anim.setOnUpdate(new AnimatorUpdateEvent() {
			@Override
			public void onUpdate(Vector3 value) {
				super.onUpdate(value);
				title.SetFont(title.GetFont().deriveFont(value.x));	// Ändere die größe vom text in der animation
			}
		});
		anim.setOnComplete(new AnimatorOnCompleteEvent() {
			@Override
			public void onComplete() {
				super.onComplete();
				
				Vector3Animator anim2 = new Vector3Animator(Vector3.one().multiply(160), Vector3.one().multiply(150), 5, EasingType.Linear); // Erstelle zweite animation für den Titel text
				anim2.setOnUpdate(new AnimatorUpdateEvent() {
					@Override
					public void onUpdate(Vector3 value) {
						super.onUpdate(value);
						title.SetFont(title.GetFont().deriveFont(value.x));	// Ändere die größe vom text in der animation
					}
				});
				anim2.setOnComplete(new AnimatorOnCompleteEvent() {
					@Override
					public void onComplete() {
						super.onComplete();
						anim.play();	// Spiele animation 1 wenn animation 2 fertig ist
					}
				});
				anim2.play();	// Spiele animation 2 wenn animation 1 fertig ist
			}
		});
		anim.play(); // Spiele animation 1
		
		GUIButton singleplayer = new GUIButton(-220, 0, 360, 75, Translation.get("mainmenu.singleplayer"), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				SceneManager.LoadScene(new OnePlayerPong());
			}
		});
		singleplayer.alignment = GUIAlignment.Center;
		addObject(singleplayer);
		
		GUIButton localMultiplayer = new GUIButton(220, 0, 360, 75, Translation.get("mainmenu.multiplayer"), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				SceneManager.LoadScene(new TwoPlayerPong());
			}
		});
		localMultiplayer.alignment = GUIAlignment.Center;
		addObject(localMultiplayer);
		
		GUIButton storymode = new GUIButton(0, 100, 800, 75, Translation.get("mainmenu.storymode"), Resources.uiFont.deriveFont(35F),  new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				SceneManager.LoadScene(new Dungeon());
			}
		});
		storymode.alignment = GUIAlignment.Center;
		addObject(storymode);
		
		GUIButton settings = new GUIButton(0, 200, 800, 75, Translation.get("mainmenu.settings"), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				GUIMenu s = new SettingsMenu();
				addObject(s);
				s.ShowMenu();
			}
		});
		settings.alignment = GUIAlignment.Center;
		addObject(settings);
		
		GUIButton quit = new GUIButton(0, 300, 800, 75, Translation.get("mainmenu.quit"), Resources.uiFont.deriveFont(35F),  new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				System.exit(0);
			}
		});
		quit.alignment = GUIAlignment.Center;
		addObject(quit);
		
		
		
		GUIImageButton shop = new GUIImageButton(475, 0, 75, 75, Resources.shopIcon, Resources.shopHoverIcon, new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				SceneManager.LoadScene(new ShopScene());
			}
		});
		shop.alignment = GUIAlignment.Center;
		addObject(shop);
	}
}
