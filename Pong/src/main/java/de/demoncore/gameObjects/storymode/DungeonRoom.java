package de.demoncore.gameObjects.storymode;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Iterator;
import java.util.List;

import de.demoncore.game.GameObject;
import de.demoncore.game.SceneManager;
import de.demoncore.utils.Resources;
import de.demoncore.utils.Vector3;

public class DungeonRoom extends GameObject {

	public int rPX, rPY;
	
	private GameObject wallRight;
	private GameObject wallTop;
	private GameObject wallLeft;
	private GameObject wallBottom;

	private GameObject secondWallRight;
	private GameObject secondWallTop;
	private GameObject secondWallLeft;
	private GameObject secondWallBottom;
	
	public DungeonRoom(int posX, int posY, int width, int height) {
		super(posX, posY, width, height);
		
		color = Color.black;
		
		collisionEnabled = false;
	}

	boolean top;
	boolean bot;
	boolean lef;
	boolean rig;
	int count;
	
	public void createWalls(List<DungeonHallway> hallways) {
		
		for (DungeonHallway h : hallways) {
			if(h.fromRPX == rPX && h.fromRPY == rPY) {
				int x = h.toRPX - h.fromRPX;
				int y = h.toRPY - h.fromRPY;
				
			}
		}
		System.err.println("HALLWAYS DETECTION");
		
		int space = 185;
		
		if(top) {
			
			wallTop = new GameObject(
					(int)getPosition().x + (int)getScale().x / 4 - space / 2,
					(int)getPosition().y,
					(int)getScale().x / 2 - space,
					25);
			//wallTop.anchorPoint = new Vector3(0, 0.5f);
			
			secondWallTop= new GameObject(
					(int)getPosition().x + (int)getScale().x - (int)getScale().x / 4 + space / 2,
					(int)getPosition().y ,
					(int)getScale().x / 2 - space,
					25);
			//secondWallTop.anchorPoint = new Vector3(1, 0.5f);

			SceneManager.getActiveScene().addObject(wallTop);
			SceneManager.getActiveScene().addObject(secondWallTop);
			
		}else {
			wallTop = new GameObject(
					(int)getPosition().x + (int)getScale().x / 2,
					(int)getPosition().y,
					(int)getScale().x,
					25);
			SceneManager.getActiveScene().addObject(wallTop);
		}

		
		if(bot) {
			
			wallBottom = new GameObject(
					(int)getPosition().x + (int)getScale().x / 4 - space / 2,
					(int)getPosition().y + (int)getScale().y,
					(int)getScale().x / 2 - space,
					25);
			//wallTop.anchorPoint = new Vector3(0, 0.5f);
			
			secondWallBottom= new GameObject(
					(int)getPosition().x + (int)getScale().x - (int)getScale().x / 4 + space / 2,
					(int)getPosition().y + (int)getScale().y,
					(int)getScale().x / 2 - space,
					25);
			//secondWallTop.anchorPoint = new Vector3(1, 0.5f);

			SceneManager.getActiveScene().addObject(wallBottom);
			SceneManager.getActiveScene().addObject(secondWallBottom);
			
		}else {
			wallBottom = new GameObject(
					(int)getPosition().x + (int)getScale().x / 2,
					(int)getPosition().y + (int)getScale().y,
					(int)getScale().x,
					25);
			SceneManager.getActiveScene().addObject(wallBottom);
		}
		
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();

		SceneManager.getActiveScene().destroyObject(wallRight);
		SceneManager.getActiveScene().destroyObject(wallTop);
		SceneManager.getActiveScene().destroyObject(wallBottom);
		SceneManager.getActiveScene().destroyObject(wallLeft);
		
		if(secondWallBottom != null)
			SceneManager.getActiveScene().destroyObject(secondWallBottom);
		if(secondWallLeft != null)
			SceneManager.getActiveScene().destroyObject(secondWallLeft);
		if(secondWallRight != null)
			SceneManager.getActiveScene().destroyObject(secondWallRight);
		if(secondWallTop != null)
			SceneManager.getActiveScene().destroyObject(secondWallTop);

	}
	
	@Override
	public void draw(Graphics2D g2d, int screenWidth, int screenHeight) {
		super.draw(g2d, screenWidth, screenHeight);
	
		g2d.setStroke(new BasicStroke(5f));
		g2d.setColor(Color.white);
		
		Vector3 worldPos = getPosition();
		g2d.drawRect((int)worldPos.x, (int)worldPos.y, (int)size.x, (int)size.y);
		
		g2d.setFont(Resources.uiFont.deriveFont(45f));
		g2d.drawString("" + top + bot + lef + rig + "  " + count, (int)worldPos.x, (int)worldPos.y + 50);
	}
	
}
