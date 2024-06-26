package de.demoncore.game.animator;

public class Easing {
	
	public enum EasingType { Linear, OutExponential, InOutQuint, InOutSine }
	
	public static float EaseOutExpo(float x) {
		return (float) (x == 1 ? 1 : 1 - Math.pow(2, -10 * x));
	}
	
	public static float EaseInOutQuint(float x) {
		return (float) (x < 0.5 ? 16 * x * x * x * x * x : 1 - Math.pow(-2 * x + 2, 5) / 2);
	}
	
	public static float EaseInOutSine(float x) {
		return (float) (-(Math.cos(Math.PI * x) - 1) / 2);
	}
}
