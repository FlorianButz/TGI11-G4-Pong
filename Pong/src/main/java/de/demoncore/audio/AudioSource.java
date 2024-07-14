package de.demoncore.audio;

import java.util.Timer;
import java.util.TimerTask;

import org.lwjgl.openal.AL10;

import de.demoncore.game.GameObject;
import de.demoncore.game.SceneManager;
import de.demoncore.game.Settings;
import de.demoncore.utils.Logger;
import de.demoncore.utils.Vector3;

public class AudioSource extends GameObject implements OnVolumeChangeListener, OnPauseListener {

	private int sourceId;
	private float volume = 1f;

	private boolean isSpacial = true;

	GameObject parentObject;

	public AudioSource(GameObject parentObject) {
		super(0, 0, 0, 0);
		Init(parentObject);
	}
	
	public AudioSource() {
		super(0, 0, 0, 0);
		Init(null);
	}

	void Init(GameObject parentObject) {
		sourceId = AL10.alGenSources();

		this.parentObject = parentObject;

		AL10.alSourcef(sourceId, AL10.AL_PITCH, 1f);
		AL10.alSource3f(sourceId, AL10.AL_POSITION, 0, 0, 0);
		SetVolume(1f);

		AudioMaster.AddOnVolumeChangeListener(this);
		AudioMaster.AddOnPauseListener(this);
	}
	
	public AudioSource setSpacial(boolean isSpacial) {
		this.isSpacial = isSpacial;
		return this;
	}

	public AudioSource Play(AudioClip audioClip) {
		if(!isInScene) {
			Logger.logWarning("Versucht Audio zu spielen bevor die Source in der Scene ist. " + this.parentObject.getClass().getName(), this);
		}

		AL10.alSourceStop(sourceId);
		
		AL10.alSourcef(sourceId, AL10.AL_GAIN, this.volume);
		AL10.alSourcei(sourceId, AL10.AL_BUFFER, audioClip.GetBuffer());
		AL10.alSourcePlay(sourceId);

		return this;
	}

	public void SetVolume(float volume) {
		this.volume = volume * Settings.getVolume() / 100f;
		AL10.alSourcef(sourceId, AL10.AL_GAIN, this.volume);
	}

	public void SetRealVolume(float volume) {
		this.volume = volume;
		AL10.alSourcef(sourceId, AL10.AL_GAIN, this.volume);
	}

	public void SetPitch(float pitch) {
		AL10.alSourcef(sourceId, AL10.AL_PITCH, pitch);
	}

	@Override
	public void update() {
		super.update();
	
		if(parentObject != null)
			position = parentObject.getPosition();

		Vector3 camPos = SceneManager.getActiveScene().cameraPosition;
		if(!isSpacial) {			
			AL10.alSource3f(sourceId, AL10.AL_POSITION, camPos.x, camPos.y, 0);
		}
		else {
			AL10.alSource3f(sourceId, AL10.AL_POSITION, getPosition().x, getPosition().y, 0);
		}
		
		AL10.alListener3f(AL10.AL_POSITION, camPos.x, camPos.y, 0);
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		AL10.alDeleteSources(sourceId);
		AudioMaster.RemoveOnVolumeChangeListener(this);
		AudioMaster.RemoveOnPauseListener(this);
	}

	@Override
	public void OnVolumeChange(float volume) {
		SetRealVolume(volume);
	}

	public int GetSourceState() {
		int state = AL10.alGetSourcei(sourceId, AL10.AL_SOURCE_STATE);
		
		return state;
	}
	
	@Override
	public void OnPause(boolean isPaused) {
		if(isPaused && GetSourceState() == AL10.AL_PLAYING) {
			AL10.alSourcePause(sourceId);
		}else if(!isPaused && GetSourceState() == AL10.AL_PAUSED) {
			AL10.alSourcePlay(sourceId);
		}
	}

}
