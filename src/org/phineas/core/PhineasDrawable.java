package org.phineas.core;

import java.awt.Graphics2D;

/**
 * Describes any object capable of drawing itself on a Graphics2D context
 * @author Sam Pottinger
 */
public interface PhineasDrawable
{
	public static final int DEFAULT_DEPTH = 0;

	/**
	 * Draws this object to the given Graphics2D context
	 * @param target The context / canvas / target upon which to draw this object
	 */
	public void draw(Graphics2D target);
	
	/**
	 * Get how many "layers" deep this is drawn (deeper depths are drawn first)
	 * A drawable with a getDepth() less than another will be drawn on top
	 * @return Integer depth indicating at what "layer" this drawable is drawn.
	 */
	public int getDepth();
}
