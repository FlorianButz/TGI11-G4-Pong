package de.demoncore.scenes;

import java.awt.Color;

import de.demoncore.actions.GameActionListener;
import de.demoncore.actions.KeyHandler;
import de.demoncore.game.GameObject;
import de.demoncore.gameObjects.PauseMenu;
import de.demoncore.gameObjects.storymode.StorymodePlayer;
import de.demoncore.gui.GUIAlignment;
import de.demoncore.gui.GUIButton;
import de.demoncore.gui.GUIButtonClickEvent;
import de.demoncore.utils.Resources;
import de.demoncore.utils.Vector3;

public class TestSzene extends BaseScene {

	StorymodePlayer player;
	
	@Override
	public void initializeScene() {		
		
		addObject(new PauseMenu());
		
		player = new StorymodePlayer(25, -50);
		addObject(player);
		
		GameObject rotesObjekt = new GameObject(0, 0, 25, 25);
		rotesObjekt.color = Color.red;
		rotesObjekt.collisionEnabled = false;
		
		addObject(rotesObjekt);

		KeyHandler.listeners.add(new GameActionListener() {
			@Override
			public void onInteractionKeyPressed() {
				super.onInteractionKeyPressed();
				
				rotesObjekt.setPosition(rotesObjekt.getPosition().add(Vector3.one()));
				ShakeCamera(50, 2, 2);
				
				rotesObjekt.enableRendering = !rotesObjekt.enableRendering;
			}
		});
	}
	
	@Override
	public void updateScene() {
		super.updateScene();

		cameraPosition = Vector3.lerp(cameraPosition, player.getPosition(), 0.035f);
	}
	
}
