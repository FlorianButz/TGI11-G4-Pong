package de.demoncore.scenes.shopnew;

public enum BallSkins {

	White(0),
	Red(10),
	Yellow(15),
	Rainbow(30);
	
	private int numVal;

	BallSkins(int numVal) {
		this.numVal = numVal;
	}

	public int getNumVal() {
		return numVal;
	}
}
