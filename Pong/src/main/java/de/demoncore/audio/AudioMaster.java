package de.demoncore.audio;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.lwjgl.openal.ALC;
import org.lwjgl.openal.ALC10;
import org.lwjgl.openal.ALCCapabilities;
import org.lwjgl.openal.ALCapabilities;
import org.lwjgl.system.MemoryUtil;

import de.demoncore.utils.Logger;
import de.demoncore.utils.Vector3;
import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.Decoder;
import javazoom.jl.decoder.Header;
import javazoom.jl.decoder.SampleBuffer;

public class AudioMaster {

	private static List<Integer> buffers = new ArrayList<Integer>();
	private static List<OnVolumeChangeListener> onVolumeChangeListeners = new ArrayList<OnVolumeChangeListener>();
	private static List<OnMusicVolumeChangeListener> onMusicVolumeChangeListeners = new ArrayList<OnMusicVolumeChangeListener>();
	private static List<OnPauseListener> onPauseListeners = new ArrayList<OnPauseListener>();

	private static long context;
	private static long device;

	private static ALCCapabilities alcCapabilities;
	private static ALCapabilities alCapabilities;
	
	private static boolean isInitialized;
	
	public static boolean isInitialized() {
		return isInitialized;
	}

	public static void initializeOpenAL() {
		
		Logger.logInfo("Initialisiere OpenAL Audio...");
		
		String defaultDeviceName = ALC10.alcGetString(0, ALC10.ALC_DEFAULT_DEVICE_SPECIFIER);
		device = ALC10.alcOpenDevice(defaultDeviceName);
		alcCapabilities = ALC.createCapabilities(device);

		context = ALC10.alcCreateContext(device, (IntBuffer) null);
		ALC10.alcMakeContextCurrent(context);

		alCapabilities = AL.createCapabilities(alcCapabilities);
	
		isInitialized = true;
	}

	public static void SetListener(Vector3 position) {
		AL10.alListener3f(AL10.AL_POSITION, position.x, 0, position.y);
		AL10.alListener3f(AL10.AL_VELOCITY, 0, 0, 0);
	}

	public static AudioClip loadSoundMp3(InputStream fis) {
		int buffer = AL10.alGenBuffers();
		buffers.add(buffer);

		// Decode MP3 file to PCM
		BufferedInputStream bis = new BufferedInputStream(fis);
		Bitstream bitstream = new Bitstream(bis);
		Decoder decoder = new Decoder();

		try {

			List<Short> pcmData = new ArrayList<>();
			Header header;
			while ((header = bitstream.readFrame()) != null) {
				SampleBuffer output = (SampleBuffer) decoder.decodeFrame(header, bitstream);
				short[] samples = output.getBuffer();
				for (short sample : samples) {
					pcmData.add(sample);
				}
				bitstream.closeFrame();
			}

			ByteBuffer bufferData = ByteBuffer.allocateDirect(pcmData.size() * 2);
	        bufferData.order(ByteOrder.nativeOrder());
	        ShortBuffer shortBuffer = bufferData.asShortBuffer();
	        for (short pcmSample : pcmData) {
	            shortBuffer.put(pcmSample);
	        }

	        AL10.alBufferData(buffer, AL10.AL_FORMAT_STEREO16, bufferData, decoder.getOutputFrequency());
	        
	        bis.close();
	        bitstream.close();
	        bufferData = null;
	        decoder = null;
	        
		}catch(Exception e) {

			Logger.logError("Fehler beim laden einer Audiodatei. | " + e.getMessage() , e);
		}
		
        // Check for errors
        checkALError();

		return new AudioClip(buffer);
	}

	public static AudioClip loadSoundWav(String file) {
		int buffer = AL10.alGenBuffers();
		buffers.add(buffer);

		WaveData waveData = WaveData.create(file);
		try {			
			AL10.alBufferData(buffer, waveData.format, waveData.data, waveData.samplerate);
			waveData.dispose();
		}catch(Exception e) {
			Logger.logError("Konnte Audio nicht laden. " + e.getMessage(), e);
		}

		return new AudioClip(buffer);
	}

	private static void checkALError() {
        int error = AL10.alGetError();
        if (error != AL10.AL_NO_ERROR) {
            throw new IllegalStateException("OpenAL error: " + error);
        }
    }
	
	public static void AddOnVolumeChangeListener(OnVolumeChangeListener listener) {
		onVolumeChangeListeners.add(listener);
	}

	public static void RemoveOnVolumeChangeListener(OnVolumeChangeListener listener) {
		onVolumeChangeListeners.remove(listener);
	}

	public static void AddOnMusicVolumeChangeListener(OnMusicVolumeChangeListener listener) {
		onMusicVolumeChangeListeners.add(listener);
	}
	
	public static void RemoveOnMusicVolumeChangeListener(OnMusicVolumeChangeListener listener) {
		onMusicVolumeChangeListeners.remove(listener);
	}
	
	public static void AddOnPauseListener(OnPauseListener listener) {
		onPauseListeners.add(listener);
	}
	
	public static void RemoveOnPauseListener(OnPauseListener listener) {
		onPauseListeners.remove(listener);
	}
	
	public static void SetMasterVolume(float volume) {
		for(OnVolumeChangeListener l : onVolumeChangeListeners) {
			l.OnVolumeChange(volume);
		}
	}

	public static void SetMusicVolume(float volume) {
		for(OnMusicVolumeChangeListener l : onMusicVolumeChangeListeners) {
			l.OnMusicVolumeChange(volume);
		}
	}

	public static void SetAllPaused(boolean isPaused) {
		for(OnPauseListener l : onPauseListeners) {
			l.OnPause(isPaused);
		}
	}
	
	public static void ClearBuffer(AudioClip clip) {
		AL10.alDeleteBuffers(clip.GetBuffer());
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
