package de.demoncore.game;

import java.util.Timer;
import java.util.TimerTask;

import de.demoncore.audio.AudioMaster;
import de.demoncore.gameObjects.PauseMenu;
import de.demoncore.gui.dialog.Dialog;

public class GameLogic {
	
	private static GameLogic _instance;
	
	private static boolean isGamePaused = false;
	
	public static GameLogic getInstance() {
		return _instance;
	}
	
	private Timer gameTimer;
	public float gameTime = 0;

	public double countedTps;
	public double tps;
	public double lastTime;
	public double accurateTps;
	public double accurateLastTime;
	
	public GameLogic() {
		_instance = this;
	}
	
	public void Start() {
		gameTimer = new Timer();
		
		lastTime = System.currentTimeMillis();
		
		TimerTask gameLogicTask = new TimerTask(){
			@Override
			public void run() {
				Thread.currentThread().setName("gamelogic");
				
				if(Dialog.isActiveDialog || PauseMenu.isPauseMenuActive)
					GameLogic.SetGamePaused(true);
				else
					GameLogic.SetGamePaused(false);
				
				if(SceneManager.getActiveScene() != null && AudioMaster.isInitialized())
					AudioMaster.SetListener(SceneManager.getActiveScene().cameraPosition);
				
				accurateTps = 1000000000.0 / (System.nanoTime() - accurateLastTime);
				accurateLastTime = System.nanoTime();
				
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
	
	public static void SetGamePaused(boolean isPaused) {
		isGamePaused = isPaused;
		AudioMaster.SetAllPaused(isPaused);
	}
	
	public static boolean IsGamePaused() {
		return isGamePaused;
	}
	
	public float getGameTime() {
		return gameTime;
	}
	
	public double GetTps() {
		return accurateTps;
	}
	
	public double GetAverageTps() {
		return tps;
	}
}
