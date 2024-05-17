package de.demoncore.utils;

public class Vector3 {
	
	public float x, y, z;

	public static Vector3 zero() {
		return new Vector3(0, 0, 0);
	}
	
	public static Vector3 one() {
		return new Vector3(1, 1, 1);
	}
	
	public Vector3(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector3(float x, float y) {
		this.x = x;
		this.y = y;
		this.z = 0;
	}
	
	public Vector3 multiply(float multiplier) {
		return new Vector3((float)((float)(x) * multiplier), (float)((float)(x) * multiplier), (float)((float)(z) * multiplier));
	}
	
	public Vector3 add(Vector3 addition) {
		return new Vector3((float)(x + addition.x), (float)(y + addition.y), (float)(z + addition.z));
	}
	
	public Vector3 subtract(Vector3 subtractor) {
		return new Vector3((float)(x - subtractor.x), (float)(y - subtractor.y), (float)(z - subtractor.z));
	}
	
	public static Vector3 Lerp(Vector3 start, Vector3 end, float value) {
		return new Vector3((float)(GameMath.Lerp(start.x, end.x, value)), (float)(GameMath.Lerp(start.x, end.x, value)), (float)(GameMath.Lerp(start.x, end.x, value)));
	}
	
	public String ToString() {
		return "X: " + x + "; Y: " + y + " Z: " + z; 
	}
	
}
