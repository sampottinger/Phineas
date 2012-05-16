package org.phineas.core;

import java.awt.Graphics2D;

/**
 * Decorator around drawables that makes them comparable on their drawing depths
 * @author Sam Pottinger
 */
class DepthComparedDrawableDecorator implements Comparable<PhineasDrawable>, PhineasDrawable
{
	private final PhineasDrawable target;
	
	/**
	 * Creates a new decorator around the given target
	 * @param newTarget The drawable to wrap
	 */
	public DepthComparedDrawableDecorator(PhineasDrawable newTarget)
	{
		target = newTarget;
	}
	
	/**
	 * Get the drawable that this decorator is wrapping
	 * @return The original drawable that this decorator encloses
	 */
	public PhineasDrawable getInnerDrawable()
	{
		return target;
	}
	
	@Override
	public void draw(Graphics2D graphicsTarget) 
	{
		target.draw(graphicsTarget);
	}

	@Override
	public int getDepth() 
	{
		return target.getDepth();
	}

	@Override
	public int compareTo(PhineasDrawable other) 
	{
		int depthCompare;
		Integer otherDepth = other.getDepth();
		Integer myDepth = getDepth();
		depthCompare = myDepth.compareTo(otherDepth);
		if(depthCompare != 0)
			return -depthCompare;
		else if(hashCode() == other.hashCode())
			return 0; // Return equal
		else
			return 1; // Return greater than
	}
}
