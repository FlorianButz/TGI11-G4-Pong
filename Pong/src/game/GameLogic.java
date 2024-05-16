package game;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import gameObjects.BeweglichesRechteck;

public class GameLogic {
	
	private Timer gameTimer;
	public int screenwidth;
	public int screenheight;
	public ArrayList<GameObject> spielObjekte;
	
	public boolean keyLeftarrowpressed;
	public boolean keyRightarrowpressed;
	
	public GameLogic() {
		gameTimer = new Timer();
		spielObjekte = new ArrayList<GameObject>();
		
		keyLeftarrowpressed = false;
		keyRightarrowpressed = false;
		
		// Objekte im Spiel:
		BeweglichesRechteck beispielObjekt1 = new BeweglichesRechteck(50, 100, 20, 20);
		spielObjekte.add(beispielObjekt1);
		beispielObjekt1.richtung = 0; // Startrichtung
		BeweglichesRechteck beispielObjekt2 = new BeweglichesRechteck(300, 400, 20, 20);
		spielObjekte.add(beispielObjekt2);
		
		gameTimer.scheduleAtFixedRate(new TimerTask(){
			@Override
			public void run() {
				// Laufende Ausführungen im Spiel:
				beispielObjekt1.automatischeKreisbewegung();
				
				if (keyLeftarrowpressed) {
					beispielObjekt2.positionX -= 1;
				} else if (keyRightarrowpressed) {
					beispielObjekt2.positionX += 1;
				}
			}
		}, 0, 5);
	}
	
}
