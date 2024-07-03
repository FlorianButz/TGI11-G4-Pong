package de.demoncore.scenes.shopnew;

public enum PedalSkins {

	White(0),
	BlueParticles(40),
	RedParticles(50);
	
	private int numVal;

	PedalSkins(int numVal) {
		this.numVal = numVal;
	}

	public int getNumVal() {
		return numVal;
	}
}
