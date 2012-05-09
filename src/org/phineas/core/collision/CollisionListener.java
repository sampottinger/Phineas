package org.phineas.core.collision;

public interface CollisionListener<A,B>
{
	public void onCollision(A firstInstance, B secondInstance);
}
