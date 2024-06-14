package de.demoncore.game;

import java.util.ArrayList;
import java.util.List;

public class PongSpawnEffect {

	public static List<PongSpawnEffectListener> listeners = new ArrayList<PongSpawnEffectListener>();
	
	public static void callEffect() {
		for(PongSpawnEffectListener l : listeners) {
			l.spawnEffect();
		}
	}
	
	public interface PongSpawnEffectListener{
		public void spawnEffect();
	}
	
}
