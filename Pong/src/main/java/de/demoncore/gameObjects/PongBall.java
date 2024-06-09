package de.demoncore.gameObjects;

import java.awt.Rectangle;

import de.demoncore.game.GameObject;
import de.demoncore.game.SceneManager;
import de.demoncore.gui.Gui;
import de.demoncore.utils.Vector3;

public class PongBall extends GameObject {

	private PongPlayer player1, player2;
	private Vector3 velocity = Vector3.one().multiply(15f);

	public PongBall(int posX, int posY, PongPlayer player1, PongPlayer player2) {
		super(posX, posY, 25, 25);

		this.player1 = player1;
		this.player2 = player2;
		collisionEnabled = false;
	}

	@Override
	public void Update() {
		// TODO Auto-generated method stub
		super.Update();

		position = position.add(velocity);

		if( player1.GetBoundingBox().intersects(GetBoundingBox()) || player2.GetBoundingBox().intersects(GetBoundingBox())) {
			System.out.println("Inetrsektion erkannt"); 
			velocity = velocity.reflect(getPlayerNormal());		
		}

	}

	private Vector3 getPlayerNormal() {
		Vector3 normal = new Vector3(-1, 0);

		if (GetPosition().x	<= 0) normal = normal.multiply(-1f);
		return normal;

	}

	private boolean isNotFullyIntersecting(Rectangle r1, Rectangle r2) {
		// Check if rectangles intersect
		if (!r1.intersects(r2)) {
			return true;
		}

		// Check if one rectangle is fully contained within the other
		Rectangle intersection = r1.intersection(r2);
		if (intersection.equals(r1) || intersection.equals(r2)) {
			return false;  // Fully intersecting
		}

		return true;  // Not fully intersecting
	}

	private Vector3 getWallNormal() {
		Vector3 normal = new Vector3(0, 0);

		// Check collision with the left wall
		if (GetPosition().x - GetScale().x < -SceneManager.GetActiveScene().GetCameraViewport().width / 2) {
			normal.x = -1;
		}

		// Check collision with the right wall
		if (GetPosition().x + GetScale().x > SceneManager.GetActiveScene().GetCameraViewport().width / 2) {
			normal.x = 1;
			System.out.println("ojijiojio");
		}

		// Check collision with the top wall
		if (GetPosition().y - GetScale().y < -SceneManager.GetActiveScene().GetCameraViewport().height / 2) {
			normal.y = 1;
		}

		// Check collision with the bottom wall
		if (GetPosition().y + GetScale().y > SceneManager.GetActiveScene().GetCameraViewport().height / 2) {
			normal.y = -1;
		}

		return normal;
	}

}
