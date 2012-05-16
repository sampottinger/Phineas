package org.phineas.core;

/**
 * Convenience interface for objects that the game needs to track (for mouse events, collisions, etc.)
 * that are made up of objects that the game also needs to track 
 * @author Sam Pottinger
 */
public interface PhineasCompoundGameObject
{
	/**
	 * Get all of the components the game should track
	 * @return Objects that the need to be added to the game / removed from it with this compound object
	 */
	public Iterable<Object> getComponents();
}
