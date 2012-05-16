package org.phineas.core;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Manager for data regarding a Phineas project
 * @author Sam Pottinger
 */
class GameModelManager
{
	private static GameModelManager instance = null;
	
	private Collection<DepthComparedDrawableDecorator> drawables;
	private Collection<PhineasStepListener> stepListeners;
	private Collection<PhineasKeyListener> keyListeners;
	private Collection<PhineasHoverListenerNanny> hoverListeners;
	private Collection<PhineasClickListener> clickListeners;
	private Collection<PhineasGlobalClickListener> globalClickListeners;
	private Collection<PhineasGlobalMouseMovementListener> globalMouseMovementListeners;
	private Collection<PhineasScrollWheelListener> mouseScrollListeners;

	/**
	 * Gets a shared instance of GameModelManager
	 * @return Shared instance of this singleton
	 */
	public static GameModelManager getInstance() 
	{
		if(instance == null)
			instance = new GameModelManager();
		return instance;
	}
	
	/**
	 * Private constructor for this singleton
	 */
	private GameModelManager()
	{
		drawables = new ConcurrentSkipListSet<DepthComparedDrawableDecorator>();
		stepListeners = new CopyOnWriteArrayList<PhineasStepListener>();
		keyListeners = new CopyOnWriteArrayList<PhineasKeyListener>();
		hoverListeners = new CopyOnWriteArrayList<PhineasHoverListenerNanny>();
		clickListeners = new CopyOnWriteArrayList<PhineasClickListener>();
		globalClickListeners = new CopyOnWriteArrayList<PhineasGlobalClickListener>();
		globalMouseMovementListeners = new CopyOnWriteArrayList<PhineasGlobalMouseMovementListener>();
		mouseScrollListeners = new CopyOnWriteArrayList<PhineasScrollWheelListener>();
	}

	/**
	 * Adds a new drawable entity to this game
	 * @param drawable The entity to add to this game
	 */
	public void addDrawable(PhineasDrawable drawable)
	{
		drawables.add(new DepthComparedDrawableDecorator(drawable));
	}

	/**
	 * Remove the given drawable entity from this game
	 * @param drawableToDelete The entity to have this game no longer draw
	 */
	public void removeDrawable(PhineasDrawable drawableToDelete)
	{
		Iterator<DepthComparedDrawableDecorator> itr = drawables.iterator();
		while(itr.hasNext())
			if(itr.next().getInnerDrawable() == drawableToDelete)
				itr.remove();
	}
	
	/**
	 * Gets access to all of the drawable entities that this game is managing
	 * @return Iterable over all of the drawable objects this game is managing
	 */
	public Iterable<PhineasDrawable> getDrawables()
	{
		// TODO: This is pretty darn messy and should move to another class
		return new Iterable<PhineasDrawable>()
		{

			@Override
			public Iterator<PhineasDrawable> iterator()
			{
				final Iterator<DepthComparedDrawableDecorator> nativeItr = drawables.iterator();
				return new Iterator<PhineasDrawable>()
				{

					@Override
					public boolean hasNext()
					{
						return nativeItr.hasNext();
					}

					@Override
					public PhineasDrawable next()
					{
						return nativeItr.next();
					}

					@Override
					public void remove()
					{
						nativeItr.remove();
					}
					
				};
			}
	
		};
	}
	
	/**
	 * Adds a new listener to this game that will be called on each
	 * iteration of the game loop
	 * @param newListener The listener to attach to this game
	 */
	public void attachStepListener(PhineasStepListener newListener)
	{
		stepListeners.add(newListener);
	}
	
	/**
	 * Removes the given step listener from this game such that
	 * it will no longer be called on each iteration of this game's
	 * game loop
	 * @param targetListener The listener to detach from this game
	 */
	public void detachStepListener(PhineasStepListener targetListener)
	{
		stepListeners.remove(targetListener);
	}

	/**
	 * Get all of the step listeners that have registered with this game
	 * @return Iterable over the objects that have subscribed to this game's
	 *         step event
	 */
	public Iterable<PhineasStepListener> getStepListeners()
	{
		return stepListeners;
	}
	
	/**
	 * Adds a new PhineasKeyListener to this game
	 * @param newListener The listener to add to this game
	 */
	public void attachKeyListener(PhineasKeyListener newListener)
	{
		keyListeners.add(newListener);
	}
	
	/**
	 * Removes a PhineasKeyListener from this game
	 * @param newListener The listener to add to this game
	 */
	public void detachKeyListener(PhineasKeyListener targetListener)
	{
		keyListeners.remove(targetListener);
	}

	/**
	 * Get all of the key listeners registered for this game
	 * @return Iterable over the objects that have subscribed to this game's
	 *         key press and release events
	 */
	public Iterable<PhineasKeyListener> getKeyListeners()
	{
		return keyListeners;
	}

	/**
	 * Adds a new hover listener to this game
	 * @param newListener The listener to add to this game
	 */
	public void attachHoverListener(PhineasHoverListener newListener) 
	{
		hoverListeners.add(new PhineasHoverListenerNanny(newListener));
	}

	/**
	 * Removes a hover listener from this game
	 * @param targetListener the listener to remove from this game
	 */
	public void detachHoverListener(PhineasHoverListener targetListener) 
	{
		PhineasHoverListenerNanny currentDecorator;
		
		Iterator<PhineasHoverListenerNanny> decoratedListeners = hoverListeners.iterator();
		while(decoratedListeners.hasNext())
		{
			currentDecorator = decoratedListeners.next();
			if(currentDecorator.getInnerListener() == targetListener)
				hoverListeners.remove(currentDecorator);
		}
	}
	
	/**
	 * Get all of the hover listeners registered for this game
	 * @return Iterable over the objects that have subscribed to
	 *         the games mouse movements, specifically for hover
	 *         events
	 */
	public Iterable<PhineasHoverListener> getHoverListeners()
	{
		
		return new Iterable<PhineasHoverListener>()
		{
			@Override
			public Iterator<PhineasHoverListener> iterator() {
				
				final Iterator<PhineasHoverListenerNanny> targetIterator = hoverListeners.iterator();
				
				return new Iterator<PhineasHoverListener>()
				{

					@Override
					public boolean hasNext()
					{
						return targetIterator.hasNext();
					}

					@Override
					public PhineasHoverListener next()
					{
						return targetIterator.next().getInnerListener();
					}

					@Override
					public void remove()
					{
						targetIterator.remove();
					}
			
				};
			}
		};
	}

	/**
	 * Get a list of all of the objects listening for hover events on this game
	 * with their nannys still attached
	 * @return Iterable over all of the registered hover listeners with decoration
	 */
	public Iterable<PhineasHoverListenerNanny> getDecoratedHoverListeners()
	{
		return hoverListeners;
	}

	/**
	 * Adds a new PhineasCLickListener to this game
	 * @param newListener The listener to add to this game
	 */
	public void attachClickListener(PhineasClickListener newListener)
	{
		clickListeners.add(newListener);
	}

	/**
	 * Removes a PhineasClickListener from this game
	 * @param newListener The listener to remove from this game
	 */
	public void detachClickListener(PhineasClickListener targetListener)
	{
		clickListeners.remove(targetListener);
	}

	/**
	 * Get a list of all of the objects listening for click events on this game
	 * @return Iterable over all of the registered click listeners
	 */
	public Iterable<PhineasClickListener> getClickListeners()
	{
		return clickListeners;
	}
	
	/**
	 * Adds a new PhineasCLickListener to this game
	 * @param newListener The listener to add to this game
	 */
	public void attachGlobalClickListener(PhineasGlobalClickListener newListener)
	{
		globalClickListeners.add(newListener);
	}
	
	/**
	 * Removes a PhineasGlobalClickListener from this game
	 * @param newListener The listener to remove from this game
	 */
	public void detachGlobalClickListener(PhineasGlobalClickListener targetListener)
	{
		globalClickListeners.remove(targetListener);
	}

	/**
	 * Get an iterable over all of the objects listeneing for global click
	 * events on this game (clicks not just on themselves)
	 * @return Iterable over all of this game's global click listeners
	 */
	public Iterable<PhineasGlobalClickListener> getGlobalClickListeners()
	{
		return globalClickListeners;
	}
	
	/**
	 * Adds a new PhineasGlobalMouseMovementListener to this game
	 * @param newListener The listener to add to this game
	 */
	public void attachGlobalMouseMovementListener(PhineasGlobalMouseMovementListener newListener)
	{
		globalMouseMovementListeners.add(newListener);
	}
	
	/**
	 * Removes a PhineasGlobalMouseMovementListener from this game
	 * @param newListener The listener to remove from this game
	 */
	public void detachGlobalMouseMovementListener(PhineasGlobalMouseMovementListener targetListener)
	{
		globalMouseMovementListeners.remove(targetListener);
	}

	/**
	 * Get an iterable over all of the objects listeneing for global mouse
	 * movement events on this game
	 * @return Iterable over all of this game's global mouse movement listeners
	 */
	public Iterable<PhineasGlobalMouseMovementListener> getGlobalMouseMovementListeners()
	{
		return globalMouseMovementListeners;
	}
	
	/**
	 * Adds a new mouse scroll listener to this game
	 * @param targetListener The listener to add to the game
	 */
	public void attachMouseScrollListener(PhineasScrollWheelListener targetListener)
	{
		mouseScrollListeners.add(targetListener);
	}
	
	/**
	 * Detaches an existing mouse scroll listener from this game
	 * @param targetListener The listener to remove from this game
	 */
	public void detachMouseScrollListener(PhineasScrollWheelListener targetListener)
	{
		mouseScrollListeners.remove(targetListener);
	}

	/**
	 * Get an iterable over all of the objects listeneing to global mouse
	 * scrollwheel rotations
	 * @return
	 */
	public Iterable<PhineasScrollWheelListener> getMouseScrollListeners() 
	{
		return mouseScrollListeners;
	}
}
