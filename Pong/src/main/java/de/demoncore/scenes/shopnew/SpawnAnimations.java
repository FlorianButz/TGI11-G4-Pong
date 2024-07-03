package de.demoncore.scenes.shopnew;

public enum SpawnAnimations {

	None(0),
	Latiku(75);
	
	private int numVal;

	SpawnAnimations(int numVal) {
		this.numVal = numVal;
	}

	public int getNumVal() {
		return numVal;
	}
}
