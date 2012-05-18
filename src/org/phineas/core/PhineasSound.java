package org.phineas.core;

import java.io.IOException;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.phineas.contrib.PhineasSoundLoader;

/**
 * High level sound abstraction
 * 
 * @author Sam Pottinger
 */
public class PhineasSound
{

    public enum VolumeLevel
    {
        LOW,
        LOW_MEDIUM,
        MEDIUM,
        HIGH_MEDIUM,
        HIGH
    };

    private Clip clip;

    private String loc;

    public PhineasSound(String loc) throws LineUnavailableException, UnsupportedAudioFileException,
        IOException
    {
        this.loc = loc;
        clip = PhineasSoundLoader.getInstance().loadSound(loc);
    }

    public PhineasSound(String loc, Clip clip) throws LineUnavailableException,
        UnsupportedAudioFileException, IOException
    {
        this.loc = loc;
        this.clip = clip;
    }

    public void start()
    {
        clip.start();
    }

    public void stop()
    {
        clip.stop();
    }

    public PhineasSound clone()
    {
        try
        {
            return new PhineasSound(loc, PhineasSoundLoader.getInstance().loadSound(loc, false));
        } catch (LineUnavailableException e)
        {
            e.printStackTrace();
            return null;
        } catch (UnsupportedAudioFileException e)
        {
            e.printStackTrace();
            return null;
        } catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Sets the volume at which this sound will play.
     * 
     * @param volumeLevel
     *        The new volume level at which this clip should play
     */
    public void setVolume(VolumeLevel volumeLevel)
    {
        FloatControl volume;
        double level = volumeLevel.ordinal() / 4.0f;
        try
        {
            volume = (FloatControl) clip.getControl(FloatControl.Type.VOLUME);
        } catch (IllegalArgumentException e1)
        { // VOLUME control not supported
            try
            {
                volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                level = Math.sqrt(level);
            } catch (IllegalArgumentException e2)
            {
                // MASTER_GAIN control not supported
                e1.printStackTrace();
                e2.printStackTrace();
                return;
            }
        }
        float minimum = volume.getMinimum();
        float maximum = volume.getMaximum();
        float newValue = (float) (minimum + level * (maximum - minimum));
        volume.setValue(newValue);
    }

    /**
     * Speeds up or slows down this clip
     * 
     * @param tempo
     *        Multiplier to use for the tempo (2 = twice as fast)
     */
    public void setTempo(float tempo)
    {
        try
        {
            FloatControl ctrl = (FloatControl) clip.getControl(FloatControl.Type.SAMPLE_RATE);
            ctrl.setValue(tempo * ctrl.getValue());
        } catch (IllegalArgumentException e)
        { // SAMPLE_RATE control not supported
            e.printStackTrace();
        }
    }

    /**
     * Has this sound stop and go back to the start of the clip
     */
    public void reset()
    {
        clip.setFramePosition(0);
    }
}
