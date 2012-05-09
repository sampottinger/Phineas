package org.phineas.core.collision;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Test description for use by client to indicate that Phineas should
 * look for collisions between objects of two classes and call a listener
 * when it sees one
 * @author Sam Pottinger
 */
public class CollisionTest<A, B>
{	
	private Collection<CollisionListener<A, B>> listeners;
	private ClassPair<A, B> pair;
	
	@SuppressWarnings("rawtypes")
	private CollisionTestStrategy strategy;
	
	/**
	 * Creates a new test between the classes described in the new pair
	 * @param newStrategy The strategy to use for testing for collisions
	 * @param newPair Pair describing classes involved in this collision
	 */
	@SuppressWarnings("rawtypes")
	public CollisionTest(CollisionTestStrategy newStrategy, ClassPair<A, B> newPair)
	{
		strategy = newStrategy;
		pair = newPair;
		listeners = Collections.synchronizedList(new ArrayList<CollisionListener<A, B>>());
	}
	
	/**
	 * Get all of the listeners on this test
	 * @return Iterable over this test's listeners
	 */
	public Iterable<CollisionListener<A, B>> getListeners()
	{
		return listeners;
	}
	
	/**
	 * Adds a new listener to this test
	 * @param newListener The new listener to fire upon a collision
	 */
	public void attachListener(CollisionListener<A, B> newListener)
	{
		listeners.add(newListener);
	}
	
	/**
	 * Have this test no longer update the given listener upon a collision
	 * @param oldListener The listener to stop informing
	 */
	public void detachListener(CollisionListener<A, B> oldListener)
	{
		listeners.remove(oldListener);
	}

	/**
	 * Get the pair of objects this collision test is betwen
	 * @return CollisionPair Get the pair of objects this test is between
	 */
	public ClassPair<A, B> getPair()
	{
		return pair;
	}

	/**
	 * Get the strategy this test is using to test for collisions
	 * @return The strategy that this test is using to test for collisions
	 */
	@SuppressWarnings("rawtypes")
	public CollisionTestStrategy getStrategy()
	{
		return strategy;
	}

}
