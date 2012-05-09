package org.phineas.core.collision;

/**
 * Simple structure with a pair of generic instances
 * @author Sam Pottinger
 *
 * @param <A> The class of the first instance in the pair
 * @param <B> The class of the second instance in the pair
 */
public class InstancePair<A, B>
{
	private A firstInstance;
	private B secondInstance;
	private ClassPair<A, B> originalCollisionPair;
	
	/**
	 * Create a new pair
	 * @param first The first instance in the pair
	 * @param second The second instance in the pair
	 * @param collisonPair The original class pair this instance pair belongs to
	 */
	public InstancePair(A first, B second, ClassPair<A, B> collisionPair)
	{
		firstInstance = first;
		secondInstance = second;
		originalCollisionPair = collisionPair;
	}
	
	/**
	 * Get the first instance in this pair
	 * @return First instance in this pair
	 */
	public A getFirst()
	{
		return firstInstance;
	}

	/**
	 * Get the second instance in this pair
	 * @return Second instance in this pair
	 */
	public B getSecond()
	{
		return secondInstance;
	}
	
	/**
	 * Get the original collision pair this instance pair was created for
	 * @return The original class pair that describes the classes in this instance pair
	 */
	public ClassPair<A, B> getCollisionPair()
	{
		return originalCollisionPair;
	}
}
