package de.demoncore.audio;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import de.demoncore.game.Settings;
import de.demoncore.utils.GameMath;


public class AudioManager {
	
	static List<Clip> activeClips = new ArrayList<Clip>();
	
	public static Clip PlaySound(String audioName, boolean isLooping) {
		float clipVolume = Settings.GetVolume();
		
		if(clipVolume == 0) return null;		
		
		CleanupClipList();
		
		try {
			
			InputStream audioSrc = AudioManager.class.getResourceAsStream("/resources/audio/" + audioName + ".wav");
			InputStream bufferedIn = new BufferedInputStream(audioSrc);
			
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(bufferedIn);
			
			Clip clip;
			clip = AudioSystem.getClip();
			clip.open(inputStream);
			
			FloatControl gainControl = 
				    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
				gainControl.setValue(GameMath.RemapValue(clipVolume, 0, 100, gainControl.getMinimum() / 1.2f, -5f));
			
			if(isLooping) clip.loop(clip.LOOP_CONTINUOUSLY);
				
			clip.start();

			activeClips.add(clip);
			
			return clip;

		} catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	public static Clip PlaySound(String audioName) {
		return PlaySound(audioName, false);
	}
	
	@SuppressWarnings("unlikely-arg-type")
	static void CleanupClipList() {
		List<Clip> clipsToRemove = new ArrayList<Clip>();
		
		for(Clip c : new ArrayList<Clip>(activeClips)) {
			if(!c.isRunning()) clipsToRemove.add(c);
		}
		
		activeClips.remove(clipsToRemove);
	}
	
	public static void ChangeMasterVolume(float volume) {		
		for(Clip c : new ArrayList<Clip>(activeClips)) {
			if(c == null) continue;			
			FloatControl gainControl = 
				    (FloatControl) c.getControl(FloatControl.Type.MASTER_GAIN);
				gainControl.setValue(GameMath.RemapValue(volume, 0, 100, gainControl.getMinimum(), 2f));
				
		}
	}
}
	
	
	
	
	
	
