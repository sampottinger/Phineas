package org.phineas.core;

/**
 * Interface for elements that have a position and can be placed
 * @author Sam Pottinger
 */
public interface Placeable extends Locatable
{
	/**
	 * Sets this object's position to the given x coordinate
	 * @param x The number of pixels this object should be
	 *          from the left side of its container
	 */
	public void setX(int x);
	
	/**
	 * Sets this object's position to the given y coordinate
	 * @param y The number of pixels this object should be
	 *          from the top side of its container
	 */
	public void setY(int y);

}
