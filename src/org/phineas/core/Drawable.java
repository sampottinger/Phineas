package org.phineas.core;

import java.awt.Graphics2D;

/**
 * Describes any object capable of drawing itself on a Graphics2D context
 * @author Sam Pottinger
 */
public abstract class Drawable implements Comparable<Drawable>
{
	public static int DEFAULT_DEPTH = 0;

	/**
	 * Draws this object to the given Graphics2D context
	 * @param target The context / canvas / target upon which to draw this object
	 */
	public abstract void draw(Graphics2D target);
	
	/**
	 * Get how many "layers" deep this is drawn (deeper depths are drawn first)
	 * A drawable with a getDepth() less than another will be drawn on top
	 * @return Integer depth indicating at what "layer" this drawable is drawn.
	 */
	public int getDepth()
	{
		return DEFAULT_DEPTH;
	}
	
	@Override
	public int compareTo(Drawable other)
	{
		int depthCompare;
		Integer otherDepth = ((Drawable)other).getDepth();
		Integer myDepth = getDepth();
		depthCompare = myDepth.compareTo(otherDepth);
		if(depthCompare != 0)
			return depthCompare;
		else if(hashCode() == other.hashCode())
			return 0;
		else
			return 1;
	}
}
