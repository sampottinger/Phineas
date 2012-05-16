package org.phineas.core;

/**
 * Interface for entities that fit inside a placeable bounding box
 * @author Sam Pottinger
 */
public interface PhineasBoundable extends PhineasLocateable
{
	
	/**
	 * Get the x coordinate of the upper left corner of this
	 * boundable object's bounding box
	 * @return The x coordinate of this object
	 */
	int getX();
	
	/**
	 * Get the y coordinate of the upper left corner of this 
	 * boundable object's bounding box
	 * @return The y coordinate of this object
	 */
	int getY();
	
	/**
	 * Get the width of this boundable object's bounding box
	 * @return How wide this object's bounding box is in pixels
	 */
	int getWidth();
	
	/**
	 * Get the height of this boundable object's bounding box
	 * @return How tall this object's bounding box is in pixels
	 */
	int getHeight();

}
