package de.demoncore.scenes.storymode;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.demoncore.game.GameObject;
import de.demoncore.gameObjects.DungeonMinimap;
import de.demoncore.gameObjects.PauseMenu;
import de.demoncore.gameObjects.PongPlayer;
import de.demoncore.gui.GUIAlignment;
import de.demoncore.scenes.BaseScene;
import de.demoncore.utils.Vector3;

public class DungeonTest extends BaseScene {

	PongPlayer player;
	
	DungeonMinimap minimap;
	
	@Override
	public void InitializeScene() {
		super.InitializeScene();

		AddObject(new PauseMenu());
		
		player = new PongPlayer(0, 0);
		AddObject(player);
		
		minimap = new DungeonMinimap(-150, 150, dungeonRoom, player, this);
		minimap.alignment = GUIAlignment.TopRight;
		AddObject(minimap);
		
		GenerateDungeon();
	}

	@Override
	public void UpdateScene() {
		super.UpdateScene();
		
		cameraPosition = Vector3.Lerp(cameraPosition, player.GetPosition(), 0.065f);
	}
	
	public long seed;
	public Random rng;

	public int dungeonSizeXY = 25;
	public int dungeonSpacing = 225;
	public int dungeonSize = 1250;

	public int maxRooms = 25;
	private int roomsCount = 0;
	
	public GameObject[][] dungeonRoom;
	public List<GameObject> hallways = new ArrayList<GameObject>();
	
	Thread gen;
	
	void GenerateDungeon() {
		
		if(dungeonRoom == null)
			dungeonRoom = new GameObject[dungeonSizeXY][dungeonSizeXY];
		
		for(GameObject[] g : dungeonRoom) {
			for(GameObject go : g) {
				if(go != null) DestroyObject(go);
			}
		}
		
		for(GameObject go : hallways) {
			DestroyObject(go);
			hallways.remove(go);
		}
		
		dungeonRoom = new GameObject[dungeonSizeXY][dungeonSizeXY];
		
		seed = new Random().nextLong();
		rng = new Random(seed);
		
		roomsCount = 0;
		
		if(gen != null) gen.interrupt();
		gen = new Thread("DungeonGen") {
			@Override
			public void run() {
				super.run();
				
				AddRoom((int)(dungeonSizeXY / 2), (int)(dungeonSizeXY / 2));
			
				minimap.dungeon = dungeonRoom;
				
				GameObject randomRoom;
				
				do {
					randomRoom = dungeonRoom[rng.nextInt(0, dungeonSizeXY)][rng.nextInt(0, dungeonSizeXY)];
				}while(randomRoom == null);
				player.SetPosition(randomRoom.GetPosition());
			}
		};
		gen.start();
	}

	void AddRoom(int x, int y) {
		if(roomsCount >= maxRooms) return;
		if(x < 0 || x >= dungeonRoom.length) return;
		if(y < 0 || y >= dungeonRoom.length) return;
		if(dungeonRoom[x][y] != null) return;
		
		GameObject go = new GameObject((int)GetRoomPosition(x, y).x, (int)GetRoomPosition(x, y).y, dungeonSize, dungeonSize);
		go.color = Color.gray;
		go.collisionEnabled = false;
		dungeonRoom[x][y] = go;
		AddObject(dungeonRoom[x][y]);
		
		OnBottom(go);
		
		roomsCount++;
		
		if(rng.nextDouble() > 0.4 * ((float)roomsCount / (float)maxRooms) * 5) {
			AddRoom(x + 1, y);
			
			GameObject hallway = new GameObject(
					(int)((GetRoomPosition(x, y).x + GetRoomPosition(x + 1, y).x) / 2),
					(int)GetRoomPosition(x, y).y,
					dungeonSpacing,
					dungeonSize / 3);
			hallway.color = Color.darkGray;
			hallway.collisionEnabled = false;
			AddObject(hallway);
			hallways.add(hallway);
			
			OnBottom(hallway);
		}
		if(rng.nextDouble() > 0.2 * ((float)roomsCount / (float)maxRooms) * 5) {
			AddRoom(x - 1, y);
			
			GameObject hallway = new GameObject(
					(int)((GetRoomPosition(x, y).x + GetRoomPosition(x - 1, y).x) / 2),
					(int)GetRoomPosition(x, y).y,
					dungeonSpacing,
					dungeonSize / 3);
			hallway.color = Color.darkGray;
			hallway.collisionEnabled = false;
			AddObject(hallway);

			OnBottom(hallway);
		}
		if(rng.nextDouble() > 0.4 * ((float)roomsCount / (float)maxRooms) * 5) {
			AddRoom(x, y + 1);
			
			GameObject hallway = new GameObject(
					(int)GetRoomPosition(x, y).x,
					(int)((GetRoomPosition(x, y).y + GetRoomPosition(x, y + 1).y) / 2),
					dungeonSize / 3,
					dungeonSpacing);
			hallway.color = Color.darkGray;
			hallway.collisionEnabled = false;
			AddObject(hallway);

			OnBottom(hallway);
		}
		if(rng.nextDouble() > 0.3 * ((float)roomsCount / (float)maxRooms) * 5) {
			AddRoom(x, y - 1);
			
			GameObject hallway = new GameObject(
					(int)GetRoomPosition(x, y).x,
					(int)((GetRoomPosition(x, y).y + GetRoomPosition(x, y - 1).y) / 2),
					dungeonSize / 3,
					dungeonSpacing);
			hallway.color = Color.darkGray;
			hallway.collisionEnabled = false;
			AddObject(hallway);

			OnBottom(hallway);
		}
	}

	Vector3 GetRoomPosition(int x, int y) {
		float xP = (dungeonSpacing * x + dungeonSize * x) - (dungeonSpacing * dungeonSizeXY + dungeonSize * dungeonSizeXY) / 2;
		float yP = (dungeonSpacing * y + dungeonSize * y) - (dungeonSpacing * dungeonSizeXY + dungeonSize * dungeonSizeXY) / 2;

		Vector3 pos = new Vector3(xP, yP);
		return pos;
	}
}