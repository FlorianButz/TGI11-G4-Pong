package gameObjects;

import game.GameObject;

public class BeweglichesRechteck extends GameObject {
	
	public byte richtung;
	public int schritteInGleicherRichtung;

	public BeweglichesRechteck(int posX, int posY, int breite, int hoehe) {
		super(posX, posY, breite, hoehe);
		schritteInGleicherRichtung = 0;
	}
	
	
	public void automatischeKreisbewegung() {
		if(richtung == 0) {
			positionX += 1;
		} else if (richtung == 1) {
			positionY += 1;
		} else if (richtung == 2) {
			positionX -= 1;
		} else if (richtung == 3) {
			positionY -= 1;
		}
		if (schritteInGleicherRichtung > 75) {
			richtung += 1;
			if (richtung > 3) {
				richtung = 0;
			}
			schritteInGleicherRichtung = 0;
		} else {
			schritteInGleicherRichtung += 1;
		}
	}
}
