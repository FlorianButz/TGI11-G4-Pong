package de.demoncore.gameObjects;

import java.util.Timer;
import java.util.TimerTask;

import de.demoncore.sprites.SpriteObject;
import de.demoncore.utils.Logger;
import de.demoncore.utils.Resources;

public class PowerupBase extends SpriteObject {
	
	public long powerupDuration = 5000l;
	
	public PowerupBase(int posX, int posY) {
		super(posX, posY, 100, 100, Resources.info_icon);
		
		collisionEnabled = false;
	}
	
	boolean isUsed = false;
	private TimerTask powerupTask;
	
	@Override
	public void update() {
		super.update();

		if(PongBall.getInstance().getBoundingBox().intersects(getBoundingBox()) && !isUsed) {
			isUsed = true;
			enableRendering = false;
			trigPowerup();
		}
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		powerupTask.cancel();
	}
	
	private void trigPowerup() {
		Timer t = new Timer();
		
		startPowerup();
		powerupTask = new TimerTask() {
			public void run() {
				stopPowerup();
			};
		};
		t.schedule(powerupTask, powerupDuration);
	}
	
	protected void startPowerup() {
		Logger.logInfo("Start powerup: " + getClass().getName());
	}
	
	protected void stopPowerup() {		
		Logger.logInfo("Stop powerup: " + getClass().getName());
	}
}