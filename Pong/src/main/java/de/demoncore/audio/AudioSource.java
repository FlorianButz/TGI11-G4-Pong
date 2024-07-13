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
	private TimerTask task;
	private Timer timer;

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
		
		timer = new Timer();
		task = new TimerTask() {
			@Override
			public void run() {				
				if(parentObject != null)
					position = parentObject.getPosition();

				if(isSpacial)
					AL10.alSource3f(sourceId, AL10.AL_POSITION, position.x, 0, position.y);
				else {
					Vector3 camPos = SceneManager.getActiveScene().cameraPosition;
					AL10.alSource3f(sourceId, AL10.AL_POSITION, camPos.x, 0, camPos.y);
				}
			}
		};
	}
	
	public AudioSource setSpacial(boolean isSpacial) {
		this.isSpacial = isSpacial;
		return this;
	}

	public AudioSource Play(AudioClip audioClip) {
		if(!isInScene) {
			Logger.logError("Versucht Audio zu spielen bevor die Source in der Scene ist. " + this.parentObject.getClass().getName(), this);
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
	public void onDestroy() {
		super.onDestroy();
		AL10.alDeleteSources(sourceId);
		AudioMaster.RemoveOnVolumeChangeListener(this);
		AudioMaster.RemoveOnPauseListener(this);
		
		task.cancel();
	}

	@Override
	public void onAddToScene() {
		super.onAddToScene();
	
		timer.scheduleAtFixedRate(task, 25, 25);
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
