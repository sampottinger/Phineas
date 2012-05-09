package org.phineas.core;

/**
 * Interface for objects that must be updated at each iteration
 * of the game loop 
 * @author Sam Pottinger
 */
public interface StepListener {
	
	/**
	 * Method to call at each iteration of the game loop
	 * @param milliseconds The number of milliseconds since 
	 * 					   the last iteration of the game loop
	 */
	public void onStep(long milliseconds);

}
