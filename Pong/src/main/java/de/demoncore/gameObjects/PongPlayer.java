package de.demoncore.gameObjects;


import java.awt.Color;

import de.demoncore.actions.KeyHandler;
import de.demoncore.game.GameLogic;
import de.demoncore.game.Settings;
import de.demoncore.gui.Gui;
import de.demoncore.utils.GameMath;
import de.demoncore.utils.Vector3;
import de.demoncore.scenes.shop.SchleagerColor;

public class PongPlayer extends RigidBody {

	SchleagerColor Farb = new SchleagerColor();
	
	public static float playerAcceleration = 15f; // Speed

	public boolean isPlayer1 = true;
	protected boolean playerControlsEnabled = true;
	
	public PongPlayer(int posX, int posY) {
		super(posX, posY, 15, 135);
	}
	
	@Override
	public void update() {
		if(GameLogic.IsGamePaused()) return;
		position.y = GameMath.clamp(position.y,(-Gui.GetScreenDimensions().y/2) + size.y / 2,(Gui.GetScreenDimensions().y/2) - size.y / 2);
		
		playerAcceleration = Settings.isSlowPedals() ? 5.5f : 15f;
		
		if(playerControlsEnabled) {			
			Vector3 geschwindigkeit = Vector3.zero();
			if (isPlayer1) {
				geschwindigkeit = new Vector3(0,KeyHandler.playerInput1.y * playerAcceleration,0);
			} else {
				geschwindigkeit = new Vector3(0,KeyHandler.playerInput2.y * playerAcceleration,0);
			}
			addForce(geschwindigkeit);
		}
		
		color = /*Farb.Farbe() */ Color.DARK_GRAY.darker().darker().darker(); //temporär
		
		super.update();
		
		position.y = GameMath.clamp(position.y,(-Gui.GetScreenDimensions().y/2) + size.y / 2,(Gui.GetScreenDimensions().y/2) - size.y / 2);
	}

}
