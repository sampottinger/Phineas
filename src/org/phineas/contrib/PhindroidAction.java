package org.phineas.contrib;

/**
 * A structure representing an action that a Phindroid can take
 * @author Sam Pottinger
 */
public class PhindroidAction
{
	private int forwardSteps;
	private double radians;
	
	/**
	 * Creates a new action record
	 * @param newForwardSteps How many steps forward this target Phindroid should take
	 * @param newRadians How many radians the target Phindroid should turn
	 */
	public PhindroidAction(int newForwardSteps, double newRadians)
	{
		forwardSteps = newForwardSteps;
		radians = newRadians;
	}
	
	/**
	 * Determine how many radians the target Phindroid should turn
	 * @return Number of radians
	 */
	public double getRadians()
	{
		return radians;
	}
	
	/**
	 * Determine how many steps forward the target Phindroid should take
	 * @return Number of forward steps
	 */
	public int getForwardSteps()
	{
		return forwardSteps;
	}
}
