package org.phineas.contrib;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

import org.phineas.core.PhineasBoundable;
import org.phineas.core.PhineasDrawable;
import org.phineas.core.PhineasPlaceable;

/**
 * Simple generic rectangle
 * @author Sam Pottinger
 */
public class PhineasRectangle implements PhineasBoundable, PhineasPlaceable, PhineasDrawable
{
	int x;
	int y;
	int width;
	int height;
	int depth;
	AlphaComposite alpha;
	AlphaComposite opaqueAlpha;
	Color color;

	/**
	 * Create a new opaque rectangle
	 * @param newX The x (horizontal) position of the rectangle
	 * @param newY The y (vertical) position of the rectangle
	 * @param newWidth The width of the rectangle in pixels
	 * @param newHeight The height of the rectangle in pixels
	 * @param newColor The color of the new rectangle
	 */
	public PhineasRectangle(int newX, int newY, int newWidth, int newHeight, Color newColor)
	{
		x = newX;
		y = newY;
		width = newWidth;
		height = newHeight;
		color = newColor;
		opaqueAlpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);
		alpha = opaqueAlpha;
		depth = PhineasDrawable.DEFAULT_DEPTH;
	}
	
	/**
	 * Create a new opaque rectangle
	 * @param newX The x (horizontal) position of the rectangle
	 * @param newY The y (vertical) position of the rectangle
	 * @param newWidth The width of the rectangle in pixels
	 * @param newHeight The height of the rectangle in pixels
	 * @param newColor The color of the new rectangle
	 * @param newDepth The numerical drawing depth to assign to this rectangle
	 *                 to determine its relative drawing order
	 */
	public PhineasRectangle(int newX, int newY, int newWidth, int newHeight, Color newColor, int newDepth)
	{
		x = newX;
		y = newY;
		width = newWidth;
		height = newHeight;
		color = newColor;
		opaqueAlpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);
		alpha = opaqueAlpha;
		depth = newDepth;
	}
	
	/**
	 * Set the color of this rectangle
	 * @param newColor The new color to use for this rectangle
	 */
	public void setColor(Color newColor)
	{
		color = newColor;
	}
	
	/**
	 * Set a new alpha level for this rectangle
	 * @param newAlpha Thew new alpha level for this rectangle
	 */
	/*public void setAlpha(float newAlpha)
	{
		alpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, newAlpha);
	}*/
	
	@Override
	public void setX(int newX)
	{
		x = newX;
	}

	@Override
	public void setY(int newY)
	{
		y = newY;
	}

	@Override
	public int getX() 
	{
		return x;
	}

	@Override
	public int getY()
	{
		return y;
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

	@Override
	public void draw(Graphics2D target)
	{
		target.setColor(color);
		target.setComposite(alpha);
		target.fillRect(x, y, width, height);
		target.setComposite(opaqueAlpha);
	}

	@Override
	public int getDepth() 
	{
		return depth;
	}

}
