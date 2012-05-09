package org.phineas.contrib;

import java.awt.Color;
import java.awt.Graphics2D;

import org.phineas.core.Drawable;

/**
 * Simple block for testing purposes
 * @author Sam Pottinger
 */
public class PhineasBlock extends Drawable
{
	private static final int BLOCK_WIDTH = 20; // pixels
	private static final int BLOCK_HEIGHT = 20; // pixels
	
	private int x;
	private int y;
	private Color color;
	
	/**
	 * Creates a new block at the given location
	 * @param newX The x position for this block
	 * @param newY The y position for this block
	 * @param newColor The color of this block
	 */
	public PhineasBlock(int newX, int newY, Color newColor)
	{
		x = newX;
		y = newY;
		color = newColor;
	}
	
	/**
	 * Creates a new white block at the given location
	 * @param newX The x position for this block
	 * @param newY The y position for this block
	 */
	public PhineasBlock(int newX, int newY)
	{
		x = newX;
		y = newY;
		color = Color.WHITE;
	}
	
	/**
	 * Get the x position at which this block is to be drawn
	 * @return The x coordinate of this block
	 */
	protected int getX()
	{
		return x;
	}
	
	/**
	 * Get the y position on which this block is to be drawn
	 * @return The y coordinate of this block
	 */
	protected int getY()
	{
		return y;
	}
	
	/**
	 * Sets the new x position of this block
	 * @param newX The new x position of this block
	 */
	protected void setX(int newX)
	{
		x = newX;
	}
	
	/**
	 * Sets the new y position of this block
	 * @param newY The new y position of this block
	 */
	protected void setY(int newY)
	{
		y = newY;
	}

	@Override
	public void draw(Graphics2D target)
	{
		target.setColor(color);
		target.drawRect(x, y, BLOCK_WIDTH, BLOCK_HEIGHT);
	}

}
