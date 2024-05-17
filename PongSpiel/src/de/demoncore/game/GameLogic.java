package de.demoncore.game;

import java.util.Timer;
import java.util.TimerTask;

import de.demoncore.gui.Gui;

public class GameLogic {
	
	private static GameLogic _instance;
	
	public static GameLogic GetInstance() {
		return _instance;
	}
	
	private Timer gameTimer;
	public float gameTime = 0;
	
	public GameLogic() {
		gameTimer = new Timer();
		_instance = this;
		
		gameTimer.scheduleAtFixedRate(new TimerTask(){	
			@Override
			public void run() {
				gameTime += 0.1f;
				
				SceneManager.UpdateScenes();
			}
		}, 0, 5);
	}
	
	public float GetGameTime() {
		return gameTime;
	}
}
