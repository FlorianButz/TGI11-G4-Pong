package de.demoncore.scenes.shopnew;

public enum BallTrails {

	None(0),
	Simple(0),
	Particles(15),
	Beam(35);
	
	private int numVal;

	BallTrails(int numVal) {
		this.numVal = numVal;
	}

	public int getNumVal() {
		return numVal;
	}
}
