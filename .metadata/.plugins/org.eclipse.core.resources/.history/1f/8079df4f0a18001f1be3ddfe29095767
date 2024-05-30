package de.demoncore.audio;

import org.lwjgl.openal.AL10;

import de.demoncore.game.GameObject;
import de.demoncore.game.Settings;

public class AudioSource extends GameObject implements OnVolumeChangeListener {

	private int sourceId;
	private float volume = 1f;
	
	private boolean isSpacial = true;
	
	GameObject parentObject;
	
	public AudioSource(GameObject parentObject) {
		super(0, 0, 0, 0);
		
		sourceId = AL10.alGenSources();

		this.parentObject = parentObject;
		
		AL10.alSourcef(sourceId, AL10.AL_PITCH, 1f);
		AL10.alSource3f(sourceId, AL10.AL_POSITION, 0, 0, 0);
		SetVolume(1f);
		
		AudioMaster.AddOnVolumeChangeListener(this);
	}
	
	public AudioSource SetSpacial(boolean isSpacial) {
		this.isSpacial = isSpacial;
		return this;
	}
	
	public AudioSource Play(AudioClip audioClip) {
		if(!isInScene) return this;

		AL10.alSourcef(sourceId, AL10.AL_GAIN, this.volume);
		AL10.alSourcei(sourceId, AL10.AL_BUFFER, audioClip.GetBuffer());
		AL10.alSourcePlay(sourceId);
		
		return this;
	}
	
	public void SetVolume(float volume) {
		this.volume = volume * Settings.GetVolume();
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
	public void Update() {
		super.Update();
		position = parentObject.GetPosition();
		
		if(isSpacial)
			AL10.alSource3f(sourceId, AL10.AL_POSITION, position.x, 0, position.y);		
		else
			AL10.alSource3f(sourceId, AL10.AL_POSITION, 0, 0, 0);
	}

	@Override
	public void OnDestroy() {
		super.OnDestroy();
		AL10.alDeleteSources(sourceId);
		AudioMaster.RemoveOnVolumeChangeListener(this);
	}

	@Override
	public void OnVolumeChange(float volume) {
		SetRealVolume(volume);
		System.out.println(this.volume);
	}
	
}
