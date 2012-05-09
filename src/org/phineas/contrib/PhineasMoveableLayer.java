package org.phineas.contrib;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.phineas.core.Placeable;

/**
 * Collection of objects that can be moved together
 * @author Sam Pottinger
 */
public class PhineasMoveableLayer
{
	private Collection<Placeable> contents;
	
	/**
	 * Create a new empty moveable layer
	 */
	public PhineasMoveableLayer()
	{
		contents = Collections.synchronizedList(new ArrayList<Placeable>());
	}
	
	/**
	 * Add a new object to manage with this moveable layer
	 * @param target The placeable to add to this layer
	 */
	public void addPlaceable(Placeable target)
	{
		contents.add(target);
	}

	/**
	 * Remove this object from this moveable layer
	 * @param target The placeable to remove from this layer
	 */
	public void removePlaceable(Placeable target)
	{
		contents.remove(target);
	}
	
	/**
	 * Move all of the members of this collection horizontally by the given value
	 * @param deltaX The number of pixels (horizontally) to move this by
	 *               (can be positive or negative)
	 */
	public void moveX(int deltaX)
	{
		for(Placeable target : contents)
			target.setX(target.getX() + deltaX);
	}
	
	/**
	 * Move all of the members of this collection vertically by the given value
	 * @param deltaX The number of pixels (vertically) to move this by
	 *               (can be positive or negative)
	 */
	public void moveY(int deltaY)
	{
		for(Placeable target : contents)
			target.setY(target.getY() + deltaY);
	}
}
