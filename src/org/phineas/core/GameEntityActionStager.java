package org.phineas.core;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Helper singleton that adds / removes an object to / from a GameFacade in all the ways it possibly can
 * given the interfaces the object implements
 * @author Sam Pottinger
 */
class GameEntityActionStager 
{
	private static GameEntityActionStager instance;
	
	private ConcurrentLinkedQueue<Object> deleteQueue = new ConcurrentLinkedQueue<Object>();
	private ConcurrentLinkedQueue<Object> addQueue = new ConcurrentLinkedQueue<Object>();
	
	/**
	 * Get access to a common instance of GameEntityAdder
	 * @return Shared instance of this singleton
	 */
	public static GameEntityActionStager getInstance()
	{
		if(instance == null)
			instance = new GameEntityActionStager();
		return instance;
	}
	
	/**
	 * Adds an object to a list of objects that need to be removed to maintain thread saftey
	 * (will be removed on the completeAllStagedActions function)
	 * @param targetEntity The entity to remove
	 */
	public void removeEntity(Object targetEntity)
	{
		deleteQueue.add(targetEntity);
	}
	
	
	/**
	 * Adds an object to a list of objects that need to be added to maintain thread saftey
	 * (will be added on the completeAllStagedActions function)
	 * @param targetEntity The entity to add
	 */
	public void addEntity(Object targetEntity) {
		addQueue.add(targetEntity);
	}
	
	/**
	 * Remove all objects that have been staged to be deleted
	 */
	public void completeAllStagedActions(GameModelManager targetManager)
	{
		targetManager.lockIterators();
		
		while(!deleteQueue.isEmpty())
			actuallyRemoveEntity(targetManager, deleteQueue.remove());
		while(!addQueue.isEmpty())
			actuallyAddEntity(targetManager, addQueue.remove());
		
		targetManager.unlockIterators();
	}
	
	private GameEntityActionStager()
	{}
	
	/**
	 * Adds the given entity to the given game facade in all the ways it possibly can
	 * given the interfaces the entity implements (not thread safe)
	 * @param targetFacade The facade to add the entity to
	 * @param targetEntity The entity to add
	 */
	private void actuallyAddEntity(GameModelManager targetFacade, Object targetEntity)
	{
		// Compound
		if(PhineasCompoundGameObject.class.isInstance(targetEntity))
			for(Object o : ((PhineasCompoundGameObject)targetEntity).getComponents())
				actuallyAddEntity(targetFacade, o);
		
		// Drawable
		if(PhineasDrawable.class.isInstance(targetEntity))
			targetFacade.addDrawable((PhineasDrawable)targetEntity);
		
		// Step listener
		if(PhineasStepListener.class.isInstance(targetEntity))
			targetFacade.attachStepListener((PhineasStepListener)targetEntity);
		
		// Key listener
		if(PhineasKeyListener.class.isInstance(targetEntity))
			targetFacade.attachKeyListener((PhineasKeyListener)targetEntity);
		
		// Hover listener
		if(PhineasHoverListener.class.isInstance(targetEntity))
			targetFacade.attachHoverListener((PhineasHoverListener)targetEntity);
		
		// Click listener
		if(PhineasClickListener.class.isInstance(targetEntity))
			targetFacade.attachClickListener((PhineasClickListener)targetEntity);
		
		// Global click listener
		if(PhineasGlobalClickListener.class.isInstance(targetEntity))
			targetFacade.attachGlobalClickListener((PhineasGlobalClickListener)targetEntity);
		
		// Global mouse movement listener
		if(PhineasGlobalMouseMovementListener.class.isInstance(targetEntity))
			targetFacade.attachGlobalMouseMovementListener((PhineasGlobalMouseMovementListener)targetEntity);
		
		// Global mouse scroll wheel listener
		if(PhineasScrollWheelListener.class.isInstance(targetEntity))
			targetFacade.attachMouseScrollListener((PhineasScrollWheelListener)targetEntity);
	}
	
	/**
	 * Removes the given entity to the given game facade in all the ways it possibly can
	 * given the interfaces the entity implements
	 * @param targetFacade The facade to remove the entity from
	 * @param targetEntity The entity to remove
	 */
	private void actuallyRemoveEntity(GameModelManager targetFacade, Object targetEntity)
	{
		// Compound
		if(PhineasCompoundGameObject.class.isInstance(targetEntity))
			for(Object o : ((PhineasCompoundGameObject)targetEntity).getComponents())
				actuallyRemoveEntity(targetFacade, o);
				
		// Drawable
		if(PhineasDrawable.class.isInstance(targetEntity))
			targetFacade.removeDrawable((PhineasDrawable)targetEntity);
		
		// Step listener
		if(PhineasStepListener.class.isInstance(targetEntity))
			targetFacade.detachStepListener((PhineasStepListener)targetEntity);
		
		// Key listener
		if(PhineasKeyListener.class.isInstance(targetEntity))
			targetFacade.detachKeyListener((PhineasKeyListener)targetEntity);
		
		// Hover listener
		if(PhineasHoverListener.class.isInstance(targetEntity))
			targetFacade.detachHoverListener((PhineasHoverListener)targetEntity);
		
		// Click listener
		if(PhineasClickListener.class.isInstance(targetEntity))
			targetFacade.detachClickListener((PhineasClickListener)targetEntity);
		
		// Global click listener
		if(PhineasGlobalClickListener.class.isInstance(targetEntity))
			targetFacade.detachGlobalClickListener((PhineasGlobalClickListener)targetEntity);
		
		// Global mouse movement listener
		if(PhineasGlobalMouseMovementListener.class.isInstance(targetEntity))
			targetFacade.detachGlobalMouseMovementListener((PhineasGlobalMouseMovementListener)targetEntity);
		
		// Global mouse scroll wheel listener
		if(PhineasScrollWheelListener.class.isInstance(targetEntity))
			targetFacade.detachMouseScrollListener((PhineasScrollWheelListener)targetEntity);
	}
}
