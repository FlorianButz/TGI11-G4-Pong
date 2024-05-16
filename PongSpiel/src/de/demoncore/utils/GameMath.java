package de.demoncore.utils;

public class GameMath {

	public static float Lerp(float a, float b, float f)
	{
		float fClamped = Clamp(f, 0, 1);
		
	    return a * (1.0f - fClamped) + (b * fClamped);
	}
	
	public static float Lerp(int a, int b, float f)
	{
		float fClamped = Clamp(f, 0, 1);
		
	    return a * (1.0f - fClamped) + (b * fClamped);
	}
	
	public static float Clamp(float value, float min, float max) {
	    return Math.max(min, Math.min(max, value));
	}
	
	public static int Clamp(int value, int min, int max) {
	    return Math.max(min, Math.min(max, value));
	}
}
