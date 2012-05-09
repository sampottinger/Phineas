package org.phineas.core;

/**
 * Singleton facade for the simple Phineas game framework
 * @author Sam Pottinger
 */
public class GameFacade
{
	private static GameFacade instance = null;
	
	private boolean displayingGame;
	private boolean runningGame;
	private FearlessGameFacade fearlessGameFacade;
	
	
	/**
	 * Get access to a common instance of GameFacade
	 * @return Shared instance of this singleton
	 */
	public static GameFacade getInstance()
	{
		if(instance == null)
			instance = new GameFacade();
		return instance;
	}
	
	/**
	 * Private constructor for this singleton
	 */
	private GameFacade()
	{
		displayingGame = false;
		runningGame = false;
		fearlessGameFacade = new FearlessGameFacade();
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
		
		// Actually execute
		fearlessGameFacade.startGame();
		
		// Update game state
		displayingGame = true;
		runningGame = true;
	}
	
	/**
	 * Temporarily pause this game's game loop
	 * @throws PhineasException Thrown if game is stopped or already paused
	 */
	public void pauseGame() throws PhineasException
	{
		// Check game state
		if(!displayingGame)
			throw new PhineasException("Game was not started or was started and stopped");
		if(!runningGame)
			throw new PhineasException("Game is already paused");
		
		// Actually execute
		fearlessGameFacade.pauseGame();
		
		// Update game state
		runningGame = false;
	}
	
	/**
	 * Restart this game's game loop
	 * @throws PhineasException Thrown if game is not paused
	 */
	public void resumeGame() throws PhineasException
	{
		// Check game state
		if(!displayingGame)
			throw new PhineasException("Game was not started or was started and stopped");
		if(runningGame)
			throw new PhineasException("Game is already running");
		
		// Actually execute
		fearlessGameFacade.resumeGame();
		
		// Update game state
		runningGame = true;
	}
	
	/**
	 * Stop this game's game loop and close window
	 * @throws PhineasException Thrown if game is not running
	 */
	public void stopGame() throws PhineasException
	{
		// Check game state
		if(!displayingGame)
			throw new PhineasException("Game is not currently running");
		
		// Actually execute
		fearlessGameFacade.stopGame();
		
		// Update game state
		runningGame = false;
		displayingGame = false;
	}
	
	/**
	 * Add a new entity to display in the game
	 * @param newEntity The game entity to be displayed
	 */
	public void addDrawable(Drawable newEntity)
	{
		fearlessGameFacade.addDrawable(newEntity);
	}
	
	/**
	 * Stop the given entity from being rendered inside of this game
	 * @param targetEntity The entity to stop displaying
	 */
	public void removeDrawable(Drawable targetEntity)
	{
		fearlessGameFacade.removeDrawable(targetEntity);
	}
	
	/**
	 * Adds a new step listener to this game, calling that listener
	 * on each iteration of this game's game loop
	 * @param newListener The listener to add to this game
	 */
	public void attachStepListener(StepListener newListener)
	{
		fearlessGameFacade.attachStepListener(newListener);
	}
	
	/**
	 * Detaches the given listener from this game such that
	 * it will no longer be called on each iteration of this
	 * game's game loop
	 * @param targetListener The listener to detach from this game
	 */
	public void detachStepListener(StepListener targetListener)
	{
		fearlessGameFacade.detachStepListener(targetListener);
	}
	
	/**
	 * Adds this entity to the game as whatever it is capable of being
	 * (Drawable, StepListener, etc.)
	 * @param entity The entity to add to this game
	 */
	public void addEntity(Object entity)
	{
		fearlessGameFacade.addEntity(entity);
	}
	
	/**
	 * Adds a new PhineasKeyListener to this game
	 * @param newListener The listener to add to this game
	 */
	public void attachKeyListener(PhineasKeyListener newListener)
	{
		fearlessGameFacade.attachKeyListener(newListener);
	}
	
	/**
	 * Removes a PhineasKeyListener from this game
	 * @param newListener The listener to remove from this game
	 */
	public void detachKeyListener(PhineasKeyListener targetListener)
	{
		fearlessGameFacade.detachKeyListener(targetListener);
	}
	
	/**
	 * Adds a new PhineasHoverListener to this game
	 * @param newListener The listener to add to this game
	 */
	public void attachHoverListener(PhineasHoverListener newListener)
	{
		fearlessGameFacade.attachHoverListener(newListener);
	}
	
	/**
	 * Removes a PhineasHoverListener from this game
	 * @param newListener The listener to remove from this game
	 */
	public void detachHoverListener(PhineasHoverListener targetListener)
	{
		fearlessGameFacade.detachHoverListener(targetListener);
	}
	
	/**
	 * Sets the dimensions of the game window
	 * @param newWidth the new width of the game window in pixels
	 * @param newHeight the new height of the game window in pixels
	 */
	public void setDimensions(int newWidth, int newHeight)
	{
		fearlessGameFacade.setDimensions(newWidth, newHeight);
	}
	
	/**
	 * Adds a new PhineasCLickListener to this game
	 * @param newListener The listener to add to this game
	 */
	public void attachClickListener(PhineasClickListener newListener)
	{
		fearlessGameFacade.attachClickListener(newListener);
	}

	/**
	 * Removes a PhineasClickListener from this game
	 * @param newListener The listener to remove from this game
	 */
	public void detachClickListener(PhineasClickListener targetListener)
	{
		fearlessGameFacade.detachClickListener(targetListener);
	}
	
	/**
	 * Removes this entity to the game
	 * @param entity The entity to remove from this game
	 */
	public void removeEntity(Object entity)
	{
		fearlessGameFacade.removeEntity(entity);
	}
	
	/**
	 * Adds a new PhineasGlobalCLickListener to this game
	 * @param newListener The listener to add to this game
	 */
	public void attachGlobalClickListener(PhineasGlobalClickListener newListener)
	{
		fearlessGameFacade.attachGlobalClickListener(newListener);
	}

	/**
	 * Removes a PhineasGlobalClickListener from this game
	 * @param newListener The listener to remove from this game
	 */
	public void detachGlobalClickListener(PhineasGlobalClickListener targetListener)
	{
		fearlessGameFacade.detachGlobalClickListener(targetListener);
	}
	
	/**
	 * Adds a new PhineasGlobalCLickListener to this game
	 * @param newListener The listener to add to this game
	 */
	public void attachGlobalMouseMovementListener(PhineasGlobalMouseMovementListener newListener)
	{
		fearlessGameFacade.attachGlobalMouseMovementListener(newListener);
	}

	/**
	 * Removes a PhineasGlobalMouseMovementListener from this game
	 * @param newListener The listener to remove from this game
	 */
	public void detachGlobalClickListener(PhineasGlobalMouseMovementListener targetListener)
	{
		fearlessGameFacade.detachGlobalMouseMovementListener(targetListener);
	}
}
