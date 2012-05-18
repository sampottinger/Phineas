package org.phineas.contrib;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class PhineasSoundLoader
{
    private static PhineasSoundLoader instance = null;

    private Map<String, Clip> sounds;

    public static PhineasSoundLoader getInstance()
    {
        if (instance == null)
            instance = new PhineasSoundLoader();
        return instance;
    }

    /**
     * Private constructor for this singleton
     */
    private PhineasSoundLoader()
    {
        sounds = new HashMap<String, Clip>();
    }

    public Clip loadSound(String loc) throws LineUnavailableException,
        UnsupportedAudioFileException, IOException
    {
        return loadSound(loc, true);
    }

    public Clip loadSound(String loc, boolean useCache) throws LineUnavailableException,
        UnsupportedAudioFileException, IOException
    {
        // Load from cache if possible
        if (useCache && sounds.containsKey(loc))
            return sounds.get(loc);

        // Load image
        URL url = this.getClass().getClassLoader().getResource(loc);
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);

        // Cache
        sounds.put(loc, clip);

        return clip;
    }

}
