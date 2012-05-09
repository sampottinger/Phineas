package org.phineas.contrib;

/**
 * Immutable object describing a 2D position
 * @author Sam Pottinger
 */
public class Position
{
	private double x;
	private double y;
	
	/**
	 * Creates a Position structure to represent the given coordinates
	 * @param newX The x coordinate of this position
	 * @param newY The y coordinate of this position
	 */
	public Position(double newX, double newY)
	{
		x = newX;
		y = newY;
	}
	
	/**
	 * Get the truncated x coordinate of this position
	 * @return The x coordinate of this position
	 */
	public int getX()
	{
		return (int)x;
	}
	
	/**
	 * Get the truncated y coordinate of this position
	 * @return The y coordinate of this position
	 */
	public int getY()
	{
		return (int)y;
	}
	
	/**
	 * Creates a new position by adding this position (with full percision)
	 * to other
	 * @param other The position to add to this one
	 * @return The resulting new position
	 */
	public Position addPosition(Position other)
	{
		return addPosition(other.getX(), other.getY());
	}

	/**
	 * Creates a new position by adding the given x and y offsets (with full percision)
	 * to this position
	 * @param dX The change in x position to apply
	 * @param dY The change in y position to apply
	 * @return Resulting position
	 */
	public Position addPosition(double dX, double dY)
	{
		return new Position(x+dX, y+dY);
	}

}
