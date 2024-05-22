package de.demoncore.audio;

import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.lwjgl.openal.ALC;
import org.lwjgl.openal.ALC10;
import org.lwjgl.openal.ALCCapabilities;
import org.lwjgl.openal.ALCapabilities;
import org.lwjgl.system.MemoryUtil;

import de.demoncore.utils.Vector3;

public class AudioMaster {

	private static List<Integer> buffers = new ArrayList<Integer>();
	private static List<OnVolumeChangeListener> onVolumeChangeListeners = new ArrayList<OnVolumeChangeListener>();
	
	private static long context;
	private static long device;
	private static ALCCapabilities alcCapabilities;
	private static ALCapabilities alCapabilities;
	
	public static void InitializeOpenAL() {
		String defaultDeviceName = ALC10.alcGetString(0, ALC10.ALC_DEFAULT_DEVICE_SPECIFIER);
		device = ALC10.alcOpenDevice(defaultDeviceName);
		alcCapabilities = ALC.createCapabilities(device);

		context = ALC10.alcCreateContext(device, (IntBuffer) null);
		ALC10.alcMakeContextCurrent(context);

		alCapabilities = AL.createCapabilities(alcCapabilities);
	}
	
	public static void SetListener(Vector3 position) {
		AL10.alListener3f(AL10.AL_POSITION, position.x, 0, position.y);
		AL10.alListener3f(AL10.AL_VELOCITY, 0, 0, 0);
	}
	
	public static AudioClip LoadSound(String file) {
		int buffer = AL10.alGenBuffers();
		buffers.add(buffer);

		WaveData data = WaveData.create(file);
		AL10.alBufferData(buffer, data.format, data.data, data.samplerate);
		data.dispose();
		
		return new AudioClip(buffer);
	}
	
	public static void AddOnVolumeChangeListener(OnVolumeChangeListener listener) {
		onVolumeChangeListeners.add(listener);
	}
	
	public static void RemoveOnVolumeChangeListener(OnVolumeChangeListener listener) {
		onVolumeChangeListeners.remove(listener);
	}
	
	public static void SetMasterVolume(float volume) {
		for(OnVolumeChangeListener l : onVolumeChangeListeners) {
			l.OnVolumeChange(volume);
		}
	}
	
	public static void SetMusicVolume(float volume) {
		System.err.println("Music volume not implemented yet");
	}
	
	public static void DestroyOpenAL() {
		for(int buffer : buffers) {
			AL10.alDeleteBuffers(buffer);
		}
		
		ALC10.alcMakeContextCurrent(MemoryUtil.NULL);
		ALC10.alcDestroyContext(context);
		ALC10.alcCloseDevice(device);
	}
}
