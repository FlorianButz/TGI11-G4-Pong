package de.demoncore.gameObjects;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import de.demoncore.game.GameObject;
import de.demoncore.game.SceneManager;
import de.demoncore.utils.Vector3;

public class RigidBody extends GameObject {

	public Vector3 velocity = Vector3.zero();
	Vector3 lastPosition;
	
	public float friction = 0.92f;
	
	public RigidBody(int posX, int posY, int width, int height) {
		super(posX, posY, width, height);
		
		lastPosition = new Vector3(posX, posY);
	}

	void CheckCollision() {
		List<GameObject> objs = new ArrayList<GameObject>(SceneManager.GetActiveScene().GetSceneObjects());
		
		for(GameObject g : objs) {
			if(g.collisionEnabled && g != this) {

				Rectangle thisObj = GetBoundingBox();
				Rectangle otherObj = g.GetBoundingBox();
				
				if(thisObj.intersects(otherObj)) {
					Rectangle intersection = thisObj.intersection(otherObj);					
					Vector3 intersectionMiddle = new Vector3((float)intersection.getCenterX(), (float)intersection.getCenterY());
					
					Vector3 positionAdd = Vector3.zero();
					positionAdd.x = (this.position.x - intersectionMiddle.x);
					positionAdd.y = (this.position.y - intersectionMiddle.y);
					
					velocity = positionAdd.multiply(0.05f);
					
					this.position = this.position.add(positionAdd.multiply(0.5f));
				}
			}
		}
	}
	
	@Override
	public void Update() {
		super.Update();

		CheckCollision();
		
		position = Vector3.Lerp(lastPosition, position.add(velocity), 0.27f);
		velocity = velocity.multiply(friction);

		lastPosition = position;
	}
	
	public void AddForce(Vector3 force) {
		velocity = velocity.add(force);
	}
}