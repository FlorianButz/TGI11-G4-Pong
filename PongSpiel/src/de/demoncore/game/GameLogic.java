package de.demoncore.game;

import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import de.demoncore.audio.AudioManager;
import de.demoncore.gui.Gui;

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
				
				if((System.currentTimeMillis() - lastTime) > 1000) {
					tps = countedTps;
					countedTps = 0;
					lastTime = System.currentTimeMillis();
				}
				countedTps++;
				
				gameTime += 0.1f;
				
				System.out.println("Number of threads " + Thread.activeCount());
				
				Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
				System.out.println("------------------------------");
				for(Thread t : threadSet) {
					System.out.println(t.getName());
				}
				
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
