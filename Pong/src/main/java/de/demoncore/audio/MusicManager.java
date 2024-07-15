package de.demoncore.audio;

import java.nio.IntBuffer;
import java.util.Timer;
import java.util.TimerTask;

import org.lwjgl.openal.AL10;

import de.demoncore.game.Settings;
import de.demoncore.game.animator.AnimatorOnCompleteEvent;
import de.demoncore.game.animator.AnimatorUpdateEvent;
import de.demoncore.game.animator.Easing.EasingType;
import de.demoncore.game.animator.Vector3Animator;
import de.demoncore.main.Main;
import de.demoncore.utils.GameMath;
import de.demoncore.utils.Logger;
import de.demoncore.utils.Vector3;

public class MusicManager {

	private static int sourceId;
	private static float volume = 1f;

	private static AudioClip activeMusic;

	private static int playing = 0;

	public static void InitializeMusicManager() {

		Logger.logInfo("Musikmanager wird Initialisiert...");

		sourceId = AL10.alGenSources();

		AL10.alSourcef(sourceId, AL10.AL_PITCH, 1f);
		AL10.alSource3f(sourceId, AL10.AL_POSITION, 0, 0, 0);
		SetVolume(1f);

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

		endboss = AudioMaster.loadSoundMp3(Main.class.getResourceAsStream("/audio/music/new/End.mp3"));
		
		playing1Audio = new AudioClip[] {
//				AudioMaster.loadSoundMp3(Main.class.getResourceAsStream("/audio/music/new/Explorer.mp3")),
//				AudioMaster.loadSoundMp3(Main.class.getResourceAsStream("/audio/music/new/FarAway.mp3")),
				AudioMaster.loadSoundMp3(Main.class.getResourceAsStream("/audio/music/new/Gone.mp3")),
//				AudioMaster.loadSoundMp3(Main.class.getResourceAsStream("/audio/music/new/NothingLeft.mp3")),
//				AudioMaster.loadSoundMp3(Main.class.getResourceAsStream("/audio/music/new/TheLastLight.mp3")),
		};

		Logger.logInfo("Loading music fully complete.");

		Timer timer = new Timer();
		TimerTask musicUpdate = new TimerTask() {
			public void run() {


				AudioClip rand = randomlyChoseMusic();
				if(rand != null && Math.random() >= 0.5f)
					PlayMusic(randomlyChoseMusic());


			};
		};

		timer.scheduleAtFixedRate(musicUpdate, 25000, 25000);
	}

	public static AudioClip endboss;
	public static AudioClip[] playing1Audio;

	static AudioClip randomlyChoseMusic() {
		AudioClip music = null;

		switch(playing) {
		case 1:
			music = playing1Audio[GameMath.RandomRange(0, playing1Audio.length)];
			break;
		}

		if(activeMusic != null && music != null) AudioMaster.ClearBuffer(activeMusic);
		return music;
	}

	public static void ForcePlayMusic(AudioClip musicClip, boolean looping) {
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
					AL10.alSourcei(sourceId, AL10.AL_LOOPING, looping ? 1 : 0);
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

	static boolean isPlaying() { int state = AL10.alGetSourcei(sourceId, AL10.AL_SOURCE_STATE); return (state == AL10.AL_PLAYING); }

	public static void PlayMusic(AudioClip musicClip) {
		if(activeMusic == musicClip || isPlaying()) return;
		ForcePlayMusic(musicClip, false);
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

	static void stopMusic() {
		AL10.alSourceStop(sourceId);
	}
	
	public static void playStorymode() {
		playing = 1;
		stopMusic();
	}
}
