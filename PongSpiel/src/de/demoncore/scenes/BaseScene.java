package de.demoncore.scenes;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import de.demoncore.game.GameObject;
import de.demoncore.gui.GUIObject;
import de.demoncore.utils.GameMath;
import de.demoncore.utils.Vector3;

public class BaseScene {

	public Vector3 cameraPosition = Vector3.zero(); // Kameraposition
	public Vector3 localCameraPosition = Vector3.zero(); // Lokale Kameraposition
	
	public float cameraZRotation = 0;
	
	protected ArrayList<GameObject> sceneObjects; // Alle objekte in dem Level
	
	public BaseScene() {
		sceneObjects = new ArrayList<GameObject>();
	}
	
	public void UpdateScene() {
		try {
			for(GameObject gameObject : sceneObjects){
				gameObject.Update();
			}
		}catch(Exception e) {
		}
	}
	
	public void UpdatePhysics() {
		try {
			for(GameObject base : sceneObjects){
				for(GameObject colTest : sceneObjects){

					if(!base.collisionEnabled || !colTest.collisionEnabled) continue;
					if(base == colTest) continue;

					Rectangle baseBounds = base.GetBoundingBox();
					Rectangle colTestBounds = colTest.GetBoundingBox();
					
					if(baseBounds.intersects(colTestBounds))
						System.out.println("Collision: " + base.getClass() + " " + colTest.getClass());
				}
			}
		}catch(Exception e) {
		}
	}
	
	public void AddObject(GameObject g) {
		sceneObjects.add(g);
	}
	
	public void DestroyObject(GameObject g) {
		g.OnDestroy();
		sceneObjects.remove(g);
	}
	
	public ArrayList<GameObject> GetSceneObjects(){
		ArrayList<GameObject> objectsCopy = new ArrayList<GameObject>(sceneObjects);
		return objectsCopy;
	}
	
	public void ShakeCamera(float magnitude, float roughness, int duration) {
		Thread camShakeThread = new Thread(new Runnable() {
			@Override
			public void run() {
				Random random = new Random();

				float lastCamPosX = 0;
				float lastCamPosY = 0;
				
				for(int i = 1; i < duration; i++) {
					float fadeOut = GameMath.Lerp(1, 0, (float)i / (float)duration);
					
					float randomX = (float) (random.nextFloat() * magnitude);
					float randomY = (float) (random.nextFloat() * magnitude);

					float smoothX = GameMath.Lerp(lastCamPosX, randomX, roughness / 5);
					float smoothY = GameMath.Lerp(lastCamPosY, randomY, roughness / 5);

					localCameraPosition.x = (smoothX - smoothX * 2f) * fadeOut;
					localCameraPosition.y = (smoothY - smoothY * 2f) * fadeOut;

					lastCamPosX = localCameraPosition.x;
					lastCamPosY = localCameraPosition.y;
					
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				localCameraPosition.x = 0;
				localCameraPosition.y = 0;
			}
		});
		
		camShakeThread.start();
	}
}
