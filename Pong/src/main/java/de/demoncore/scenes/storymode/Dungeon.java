package de.demoncore.scenes.storymode;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.demoncore.game.GameObject;
import de.demoncore.gameObjects.InteractEvent;
import de.demoncore.gameObjects.InteractableObject;
import de.demoncore.gameObjects.PauseMenu;
import de.demoncore.gameObjects.storymode.Cake;
import de.demoncore.gameObjects.storymode.DungeonDoor;
import de.demoncore.gameObjects.storymode.DungeonHallway;
import de.demoncore.gameObjects.storymode.DungeonMinimap;
import de.demoncore.gameObjects.storymode.DungeonRoom;
import de.demoncore.gameObjects.storymode.StorymodePlayer;
import de.demoncore.gui.GUIAlignment;
import de.demoncore.scenes.BaseScene;
import de.demoncore.utils.Vector3;

public class Dungeon extends BaseScene {

	StorymodePlayer player;
	DungeonMinimap minimap;
	
	@Override
	public void initializeScene() {
		super.initializeScene();

		addObject(new PauseMenu());
		
		player = new StorymodePlayer(0, 0);
		addObject(player);
		
		minimap = new DungeonMinimap(-150, 150, dungeonRoom, player, this);
		minimap.alignment = GUIAlignment.TopRight;
		addObject(minimap);
		
		InteractableObject interact = new InteractableObject(25, 25, 75, 75, player, new InteractEvent() {
		@Override
		public void OnInteract() {
			player.health.damage(1);
		}
		});

		addObject(new Cake(250, 100));
		addObject(new Cake(100, 250));
		addObject(new Cake(250, 250));
		
		addObject(interact);
		
		generateDungeon();
	}

	@Override
	public void uninitializeScene() {
		super.uninitializeScene();

		StorymodeMain.savePlayerStats();
	}
	
	@Override
	public void updateScene() {
		super.updateScene();
		
		cameraPosition = Vector3.Lerp(cameraPosition, player.getPosition(), 0.065f);
	}
	
	public long seed;
	public Random rng;

	public int dungeonSizeArray = 25;
	public int dungeonSpacing = 225;
	public int dungeonSize = 1250;

	public int maxRooms = 25;
	private int roomsCount = 0;
	
	public GameObject[][] dungeonRoom;
	public List<DungeonHallway> hallways = new ArrayList<DungeonHallway>();
	
	Thread gen;
	
	void generateDungeon() {
		
		if(dungeonRoom == null)
			dungeonRoom = new GameObject[dungeonSizeArray][dungeonSizeArray];
		
		for(GameObject[] g : dungeonRoom) {
			for(GameObject go : g) {
				if(go != null) destroyObject(go);
			}
		}
		
		for(GameObject go : hallways) {
			destroyObject(go);
			hallways.remove(go);
		}
		
		dungeonRoom = new GameObject[dungeonSizeArray][dungeonSizeArray];
		
		seed = new Random().nextLong();
		rng = new Random(seed);
		
		roomsCount = 0;
		
		if(gen != null) gen.interrupt();
		gen = new Thread("DungeonGen") {
			@Override
			public void run() {
				super.run();
				
				addRoom((int)(dungeonSizeArray / 2), (int)(dungeonSizeArray / 2));
			
				for(GameObject[] roomArr : dungeonRoom) {
					for(GameObject room : roomArr) {
						if(room == null) continue;
						((DungeonRoom)room).createWalls(hallways);
						onBottom(room);
					}
				}
				
				for(DungeonHallway hallway : hallways) {
					if(hallway == null) continue;
					hallway.createWalls();
					onTop(hallway);
				}
				
				for(GameObject gO : getSceneObjects()) {
					if(gO instanceof DungeonRoom || gO instanceof DungeonHallway) continue;
					onTop(gO);
				}
				
				minimap.dungeon = dungeonRoom;
				GameObject randomRoom;
				
				do {
					randomRoom = dungeonRoom[rng.nextInt(0, dungeonSizeArray)][rng.nextInt(0, dungeonSizeArray)];
				}while(randomRoom == null);
				randomRoom.startRoom();
			}
		};
		gen.start();
	}

	boolean addRoom(int x, int y) {
		if(roomsCount >= maxRooms) return false;
		if(x < 0 || x >= dungeonRoom.length) return false;
		if(y < 0 || y >= dungeonRoom.length) return false;
		if(dungeonRoom[x][y] != null) return false;
		
		int sp = 20;
		
		DungeonRoom go = new DungeonRoom((int)getRoomPosition(x, y).x, (int)getRoomPosition(x, y).y, dungeonSize, dungeonSize);
		dungeonRoom[x][y] = go;
		go.rPX = x;
		go.rPY = y;
		addObject(dungeonRoom[x][y]);
		
		onBottom(go);
		roomsCount++;
		
		if(rng.nextDouble() > 0.4 * ((float)roomsCount / (float)maxRooms) * 5) {
			if(addRoom(x + 1, y)) {			
				DungeonHallway hallway = new DungeonHallway(
						(int)((getRoomPosition(x, y).x + getRoomPosition(x + 1, y).x) / 2),
						(int)getRoomPosition(x, y).y,
						dungeonSpacing + sp,
						dungeonSize / 3,
						true);
				hallway.collisionEnabled = false;
				addObject(hallway);
				hallway.fromRPX = x;
				hallway.fromRPY = y;
				hallway.toRPX = x + 1;
				hallway.toRPY = y;
				hallways.add(hallway);
				
				onBottom(hallway);
			}
		}
		if(rng.nextDouble() > 0.2 * ((float)roomsCount / (float)maxRooms) * 5) {
			if(addRoom(x - 1, y)) {				
				DungeonHallway hallway = new DungeonHallway(
						(int)((getRoomPosition(x, y).x + getRoomPosition(x - 1, y).x) / 2),
						(int)getRoomPosition(x, y).y,
						dungeonSpacing + sp,
						dungeonSize / 3,
						true);
				hallway.collisionEnabled = false;
				addObject(hallway);
				hallway.fromRPX = x;
				hallway.fromRPY = y;
				hallway.toRPX = x - 1;
				hallway.toRPY = y;
				hallways.add(hallway);
				
				onBottom(hallway);
			}
		}
		if(rng.nextDouble() > 0.4 * ((float)roomsCount / (float)maxRooms) * 5) {
			if(addRoom(x, y + 1)) {			
				DungeonHallway hallway = new DungeonHallway(
						(int)getRoomPosition(x, y).x,
						(int)((getRoomPosition(x, y).y + getRoomPosition(x, y + 1).y) / 2),
						dungeonSize / 3,
						dungeonSpacing + sp,
						false);
				hallway.collisionEnabled = false;
				addObject(hallway);
				hallway.fromRPX = x;
				hallway.fromRPY = y;
				hallway.toRPX = x;
				hallway.toRPY = y + 1;
				hallways.add(hallway);
				
				onBottom(hallway);
			}
		}
		if(rng.nextDouble() > 0.3 * ((float)roomsCount / (float)maxRooms) * 5) {
			if(addRoom(x, y - 1)) {				
				DungeonHallway hallway = new DungeonHallway(
						(int)getRoomPosition(x, y).x,
						(int)((getRoomPosition(x, y).y + getRoomPosition(x, y - 1).y) / 2),
						dungeonSize / 3,
						dungeonSpacing + sp,
						false);
				hallway.collisionEnabled = false;
				addObject(hallway);
				hallway.fromRPX = x;
				hallway.fromRPY = y;
				hallway.toRPX = x;
				hallway.toRPY = y - 1;
				hallways.add(hallway);
				
				onBottom(hallway);
			}
		}
		
		return true;
	}

	Vector3 getRoomPosition(int x, int y) {
		float xP = (dungeonSpacing * x + dungeonSize * x) - (dungeonSpacing * dungeonSizeArray + dungeonSize * dungeonSizeArray) / 2;
		float yP = (dungeonSpacing * y + dungeonSize * y) - (dungeonSpacing * dungeonSizeArray + dungeonSize * dungeonSizeArray) / 2;

		Vector3 pos = new Vector3(xP, yP);
		return pos;
	}
}