package de.demoncore.gameObjects;

import java.awt.Color;

import de.demoncore.utils.Vector3;

public class Particle {
	public Vector3 position = Vector3.zero();
	public Vector3 size = Vector3.one();
	public Vector3 velocity = Vector3.zero();

	public Color startColor;
	public Color color;
	
	public int currentLifetime = 0;
	public int maxLifetime = 0;
	
	public int rotation = 0;
}