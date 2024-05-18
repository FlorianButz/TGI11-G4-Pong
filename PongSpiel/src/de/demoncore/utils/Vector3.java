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
		return new Vector3(x * multiplier, y * multiplier, z * multiplier);
	}
	
	public Vector3 add(Vector3 addition) {
		return new Vector3(x + addition.x, y + addition.y, z + addition.z);
	}
	
	public Vector3 subtract(Vector3 subtractor) {
		return new Vector3(x - subtractor.x, y - subtractor.y, z - subtractor.z);
	}
	
	public static Vector3 Lerp(Vector3 start, Vector3 end, float value) {
		return new Vector3(GameMath.Lerp(start.x, end.x, value), GameMath.Lerp(start.y, end.y, value), GameMath.Lerp(start.z, end.z, value));
	}
	
	public String ToString() {
		return "X: " + x + "; Y: " + y + " Z: " + z; 
	}
	
	public float Magnitude() {
		return (x + y + z) / 3;
	}	
}
