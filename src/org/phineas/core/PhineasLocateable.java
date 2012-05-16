package org.phineas.core;

/**
 * Interface for objects with locations
 * @author Sam Pottinger
 */
public interface PhineasLocateable
{
	/**
	 * Get the x position of this object (in pixels)
	 * @return Horizontal position from left of container in pixels
	 */
	public int getX();
	
	/**
	 * Get the x position of this object (in pixels)
	 * @return Vertical position from top of container in pixels
	 */
	public int getY();
}
