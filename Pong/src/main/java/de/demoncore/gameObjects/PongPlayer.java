package de.demoncore.gameObjects;

import java.awt.Graphics2D;

import de.demoncore.actions.GameActionListener;
import de.demoncore.actions.KeyHandler;
import de.demoncore.game.GameLogic;
import de.demoncore.game.Settings;
import de.demoncore.gui.Gui;
import de.demoncore.scenes.shopnew.PedalSkins;
import de.demoncore.scenes.shopnew.ShopValues;
import de.demoncore.utils.GameMath;
import de.demoncore.utils.Logger;
import de.demoncore.utils.Resources;
import de.demoncore.utils.Vector3;

public class PongPlayer extends RigidBody {

	public static float playerAcceleration = 15f; // Speed

	public boolean isPlayer1 = true;
	protected boolean playerControlsEnabled = true;

	public Vector3 currentScale = getScale();
	
	public static PongPlayer player1;
	public static PongPlayer player2;
	
	public PongPlayer(int posX, int posY) {
		super(posX, posY, 15, 135);
	}

	@Override
	public void update() {
		if (GameLogic.IsGamePaused())
			return;
		position.y = GameMath.clamp(position.y, (-Gui.GetScreenDimensions().y / 2) + size.y / 2,
				(Gui.GetScreenDimensions().y / 2) - size.y / 2);

		size = Vector3.lerp(size, currentScale, 0.25f);
		
		if(isPlayer1)
			player1 = this;
		else
			player2 = this;
		
		playerAcceleration = Settings.isSlowPedals() ? 5.5f : 15f;

		if (playerControlsEnabled) {
			Vector3 geschwindigkeit = Vector3.zero();
			if (isPlayer1) {
				geschwindigkeit = new Vector3(0, KeyHandler.playerInput1.y * playerAcceleration, 0);
			} else {
				geschwindigkeit = new Vector3(0, KeyHandler.playerInput2.y * playerAcceleration, 0);
			}
			addForce(geschwindigkeit);
		}

		super.update();

		position.y = GameMath.clamp(position.y, (-Gui.GetScreenDimensions().y / 2) + size.y / 2,
				(Gui.GetScreenDimensions().y / 2) - size.y / 2);
	}

	@Override
	public void draw(Graphics2D g2d, int screenWidth, int screenHeight) {
		super.draw(g2d, screenWidth, screenHeight);

		if (ShopValues.shopData.activePedalSkin == PedalSkins.Waves) {
			g2d.drawImage(Resources.shop_wavespedalicon.getTexture(), (int) position.x - (int) getScale().y / 2,
					(int) getPosition().y, (int) getScale().y, (int) getScale().y, null);
		} else if (ShopValues.shopData.activePedalSkin == PedalSkins.Lines) {
			g2d.drawImage(Resources.shop_linespedalicon.getTexture(), (int) position.x - (int) getScale().y / 2,
					(int) getPosition().y, (int) getScale().y, (int) getScale().y, null);
		} else if (ShopValues.shopData.activePedalSkin == PedalSkins.Dots) {
			g2d.drawImage(Resources.shop_dotspedalicon.getTexture(), (int) position.x - (int) getScale().y / 2,
					(int) getPosition().y, (int) getScale().y, (int) getScale().y, null);
		}
	}

}
