package de.demoncore.game;

import java.util.ArrayList;
import java.util.List;

public class PointSystem {

	private static int player1Points = 0;
	private static int player2Points = 0;
	
	public static List<PointListener> listeners = new ArrayList<PointListener>();
	
	public static void Initialize() {
		listeners = new ArrayList<PointListener>();
		
		player1Points = 0;
		player2Points = 0;
	}
	
	public static int getPlayer1Points() {
		return player1Points;
	}
	
	public static int getPlayer2Points() {
		return player2Points;
	}
	
	public static void addPlayer1Points(int amount) {
		player1Points += amount;
		for(PointListener listener : listeners) {
			listener.onPointAdded();
		}
	}
	
	public static void addPlayer2Points(int amount) {
		player2Points += amount;
		for(PointListener listener : listeners) {
			listener.onPointAdded();
		}
	}
}