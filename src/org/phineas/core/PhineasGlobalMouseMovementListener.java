package org.phineas.core;

/**
 * Describes objects that listen for any movement of the mouse within the game
 * @author Sam Pottinger
 */
public interface PhineasGlobalMouseMovementListener
{
	public void onMouseMove(int newX, int newY);
}
