package org.phineas.contrib;

import org.phineas.core.Drawable;

/**
 * Generic interface for drawable objects that can be placed at x and y coordinates
 * @author Sam Pottinger
 */
public abstract class PlaceableGraphic extends Drawable
{
	/**
	 * Have this drawable now be drawn at the given position
	 * @param newPosition the position this drawable is to be drawn at
	 */
	public abstract void setPosition(Position position);
}
