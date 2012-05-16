package org.phineas.core;

/**
 * Interface for objects that respond to scroll wheel events
 * @author Sam Pottinger
 */
public interface PhineasScrollWheelListener
{
	/**
	 * Method to call when the mouse wheel is moved
	 * @param units The number of notches (positive or negative) scrolled
	 */
	public void onWheelMove(int notches);
}
