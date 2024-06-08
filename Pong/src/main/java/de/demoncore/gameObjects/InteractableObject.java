package de.demoncore.gameObjects;

import java.awt.Color;
import java.awt.Graphics2D;

import de.demoncore.actions.GameActionListener;
import de.demoncore.actions.KeyHandler;
import de.demoncore.game.GameObject;
import de.demoncore.game.SceneManager;
import de.demoncore.game.Translation;
import de.demoncore.utils.GameMath;
import de.demoncore.utils.Resources;
import de.demoncore.utils.Vector3;

public class InteractableObject extends GameObject {

	GameObject player;
	InteractEvent event;

	public InteractableObject(int posX, int posY, int width, int height, GameObject player, InteractEvent e) {
		super(posX, posY, width, height);
		this.player = player;

		collisionEnabled = false;

		KeyHandler.listeners.add(new GameActionListener() {
			@Override
			public void OnInteractionKeyPressed() {
				super.OnInteractionKeyPressed();

				if(player.GetBoundingBox().intersects(GetBoundingBox())) {
					textColor = Color.white;
					OnInteract();
				}
			}
		});
		
		event = e;
	}

	void OnInteract() {
		SceneManager.GetActiveScene().ShakeCamera(50, 2, 105);
		if(event != null) event.OnInteract();
	}

	Color defTextCol = new Color(0.5f, 0.5f, 0.5f, 1f);
	Color textColor = new Color(0.35f, 0.35f, 0.35f, 1f);

	@Override
	public void Draw(Graphics2D g2d, int screenWidth, int screenHeight) {
		Vector3 worldPos = GetPosition();
		g2d.setColor(new Color(1, 1, 1, 0.5f));
		g2d.fillRect((int)worldPos.x, (int)worldPos.y, (int)size.x, (int)size.y);

		if(player.GetBoundingBox().intersects(GetBoundingBox())) {
			g2d.setColor(textColor);
			g2d.setFont(Resources.uiFont.deriveFont(35F));
			g2d.drawString(Translation.Get("interactable.interact").Get(), GetPosition().x + 15, GetPosition().y - 15);
		}
	}

	@Override
	public void Update() {
		super.Update();

		textColor = GameMath.LerpColor(textColor, defTextCol, 0.1f);
	}
}
