package org.phineas.core;

/**
 * Interface for bounded regions looking for mouse hover events
 * @author Sam Pottinger
 */
public interface PhineasHoverListener extends Boundable
{	
	/**
	 * Method that will be called when the mouse enters
	 * this object's bounding box
	 */
	public abstract void onEnter();
	
	/**
	 * Method that will be called when the mouse leaves
	 * this object's bounding box
	 */
	public abstract void onLeave();
}
