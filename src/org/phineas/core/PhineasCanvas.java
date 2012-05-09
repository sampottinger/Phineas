package org.phineas.core;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.concurrent.Semaphore;

/**
 * Drawing surface for the Phineas game framework
 * @author Sam Pottinger
 */
public class PhineasCanvas extends Canvas
{
	private static final long serialVersionUID = 6758626030324493864L;
	
	private BufferStrategy bufferStrategy;
	private Semaphore checkoutSemaphore;
	private int width;
	private int height;
	private int horizontalOffset;
	private int verticalOffset;

	/**
	 * Create a new canvas to draw on
	 * @param width The horiztonal size in pixels of this canvas
	 * @param height The vertical size in pixels of this canvas
	 * @param horizontalOffset The horizontal offset in pixels for this canvas
	 * @param verticalOffset The vertical offset (from the top) in pixels for this canvas
	 */
	public PhineasCanvas(int newWidth, int newHeight, int newHorizontalOffset, int newVerticalOffset)
	{
		super();
		width = newWidth;
		height = newHeight;
		horizontalOffset = newHorizontalOffset;
		verticalOffset = newVerticalOffset;
	}
	
	/**
	 * Sets this canvas up within its parent
	 */
	public void setUp()
	{
		// Establish size
		setBounds(horizontalOffset, verticalOffset, width, height);
		
		// Take control of drawing
		setIgnoreRepaint(true);
		
		// Setup accelerated graphics
		createBufferStrategy(2);
		bufferStrategy = getBufferStrategy();
		
		// Make graphics available concurrently
		checkoutSemaphore = new Semaphore(1);
	}
	
	/**
	 * Checkout a graphics object to draw on that includes a clear screen step
	 * @note Only one copy of this graphics object can be out at once
	 * @return Graphics object to draw on or null if a graphics object is already checked out
	 */
	public Graphics2D checkoutGraphics()
	{
		return checkoutGraphics(true);
	}
	
	/**
	 * Checkout a graphics object to draw on
	 * @param clearScreen If true, the graphics object returned will already have a rectangle
	 *                             drawn to it that clears the screen
	 * @return Graphics object to draw on or null if a graphics object is not currently available
	 */
	public Graphics2D checkoutGraphics(boolean clearScreen)
	{
		Graphics2D graphics;
		
		// Wait until graphics becomes available
		try
		{
			checkoutSemaphore.acquire();
		} 
		catch (InterruptedException e)
		{
			return null;
		}
		
		// Get graphics
		try
		{
			graphics = (Graphics2D) bufferStrategy.getDrawGraphics();
		}
		catch (IllegalStateException e)
		{return null;}
		
		// Clear screen if requested
		if(clearScreen)
		{
			graphics.setColor(Color.black);
			graphics.fillRect(0,0,width,height);
		}
		
		return graphics;
	}
	
	/**
	 * Checks a graphics object back in after drawing for rendering
	 * @param graphics The graphics object to check back in
	 */
	public void checkinGraphics(Graphics2D graphics)
	{	
		try
		{
			graphics.dispose();
			bufferStrategy.show();
			
			// Allow checkout of graphics again
			checkoutSemaphore.release();
		}
		catch(IllegalStateException e)
		{ }
	}

	/**
	 * Sets the dimensions of this canvas
	 * @param newWidth
	 * @param newHeight
	 */
	public void setDimensions(int newWidth, int newHeight)
	{
		width = newWidth;
		height = newHeight;
		setBounds(horizontalOffset, verticalOffset, width, height);
		setSize(new Dimension(newWidth, newHeight));
		invalidate();
	}
}
