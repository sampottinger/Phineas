package org.phineas.core.collision;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Decorator around normal CollisionTests representing a type of collision
 * that Phineas is looking for and all of the individual tests registered for it
 * @author Sam Pottinger
 */
class InternalCollisionTestBucket
{
	@SuppressWarnings("rawtypes")
	private Collection<CollisionTest> tests;
	@SuppressWarnings("rawtypes")
	private CollisionTest exampleTest;
	
	/**
	 * Creates a new test that uses the following strategy and alerts the following listener
	 * @param test The test this decorator is decorating
	 */
	@SuppressWarnings("rawtypes")
	public InternalCollisionTestBucket(CollisionTest test)
	{
		tests = new ArrayList<CollisionTest>();
		tests.add(test);
		exampleTest = test;
	}
	
	/**
	 * Get the pair of classes that are being tested for in this collision test
	 * @return Structure representing collision pair for testing
	 */
	@SuppressWarnings("rawtypes")
	public ClassPair getCollisionPair()
	{
		return exampleTest.getPair();
	}
	
	/**
	 * Tests to see if A and B have collided and fire appropriate events
	 * if they have
	 * @param pair The pair of instances to check
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void testAndFire(InstancePair pair)
	{
		// Test for collision
		if(exampleTest.getStrategy().collided(pair))
			fireEvents(pair);
	}

	/**
	 * Actually fires events
	 * @param pair The pair of events to fire this for
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void fireEvents(InstancePair pair)
	{
		ClassPair targetCollisionPair = pair.getCollisionPair();
		ClassPair memberTestPair;
		Object first = pair.getFirst();
		Object second = pair.getSecond();
		Object effectiveFirst;
		Object effectiveSecond;
		
		for(CollisionTest test : tests)
		{
			// Sort out the order of the objects in the pair
			memberTestPair = test.getPair();
			if(!targetCollisionPair.equals(memberTestPair))
			{
				throw new RuntimeException("Incompatable pair types");
			}
			else if(targetCollisionPair.isBackwardsTo(memberTestPair))
			{
				effectiveFirst = second;
				effectiveSecond = first;
			}
			else
			{
				effectiveFirst = first;
				effectiveSecond = second;
			}
				
			// Call the listeners
			for(Object listener : test.getListeners())
			{
				((CollisionListener)listener).onCollision(effectiveFirst, effectiveSecond);
			}
		}
	}
}
