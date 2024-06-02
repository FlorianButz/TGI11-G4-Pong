package de.demoncore.audio;

public class AudioClip {
	private int buffer;
	
	public AudioClip(int buffer) {
		this.buffer = buffer;
	}
	
	public int GetBuffer() {
		return this.buffer;
	}
}
