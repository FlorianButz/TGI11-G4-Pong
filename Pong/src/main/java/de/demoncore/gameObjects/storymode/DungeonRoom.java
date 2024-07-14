package de.demoncore.gameObjects.storymode;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;

import de.demoncore.game.GameObject;
import de.demoncore.game.SceneManager;
import de.demoncore.game.Settings;
import de.demoncore.gameObjects.ParticleSystem;
import de.demoncore.scenes.storymode.Dungeon;
import de.demoncore.scenes.storymode.StorymodeMain;
import de.demoncore.utils.Logger;
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
	}
	
	public void startRoom() {
		StorymodePlayer.getPlayerInstance().setPosition(getPosition().add(getScale().multiply(0.5f)));
	}
	
	public void spawnEnemies() {
		if(((Dungeon)SceneManager.getActiveScene()).rng.nextFloat() > 0.5f) {
			for(int i = 0; i < ((int)(((Dungeon)SceneManager.getActiveScene()).rng.nextFloat() * (int)(5 * getDifficulty())) + 2); i++) {
				spawnEnemy();
			}
		}
	}
	
	public void spawnEnemy() {
		Vector3 rand = getRandomPositionInRoom();
		
		if(getDifficulty() >= 0.8) {
			
			int rng = (int)(((Dungeon)SceneManager.getActiveScene()).rng.nextFloat() * 10);
			
			switch(rng) {
			case 0:
				SceneManager.getActiveScene().addObject(new ZombieEnemy(rand.getX(), rand.getY()));
				break;
			case 1:
				SceneManager.getActiveScene().addObject(new ZombieEnemy(rand.getX(), rand.getY()));
				break;
			case 2:
				SceneManager.getActiveScene().addObject(new SkeletonEnemy(rand.getX(), rand.getY()));
				break;
			case 3:
				SceneManager.getActiveScene().addObject(new SkeletonEnemy(rand.getX(), rand.getY()));
				break;
			case 4:
				SceneManager.getActiveScene().addObject(new SkeletonEnemy(rand.getX(), rand.getY()));
				break;
			case 5:
				SceneManager.getActiveScene().addObject(new SkeletonEnemy(rand.getX(), rand.getY()));
				break;
			case 6:
				SceneManager.getActiveScene().addObject(new BabyZombieEnemy(rand.getX(), rand.getY()));
				break;
			case 7:
				SceneManager.getActiveScene().addObject(new BabyZombieEnemy(rand.getX(), rand.getY()));
				break;
			case 8:
				SceneManager.getActiveScene().addObject(new BabyZombieEnemy(rand.getX(), rand.getY()));
				break;
			case 9:
				SceneManager.getActiveScene().addObject(new GhostEnemy(rand.getX(), rand.getY()));
				break;
			}
		}else if(getDifficulty() >= 0.6) {
			
			int rng = (int)(((Dungeon)SceneManager.getActiveScene()).rng.nextFloat() * 10);
			
			switch(rng) {
			case 0:
				SceneManager.getActiveScene().addObject(new ZombieEnemy(rand.getX(), rand.getY()));
				break;
			case 1:
				SceneManager.getActiveScene().addObject(new ZombieEnemy(rand.getX(), rand.getY()));
				break;
			case 2:
				SceneManager.getActiveScene().addObject(new ZombieEnemy(rand.getX(), rand.getY()));
				break;
			case 3:
				SceneManager.getActiveScene().addObject(new ZombieEnemy(rand.getX(), rand.getY()));
				break;
			case 4:
				SceneManager.getActiveScene().addObject(new SkeletonEnemy(rand.getX(), rand.getY()));
				break;
			case 5:
				SceneManager.getActiveScene().addObject(new SkeletonEnemy(rand.getX(), rand.getY()));
				break;
			case 6:
				SceneManager.getActiveScene().addObject(new BabyZombieEnemy(rand.getX(), rand.getY()));
				break;
			case 7:
				SceneManager.getActiveScene().addObject(new BabyZombieEnemy(rand.getX(), rand.getY()));
				break;
			case 8:
				SceneManager.getActiveScene().addObject(new GhostEnemy(rand.getX(), rand.getY()));
				break;
			case 9:
				SceneManager.getActiveScene().addObject(new GhostEnemy(rand.getX(), rand.getY()));
				break;
			}
		}else if(getDifficulty() >= 0.4) {
			
			int rng = (int)(((Dungeon)SceneManager.getActiveScene()).rng.nextFloat() * 10);
			
			switch(rng) {
			case 0:
				SceneManager.getActiveScene().addObject(new ZombieEnemy(rand.getX(), rand.getY()));
				break;
			case 1:
				SceneManager.getActiveScene().addObject(new ZombieEnemy(rand.getX(), rand.getY()));
				break;
			case 2:
				SceneManager.getActiveScene().addObject(new ZombieEnemy(rand.getX(), rand.getY()));
				break;
			case 3:
				SceneManager.getActiveScene().addObject(new ZombieEnemy(rand.getX(), rand.getY()));
				break;
			case 4:
				SceneManager.getActiveScene().addObject(new GhostEnemy(rand.getX(), rand.getY()));
				break;
			case 5:
				SceneManager.getActiveScene().addObject(new SkeletonEnemy(rand.getX(), rand.getY()));
				break;
			case 6:
				SceneManager.getActiveScene().addObject(new ZombieEnemy(rand.getX(), rand.getY()));
				break;
			case 7:
				SceneManager.getActiveScene().addObject(new BabyZombieEnemy(rand.getX(), rand.getY()));
				break;
			case 8:
				SceneManager.getActiveScene().addObject(new GhostEnemy(rand.getX(), rand.getY()));
				break;
			case 9:
				SceneManager.getActiveScene().addObject(new GhostEnemy(rand.getX(), rand.getY()));
				break;
			}
		}else if(getDifficulty() >= 0.2) {
			
			int rng = (int)(((Dungeon)SceneManager.getActiveScene()).rng.nextFloat() * 10);
			
			switch(rng) {
			case 0:
				SceneManager.getActiveScene().addObject(new ZombieEnemy(rand.getX(), rand.getY()));
				break;
			case 1:
				SceneManager.getActiveScene().addObject(new GhostEnemy(rand.getX(), rand.getY()));
				break;
			case 2:
				SceneManager.getActiveScene().addObject(new GhostEnemy(rand.getX(), rand.getY()));
				break;
			case 3:
				SceneManager.getActiveScene().addObject(new ZombieEnemy(rand.getX(), rand.getY()));
				break;
			case 4:
				SceneManager.getActiveScene().addObject(new ZombieEnemy(rand.getX(), rand.getY()));
				break;
			case 5:
				SceneManager.getActiveScene().addObject(new GhostEnemy(rand.getX(), rand.getY()));
				break;
			case 6:
				SceneManager.getActiveScene().addObject(new GhostEnemy(rand.getX(), rand.getY()));
				break;
			case 7:
				SceneManager.getActiveScene().addObject(new GhostEnemy(rand.getX(), rand.getY()));
				break;
			case 8:
				SceneManager.getActiveScene().addObject(new GhostEnemy(rand.getX(), rand.getY()));
				break;
			case 9:
				SceneManager.getActiveScene().addObject(new GhostEnemy(rand.getX(), rand.getY()));
				break;
			}
		}else {
			
			int rng = (int)(((Dungeon)SceneManager.getActiveScene()).rng.nextFloat() * 2);
			
			switch(rng) {
			case 0:
				SceneManager.getActiveScene().addObject(new ZombieEnemy(rand.getX(), rand.getY()));
				break;
			case 1:
				SceneManager.getActiveScene().addObject(new GhostEnemy(rand.getX(), rand.getY()));
				break;
			}
		}
	}
	
	float getDifficulty() {
		return (float)StorymodeMain.getCompleteDungeonCount() / (float)StorymodeMain.getDungeonCount();
	}
	
	Vector3 getRandomPositionInRoom() {
		Vector3 startPos = getPosition().add(new Vector3(20, 20));
		Vector3 toPos = getScale().subtract(new Vector3(20, 20));
		
		return startPos.add(new Vector3(toPos.x * ((Dungeon)SceneManager.getActiveScene()).rng.nextFloat(), toPos.y * ((Dungeon)SceneManager.getActiveScene()).rng.nextFloat()));
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

	public DungeonDoor spawnExit() {
		DungeonDoor door = new DungeonDoor(getPosition().getX() + getScale().getX() / 2, getPosition().getY() + getScale().getY() / 2, false);
		SceneManager.getActiveScene().addObject(door);
		
		ParticleSystem s = new ParticleSystem(getPosition().getX() + getScale().getX() / 2, getPosition().getY() + getScale().getY() / 2);
		
		s.emitChunk = 500;
		s.initialParticleSpeedMax = new Vector3(10, 10);
		s.initialParticleSpeedMin = new Vector3(-10, -10);
		s.particleColorEnd = new Color(0, 0, 0, 0);
		s.particleSpeedMultiplier = 0.45f;
		s.particleGravity = 0.025f;
		s.particleLifetime = 750;
		s.particleLifetimeRandom = 150;
		s.initialParticleSize = 35;
		s.initialParticleSizeRandom = 50;
		s.endParticleSize = 0;
		
		SceneManager.getActiveScene().addObject(s);
		s.Init();
		
		return door;
	}
	
}
