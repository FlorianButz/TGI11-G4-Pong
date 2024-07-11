package de.demoncore.gameObjects.storymode;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Iterator;
import java.util.List;

import de.demoncore.game.GameObject;
import de.demoncore.game.SceneManager;
import de.demoncore.game.Settings;
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
		
		color = new Color(1, 1, 1, 0.1f);
		collisionEnabled = false;

		if(Math.random() > 0.4f) {
			for(int i = 0; i < ((int)(Math.random() * 5 + 1)); i++) {
				Vector3 rand = getRandomPositionInRoom();
				SceneManager.getActiveScene().addObject(new BaseEnemy((int)rand.x, (int)rand.y));
			}
		}
	}

	Vector3 getRandomPositionInRoom() {
		
		Vector3 startPos = getPosition().add(new Vector3(20, 20));
		Vector3 toPos = getScale().subtract(new Vector3(20, 20));
		
		return startPos.add(new Vector3(toPos.x * (float)Math.random(), toPos.y * (float)Math.random()));
	}
	
	boolean top;
	boolean bot;
	boolean lef;
	boolean rig;
	int count = 0;
	
	public void createWalls(List<DungeonHallway> hallways) {
		
		for (DungeonHallway h : hallways) {
			if(h.fromRPX == rPX && h.fromRPY == rPY) {
				int x = h.toRPX - h.fromRPX;
				int y = h.toRPY - h.fromRPY;
				
				if(x == -1) {
					count++;
					lef = true;
				}else if(x == 1) {
					count++;
					rig = true;
				}
				
				if(y == -1) {
					count++;
					top = true;
				}else if(y == 1) {
					count++;
					bot = true;
				}
			}else if(h.toRPX == rPX && h.toRPY == rPY) {
				int x = h.fromRPX - h.toRPX;
				int y = h.fromRPY - h.toRPY;
				
				if(x == -1) {
					count++;
					lef = true;
				}else if(x == 1) {
					count++;
					rig = true;
				}
				
				if(y == -1) {
					count++;
					top = true;
				}else if(y == 1) {
					count++;
					bot = true;
				}
			}
		}
		
		int space = 210;
		
		if(top) {
			
			wallTop = new GameObject(
					(int)getPosition().x + (int)getScale().x / 4 - space / 2,
					(int)getPosition().y,
					(int)getScale().x / 2 - space,
					25);
			
			secondWallTop= new GameObject(
					(int)getPosition().x + (int)getScale().x - (int)getScale().x / 4 + space / 2,
					(int)getPosition().y ,
					(int)getScale().x / 2 - space,
					25);

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
			
			secondWallBottom= new GameObject(
					(int)getPosition().x + (int)getScale().x - (int)getScale().x / 4 + space / 2,
					(int)getPosition().y + (int)getScale().y,
					(int)getScale().x / 2 - space,
					25);

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
		
		if(rig) {
			
			wallRight = new GameObject(
					(int)getPosition().x + (int)getScale().x,
					(int)getPosition().y + (int)getScale().y / 4 - space / 2,
					25,
					(int)getScale().y / 2 - space);
			
			secondWallRight= new GameObject(
					(int)getPosition().x + (int)getScale().x,
					(int)getPosition().y + (int)getScale().y - (int)getScale().y / 4 + space / 2,
					25,
					(int)getScale().y / 2 - space);

			SceneManager.getActiveScene().addObject(wallRight);
			SceneManager.getActiveScene().addObject(secondWallRight);
			
		}else {
			wallRight = new GameObject(
					(int)getPosition().x + (int)getScale().x,
					(int)getPosition().y + (int)getScale().y / 2,
					25,
					(int)getScale().y);
			SceneManager.getActiveScene().addObject(wallRight);
		}
		
		if(lef) {
			
			wallLeft = new GameObject(
					(int)getPosition().x,
					(int)getPosition().y + (int)getScale().y / 4 - space / 2,
					25,
					(int)getScale().y / 2 - space);
			
			secondWallLeft = new GameObject(
					(int)getPosition().x,
					(int)getPosition().y + (int)getScale().y - (int)getScale().y / 4 + space / 2,
					25,
					(int)getScale().y / 2 - space);

			SceneManager.getActiveScene().addObject(wallLeft);
			SceneManager.getActiveScene().addObject(secondWallLeft);
			
		}else {
			wallLeft = new GameObject(
					(int)getPosition().x,
					(int)getPosition().y + (int)getScale().y / 2,
					25,
					(int)getScale().y);
			SceneManager.getActiveScene().addObject(wallLeft);
		}

		wallTop.enableRendering = false;
		wallBottom.enableRendering = false;
		wallLeft.enableRendering = false;
		wallRight.enableRendering = false;

		if(secondWallBottom != null)
			secondWallBottom.enableRendering = false;
		if(secondWallLeft != null)
			secondWallLeft.enableRendering = false;
		if(secondWallRight != null)
			secondWallRight.enableRendering = false;
		if(secondWallTop != null)
			secondWallTop.enableRendering = false;
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
	
		g2d.setStroke(new BasicStroke(15f));
		g2d.setColor(Color.white);
		
		Vector3 worldPos = getPosition();
		g2d.drawRect((int)worldPos.x, (int)worldPos.y, (int)size.x, (int)size.y);
		
		if(Settings.getDebugMode()) {
			g2d.setFont(Resources.uiFont.deriveFont(45f));
			g2d.drawString("" + top + bot + lef + rig + " HC: " + count, (int)worldPos.x + 45, (int)worldPos.y + 100);
		}
	}
	
}
