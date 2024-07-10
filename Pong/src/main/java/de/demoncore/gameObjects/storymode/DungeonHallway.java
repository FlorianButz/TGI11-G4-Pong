package de.demoncore.gameObjects.storymode;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import de.demoncore.game.GameObject;
import de.demoncore.game.SceneManager;

public class DungeonHallway extends GameObject {

	public int fromRPX, fromRPY;
	public int toRPX, toRPY;

	public boolean horizontalOrientation = false;

	public GameObject wall1;
	public GameObject wall2;

	public void createWalls() {

		if(horizontalOrientation) {

			wall1 = new GameObject(
					(int)getPosition().x + (int)getScale().x / 2,
					(int)getPosition().y + (int)getScale().y,
					(int)getScale().x - 6,
					15);

			wall2 = new GameObject(
					(int)getPosition().x + (int)getScale().x / 2,
					(int)getPosition().y,
					(int)getScale().x - 6,
					15);

			SceneManager.getActiveScene().addObject(wall1);
			SceneManager.getActiveScene().addObject(wall2);

		}else {

			wall1 = new GameObject(
					(int)getPosition().x + (int)getScale().x,
					(int)getPosition().y + (int)getScale().y / 2,
					15,
					(int)getScale().y - 6);

			wall2 = new GameObject(
					(int)getPosition().x,
					(int)getPosition().y + (int)getScale().y / 2,
					15,
					(int)getScale().y - 6);

			SceneManager.getActiveScene().addObject(wall1);
			SceneManager.getActiveScene().addObject(wall2);
		}

		wall1.enableRendering = false;
		wall2.enableRendering = false;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		SceneManager.getActiveScene().destroyObject(wall1);
		SceneManager.getActiveScene().destroyObject(wall2);
	}

	public DungeonHallway(int posX, int posY, int width, int height, boolean isHorizontal) {
		super(posX, posY, width, height);

		this.horizontalOrientation = isHorizontal;
		color = new Color(26, 26, 26);
	}

	@Override
	public void draw(Graphics2D g2d, int screenWidth, int screenHeight) {
		super.draw(g2d, screenWidth, screenHeight);

		float off = 10f;
		
		if(horizontalOrientation) {
			Rectangle2D w1 = new Rectangle2D.Float(
					getPosition().x + off / 2,
					getPosition().y + getScale().y,
					getScale().x - off,
					15);

			Rectangle2D w2 = new Rectangle2D.Float(
					getPosition().x + off / 2,
					getPosition().y - 15,
					getScale().x - off,
					15);
			g2d.setColor(Color.white);
			g2d.fill(w1);
			g2d.fill(w2);
		}else {
			Rectangle2D w1 = new Rectangle2D.Float(
					getPosition().x + getScale().x,
					getPosition().y + off / 2,
					15,
					getScale().y - off);

			Rectangle2D w2 = new Rectangle2D.Float(
					getPosition().x - 15,
					getPosition().y + off / 2,
					15,
					getScale().y - off);
			g2d.setColor(Color.white);
			g2d.fill(w1);
			g2d.fill(w2);
		}
	}
}
