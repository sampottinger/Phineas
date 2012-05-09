package org.phineas.contrib;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

import org.phineas.core.Boundable;
import org.phineas.core.Drawable;
import org.phineas.core.Placeable;

/**
 * Simple generic rectangle
 * @author Sam Pottinger
 */
public class PhineasRectangle extends Drawable implements Boundable, Placeable
{
	int x;
	int y;
	int width;
	int height;
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
	}
	
	/**
	 * Create a new rectangle
	 * @param newX The x (horizontal) position of the rectangle
	 * @param newY The y (vertical) position of the rectangle
	 * @param newWidth The width of the rectangle in pixels
	 * @param newHeight The height of the rectangle in pixels
	 * @param newColor The color of the new rectangle
	 * @param newAlpha The alpha value to use for this rectangle
	 */
	public PhineasRectangle(int newX, int newY, int newWidth, int newHeight, Color newColor, float newAlpha)
	{
		x = newX;
		y = newY;
		width = newWidth;
		height = newHeight;
		color = newColor;
		alpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, newAlpha);
		opaqueAlpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);
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
	public void setAlpha(float newAlpha)
	{
		alpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, newAlpha);
	}
	
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

}
