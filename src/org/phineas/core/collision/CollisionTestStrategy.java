package org.phineas.core.collision;

/**
 * General interface for strategies capable to seeing if two
 * objects collided
 * @author Sam Pottinger
 */
public interface CollisionTestStrategy<A, B>
{

	/**
	 * Tests to see if a collided with b
	 * @param pair Pair of instances that collided
	 * @return
	 */
	public boolean collided(InstancePair<A, B> pair);

}
