package de.demoncore.gameObjects;

import de.demoncore.game.GameObject;
import de.demoncore.utils.Vector3;

public class PongBall extends GameObject {
	
	private PongPlayer player1, player2;
	private Vector3 velocity = Vector3.one();

	public PongBall(int posX, int posY, PongPlayer player1, PongPlayer player2) {
		super(posX, posY, 25, 25);
		
		this.player1 = player1;
		this.player2 = player2;
		collisionEnabled = false;
	}
	
	@Override
	public void Update() {
		// TODO Auto-generated method stub
		super.Update();
		
		position = position.add(velocity);
		
		if( player1.GetBoundingBox().intersects(GetBoundingBox()) || player2.GetBoundingBox().intersects(GetBoundingBox())) {
		System.out.println("Inetrsektion erkannt"); 
		velocity = velocity.reflect(getPlayerNormal());		
		}
		
			
		
	}
	
	private Vector3 getPlayerNormal() {
		Vector3 normal = new Vector3(-1, 0);
	
		if (GetPosition().x	<= 0) normal = normal.multiply(-1f);
		return normal;
			
		}
	

}
