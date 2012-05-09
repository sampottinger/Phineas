package org.phineas.core;

/**
 * Facade for the simple Phineas game framework
 * that does not throw exceptions or handle thread saftey.
 * Used for "educational purposes" (read AP class)
 * @note Really, just use GameFacade if you know what is good for you
 * @author Sam Pottinger (shameful)
 */
public class FearlessGameFacade
{
	
	private GamePresenter gamePresenter;
	private GameModelManager gameModel;
	
	/**
	 * Protected constructor for this singleton
	 */
	public FearlessGameFacade()
	{
		gamePresenter = GamePresenter.getInstance();
		gameModel = GameModelManager.getInstance();
	}
	
	/**
	 * Show the game window and game loop
	 * @throws PhineasException Throws exception if game is already running
	 */
	public void startGame()
	{
		// Show the game window
		gamePresenter.showWindow();
		
		// Start the mainloop
		gamePresenter.startMainLoop();
	}
	
	/**
	 * Temporarily pause this game's game loop
	 */
	public void pauseGame()
	{
		gamePresenter.stopMainLoop();
	}
	
	/**
	 * Restart this game's game loop
	 */
	public void resumeGame()
	{
		gamePresenter.startMainLoop();
	}
	
	/**
	 * Stop this game's game loop and close window
	 */
	public void stopGame()
	{
		// TODO
		try
		{
			gamePresenter.stopMainLoop();
			gamePresenter.hideWindow();
		}
		catch(IllegalStateException e)
		{}
	}
	
	/**
	 * Add a new drawable entity to display in the game
	 * @param newEntity The game entity to be displayed
	 */
	public void addDrawable(Drawable newEntity)
	{
		gameModel.addDrawable(newEntity);
	}
	
	/**
	 * Stop the given entity from being rendered inside of this game
	 * @param targetEntity The drawable entity to stop displaying
	 */
	public void removeDrawable(Drawable targetEntity)
	{
		gameModel.removeDrawable(targetEntity);
	}
	
	/**
	 * Adds a new step listener to this game, calling that listener
	 * on each iteration of this game's game loop
	 * @param newListener The listener to add to this game
	 */
	public void attachStepListener(StepListener newListener)
	{
		gameModel.attachStepListener(newListener);
	}
	
	/**
	 * Detaches the given listener from this game such that
	 * it will no longer be called on each iteration of this
	 * game's game loop
	 * @param targetListener The listener to detach from this game
	 */
	public void detachStepListener(StepListener targetListener)
	{
		gameModel.detachStepListener(targetListener);
	}
	
	/**
	 * Adds this entity to the game as whatever it is capable of being
	 * (Drawable, StepListener, etc.)
	 * @param entity The entity to add to this game
	 */
	public void addEntity(Object entity)
	{
		// Drawable
		if(Drawable.class.isInstance(entity))
			addDrawable((Drawable)entity);
		
		// Step listener
		if(StepListener.class.isInstance(entity))
			attachStepListener((StepListener)entity);
		
		// Key listener
		if(PhineasKeyListener.class.isInstance(entity))
			attachKeyListener((PhineasKeyListener)entity);
		
		// Hover listener
		if(PhineasHoverListener.class.isInstance(entity))
			attachHoverListener((PhineasHoverListener)entity);
		
		// Click listener
		if(PhineasClickListener.class.isInstance(entity))
			attachClickListener((PhineasClickListener)entity);
		
		// Global click listener
		if(PhineasGlobalClickListener.class.isInstance(entity))
			attachGlobalClickListener((PhineasGlobalClickListener)entity);
		
		// Global mouse movement listener
		if(PhineasGlobalMouseMovementListener.class.isInstance(entity))
			attachGlobalMouseMovementListener((PhineasGlobalMouseMovementListener)entity);
	}
	
	/**
	 * Removes this entity to the game
	 * @param entity The entity to remove from this game
	 */
	public void removeEntity(Object entity)
	{
		// Drawable
		if(Drawable.class.isInstance(entity))
			removeDrawable((Drawable)entity);
		
		// Step listener
		if(StepListener.class.isInstance(entity))
			detachStepListener((StepListener)entity);
		
		// Key listener
		if(PhineasKeyListener.class.isInstance(entity))
			detachKeyListener((PhineasKeyListener)entity);
		
		// Hover listener
		if(PhineasHoverListener.class.isInstance(entity))
			detachHoverListener((PhineasHoverListener)entity);
		
		// Click listener
		if(PhineasClickListener.class.isInstance(entity))
			detachClickListener((PhineasClickListener)entity);
		
		// Global click listener
		if(PhineasGlobalClickListener.class.isInstance(entity))
			detachGlobalClickListener((PhineasGlobalClickListener)entity);
		
		// Global mouse movement listener
		if(PhineasGlobalMouseMovementListener.class.isInstance(entity))
			detachGlobalMouseMovementListener((PhineasGlobalMouseMovementListener)entity);
	}
	
	/**
	 * Adds a new PhineasKeyListener to this game
	 * @param newListener The listener to add to this game
	 */
	public void attachKeyListener(PhineasKeyListener newListener)
	{
		gameModel.attachKeyListener(newListener);
	}
	
	/**
	 * Removes a PhineasKeyListener from this game
	 * @param newListener The listener to add to this game
	 */
	public void detachKeyListener(PhineasKeyListener targetListener)
	{
		gameModel.detachKeyListener(targetListener);
	}
	
	/**
	 * Sets the dimensions of the game window
	 * @param newWidth the new width of the game window in pixels
	 * @param newHeight the new height of the game window in pixels
	 */
	public void setDimensions(int newWidth, int newHeight)
	{
		gamePresenter.setDimensions(newWidth, newHeight);
	}

	/**
	 * Adds a new PhineasHoverListener to this game
	 * @param newListener The listener to add to this game
	 */
	public void attachHoverListener(PhineasHoverListener newListener)
	{
		gameModel.attachHoverListener(newListener);
	}

	/**
	 * Removes a PhineasHoverListener from this game
	 * @param newListener The listener to remove from this game
	 */
	public void detachHoverListener(PhineasHoverListener targetListener)
	{
		gameModel.detachHoverListener(targetListener);
	}
	
	/**
	 * Adds a new PhineasCLickListener to this game
	 * @param newListener The listener to add to this game
	 */
	public void attachClickListener(PhineasClickListener newListener)
	{
		gameModel.attachClickListener(newListener);
	}

	/**
	 * Removes a PhineasClickListener from this game
	 * @param newListener The listener to remove from this game
	 */
	public void detachClickListener(PhineasClickListener targetListener)
	{
		gameModel.detachClickListener(targetListener);
	}
	
	/**
	 * Adds a new PhineasGlobalCLickListener to this game
	 * @param newListener The listener to add to this game
	 */
	public void attachGlobalClickListener(PhineasGlobalClickListener newListener)
	{
		gameModel.attachGlobalClickListener(newListener);
	}

	/**
	 * Removes a PhineasGlobalClickListener from this game
	 * @param newListener The listener to remove from this game
	 */
	public void detachGlobalClickListener(PhineasGlobalClickListener targetListener)
	{
		gameModel.detachGlobalClickListener(targetListener);
	}
	
	/**
	 * Adds a new PhineasGlobalCLickListener to this game
	 * @param newListener The listener to add to this game
	 */
	public void attachGlobalMouseMovementListener(PhineasGlobalMouseMovementListener newListener)
	{
		gameModel.attachGlobalMouseMovementListener(newListener);
	}

	/**
	 * Removes a PhineasGlobalMouseMovementListener from this game
	 * @param newListener The listener to remove from this game
	 */
	public void detachGlobalMouseMovementListener(PhineasGlobalMouseMovementListener targetListener)
	{
		gameModel.detachGlobalMouseMovementListener(targetListener);
	}
	
	/**
	 * Set a new target FPS for the game to shoot for
	 * @param newFPS
	 */
	public void setFPS(int newFPS)
	{
		gamePresenter.setFPS(newFPS);
	}

}
