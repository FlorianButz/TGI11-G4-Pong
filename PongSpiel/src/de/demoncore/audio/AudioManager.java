package de.demoncore.audio;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class AudioManager {

	private float mainVolume;
	
	
	public void PlaySound(Clip clip, float volume) {
		
		
		mainVolume = mainVolume * volume;
		
	}
	
	public Clip AddAudioClip(String filePath) {
		
		Clip in = null;

	    try
	    {
	        AudioInputStream audioIn = AudioSystem.getAudioInputStream( getClass().getResource( filePath) );
	        in = AudioSystem.getClip();
	        in.open( audioIn );
	    }
	    catch( Exception e )
	    {
	        e.printStackTrace();
	    }

	    return in;
		
		
		
	}
	
	
	
}
	
	
	
	
	
	
