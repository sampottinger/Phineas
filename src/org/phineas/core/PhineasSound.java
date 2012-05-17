package org.phineas.core;

/**
 * High level sound abstraction
 * @author Sam Pottinger
 */
public class PhineasSound
{
	
	public enum VolumeLevel { LOW, LOW_MEDIUM, MEDIUM, HIGH_MEDIUM, HIGH };
	
	public void start()
	{
		throw new UnsupportedOperationException("Not yet implemented");
	}
	
	public void stop()
	{
		throw new UnsupportedOperationException("Not yet implemented");
	}
	
	public PhineasSound clone()
	{
		throw new UnsupportedOperationException("Not yet implemented");
	}
	
	/**
	 * Sets the volume at which this sound will play. 
	 * @param volumeLevel The new volume level at which this clip should play
	 */
	public void setVolume(VolumeLevel volumeLevel)
	{
		throw new UnsupportedOperationException("Not yet implemented");
	}
	
	/**
	 * Speeds up or slows down this clip
	 * @param tempo Multiplier to use for the tempo (2 = twice as fast)
	 */
	public void setTempo(float tempo)
	{
		throw new UnsupportedOperationException("Not yet implemented");
	}
	
	/**
	 * Has this sound stop and go back to the start of the clip
	 */
	public void reset()
	{
		throw new UnsupportedOperationException("Not yet implemented");
	}
}
