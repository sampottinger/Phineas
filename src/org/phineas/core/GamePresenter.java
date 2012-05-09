package org.phineas.core;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Controller / presenter for GameLoopManager that simplifies game loop management and the step event
 * @author Sam Pottinger
 */
public class GamePresenter implements StepListener, WindowListener, KeyListener, MouseListener, MouseMotionListener
{	
	private static GamePresenter instance = null;
	
	private GameLoopManager loopManager;
	private GameView gameView;

	/**
	 * Gets a shared instance of GamePresenter
	 * @return Shared instance of this singleton
	 */
	public static GamePresenter getInstance() {
		if(instance == null)
			instance = new GamePresenter();
		return instance;
	}
	
	/**
	 * Private constructor for this singleton
	 */
	private GamePresenter()
	{
		loopManager = new GameLoopManager();
		gameView = new GameView(this, this, this, this);
	}

	/**
	 * Starts the Phineas main loop
	 */
	public void startMainLoop() 
	{
		loopManager.startLoop(this);
	}

	/**
	 * Stops the Phineas main loop thread and waits for it to finish
	 */
	public void stopMainLoop() 
	{
		loopManager.endLoop();
	}
	
	/**
	 * Sets the dimensions of the game window
	 * @param newWidth the new width of the game window in pixels
	 * @param newHeight the new height of the game window in pixels
	 */
	public void setDimensions(int newWidth, int newHeight)
	{
		gameView.setDimensions(newWidth, newHeight);
	}
	
	/**
	 * Checks the given hover listener to see if it is under the given
	 * mouse coordinates
	 * @param listener
	 * @param mouseX
	 * @param mouseY
	 */
	private void checkForHover(PhineasHoverListenerNanny listener, int mouseX,
			int mouseY)
	{
		BoundableHelper helper = BoundableHelper.getInstance();
		
		if(helper.isInBounds(listener, mouseX, mouseY))
			listener.reportMouseIn();
		else
			listener.reportMouseOut();
	}

	@Override
	public void onStep(long milliseconds)
	{
		Graphics2D graphics = null;
		Iterable<Drawable> drawables;
		
		GameModelManager gameModelManager = GameModelManager.getInstance();
		
		// Update those that are listening for the step event
		for(StepListener listener : gameModelManager.getStepListeners())
			listener.onStep(milliseconds);
		
		// Draw entities
		graphics = gameView.checkoutGraphics();
		if (graphics == null) return;
		drawables = gameModelManager.getDrawables();
		for(Drawable drawable : drawables)
			drawable.draw(graphics);
		gameView.checkinGraphics(graphics);
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		for(PhineasKeyListener listener : GameModelManager.getInstance().getKeyListeners())
			listener.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		for(PhineasKeyListener listener : GameModelManager.getInstance().getKeyListeners())
			listener.keyReleased(e);
	}

	
	public void showWindow()
	{
		gameView.show();
	}

	public void hideWindow()
	{
		gameView.hide();
	}

	@Override
	public void windowActivated(WindowEvent arg0)
	{}

	@Override
	public void windowClosed(WindowEvent arg0)
	{}

	@Override
	public void windowClosing(WindowEvent arg0)
	{
		gameView.dispose();
		loopManager.endLoop(true);
		System.exit(0);
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) 
	{}

	@Override
	public void windowDeiconified(WindowEvent arg0)
	{}

	@Override
	public void windowIconified(WindowEvent arg0)
	{}

	@Override
	public void windowOpened(WindowEvent arg0)
	{}

	@Override
	public void keyTyped(KeyEvent arg0) {}

	@Override
	public void mouseClicked(MouseEvent e)
	{}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e)
	{	
		BoundableHelper helper = BoundableHelper.getInstance();
		
		// Respond to left click
		if(e.getButton() == MouseEvent.BUTTON1)
		{
			int mouseX = e.getX();
			int mouseY = e.getY();
			
			// Respond to global listeners
			for(PhineasGlobalClickListener listener : GameModelManager.getInstance().getGlobalClickListeners())
				listener.onGlobalLeftDown(mouseX, mouseY);
			
			// Respond to object only listeners
			for(PhineasClickListener listener : GameModelManager.getInstance().getClickListeners())
			{
				if(helper.isInBounds(listener, mouseX, mouseY))
					listener.onLeftDown(mouseX - listener.getX(), mouseY - listener.getY());
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		int mouseX;
		int mouseY;
		BoundableHelper helper;
		
		helper = BoundableHelper.getInstance();
		mouseX = e.getX();
		mouseY = e.getY();
		
		if(e.getButton() == MouseEvent.BUTTON1)
		{
			// Respond to global listeners
			for(PhineasGlobalClickListener listener : GameModelManager.getInstance().getGlobalClickListeners())
				listener.onGlobalLeftRelease(mouseX, mouseY);
			
			// Respond to object only listeners
			for(PhineasClickListener listener : GameModelManager.getInstance().getClickListeners())
			{
				if(helper.isInBounds(listener, mouseX, mouseY))
					listener.onLeftRelease(mouseX - listener.getX(), e.getY() - listener.getY());
			}
		}
	}

	@Override
	public void mouseDragged(MouseEvent e)
	{
		mouseMoved(e);
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
		int mouseX;
		int mouseY;
		
		// Get position
		mouseX = e.getX();
		mouseY = e.getY();
		
		// Respond to global listeners
		for(PhineasGlobalMouseMovementListener listener : GameModelManager.getInstance().getGlobalMouseMovementListeners())
			listener.onMouseMove(mouseX, mouseY);
		
		// Check for hovering
		for(PhineasHoverListenerNanny listener : GameModelManager.getInstance().getDecoratedHoverListeners())
			checkForHover(listener, mouseX, mouseY);
	}
	
	/**
	 * Update the target FPS that the game should be shooting for
	 * @param newFPS the new FPS for the game to aim for
	 */
	public void setFPS(int newFPS)
	{
		loopManager.setFPS(newFPS);
	}

}
