package de.demoncore.scenes;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.demoncore.game.GameObject;
import de.demoncore.gui.Gui;
import de.demoncore.utils.GameMath;
import de.demoncore.utils.Vector3;

public class BaseScene {

	public Vector3 cameraPosition = Vector3.zero(); // Kameraposition
	public Vector3 localCameraPosition = Vector3.zero(); // Lokale Kameraposition
	
	public float cameraZRotation = 0;
	
	private boolean isInitialized = false;
	
	protected ArrayList<GameObject> sceneObjects; // Alle objekte in dem Level
	Rectangle cameraViewport;
	
	float cameraViewportShrink = -35f;
	
	public float GetViewportShrink() {
		return cameraViewportShrink;
	}
	
	public BaseScene() {
		sceneObjects = new ArrayList<GameObject>();
	}
	
	public void initializeScene() {
	}
	
	public boolean IsInitialized() {
		return this.isInitialized;
	}
	
	public void OnTop(GameObject obj) {
		sceneObjects.remove(obj);
		sceneObjects.add(obj);
	}
	
	public void OnBottom(GameObject obj) {
		sceneObjects.remove(obj);
		sceneObjects.add(0, obj);
	}
	
	public void SetInitializedComplete() {
		this.isInitialized = true;
	}
	
	public Rectangle GetCameraViewport() {
		if(cameraViewport == null){
			this.cameraViewport = CalcViewport();
		}
			
		return cameraViewport;
	}
	
	public Rectangle GetRawCameraViewport() {
		return new Rectangle(
				(int)((cameraPosition.x -Gui.GetScreenDimensions().x/2)),
				(int)((cameraPosition.y -Gui.GetScreenDimensions().y/2)),
				(int)((Gui.GetScreenDimensions().x)),
				(int)((Gui.GetScreenDimensions().y))
				);
	}
	
	private Rectangle CalcViewport() {
		return cameraViewport = new Rectangle(
				(int)((cameraPosition.x -Gui.GetScreenDimensions().x/2) + cameraViewportShrink),
				(int)((cameraPosition.y -Gui.GetScreenDimensions().y/2) + cameraViewportShrink),
				(int)((Gui.GetScreenDimensions().x) - cameraViewportShrink * 2),
				(int)((Gui.GetScreenDimensions().y) - cameraViewportShrink * 2)
				);
	}
	
	public void updateScene() {
		try {
			cameraViewport = CalcViewport();
			
			for(GameObject gameObject : sceneObjects){
				gameObject.update();
				
				if(gameObject.CheckDistanceCulled(cameraViewport)) {
					gameObject.isDistanceCulled = false;
				}else {
					gameObject.isDistanceCulled = true;
				}

			}
		}catch(Exception e) {
		}
	}
	
	public void AddObject(GameObject g) {
		sceneObjects.add(g);
		g.OnAddToScene();
	}
	
	public void DestroyObject(GameObject g) {
		g.onDestroy();
		sceneObjects.remove(g);
	}
	
	public List<GameObject> GetSceneObjects(){
		List<GameObject> objectsCopy = new ArrayList<GameObject>(sceneObjects);
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
