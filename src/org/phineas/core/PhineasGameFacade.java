package org.phineas.core;

/**
 * Singleton facade for the simple Phineas game framework
 * @author Sam Pottinger
 */
public class PhineasGameFacade
{
	private static PhineasGameFacade instance = null;
	
	private GamePresenter gamePresenter;
	private GameModelManager gameModel;
	
	private boolean displayingGame;
	private boolean runningGame;
	
	/**
	 * Get access to a common instance of GameFacade
	 * @return Shared instance of this singleton
	 */
	public static PhineasGameFacade getInstance()
	{
		if(instance == null)
			instance = new PhineasGameFacade();
		return instance;
	}
	
	/**
	 * Hidden constructor for this singleton
	 */
	private PhineasGameFacade()
	{
		gamePresenter = GamePresenter.getInstance();
		gameModel = GameModelManager.getInstance();
		displayingGame = false;
		runningGame = false;
	}
	
	/**
	 * Show the game window and game loop
	 * @throws PhineasException Throws exception if game is already running
	 */
	public void startGame() throws PhineasException
	{
		// Check game state
		if(displayingGame)
			throw new PhineasException("Game is already running");
		
		// Show the game window
		gamePresenter.showWindow();
		
		// Start the mainloop
		gamePresenter.startMainLoop();
		
		// Update game state
		displayingGame = true;
		runningGame = true;
	}
	
	/**
	 * Temporarily pause this game's game loop
	 * @throws PhineasException Thrown if game was already paused or never started
	 */
	public void pauseGame() throws PhineasException
	{
		// Check game state
		if(!displayingGame)
			throw new PhineasException("Game was not started or was started and stopped");
		if(!runningGame)
			throw new PhineasException("Game is already paused");
		
		// Stop main loop to pause
		gamePresenter.stopMainLoop();
		
		// Update game state
		runningGame = false;
	}
	
	/**
	 * Restart this game's game loop
	 * @throws PhineasException Thrown if game was not paused or never started
	 */
	public void resumeGame() throws PhineasException
	{
		// Check game state
		if(!displayingGame)
			throw new PhineasException("Game was not started or was started and stopped");
		if(runningGame)
			throw new PhineasException("Game is already running");
		
		// Start main loop again
		gamePresenter.startMainLoop();
		
		// Update game state
		runningGame = true;
	}
	
	/**
	 * Stop this game's game loop and close window
	 * @throws PhineasException Thrown in response to underlying state exception or if the game was not
	 *                          running / paused
	 */
	public void stopGame() throws PhineasException
	{
		// Check game state
		if(!displayingGame)
			throw new PhineasException("Game is not currently running");
		
		// TODO
		try
		{
			gamePresenter.stopMainLoop();
			gamePresenter.hideWindow();
		}
		catch(IllegalStateException e)
		{
			throw new PhineasException("Game failed to stop due to state exception: " + e.getMessage());
		}
		
		// Update game state
		runningGame = false;
		displayingGame = false;
	}
	
	/**
	 * Add a new drawable entity to display in the game
	 * @param newEntity The game entity to be displayed
	 */
	public void addDrawable(PhineasDrawable newEntity)
	{
		gameModel.addDrawable(newEntity);
	}
	
	/**
	 * Stop the given entity from being rendered inside of this game
	 * @param targetEntity The drawable entity to stop displaying
	 */
	public void removeDrawable(PhineasDrawable targetEntity)
	{
		gameModel.removeDrawable(targetEntity);
	}
	
	/**
	 * Adds a new step listener to this game, calling that listener
	 * on each iteration of this game's game loop
	 * @param newListener The listener to add to this game
	 */
	public void attachStepListener(PhineasStepListener newListener)
	{
		gameModel.attachStepListener(newListener);
	}
	
	/**
	 * Detaches the given listener from this game such that
	 * it will no longer be called on each iteration of this
	 * game's game loop
	 * @param targetListener The listener to detach from this game
	 */
	public void detachStepListener(PhineasStepListener targetListener)
	{
		gameModel.detachStepListener(targetListener);
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
	
	/**
	 * Attach a new scroll wheel listener to this game
	 * @param targetListener The new listener to add to this game
	 */
	public void attachMouseScrollListener(PhineasScrollWheelListener targetListener)
	{
		gameModel.attachMouseScrollListener(targetListener);
	}
	
	/**
	 * Removes this scroll wheel listener from this game
	 * @param targetListener The listener to remove from this game
	 */
	public void detachMouseScrollListener(PhineasScrollWheelListener targetListener)
	{
		gameModel.detachMouseScrollListener(targetListener);
	}
	
	/**
	 * Adds the given entity to this game facade in all the ways it possibly can
	 * given the interfaces the entity implements
	 * @param targetEntity The entity to add
	 */
	public void addEntity(Object targetEntity)
	{
		GameEntityAdder.getInstance().addEntity(this, targetEntity);
	}
	
	/**
	 * Removes the given entity to this game facade in all the ways it possibly can
	 * given the interfaces the entity implements
	 * @param targetEntity The entity to add
	 */
	public void removeEntity(Object targetEntity)
	{
		GameEntityAdder.getInstance().removeEntity(this, targetEntity);
	}
}
