package org.phineas.contrib;

import java.awt.Graphics2D;
import java.util.ArrayList;

import org.phineas.core.Drawable;

/**
 * Super class for objects that are collections of drawable components
 * @author Sam Pottinger
 */
public class CompoundDrawable extends Drawable
{
	ArrayList<Drawable> components;
	
	/**
	 * Creates a new drawable collection without any components
	 */
	public CompoundDrawable()
	{
		components = new ArrayList<Drawable>();
	}
	
	@Override
	public void draw(Graphics2D target)
	{
		for(Drawable component : components)
			component.draw(target);
	}
	
	/**
	 * Adds a new component to this compound drawable
	 * @param component The new component to add to this collection
	 */
	protected void addComponent(Drawable component)
	{
		components.add(component);
	}
	
	/**
	 * Removes a component from this compound drawable
	 * @param component The component to remove from this collection
	 */
	protected void removeComponent(Drawable component)
	{
		components.remove(component);
	}
	
}
