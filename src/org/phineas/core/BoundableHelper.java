package org.phineas.core;

/**
 * Behavioral construct with common computational routines
 * for boundable objects
 * @author Sam Pottinger
 */
class BoundableHelper {
	
	private static BoundableHelper instance = null;
	
	/**
	 * Get access to a common instance of GameFacade
	 * @return Shared instance of this singleton
	 */
	public static BoundableHelper getInstance()
	{
		if(instance == null)
			instance = new BoundableHelper();
		return instance;
	}
	
	private BoundableHelper()
	{}
	
	public boolean isInBounds(PhineasBoundable target, int x, int y)
	{
		int leftX;
		int rightX;
		int topY;
		int bottomY;
		
		leftX = target.getX();
		rightX = leftX + target.getWidth();
		topY = target.getY();
		bottomY = target.getY() + target.getHeight();
		
		return x >= leftX && x <= rightX && y >= topY && y <= bottomY;
	}
}
