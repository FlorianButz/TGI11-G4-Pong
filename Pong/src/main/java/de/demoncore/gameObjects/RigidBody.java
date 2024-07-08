package de.demoncore.gameObjects;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import de.demoncore.game.GameLogic;
import de.demoncore.game.GameObject;
import de.demoncore.game.SceneManager;
import de.demoncore.utils.Vector3;

public class RigidBody extends GameObject {

	public Vector3 velocity = Vector3.zero();
	Vector3 lastPosition;
	
	public float friction = 0.875f; // Wie viel geschwindigkeit jede iteration entnommen wird
	
	public RigidBody(int posX, int posY, int width, int height) {
		super(posX, posY, width, height);
		
		lastPosition = new Vector3(posX, posY);
	}

	void checkCollision() {
		List<GameObject> objs = new ArrayList<GameObject>(SceneManager.getActiveScene().getSceneObjects());
		
		for(GameObject g : objs) {
			if(g.collisionEnabled && g != this) {

				Rectangle thisObj = getBoundingBox();
				Rectangle otherObj = g.getBoundingBox();
				
				if(thisObj.intersects(otherObj)) {
					Rectangle intersection = thisObj.intersection(otherObj);					
					Vector3 intersectionMiddle = new Vector3((float)intersection.getCenterX(), (float)intersection.getCenterY());
					
					Vector3 positionAdd = Vector3.zero();
					positionAdd.x = (this.position.x - intersectionMiddle.x);
					positionAdd.y = (this.position.y - intersectionMiddle.y);
					
					velocity = velocity.multiply(0f);
					
					this.position = this.position.add(positionAdd.multiply(0.5f));
					
					onCollision(thisObj, otherObj);
					onCollision(this, g);
				}
			}
		}
	}
	
	protected void onCollision(Rectangle thisObject, Rectangle otherObject) {
		
	}
	
	protected void onCollision(GameObject thisObject, GameObject otherObject) {
		
	}
	
	boolean checkIntersect() {
		List<GameObject> objs = new ArrayList<GameObject>(SceneManager.getActiveScene().getSceneObjects());
		
		for(GameObject g : objs) {
			if(g.collisionEnabled && g != this) {

				Rectangle thisObj = getBoundingBox();
				Rectangle otherObj = g.getBoundingBox();
				
				if(thisObj.intersects(otherObj)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	void checkValidVelocity() {
		
		{
			Vector3 velocityX = new Vector3(velocity.x, 0, 0);
			
			boolean isIntersecting = false;
			int iteration = 0;

			do {
				iteration++;

				if(iteration >= 4) {
					break;
				}
				position = position.add(velocityX.multiply(1f / (float)iteration));
				isIntersecting = checkIntersect();
				if(!isIntersecting)
					velocity.x = velocityX.multiply(1f / (float)iteration).x;

				position = position.subtract(velocityX.multiply(1f / (float)iteration));
			}while(isIntersecting);
		}
		
		{
			Vector3 velocityY = new Vector3(0, velocity.y, 0);
			
			boolean isIntersecting = false;
			int iteration = 0;

			do {
				
				iteration++;

				if(iteration >= 4) {
					break;
				}

				position = position.add(velocityY.multiply(1f / (float)iteration));
				isIntersecting = checkIntersect();
				if(!isIntersecting) {
					velocity.y = velocityY.multiply(1f / (float)iteration).y;
				}

				position = position.subtract(velocityY.multiply(1f / (float)iteration));
			}while(isIntersecting);
		}
		
	}
	
	
	@Override
	public void update() {
		super.update();

		if(GameLogic.IsGamePaused()) return;
		
		checkCollision();
		//checkValidVelocity();
		
		position = Vector3.Lerp(lastPosition, position.add(velocity), 0.27f);
		velocity = velocity.multiply(friction);

		checkCollision();
		//checkValidVelocity();
		
		lastPosition = position;
	}
	
	public void addForce(Vector3 force) {
//		boolean isIntersecting = false;
//		int iteration = 0;
//
//		do {
//			iteration++;
//
//			if(iteration >= 4) {
				velocity = velocity.add(force.multiply(1f));
//				return;
//			}
//
//			position = position.add(force.multiply(1f / (float)iteration));
//			isIntersecting = CheckIntersect();
//			if(!isIntersecting)
//				velocity = velocity.add(force.multiply(1f / (float)iteration));
//
//			position = position.subtract(force.multiply(1f / (float)iteration));
//		}while(isIntersecting);
	}
}
