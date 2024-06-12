package de.demoncore.gui.dialog;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import de.demoncore.actions.GameActionListener;
import de.demoncore.actions.KeyHandler;
import de.demoncore.audio.AudioSource;
import de.demoncore.game.GameObject;
import de.demoncore.game.SceneManager;
import de.demoncore.game.Translation;
import de.demoncore.game.TranslationComponent;
import de.demoncore.gui.GUIAlignment;
import de.demoncore.gui.GUIButton;
import de.demoncore.gui.GUIButtonClickEvent;
import de.demoncore.gui.GUIObject;
import de.demoncore.gui.GUIRectangle;
import de.demoncore.gui.GUIText;
import de.demoncore.gui.GUITextArea;
import de.demoncore.gui.GUITheme;
import de.demoncore.gui.GUITheme.Theme;
import de.demoncore.gui.Gui;
import de.demoncore.gui.TextAlignment;
import de.demoncore.utils.Resources;

public class Dialog extends GameObject {
	
	public static boolean isActiveDialog = false;
	
	List<GUIObject> content = new ArrayList<GUIObject>();

	private DialogLine activeLine;
	
	GUIText name;
	GUITextArea dialogueText;
	GUIText spaceText;
	
	GameActionListener listener;
	
	AudioSource[] sfxSource;
	
	public Dialog(DialogLine startLine) {		
		super(0, 0, 0, 0);
		
		listener = new GameActionListener() {
			@Override
			public void onSpaceKeyPressed() {
				super.onSpaceKeyPressed();
				nextLine();
			}
		};
		KeyHandler.listeners.add(listener);

		this.activeLine = startLine;
		
		content.add(createBackground());
		
		sfxSource = new AudioSource[] { new AudioSource(this).SetSpacial(false), new AudioSource(this).SetSpacial(false) };
		SceneManager.GetActiveScene().addObject(sfxSource[0]);
		SceneManager.GetActiveScene().addObject(sfxSource[1]);
		
		name = new GUIText(25, -325, Translation.literal(""), Resources.dialogFont.deriveFont(75F), new Color(1f, 1f, 1f, 1f));
		name.alignment = GUIAlignment.DownLeft;
		name.SetTextAlignment(TextAlignment.Left);
		content.add(name);
		
		spaceText = new GUIText(-75 -125, -35, Translation.get("dialog.continue"), Resources.uiFont.deriveFont(20F), new Color(1f, 1f, 1f, 0.5f));
		spaceText.SetTextAlignment(TextAlignment.Right);
		spaceText.alignment = GUIAlignment.DownRight;
		content.add(spaceText);
		
		GUIButton skipBtn = new GUIButton(-100, -35, 150, 35, Translation.get("dialog.skip"), Resources.uiFont.deriveFont(20F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				super.ButtonClick();
				destroyDialog();
			}
		});
		skipBtn.alignment = GUIAlignment.DownRight;
		GUITheme.LoadButtonTheme(skipBtn, Theme.ButtonSecondary);
		content.add(skipBtn);
		
		dialogueText = new GUITextArea(25, -225, Translation.literal(""),
				Resources.uiFont.deriveFont(35F), new Color(1f, 1f, 1f, 1f));
		dialogueText.alignment = GUIAlignment.DownLeft;
		dialogueText.SetTextAlignment(TextAlignment.Left);
		dialogueText.maxLetterInOneLine = 75;
		GUITheme.LoadTextTheme(dialogueText, Theme.TextSecondary);
		content.add(dialogueText);
	}
	
	public void showDialog() {
		isActiveDialog = true;
		
		createDialog();
		nextLine();
	}
	
	boolean isLinePlaying = false;
	
	void nextLine() {
		
		if(isLinePlaying) return;
		isLinePlaying = true;
		
		if(activeLine == null) {
			destroyDialog();
			return;
		}

		SceneManager.GetActiveScene().destroyObject(spaceText);
		
		Thread characterThread = new Thread("DialogAnimate") {
			@Override
			public void run() {

				name.SetText(Translation.literal(activeLine.GetName()));
				
				String displayText = "";
				String dialogLineText = activeLine.GetLine();
				
				for(int c = 0; c < dialogLineText.length(); c++) {
					displayText += dialogLineText.charAt(c);
					dialogueText.SetText(Translation.literal(displayText));
					
					sfxSource[c % 2].Play(Resources.dialogTalk);
					
					try {
						Thread.sleep(55);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				if(isInScene)
					SceneManager.GetActiveScene().addObject(spaceText);
				
				isLinePlaying = false;
				
				if(activeLine.GetNextLine() != null)
					activeLine = activeLine.GetNextLine();
				else {
					activeLine = null;
					return;
				}
			}
		};
		
		characterThread.start();
	}
	
	void createDialog() {
		for(GUIObject o : content) {
			SceneManager.GetActiveScene().addObject(o);
		}
	}
	
	void destroyDialog() {
		SceneManager.GetActiveScene().destroyObject(this);
		
		for(GUIObject o : content) {
			SceneManager.GetActiveScene().destroyObject(o);
		}
		
		isActiveDialog = false;
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();

		KeyHandler.listeners.remove(listener);
		SceneManager.GetActiveScene().destroyObject(sfxSource[0]);
		SceneManager.GetActiveScene().destroyObject(sfxSource[1]);
	}
	
	protected GUIObject createBackground() {
		GUIRectangle bg = new GUIRectangle(0, -200, (int)Gui.GetScreenDimensions().x, (int)400, new Color(0.075f, 0.075f, 0.075f, 0.65f));
		bg.alignment = GUIAlignment.DownMiddle;
		return bg;
	}
	
}
