package de.demoncore.audio;

import java.nio.IntBuffer;

import org.lwjgl.openal.AL10;

import de.demoncore.game.Settings;
import de.demoncore.game.animator.Easing.EasingType;
import de.demoncore.game.animator.AnimatorOnCompleteEvent;
import de.demoncore.game.animator.AnimatorUpdateEvent;
import de.demoncore.game.animator.Vector3Animator;
import de.demoncore.utils.Logger;
import de.demoncore.utils.Vector3;

public class MusicManager {

	private static int sourceId;
	private static float volume = 1f;
	
	private static AudioClip activeMusic;
	
	public static void InitializeMusicManager() {

		Logger.logInfo("Musikmanager wird Initialisiert...");
		
		sourceId = AL10.alGenSources();
		
		AL10.alSourcef(sourceId, AL10.AL_PITCH, 1f);
		AL10.alSource3f(sourceId, AL10.AL_POSITION, 0, 0, 0);
		SetVolume(1f);
		
		AL10.alSourcei(sourceId, AL10.AL_LOOPING, 1);
		
		AudioMaster.AddOnMusicVolumeChangeListener(new OnMusicVolumeChangeListener() {
			@Override
			public void OnMusicVolumeChange(float volume) {
				MusicManager.SetRealVolume(volume);
			}
		});
		AudioMaster.AddOnPauseListener(new OnPauseListener() {
			@Override
			public void OnPause(boolean isPaused) {
				OnMusicPause(isPaused);
			}
		});
	}
	
	public static void ForcePlayMusic(AudioClip musicClip) {
		float originalVolume = volume;
		
		if(activeMusic != null) {
			
			Vector3Animator volumeAnim = new Vector3Animator(Vector3.one().multiply(originalVolume), Vector3.zero(), 1f, EasingType.Linear);
			volumeAnim.setOnUpdate(new AnimatorUpdateEvent() {@Override public void onUpdate(Vector3 value) { super.onUpdate(value); AL10.alSourcef(sourceId, AL10.AL_GAIN, value.x); }});
			volumeAnim.setOnComplete(new AnimatorOnCompleteEvent() {
			@Override
			public void onComplete() {
				super.onComplete();
				
				AL10.alSourceStop(sourceId);
				activeMusic = musicClip;
				AL10.alSourcei(sourceId, AL10.AL_BUFFER, activeMusic.GetBuffer());
				AL10.alSourcePlay(sourceId);
				
				Vector3Animator volumeAnim = new Vector3Animator(Vector3.zero(), Vector3.one().multiply(originalVolume), 1f, EasingType.Linear);
				volumeAnim.setOnUpdate(new AnimatorUpdateEvent() {@Override public void onUpdate(Vector3 value) { super.onUpdate(value); AL10.alSourcef(sourceId, AL10.AL_GAIN, value.x); }});
				volumeAnim.play();
			}
			});
			
			volumeAnim.play();
		}else {
			activeMusic = musicClip;
			AL10.alSourcei(sourceId, AL10.AL_BUFFER, activeMusic.GetBuffer());
			AL10.alSourcePlay(sourceId);
		}
	}
	
	public static void PlayMusic(AudioClip musicClip) {
		if(activeMusic == musicClip) return;
		ForcePlayMusic(musicClip);
	}
	
	public static void SetVolume(float pVolume) {
		volume = pVolume * Settings.getMusicVolume() / 100f;
		AL10.alSourcef(sourceId, AL10.AL_GAIN, volume);
	}

	public static void SetRealVolume(float pVolume) {
		volume = pVolume;
		AL10.alSourcef(sourceId, AL10.AL_GAIN, volume);
	}
	
	public static int GetSourceState() {
		int state = AL10.alGetSourcei(sourceId, AL10.AL_SOURCE_STATE);
		
		return state;
	}
	
	public static void OnMusicPause(boolean isPaused) {
		if(isPaused && GetSourceState() == AL10.AL_PLAYING) {
			AL10.alSourcePause(sourceId);
		}else if(!isPaused && GetSourceState() == AL10.AL_PAUSED) {
			AL10.alSourcePlay(sourceId);
		}
	}
}
