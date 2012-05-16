package org.phineas.core;

/**
 * Helper singleton that adds / removes an object to / from a GameFacade in all the ways it possibly can
 * given the interfaces the object implements
 * @author Sam Pottinger
 */
class GameEntityAdder 
{
	private static GameEntityAdder instance;
	
	/**
	 * Get access to a common instance of GameEntityAdder
	 * @return Shared instance of this singleton
	 */
	public static GameEntityAdder getInstance()
	{
		if(instance == null)
			instance = new GameEntityAdder();
		return instance;
	}
	
	private GameEntityAdder()
	{}
	
	/**
	 * Adds the given entity to the given game facade in all the ways it possibly can
	 * given the interfaces the entity implements
	 * @param targetFacade The facade to add the entity to
	 * @param targetEntity The entity to add
	 */
	public void addEntity(PhineasGameFacade targetFacade, Object targetEntity)
	{
		// Compound
		if(PhineasCompoundGameObject.class.isInstance(targetEntity))
			for(Object o : ((PhineasCompoundGameObject)targetEntity).getComponents())
				addEntity(targetFacade, o);
		
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
	public void removeEntity(PhineasGameFacade targetFacade, Object targetEntity)
	{
		// Compound
		if(PhineasCompoundGameObject.class.isInstance(targetEntity))
			for(Object o : ((PhineasCompoundGameObject)targetEntity).getComponents())
				removeEntity(targetFacade, o);
				
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
