package org.phineas.core;

/**
 * Singleton manager that simplifies the game loop for Phineas projects
 * @author Sam Pottinger
 */
public class GameLoopManager
{
	private static final int DEFAULT_FPS = 40;
	private static final float MILLISECONDS_PER_SECOND = 1000;
	
	private boolean loopRunning;
	private boolean loopShouldBeRunning;
	private long lastLoopTime;
	private long targetFPS;
	private StepListener stepListener;
	
	/**
	 * Private constructor for this singleton
	 */
	public GameLoopManager()
	{
		loopRunning = false;
		loopShouldBeRunning = false;
		targetFPS = DEFAULT_FPS;
	}
	
	/**
	 * Set this game loop to use a new target FPS
	 * @param newFPS The new FPS for this game loop to target
	 */
	public void setFPS(int newFPS)
	{
		targetFPS = newFPS;
	}
	
	/**
	 * Starts the game loop. If it is already running, this does nothing.
	 * @param newStepListener The listener to inform that a step occurred
	 * @return true if loop started, false if it was already running
	 */
	public boolean startLoop(StepListener newStepListener)
	{
		// Make sure loop is not already running
		if(loopRunning)
			return false;
		
		// Save the listener
		stepListener = newStepListener;
		
		// Update state
		loopRunning = true;
		loopShouldBeRunning = true;
		lastLoopTime = System.currentTimeMillis();
		
		// Start thread
		new Thread()
		{
			public void run() {gameLoop();}
		}.start();
		
		return true;
	}
	
	/**
	 * Stops the game loop thread and waits to return until it actually stops.
	 * If it is not running, this does nothing.
	 */
	public void endLoop()
	{
		endLoop(true);
	}
	
	/**
	 * Stops the game loop thread. If it is not running, this does nothing.
	 * @param block If true, this will wait to return until the game loop actually ends.
	 */
	public void endLoop(boolean block)
	{
		if(!loopShouldBeRunning)
			return;
		
		loopShouldBeRunning = false;
		
		if(block)
			while(loopRunning){try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
	}
	
	/**
	 * Actual game loop
	 */
	private void gameLoop()
	{
		long delta;
		long sleepTime;
		
		while(loopShouldBeRunning)
		{
			// Figure out how long its been since we have been here
			delta = System.currentTimeMillis() - lastLoopTime;
			lastLoopTime = System.currentTimeMillis();
			
			// Let the listener know
			stepListener.onStep(delta);
			
			// Aim for FPS
			sleepTime = (long)(MILLISECONDS_PER_SECOND / targetFPS - delta);
			if(sleepTime > 0)
				try { 
					Thread.sleep(sleepTime); 
				} catch (Exception e) {}
		}
		loopRunning = false;
	}
}
