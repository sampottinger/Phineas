package org.phineas.contrib;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import org.phineas.core.PhineasDrawable;
import org.phineas.core.PhineasPlaceable;

/**
 * Colorable text abstraction for Phineas-powered applications
 * @author Sam Pottinger
 */
public class PhineasText implements PhineasPlaceable, PhineasDrawable
{
	private int x;
	private int y;
	private String text;
	private Color color;
	private int depth;
	private Font font;
	
	/**
	 * Creates a new text abstraction
	 * @param newX The x position of this text abstraction's upper left corner
	 * @param newY The y position of this text abstraction's upper left corner
	 * @param newText The actual text this object should print out
	 * @param newColor The color of that text
	 */
	public PhineasText(int newX, int newY, String newText, Color newColor)
	{
		x = newX;
		y = newY;
		text = newText;
		color = newColor;
		depth = PhineasDrawable.DEFAULT_DEPTH;
		setupDefaultFont();
	}
	
	/**
	 * Creates a new text abstraction
	 * @param newX The x position of this text abstraction's upper left corner
	 * @param newY The y position of this text abstraction's upper left corner
	 * @param newText The actual text this object should print out
	 * @param newColor The color of that text
	 * @param newDepth The depth the text should be drawn at (affecting drawing order)
	 */
	public PhineasText(int newX, int newY, String newText, Color newColor, int newDepth)
	{
		x = newX;
		y = newY;
		text = newText;
		color = newColor;
		depth = newDepth;
		setupDefaultFont();
	}

	/**
	 * Updates the text that this object should display
	 * @param newText The new text this object should display
	 */
	public void setText(String newText)
	{
		text = newText;
	}
	
	/**
	 * Sets a new color this object's text should be drawn in
	 * @param newColor The new color for this object to write text with
	 */
	public void setColor(Color newColor)
	{
		color = newColor;
	}
	
	/**
	 * Sets a new font for this object's text to be drawn in
	 * @param newFont This object's new font
	 */
	public void setFont(Font newFont)
	{
		font = newFont;
	}
	
	/**
	 * Sets the depth that this text should be drawn at
	 * @param newDepth The new drawing depth for this text (affects drawing order)
	 */
	public void setDepth(int newDepth)
	{
		depth = newDepth;
	}
	
	/**
	 * Sets this text to use a reasonable default font
	 */
	private void setupDefaultFont()
	{
		font = new Font(Font.SANS_SERIF, Font.PLAIN, 12);
	}
	
	@Override
	public void draw(Graphics2D target)
	{
		target.setColor(color);
		target.setFont(font);
		target.drawString(text, getX(), getY());
	}

	@Override
	public int getDepth()
	{
		return depth;
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

}
