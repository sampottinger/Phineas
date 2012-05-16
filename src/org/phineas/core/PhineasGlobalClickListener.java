package org.phineas.core;

/**
 * Interface for entities that respond to click events everywhere in
 * the game (clicks not necessarily on themselves)
 * @author Sam Pottinger
 */
public interface PhineasGlobalClickListener extends PhineasBoundable
{
	
	/**
	 * Called when the left mouse button is clicked when the
	 * cursor is in the game
	 * @param absX The x coordinate relative to the window of the cursor
	 *             when it clicked
	 * @param absY The y coordinate relative to the window of the cursor
	 *             when it clicked
	 */
	public void onGlobalLeftDown(int absX, int absY);
	
	/**
	 * Called when the left mouse button releases when the
	 * cursor is in the game
	 * @param absX The x coordinate relative to the window of the cursor
	 *             when it released
	 * @param absY The y coordinate relative to the window of the cursor
	 *             when it released
	 */
	public void onGlobalLeftRelease(int absX, int absY);

}
