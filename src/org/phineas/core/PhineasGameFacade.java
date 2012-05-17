package org.phineas.core;

/**
 * Singleton facade for the simple Phineas game framework
 * @author Sam Pottinger
 */
public class PhineasGameFacade
{
	private static PhineasGameFacade instance = null;
	
	private GamePresenter gamePresenter;
	
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
		displayingGame = false;
		runningGame = false;
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
	 * Set a new target FPS for the game to shoot for
	 * @param newFPS
	 */
	public void setFPS(int newFPS)
	{
		gamePresenter.setFPS(newFPS);
	}
	
	/**
	 * Adds the given entity to this game facade in all the ways it possibly can
	 * given the interfaces the entity implements
	 * @param targetEntity The entity to add
	 */
	public void addEntity(Object targetEntity)
	{
		GameEntityActionStager.getInstance().addEntity(targetEntity);
	}
	
	/**
	 * Removes the given entity to this game facade in all the ways it possibly can
	 * given the interfaces the entity implements
	 * @param targetEntity The entity to add
	 */
	public void removeEntity(Object targetEntity)
	{
		GameEntityActionStager.getInstance().removeEntity(targetEntity);
	}
}
