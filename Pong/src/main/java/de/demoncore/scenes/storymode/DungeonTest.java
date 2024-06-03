package de.demoncore.scenes.storymode;

import java.util.Random;

import de.demoncore.game.GameObject;
import de.demoncore.gameObjects.PauseMenu;
import de.demoncore.scenes.BaseScene;
import de.demoncore.utils.Vector3;

public class DungeonTest extends BaseScene {

	@Override
	public void InitializeScene() {
		super.InitializeScene();
		
		AddObject(new PauseMenu());
		
		seed = new Random().nextLong();
		
		GenerateDungeon();
	}
	
	public long seed;
	public Random rng;

	public int dungeonSizeXY = 10;
	public int dungeonSpacing = 25;
	public int dungeonSize = 50;
	
	public GameObject[][] dungeonRoom;
	
	void GenerateDungeon() {
		rng = new Random(seed);
		
		dungeonRoom = new GameObject[dungeonSizeXY][dungeonSizeXY];

		dungeonRoom[(int)(dungeonSizeXY / 2)][(int)(dungeonSizeXY / 2)] = new GameObject((int)GetRoomPosition(dungeonSizeXY/2, dungeonSizeXY/2).x, (int)GetRoomPosition(dungeonSizeXY/2, dungeonSizeXY/2).y, dungeonSize, dungeonSize);
		
		for(int x = 0; x < dungeonSizeXY; x++) {
			for(int y = 0; y < dungeonSizeXY; y++)
			{
				if(dungeonRoom[(int)(x)][(int)(y)] != null) {
			
					if(rng.nextDouble() > 0.75) {
						int xN = x + 1;
						int yN = x + 1;
						
						dungeonRoom[(int)(xN)][(int)(yN)] = new GameObject((int)GetRoomPosition(xN, yN).x, (int)GetRoomPosition(xN, yN).y, dungeonSize, dungeonSize);
					}else if(rng.nextDouble() > 0.75) {
						int xN = x + 1;
						int yN = x - 1;
						
						dungeonRoom[(int)(xN)][(int)(yN)] = new GameObject((int)GetRoomPosition(xN, yN).x, (int)GetRoomPosition(xN, yN).y, dungeonSize, dungeonSize);
					}else if(rng.nextDouble() > 0.75) {
						int xN = x - 1;
						int yN = x + 1;
						
						dungeonRoom[(int)(xN)][(int)(yN)] = new GameObject((int)GetRoomPosition(xN, yN).x, (int)GetRoomPosition(xN, yN).y, dungeonSize, dungeonSize);
					}else if(rng.nextDouble() > 0.75) {
						int xN = x - 1;
						int yN = x - 1;
						
						dungeonRoom[(int)(xN)][(int)(yN)] = new GameObject((int)GetRoomPosition(xN, yN).x, (int)GetRoomPosition(xN, yN).y, dungeonSize, dungeonSize);
					}
					
				}
			}
		}
		
		for(int x = 0; x < dungeonSizeXY; x++) {
			for(int y = 0; y < dungeonSizeXY; y++)
			{
				AddObject(dungeonRoom[x][(int)(y)]);
			}
		}
	}
	
	Vector3 GetRoomPosition(int x, int y) {
		float xP = (dungeonSpacing * x + dungeonSize * x) - (dungeonSpacing * dungeonSizeXY + dungeonSize * dungeonSizeXY) / 2;
		float yP = (dungeonSpacing * y + dungeonSize * y) - (dungeonSpacing * dungeonSizeXY + dungeonSize * dungeonSizeXY) / 2;
		
		Vector3 pos = new Vector3(xP, yP);
		return pos;
	}
}