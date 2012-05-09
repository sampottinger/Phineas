package org.phineas.contrib;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.phineas.core.StepListener;

/**
 * Simple logo-like robot for Phineas
 * @author Sam Pottinger
 */
public class Phindroid extends PlaceableGraphic implements StepListener
{
	private static final double DEFAULT_ROTATION = -Math.PI/2;
	private static final int ROBOT_DEPTH = 0;
	private static final Color DEFAULT_COLOR = Color.WHITE;
	private static final int RADIUS = 20;
	private static final int DIAMETER = RADIUS * 2;
	private static final int X_DRAW_OFFSET = RADIUS;
	private static final int Y_DRAW_OFFSET = RADIUS;
	private static final double LINEAR_SPEED = 0.05; // pixels / millisecond
	private static final double ROT_SPEED = 0.01; // radians / millisecond
	
	private Position position;
	private double rotation; // clockwise
	private Color color;
	private Queue<PhindroidAction> actions = new ConcurrentLinkedQueue<PhindroidAction>();
	
	private double pixelsLeftToMoveForward = 0;
	private double positiveRadiansLeftToTurn = 0;
	private double negativeRadiansLeftToTurn = 0;
	
	/**
	 * Creates a new Phindroid at the given position with default 
	 * rotation (facing due north) and color
	 * @param x The x position of this robot
	 * @param y The y position of this robot
	 */
	public Phindroid(int x, int y)
	{
		position = new Position(x, y);
		rotation = DEFAULT_ROTATION;
		color = DEFAULT_COLOR;
	}
	
	/**
	 * Creates a new Phindroid at the given position with default
	 * rotation (facing due north) and color
	 * @param newPosition The position at which this placed
	 */
	public Phindroid(Position newPosition)
	{
		position = newPosition;
		rotation = DEFAULT_ROTATION;
		color = DEFAULT_COLOR;
	}
	
	/**
	 * Creates a new Phindroid at the given position with the
	 * given color and default rotation
	 * @param x The x position of this robot
	 * @param y The y position of this robot
	 * @param startingRotation The rotation of this robot (in radians)
	 * @param newColor The color of this robot
	 */
	public Phindroid(int x, int y, Color newColor)
	{
		position = new Position(x, y);
		rotation = DEFAULT_ROTATION;
		color = newColor;
	}
	
	/**
	 * Creates a new Phindroid at the given position with default
	 * rotation and the given color
	 * @param newPosition The position at which this placed
	 * @param startingRotation The rotation of this robot (in radians)
	 * @param newColor The color of this robot
	 */
	public Phindroid(Position newPosition, Color newColor)
	{
		position = newPosition;
		rotation = DEFAULT_ROTATION;
		color = newColor;
	}
	
	/**
	 * Creates a new Phindroid at the given position with the
	 * given color and default rotation
	 * @param x The x position of this robot
	 * @param y The y position of this robot
	 * @param newColor The color of this robot
	 * @param startingRotation The rotation of this robot (in radians)
	 */
	public Phindroid(int x, int y, Color newColor, double startingRotation)
	{
		position = new Position(x, y);
		rotation = startingRotation;
		color = newColor;
	}
	
	/**
	 * Creates a new Phindroid at the given position with default
	 * rotation and the given color
	 * @param newPosition The position at which this placed
	 * @param newColor The color of this robot
	 * @param startingRotation The rotation of this robot (in radians)
	 */
	public Phindroid(Position newPosition, Color newColor, float startingRotation)
	{
		position = newPosition;
		rotation = startingRotation;
		color = newColor;
	}
	
	/**
	 * Creates a new Phindroid at the given position with the
	 * given rotation
	 * @param x The x position of this robot
	 * @param y The y position of this robot
	 * @param startingRotation The rotation of this robot (in radians)
	 */
	public Phindroid(int x, int y, double startingRotation)
	{
		position = new Position(x, y);
		rotation = startingRotation;
		color = DEFAULT_COLOR;
	}
	
	/**
	 * Creates a new Phindroid at the given position with default
	 * rotation (facing due north)
	 * @param newPosition The position at which this placed
	 * @param startingRotation The rotation of this robot (in radians)s
	 */
	public Phindroid(Position newPosition, double startingRotation)
	{
		position = newPosition;
		rotation = startingRotation;
		color = DEFAULT_COLOR;
	}
	
	/**
	 * Have the Phindroid move forward the given number of steps
	 * @param numSteps The number of steps forward this Phindroid should move
	 */
	public void moveForward(int numSteps)
	{
		actions.add(new PhindroidAction(numSteps, 0));
	}
	
	/**
	 * Have the Phindroid rotate the given number of radians clockwise
	 * @param radians The number of radians to have this Phindroid rotate clockwise
	 */
	public void rotateClockwise(double radians)
	{
		actions.add(new PhindroidAction(0, radians));
	}
	
	/**
	 * Start running the given action. Stops executing current
	 * action if any.
	 * @param nextAction The action this Phindroid should start
	 */
	private void startAction(PhindroidAction nextAction)
	{
		double radians = nextAction.getRadians();
		
		pixelsLeftToMoveForward = nextAction.getForwardSteps();
		
		if(radians >= 0)
		{
			positiveRadiansLeftToTurn = radians;
			negativeRadiansLeftToTurn = 0;
		}
		else
		{
			positiveRadiansLeftToTurn = 0;
			negativeRadiansLeftToTurn = -radians;
		}
	}


	@Override
	public void draw(Graphics2D target)
	{
		int x = position.getX();
		int y = position.getY();
		
		int circleX = x - X_DRAW_OFFSET;
		int circleY = y - Y_DRAW_OFFSET;
		
		int xLineEndOffset = (int)(RADIUS * Math.cos(rotation));
		int yLineEndOffset = (int)(RADIUS * Math.sin(rotation));
		
		int xLineEnd = x + xLineEndOffset;
		int yLineEnd = y + yLineEndOffset;
		
		// Draw circle
		target.setColor(color);
		Ellipse2D.Double circle = new Ellipse2D.Double(circleX, circleY, DIAMETER, DIAMETER);
		target.draw(circle);
		
		// Draw line
		target.drawLine(x, y, xLineEnd, yLineEnd);
	}

	@Override
	public int getDepth()
	{
		return ROBOT_DEPTH;
	}

	@Override
	public synchronized void onStep(long milliseconds)
	{
		double absRad;
		double dRad = 0;
		double dX = 0;
		double dY = 0;
		double distance = 0;
		boolean idle = true;
		
		// See if we need to move linearly
		if(pixelsLeftToMoveForward > 0)
		{
			distance = milliseconds * LINEAR_SPEED;
			if(distance > pixelsLeftToMoveForward)
				distance = pixelsLeftToMoveForward;
			
			dX += Math.cos(rotation) * distance;
			dY += Math.sin(rotation) * distance;
			pixelsLeftToMoveForward -= distance;
			
			idle = false;
		}
		
		// Calculate rotation
		if(positiveRadiansLeftToTurn > 0)
		{
			dRad += milliseconds * ROT_SPEED;
			if(dRad > positiveRadiansLeftToTurn)
				dRad = positiveRadiansLeftToTurn;
			
			positiveRadiansLeftToTurn -= dRad;
			
			idle = false;
		}
		if(negativeRadiansLeftToTurn > 0)
		{
			absRad = milliseconds * ROT_SPEED;
			if(absRad > negativeRadiansLeftToTurn);
				absRad = negativeRadiansLeftToTurn;
			
			negativeRadiansLeftToTurn -= absRad;
			dRad -= absRad;
			
			idle = false;
		}
		
		if(idle && !actions.isEmpty())
		{
			PhindroidAction nextAction = actions.remove();
			startAction(nextAction);
		}
		else
		{
			rotation += dRad;
			position = position.addPosition(dX, dY);
		}
	}
	
	@Override
	public synchronized void setPosition(Position newPosition)
	{
		position = newPosition;
	}

}
