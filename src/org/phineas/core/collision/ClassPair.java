package org.phineas.core.collision;

import java.lang.reflect.ParameterizedType;

/**
 * Record of classes that
 * @author apottinger
 *
 * @param <A>
 * @param <B>
 */
public final class ClassPair <A, B>
{
	private Class<A> firstClass;
	private Class<B> secondClass;
	
	@SuppressWarnings("unchecked")
	public ClassPair()
	{
		ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
		firstClass = (Class<A>) parameterizedType.getActualTypeArguments()[0];
		secondClass = (Class<B>) parameterizedType.getActualTypeArguments()[1];
	}
	
	public Class<A> getFirstClass()
	{
		return firstClass;
	}
	
	public Class<B> getSecondClass()
	{
		return secondClass;
	}
	
	/**
	 * Get these two instances ordered in a pair
	 * @param a The first instance to put into a new instance pair
	 * @param b The second instance to put into a new instance pair
	 * @return Sorted pair of instances
	 */
	public InstancePair<A, B> getPair(Object a, Object b)
	{
		return new InstancePair<A, B>(getFirst(a, b), getSecond(a, b), this);
	}
	
	/**
	 * Get the object whose class is listed first in this pair
	 * @param a The first candidate whose class must be in this pair
	 * @param b The second candidate whose class must be in this pair
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private A getFirst(Object a, Object b)
	{
		if(a.getClass().equals(getFirstClass()))
			return (A)a;
		else if(b.getClass().equals(getFirstClass()))
			return (A)b;
		else
			throw new RuntimeException("Neither instances are of the right class type to be the first in this pair");
	}

	/**
	 * Get the object whose class is listed second in this pair
	 * @param a The first candidate whose class must be in this pair
	 * @param b The second candidate whose class must be in this pair
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private B getSecond(Object a, Object b)
	{
		if(a.getClass().equals(getSecondClass()))
			return (B)a;
		else if(b.getClass().equals(getSecondClass()))
			return (B)b;
		else
			throw new RuntimeException("Neither instances are of the right class type to be the second in this pair");
	}
	
	/**
	 * Determines if these are the same pair but their classes are in reverse order
	 * @param otherPair The pair to test
	 * @return true if the pairs have the same classes but in opposite order, false otherwise
	 */
	@SuppressWarnings("rawtypes")
	public boolean isBackwardsTo(ClassPair otherPair)
	{
		return otherPair.getSecondClass().equals(getFirstClass()) && otherPair.getFirstClass().equals(getSecondClass());
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(Object other)
	{
		if(!getClass().equals(other.getClass()))
			return false;
		
		ClassPair otherPair = (ClassPair) other;
		
		boolean equal = otherPair.getFirstClass().equals(getFirstClass()) && otherPair.getSecondClass().equals(getSecondClass());
		equal = equal || isBackwardsTo(otherPair);
		return equal;
	}

}
