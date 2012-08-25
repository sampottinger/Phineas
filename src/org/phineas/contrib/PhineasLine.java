package org.phineas.contrib;

import java.awt.Color;
import java.awt.Graphics2D;

import org.phineas.core.PhineasBoundable;
import org.phineas.core.PhineasDrawable;

/**
 * Simple graphical line that integrates into the Phineas game framework
 * @author Sam Pottinger
 */
public class PhineasLine implements PhineasBoundable, PhineasDrawable
{
	private int smallerX;
	private int smallerY;
	private int width;
	private int height;
	private int startX;
	private int endX;
	private int startY;
	private int endY;
	private int depth;
	private Color color;
	
	/**
	 * Creates a new line between the given start and end coordinates
	 * @param newStartX The x position that the line starts at
	 * @param newStartY The y position that the line starts at
	 * @param newEndX The x position that the line ends at
	 * @param newEndY The y position that the line ends at
	 * @param newColor The color of the line
	 */
	public PhineasLine(int newStartX, int newStartY, int newEndX, int newEndY, Color newColor)
	{
		startX = newStartX;
		startY = newStartY;
		endX = newEndX;
		endY = newEndY;
		color = newColor;
		depth = PhineasDrawable.DEFAULT_DEPTH;
		
		calculateBoundingBox();
	}

	/**
	 * Creates a new line between the given start and end coordinates
	 * @param newStartX The x position that the line starts at
	 * @param newStartY The y position that the line starts at
	 * @param newEndX The x position that the line ends at
	 * @param newEndY The y position that the line ends at
	 * @param newColor The color of the line
	 * @param newDepth The depth at which the line will be drawn
	 *                 (affects drawing order)
	 */
	public PhineasLine(int newStartX, int newStartY, int newEndX, int newEndY, 
			Color newColor, int newDepth)
	{
		startX = newStartX;
		startY = newStartY;
		endX = newEndX;
		endY = newEndY;
		color = newColor;
		depth = newDepth;
		
		calculateBoundingBox();
	}
	
	private void calculateBoundingBox()
	{
		smallerX = Math.min(startX, endX);
		smallerY = Math.min(startY, endY);
		
		width = Math.max(startX, endX) - smallerX;
		height = Math.max(startY, endY) - smallerY;
	}

	@Override
	public void draw(Graphics2D target)
	{
		target.setColor(color);
		target.drawLine(startX, startY, endX, endY);
	}

	@Override
	public int getDepth()
	{
		return depth;
	}

	@Override
	public int getX()
	{
		return smallerX;
	}

	@Override
	public int getY()
	{
		return smallerY;
	}

	@Override
	public int getWidth()
	{
		return width;
	}

	@Override
	public int getHeight()
	{
		return height;
	}

}
