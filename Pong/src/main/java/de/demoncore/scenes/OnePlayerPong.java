package de.demoncore.scenes;

import java.awt.Color;

import de.demoncore.game.PointListener;
import de.demoncore.game.PointSystem;
import de.demoncore.game.SceneManager;
import de.demoncore.game.Translation;
import de.demoncore.gameObjects.PauseMenu;
import de.demoncore.gameObjects.PongBall;
import de.demoncore.gameObjects.PongEndScreen;
import de.demoncore.gameObjects.PongLatiku;
import de.demoncore.gameObjects.PongPlayer;
import de.demoncore.gameObjects.PongPlayerAI;
import de.demoncore.gameObjects.PowerupBase;
import de.demoncore.gui.GUIText;
import de.demoncore.gui.Gui;
import de.demoncore.scenes.shopnew.ShopValues;
import de.demoncore.scenes.shopnew.ShopValuesSave;
import de.demoncore.utils.Logger;
import de.demoncore.utils.Resources;
import de.demoncore.utils.Vector3;

public class OnePlayerPong extends BaseScene {

	PongBall ball;

	PongPlayer player1;
	PongPlayer player2;

	GUIText points;
	PointListener pointListener;

	@Override
	public void initializeScene() {
		super.initializeScene();

		PointSystem.Initialize();

		addObject(new PauseMenu());
		addObject(new PongLatiku());

		addObject(new PowerupBase(150, 150));
		
		player1 = new PongPlayer(0, 0);
		player1.isPlayer1 = true;
		addObject(player1);

		player2 = new PongPlayerAI(0, 0);
		player2.isPlayer1 = false;
		addObject(player2);

		ball = new PongBall(0, 0, player1, player2);
		addObject(ball);

		onTop(ball);
		
		points = new GUIText(0, 100, Translation.literal(PointSystem.getPlayer1Points() + "   |   " + PointSystem.getPlayer2Points()), Resources.uiFont.deriveFont(65F), Color.white);
		addObject(points);

		pointListener = new PointListener() {
			@Override
			public void onPointAdded() {
				addPlayerPoint();
			}
		};
		PointSystem.listeners.add(pointListener);
		
		ogSpeed = ball.speed;
	}

	public void addPlayerPoint() {
		points.SetText(Translation.literal(PointSystem.getPlayer1Points() + "   |   " + PointSystem.getPlayer2Points()));

		if(PointSystem.getPlayer1Points() >= 10) {
			endGame(true);
			Logger.logWarning("Spieler 1 Gewinnt!");
		}
		else if(PointSystem.getPlayer2Points() >= 10) {
			endGame(false);
			Logger.logWarning("Spieler 2 Gewinnt!");
		}
	}

	void endGame(boolean player1) {
		ShopValues.shopData.addPlayerMoney(PointSystem.getPlayer1Points()-PointSystem.getPlayer2Points());
		new PongEndScreen(player1, PointSystem.getPlayer1Points()-PointSystem.getPlayer2Points()).ShowMenu();
		destroyObject(ball);
	}
		
	float ogSpeed = 0;
		
	@Override
	public void updateScene() {
		super.updateScene();

		//ball.speed = ogSpeed + (PointSystem.getPlayer1Points() + PointSystem.getPlayer2Points() / 2);
		
		Vector3 pos = player1.getRawPosition();
		pos.x = -Gui.GetScreenDimensions().x / 2 + (Gui.GetScreenDimensions().x / 15);
		player1.setPosition(pos);

		Vector3 pos2 = player2.getRawPosition();
		pos2.x = Gui.GetScreenDimensions().x / 2 - (Gui.GetScreenDimensions().x / 15);
		player2.setPosition(pos2);
	}
}
