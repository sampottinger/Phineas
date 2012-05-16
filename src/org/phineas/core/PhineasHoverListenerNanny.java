package org.phineas.core;

public class PhineasHoverListenerNanny implements PhineasBoundable
{
	
	PhineasHoverListener target;
	boolean mouseIn;
	
	/**
	 * Creates a decorator for the given listener that allows it to be used with
	 * the GamePresenter
	 * @param newTarget The listener to wrap
	 */
	public PhineasHoverListenerNanny(PhineasHoverListener newTarget)
	{
		mouseIn = false;
		target = newTarget;
	}
	
	/**
	 * Indicate that the mouse is currently over
	 * this hover listener's bounding box
	 */
	public void reportMouseIn()
	{
		if(!mouseIn)
		{
			mouseIn = true;
			target.onEnter();
		}
	}
	
	/**
	 * Indicate that this mouse is currently outside
	 * this hover listener's bounding box
	 */
	public void reportMouseOut()
	{
		if(mouseIn)
		{
			mouseIn = false;
			target.onLeave();
		}
	}
	
	/**
	 * Get the listener that this nanny is minding
	 * @return The PhineasHoverListener this decorator is decorating
	 */
	public PhineasHoverListener getInnerListener()
	{
		return target;
	}

	@Override
	public int getX()
	{
		return target.getX();
	}

	@Override
	public int getY()
	{
		return target.getY();
	}

	@Override
	public int getWidth()
	{
		return target.getWidth();
	}

	@Override
	public int getHeight()
	{
		return target.getHeight();
	}
}
