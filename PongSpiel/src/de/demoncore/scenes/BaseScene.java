package de.demoncore.scenes;

import java.util.ArrayList;

import de.demoncore.game.GameObject;
import de.demoncore.utils.Vector3;

public class BaseScene {

	public Vector3 cameraPosition = Vector3.zero; // Kameraposition
	public float cameraZRotation = 0;
	
	protected ArrayList<GameObject> sceneObjects; // Alle objekte in dem Level
	
	public BaseScene() {
		sceneObjects = new ArrayList<GameObject>();
	}
	
	public void UpdateScene() {
		for(GameObject gameObject : sceneObjects){
			gameObject.Update();
		}
	}
	
	public void AddObject(GameObject g) {
		sceneObjects.add(g);
	}
	
	public ArrayList<GameObject> GetSceneObjects(){
		return sceneObjects;
	}
}
