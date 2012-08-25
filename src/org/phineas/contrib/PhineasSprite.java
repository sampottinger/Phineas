package org.phineas.contrib;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;

import org.phineas.core.PhineasBoundable;
import org.phineas.core.PhineasDrawable;
import org.phineas.core.PhineasPlaceable;

/**
 * Simple 2D drawable image that can be used in Phineas
 * @author Sam Pottinger
 */
public class PhineasSprite implements PhineasBoundable, PhineasPlaceable, PhineasDrawable, Cloneable
{
	private Image image;
	private int depth;
	private int x;
	private int y;
	private int imageWidth;
	private int imageHeight;
	
	/**
	 * Loads a sprite from the given location
	 * @param loc The location to load the given sprite from
	 * @param newX The starting x position of this sprite
	 * @throws IOException 
	 */
	public PhineasSprite(int newX, int newY, String loc) throws IOException
	{
		image = PhineasSpriteLoader.getInstance().loadSprite(loc);
		imageWidth = image.getWidth(null);
		imageHeight = image.getHeight(null);
		depth = PhineasDrawable.DEFAULT_DEPTH;
		x = newX;
		y = newY;
	}
	
	/**
	 * Loads a sprite from a given location
	 * @param loc The location from which this sprite is loaded
	 * @param newDepth How far deep this sprite will be drawn (objects
	 *                 of a deeper depth will be drawn first)
	 * @throws IOExecption
	 */
	public PhineasSprite(int newX, int newY, String loc, int newDepth) throws IOException
	{
		image = PhineasSpriteLoader.getInstance().loadSprite(loc);
		imageWidth = image.getWidth(null);
		imageHeight = image.getHeight(null);
		depth = newDepth;
		x = newX;
		y = newY;
	}
	
	/**
	 * Creates a new sprite with the given image
	 * @param newImage The image that his sprite uses when being drawn
	 */
	public PhineasSprite(int newX, int newY, Image newImage)
	{
		image = newImage;
		imageWidth = image.getWidth(null);
		imageHeight = image.getHeight(null);
		depth = PhineasDrawable.DEFAULT_DEPTH;
		x = newX;
		y = newY;
	}
	
	/**
	 * Creates a new sprite with the given location and depth
	 * @param newImage The image that his sprite uses when being drawn
	 * @param newDepth How far deep this sprite will be drawn (objects
	 *                 of a deeper depth will be drawn first)
	 * @throws IOExecption
	 */
	public PhineasSprite(int newX, int newY, Image newImage, int newDepth)
	{
		image = newImage;
		imageWidth = image.getWidth(null);
		imageHeight = image.getHeight(null);
		depth = newDepth;
		x = newX;
		y = newY;
	}
	
	@Override
	public int getDepth()
	{
		return depth;
	}

	@Override
	public void draw(Graphics2D target)
	{
		target.drawImage(image, getX(), getY(), null);
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
		return imageWidth;
	}
	
	@Override
	public int getHeight()
	{
		return imageHeight;
	}
	
	/**
	 * Sets this sprite's new x location
	 * @param newX The new x coordinate of this sprite
	 */
	public void setX(int newX)
	{
		x = newX;
	}
	
	/**
	 * Sets this sprite's new y location
	 * @param newY The new y coordinate of this sprite
	 */
	public void setY(int newY)
	{
		y = newY;
	}
	
	@Override
	public PhineasSprite clone()
	{
		return new PhineasSprite(getX(), getY(), image, getDepth());
	}

	protected void setImage(Image newImage)
	{
		image = newImage;
	}
	
	protected void setImage(String loc) throws IOException
	{
		image = PhineasSpriteLoader.getInstance().loadSprite(loc);
	}
}
