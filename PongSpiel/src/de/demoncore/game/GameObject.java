package de.demoncore.game;

import de.demoncore.utils.Vector3;

public class GameObject {
	
	public Vector3 size;
	public Vector3 position;
	
	public GameObject(int posX, int posY, int width, int height) {
		position = new Vector3(posX, posY);
		size = new Vector3(width, height);
	}
	
	public void Update() {}

}
