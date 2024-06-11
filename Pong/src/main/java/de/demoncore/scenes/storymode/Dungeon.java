package de.demoncore.scenes.storymode;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.demoncore.game.GameObject;
import de.demoncore.gameObjects.Cake;
import de.demoncore.gameObjects.DungeonMinimap;
import de.demoncore.gameObjects.InteractEvent;
import de.demoncore.gameObjects.InteractableObject;
import de.demoncore.gameObjects.PauseMenu;
import de.demoncore.gameObjects.StorymodePlayer;
import de.demoncore.gui.GUIAlignment;
import de.demoncore.scenes.BaseScene;
import de.demoncore.utils.Vector3;

public class Dungeon extends BaseScene {

	StorymodePlayer player;
	DungeonMinimap minimap;
	
	@Override
	public void initializeScene() {
		super.initializeScene();

		AddObject(new PauseMenu());
		
		player = new StorymodePlayer(0, 0);
		AddObject(player);
		
		minimap = new DungeonMinimap(-150, 150, dungeonRoom, player, this);
		minimap.alignment = GUIAlignment.TopRight;
		AddObject(minimap);
		
		InteractableObject interact = new InteractableObject(25, 25, 75, 75, player, new InteractEvent() {
		@Override
		public void OnInteract() {
			player.health.Damage(1);
		}
		});

		AddObject(new Cake(250, 100));
		AddObject(new Cake(100, 250));
		AddObject(new Cake(250, 250));
		
		AddObject(interact);
		
		generateDungeon();
	}

	@Override
	public void updateScene() {
		super.updateScene();
		
		cameraPosition = Vector3.Lerp(cameraPosition, player.GetPosition(), 0.065f);
	}
	
	public long seed;
	public Random rng;

	public int dungeonSizeArray = 25;
	public int dungeonSpacing = 225;
	public int dungeonSize = 1250;

	public int maxRooms = 25;
	private int roomsCount = 0;
	
	public GameObject[][] dungeonRoom;
	public List<GameObject> hallways = new ArrayList<GameObject>();
	
	Thread gen;
	
	void generateDungeon() {
		
		if(dungeonRoom == null)
			dungeonRoom = new GameObject[dungeonSizeArray][dungeonSizeArray];
		
		for(GameObject[] g : dungeonRoom) {
			for(GameObject go : g) {
				if(go != null) DestroyObject(go);
			}
		}
		
		for(GameObject go : hallways) {
			DestroyObject(go);
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
			
				minimap.dungeon = dungeonRoom;
				GameObject randomRoom;
				
				do {
					randomRoom = dungeonRoom[rng.nextInt(0, dungeonSizeArray)][rng.nextInt(0, dungeonSizeArray)];
				}while(randomRoom == null);
				player.SetPosition(Vector3.zero().subtract(Vector3.one().multiply(dungeonSize * 2).subtract(Vector3.one().multiply(15))));
			}
		};
		gen.start();
	}

	void addRoom(int x, int y) {
		if(roomsCount >= maxRooms) return;
		if(x < 0 || x >= dungeonRoom.length) return;
		if(y < 0 || y >= dungeonRoom.length) return;
		if(dungeonRoom[x][y] != null) return;
		
		GameObject go = new GameObject((int)getRoomPosition(x, y).x, (int)getRoomPosition(x, y).y, dungeonSize, dungeonSize);
		go.color = Color.gray;
		go.collisionEnabled = false;
		dungeonRoom[x][y] = go;
		AddObject(dungeonRoom[x][y]);
		
		OnBottom(go);
		
		roomsCount++;
		
		if(rng.nextDouble() > 0.4 * ((float)roomsCount / (float)maxRooms) * 5) {
			addRoom(x + 1, y);
			
			GameObject hallway = new GameObject(
					(int)((getRoomPosition(x, y).x + getRoomPosition(x + 1, y).x) / 2),
					(int)getRoomPosition(x, y).y,
					dungeonSpacing,
					dungeonSize / 3);
			hallway.color = Color.darkGray;
			hallway.collisionEnabled = false;
			AddObject(hallway);
			hallways.add(hallway);
			
			OnBottom(hallway);
		}
		if(rng.nextDouble() > 0.2 * ((float)roomsCount / (float)maxRooms) * 5) {
			addRoom(x - 1, y);
			
			GameObject hallway = new GameObject(
					(int)((getRoomPosition(x, y).x + getRoomPosition(x - 1, y).x) / 2),
					(int)getRoomPosition(x, y).y,
					dungeonSpacing,
					dungeonSize / 3);
			hallway.color = Color.darkGray;
			hallway.collisionEnabled = false;
			AddObject(hallway);

			OnBottom(hallway);
		}
		if(rng.nextDouble() > 0.4 * ((float)roomsCount / (float)maxRooms) * 5) {
			addRoom(x, y + 1);
			
			GameObject hallway = new GameObject(
					(int)getRoomPosition(x, y).x,
					(int)((getRoomPosition(x, y).y + getRoomPosition(x, y + 1).y) / 2),
					dungeonSize / 3,
					dungeonSpacing);
			hallway.color = Color.darkGray;
			hallway.collisionEnabled = false;
			AddObject(hallway);

			OnBottom(hallway);
		}
		if(rng.nextDouble() > 0.3 * ((float)roomsCount / (float)maxRooms) * 5) {
			addRoom(x, y - 1);
			
			GameObject hallway = new GameObject(
					(int)getRoomPosition(x, y).x,
					(int)((getRoomPosition(x, y).y + getRoomPosition(x, y - 1).y) / 2),
					dungeonSize / 3,
					dungeonSpacing);
			hallway.color = Color.darkGray;
			hallway.collisionEnabled = false;
			AddObject(hallway);

			OnBottom(hallway);
		}
	}

	Vector3 getRoomPosition(int x, int y) {
		float xP = (dungeonSpacing * x + dungeonSize * x) - (dungeonSpacing * dungeonSizeArray + dungeonSize * dungeonSizeArray) / 2;
		float yP = (dungeonSpacing * y + dungeonSize * y) - (dungeonSpacing * dungeonSizeArray + dungeonSize * dungeonSizeArray) / 2;

		Vector3 pos = new Vector3(xP, yP);
		return pos;
	}
}