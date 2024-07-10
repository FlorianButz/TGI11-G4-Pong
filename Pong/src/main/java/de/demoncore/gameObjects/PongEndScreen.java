package de.demoncore.gameObjects;

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
import de.demoncore.scenes.MainMenu;
import de.demoncore.utils.Resources;

public class PongEndScreen extends GUIMenu {

	boolean isPlayer1 = false;
	
	int points = 0;
	
	public PongEndScreen(boolean isPlayer1, int points) {
		this.isPlayer1 = isPlayer1;
		this.points = points;
	}
	
	public PongEndScreen(boolean isPlayer1) {
		this.isPlayer1 = isPlayer1;
		this.points = -100;
	}
	
	@Override
	protected List<GUIObject> addMenuContent() {
	
		List<GUIObject> content = new ArrayList<GUIObject>();
		
		if(this.points != -100) {
			GUIText points = new GUIText(0, -250, Translation.literal(Translation.get("pong.end_title_points").Get() + this.points), Resources.uiFont.deriveFont(60F), Color.white);
			points.alignment = GUIAlignment.Center;
			content.add(points);
		}
		
		
		GUIText title = new GUIText(0, -150, Translation.literal((isPlayer1 ? "Player 1 " : "Player 2") + Translation.get("pong.end_title").Get()), Resources.uiFont.deriveFont(60F), Color.white);
		title.alignment = GUIAlignment.Center;
		content.add(title);
		
		GUIButton btn = new GUIButton(0, 150, 650, 75, Translation.get("pong.back"), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				SceneManager.loadScene(new MainMenu());
				super.ButtonClick();
			}
			});
		btn.alignment = GUIAlignment.Center;
		content.add(btn);
		
		return content;
	}
	
	@Override
	protected GUIButton createBackButton() {
		
		GUIButton btn = new GUIButton(0, 250, 650, 75, Translation.get("pong.play_again"), Resources.uiFont.deriveFont(35F), new GUIButtonClickEvent() {
			@Override
			public void ButtonClick() {
				SceneManager.loadScene(SceneManager.getActiveScene());
				super.ButtonClick();
			}
			});
		btn.alignment = GUIAlignment.Center;
		return btn;
	}
	
}
