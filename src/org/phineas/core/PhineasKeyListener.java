package org.phineas.core;

import java.awt.event.KeyEvent;

/**
 * Slightly simplified KeyListener for use in Phineas
 * @author Sam Pottinger
 */
public interface PhineasKeyListener
{
	/**
	 * Method to call when a key is pressed
	 * @param e Java native KeyEvent
	 */
	public void keyPressed(KeyEvent e);
	
	/**
	 * Method to call when a key is released
	 * @param e Java native KeyEvent
	 */
	public void keyReleased(KeyEvent e);
}
