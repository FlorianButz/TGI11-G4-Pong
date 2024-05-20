package de.demoncore.audio;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

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

    private static final int DEFAULT_BUFFER_SIZE = 1024;

    public static Clip PlaySound(String audioName, boolean isLooping) {
        return PlaySound(audioName, isLooping, DEFAULT_BUFFER_SIZE);
    }

    public static Clip PlaySound(String audioName, boolean isLooping, int bufferSize) {
        float clipVolume = Settings.GetVolume();

        if (clipVolume == 0) return null;

        CleanupClipList();

        try {
            InputStream audioSrc = AudioManager.class.getResourceAsStream("/resources/audio/" + audioName + ".wav");
            InputStream bufferedIn = new BufferedInputStream(audioSrc, bufferSize);

            AudioInputStream inputStream = AudioSystem.getAudioInputStream(bufferedIn);

            Clip clip = AudioSystem.getClip();
            clip.open(inputStream);

            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(GameMath.RemapValue(clipVolume, 0, 100, gainControl.getMinimum(), 1f));

            if (isLooping) clip.loop(Clip.LOOP_CONTINUOUSLY);

            clip.start();

            activeClips.add(clip);

            return clip;

        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Clip PlaySound(String audioName) {
        return PlaySound(audioName, false);
    }

    @SuppressWarnings("unlikely-arg-type")
    static void CleanupClipList() {
        List<Clip> clipsToRemove = new ArrayList<>();

        for (Clip c : new ArrayList<>(activeClips)) {
            if (!c.isRunning()) clipsToRemove.add(c);
        }

        activeClips.removeAll(clipsToRemove);
    }

    public static void ChangeMasterVolume(float volume) {
        float minGain = -50f;
        float maxGain = 6.0f; // Typical value for the upper bound of the gain control, you might need to adjust this.
        float newGain = GameMath.RemapValue(volume, 0, 100, minGain, maxGain);

        for (Clip c : new ArrayList<Clip>(activeClips)) {
            if (c == null) continue;
            try {
                FloatControl gainControl = (FloatControl) c.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(newGain);
            } catch (Exception e) {
                // Handle exception if the control is not supported or other issues
                e.printStackTrace();
            }
        }
    }

    public static void ChangeMusicVolume(float volume) {
        System.out.println("MUSIC VOLUME CHANGE NOT IMPLEMENTED YET");
    }
}
