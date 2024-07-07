package de.demoncore.scenes.shopnew;

public enum PedalSkins {

	White(0),
	Lines(40),
	Waves(50),
	Dots(65);
	
	private int numVal;

	PedalSkins(int numVal) {
		this.numVal = numVal;
	}

	public int getNumVal() {
		return numVal;
	}
}
