package de.demoncore.utils;

import java.awt.Color;
import java.util.Random;

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
	
	public static Color LerpColor(Color a, Color b, float f) {
		return new Color(
					Lerp((float)a.getRed() / 255, (float)b.getRed() / 255, f),
					Lerp((float)a.getGreen() / 255, (float)b.getGreen() / 255, f),
					Lerp((float)a.getBlue() / 255, (float)b.getBlue() / 255, f),
					Lerp((float)a.getAlpha() / 255, (float)b.getAlpha() / 255, f)
			);
	}
	
	public static float Clamp(float value, float min, float max) {
	    return Math.max(min, Math.min(max, value));
	}
	
	public static int Clamp(int value, int min, int max) {
	    return Math.max(min, Math.min(max, value));
	}
	
	static Random random = new Random();

	public static int RandomRange(int min, int max) {
		if(min == 0 && max == 0) return 0;
		int randomNumber = random.nextInt(max - min) + min;
		return randomNumber;
	}
	
	public static float RandomRange(float min, float max) {
		if(min == 0 && max == 0) return 0;
		else if(min > max) {
			return RandomRange(max, min);
		}
		else if(min == max) {
			return max;
		}
		
		float randomNumber = random.nextFloat(max - min) + min;
		return randomNumber;
	}
}
