package org.phineas.core;

/**
 * Interface for entities that respond being clicked on
 * @author Sam Pottinger
 */
public interface PhineasClickListener extends Boundable
{
	
	/**
	 * Called when the left button depresses on this
	 * boundable object
	 * @param relativeX The x coordinate of the cursor
	 *                  relative to the upper left corner
	 *                  of this object when the button was
	 *                  depressed
	 * @param relativeY The y coordinate of the cursor
	 *                  relative to the upper left corner
	 *                  of this object when the button was
	 *                  depressed
	 */
	public void onLeftDown(int relativeX, int relativeY);
	
	/**
	 * Called when the right button depresses on this
	 * boundable object
	 * @param relativeX The x coordinate of the cursor
	 *                  relative to the upper left corner
	 *                  of this object when the button was
	 *                  released
	 * @param relativeY The y coordinate of the cursor
	 *                  relative to the upper left corner
	 *                  of this object when the button was
	 *                  released
	 */
	public void onLeftRelease(int relativeX, int relativeY);

}
