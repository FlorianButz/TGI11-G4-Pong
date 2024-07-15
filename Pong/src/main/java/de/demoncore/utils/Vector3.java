package de.demoncore.utils;

public class Vector3 {
	
	public float x, y, z;

	public static Vector3 zero() {
		return new Vector3(0, 0, 0);
	}
	
	public static Vector3 one() {
		return new Vector3(1, 1, 1);
	}
	
	public int getX() {
		return (int) Math.round(x);
	}
	
	public int getY() {
		return (int) Math.round(y);
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
	
	public Vector3 clamp(Vector3 lowerLimit, Vector3 upperLimit) {
		return new Vector3(GameMath.clamp(x, lowerLimit.x, upperLimit.x), GameMath.clamp(y, lowerLimit.y, upperLimit.y), GameMath.clamp(z, lowerLimit.z, upperLimit.z));
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
	
	public Vector3 rotated(double n)
	{
	    float x = (float) ((float)(this.x * Math.cos(Math.toRadians(n))) - (this.y * Math.sin(Math.toRadians(n))));
	    float y = (float) ((float)(this.x * Math.sin(Math.toRadians(n))) + (this.y * Math.cos(Math.toRadians(n))));
	
	    return new Vector3(x, y);
	}
	
	public Vector3 reflect(Vector3 normal) {
        float normLength = (float) Math.sqrt(normal.x * normal.x + normal.y * normal.y + normal.z * normal.z);
        float nx = normal.x / normLength;
        float ny = normal.y / normLength;
        float nz = normal.z / normLength;

        float dotProduct = this.x * nx + this.y * ny + this.z * nz;

        float reflectedX = this.x - 2 * dotProduct * nx;
        float reflectedY = this.y - 2 * dotProduct * ny;
        float reflectedZ = this.z - 2 * dotProduct * nz;

        return new Vector3(reflectedX, reflectedY, reflectedZ);
    }
	
	public String ToString() {
		return "X: " + x + "; Y: " + y + " Z: " + z; 
	}
	
	public float Magnitude() {
		return (Math.abs(x) + Math.abs(y) + Math.abs(z)) / 3;
	}
	
	public Vector3 normalized() {
        float length = (float) Math.sqrt(x * x + y * y + z * z);
        if (length == 0) {
            return Vector3.zero(); // Nullvektor zurueck geben wenn laenge = 0
        }
        return new Vector3(x / length, y / length, z / length);
    }
	
	public static float Distance(Vector3 a, Vector3 b) {
		return (float) Math.sqrt(Math.pow(b.x - a.x, 2) + Math.pow(b.y - a.y, 2) + Math.pow(b.z - a.z, 2));
	}
}
