package de.demoncore.game;

import java.util.Timer;
import java.util.TimerTask;

import de.demoncore.audio.AudioMaster;

public class GameLogic {
	
	private static GameLogic _instance;
	
	public static boolean isGamePaused = false;
	
	public static GameLogic GetInstance() {
		return _instance;
	}
	
	private Timer gameTimer;
	public float gameTime = 0;

	public double countedTps;
	public double tps;
	public double lastTime;
	
	public GameLogic() {
		gameTimer = new Timer();
		_instance = this;
		
		lastTime = System.currentTimeMillis();
		
		TimerTask gameLogicTask = new TimerTask(){
			@Override
			public void run() {
				Thread.currentThread().setName("gamelogic");
				
				if(SceneManager.GetActiveScene() != null) 
					AudioMaster.SetListener(SceneManager.GetActiveScene().cameraPosition);
				
				if((System.currentTimeMillis() - lastTime) > 1000) {
					tps = countedTps;
					countedTps = 0;
					lastTime = System.currentTimeMillis();
				}
				countedTps++;
				
				gameTime += 0.1f;
				
				SceneManager.UpdateScenes();
			}
		};
		
		gameTimer.scheduleAtFixedRate(gameLogicTask, 0, 16);
	}
	
	public float GetGameTime() {
		return gameTime;
	}
	
	public double GetTps() {
		return tps;
	}
}
