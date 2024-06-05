package de.demoncore.scenes;

import java.awt.Color;

import de.demoncore.actions.GameActionListener;
import de.demoncore.actions.KeyHandler;
import de.demoncore.game.GameObject;
import de.demoncore.gameObjects.PauseMenu;
import de.demoncore.gameObjects.PongPlayer;
import de.demoncore.gui.GUIAlignment;
import de.demoncore.gui.GUIButton;
import de.demoncore.gui.GUIButtonClickEvent;
import de.demoncore.utils.Resources;
import de.demoncore.utils.Vector3;

public class TestSzene extends BaseScene {

	PongPlayer player;
	
	@Override
	public void InitializeScene() {		
		
		AddObject(new PauseMenu());
		
		player = new PongPlayer(25, -50);
		AddObject(player);
		
		GameObject rotesObjekt = new GameObject(0, 0, 25, 25);
		rotesObjekt.color = Color.red;
		rotesObjekt.collisionEnabled = false;
		
		AddObject(rotesObjekt);

		KeyHandler.listeners.add(new GameActionListener() {
			@Override
			public void OnInteractionKeyPressed() {
				super.OnInteractionKeyPressed();
				
				rotesObjekt.SetPosition(rotesObjekt.GetPosition().add(Vector3.one()));
				ShakeCamera(50, 2, 2);
				
				rotesObjekt.enableRendering = !rotesObjekt.enableRendering;
			}
		});
		
		
		GUIButton btn = new GUIButton(0, 0, 250, 50, "Test Button: 0", Resources.uiFont.deriveFont(25F), null);
		btn.SetButtonEvent(new GUIButtonClickEvent() {
			int counter = 0;
		@Override
		public void ButtonClick() {
			super.ButtonClick();
		
			counter++;
			btn.SetText("Test Button: + " + counter);
			
			rotesObjekt.SetPosition(rotesObjekt.GetPosition().add(Vector3.one()));
			ShakeCamera(50, 2, 2);
		}
		});
		
		btn.alignment = GUIAlignment.Center;
		AddObject(btn);
	}
	
	@Override
	public void UpdateScene() {
		super.UpdateScene();

		cameraPosition = Vector3.Lerp(cameraPosition, player.GetPosition(), 0.035f);
	}
	
}
