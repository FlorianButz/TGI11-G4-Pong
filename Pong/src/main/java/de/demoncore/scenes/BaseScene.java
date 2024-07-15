package de.demoncore.scenes;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.system.NonnullDefault;

import de.demoncore.game.GameObject;
import de.demoncore.game.Settings;
import de.demoncore.gui.Gui;
import de.demoncore.utils.GameMath;
import de.demoncore.utils.Logger;
import de.demoncore.utils.Vector3;

public class BaseScene {

	public Vector3 cameraPosition = Vector3.zero(); // Kameraposition
	public Vector3 localCameraPosition = Vector3.zero(); // Lokale Kameraposition

	public float cameraZRotation = 0;
	public float cameraZoom = 1f;

	private boolean isInitialized = false;

	protected ArrayList<GameObject> sceneObjects; // Alle objekte in dem Level
	Rectangle cameraViewport;

	float cameraViewportShrink = -75f;

	public float GetViewportShrink() {
		return cameraViewportShrink;
	}

	public BaseScene() {
		sceneObjects = new ArrayList<GameObject>();
	}

	public void initializeScene() {
	}

	public void uninitializeScene() {
	}
	
	public boolean isInitialized() {
		return this.isInitialized;
	}

	public void onTop(GameObject obj) {
		sceneObjects.remove(obj);
		sceneObjects.add(obj);
	}

	public void onBottom(GameObject obj) {
		sceneObjects.remove(obj);
		sceneObjects.add(0, obj);
	}

	public void setInitializedComplete() {
		this.isInitialized = true;
	}

	public Rectangle getCameraViewport() {
		if(cameraViewport == null){
			this.cameraViewport = calcViewport();
		}

		return cameraViewport;
	}

	public Rectangle getRawCameraViewport() {
		return new Rectangle(
				(int)((cameraPosition.x -Gui.GetScreenDimensions().x/2)),
				(int)((cameraPosition.y -Gui.GetScreenDimensions().y/2)),
				(int)((Gui.GetScreenDimensions().x)),
				(int)((Gui.GetScreenDimensions().y))
				);
	}

	private Rectangle calcViewport() {
		return cameraViewport = new Rectangle(
				(int)((cameraPosition.x -Gui.GetScreenDimensions().x/2) + (cameraViewportShrink) * (1f / cameraZoom * 6)),
				(int)((cameraPosition.y -Gui.GetScreenDimensions().y/2) + (cameraViewportShrink) * (1f / cameraZoom * 6)),
				(int)((Gui.GetScreenDimensions().x) - (cameraViewportShrink) * (1f / cameraZoom * 6) * 2),
				(int)((Gui.GetScreenDimensions().y) - (cameraViewportShrink) * (1f / cameraZoom * 6) * 2)
				);
	}

	public void updateScene() {
		cameraViewport = calcViewport();

		for(GameObject gameObject : new ArrayList<GameObject>(sceneObjects)){
			if(gameObject != null) {

				gameObject.update();

				if(gameObject.checkDistanceCulled(cameraViewport)) {
					gameObject.isDistanceCulled = false;
				}else {
					gameObject.isDistanceCulled = true;
				}
			}

		}

	}

	public void addObject(GameObject g) {
		sceneObjects.add(g);
		g.onAddToScene();
	}

	public void destroyObject(GameObject g) {
		g.onDestroy();
		sceneObjects.remove(g);
	}

	public List<GameObject> getSceneObjects(){
		List<GameObject> objectsCopy = new ArrayList<GameObject>(sceneObjects);
		return objectsCopy;
	}

	public void ShakeCamera(float magnitude, float roughness, int duration) {
		if(!Settings.isCameraShake()) return;

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
