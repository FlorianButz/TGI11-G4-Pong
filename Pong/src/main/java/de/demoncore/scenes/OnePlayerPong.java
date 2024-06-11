package de.demoncore.scenes;

import java.awt.Color;

import de.demoncore.game.PointListener;
import de.demoncore.game.PointSystem;
import de.demoncore.game.SceneManager;
import de.demoncore.game.Translation;
import de.demoncore.gameObjects.PauseMenu;
import de.demoncore.gameObjects.PongBall;
import de.demoncore.gameObjects.PongPlayer;
import de.demoncore.gui.GUIText;
import de.demoncore.gui.Gui;
import de.demoncore.utils.Resources;
import de.demoncore.utils.Vector3;

public class OnePlayerPong extends BaseScene {

	PongBall Ball;

	PongPlayer player1;
	PongPlayer player2;

	GUIText points;
	PointListener pointListener;

	@Override
	public void InitializeScene() {
		super.InitializeScene();

		PointSystem.Initialize();

		AddObject(new PauseMenu());

		player1 = new PongPlayer(0, 0);
		AddObject(player1);

		player2 = new PongPlayer(0, 0);
		AddObject(player2);

		Ball = new PongBall(0, 0, player1, player2);
		AddObject(Ball);

		points = new GUIText(0, 100, Translation.Literal(PointSystem.getPlayer1Points() + "   |   " + PointSystem.getPlayer2Points()), Resources.uiFont.deriveFont(65F), Color.white);
		AddObject(points);

		pointListener = new PointListener() {
			@Override
			public void OnPointAdded() {
				addPlayerPoint();
			}
		};
		PointSystem.listeners.add(pointListener);
	}

	public void addPlayerPoint() {
		points.SetText(Translation.Literal(PointSystem.getPlayer1Points() + "   |   " + PointSystem.getPlayer2Points()));
	}

	@Override
	public void UpdateScene() {
		super.UpdateScene();

		Vector3 pos = player1.GetRawPosition();
		pos.x = -Gui.GetScreenDimensions().x / 2 + (Gui.GetScreenDimensions().x / 15);
		player1.SetPosition(pos);

		Vector3 pos2 = player2.GetRawPosition();
		pos2.x = Gui.GetScreenDimensions().x / 2 - (Gui.GetScreenDimensions().x / 15);
		player2.SetPosition(pos2);
	}
}
