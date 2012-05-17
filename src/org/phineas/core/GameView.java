package org.phineas.core;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Facade for GUI elements for Phineas game
 * @author Sam Pottinger
 */
class GameView {
	
	private static final int GAME_HORIZONTAL_OFFSET = 0;
	private static final int GAME_VERTICAL_OFFSET = 0;
	private static final int GAME_WIDTH = 500;
	private static final int GAME_HEIGHT = 500;
	private static final String DEFAULT_TITLE = "Phineas";
	
	private JFrame window;
	private Container gameContainer;
	private GameCanvas canvas;
	private int width;
	private int height;
	
	/**
	 * Creates a new window for a game
	 * @param eventListener The listener that will respond to this window's window events
	 * @param keyListener The listener that will respond to this window's key events
	 * @param moustListener The listener that will respond to this window's click events
	 */
	public GameView(WindowListener eventListener, KeyListener keyListener, MouseListener mouseListener, 
			MouseMotionListener mouseMotionListener, MouseWheelListener mouseWheelListener)
	{
		// Native integration
		// take the menu bar off the jframe
		System.setProperty("apple.laf.useScreenMenuBar", "true");

		// set the name of the application menu item
		System.setProperty("com.apple.mrj.application.apple.menu.about.name", DEFAULT_TITLE);
		
		// Create window
		window = new JFrame();
		window.setTitle(DEFAULT_TITLE);
		
		// Setup close logic
		window.addWindowListener(eventListener);
		
		// Add key listener
		window.addKeyListener(keyListener);
		
		// Add mouse wheel listener
		window.addMouseWheelListener(mouseWheelListener);
		
		// Extract and configure surface for drawing
		gameContainer = window.getContentPane();
		gameContainer.setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
		gameContainer.setLayout(null);
		
		// Add canvas and establish layout
		canvas = new GameCanvas(GAME_WIDTH, GAME_HEIGHT, GAME_HORIZONTAL_OFFSET, GAME_VERTICAL_OFFSET);
		canvas.addMouseListener(mouseListener);
		canvas.addMouseMotionListener(mouseMotionListener);
		canvas.addMouseWheelListener(mouseWheelListener);
		window.add(canvas);
		window.pack();
		
		// Do not let user resize
		window.setResizable(false);
		
		// Save width and height
		width = GAME_WIDTH;
		height = GAME_HEIGHT;
		
		// Setup canvas
		canvas.setUp();
	}

	/**
	 * Show the game window
	 */
	public void show()
	{
		window.setVisible(true);
	}
	
	/**
	 * Hide the game window
	 */
	public void hide()
	{
		window.setVisible(false);
	}
	
	/**
	 * Borrow a graphics context for this game's canvas
	 * @note Only one Graphics object is released at once. It must be checked back in!
	 *       Do not dispose of it.
	 * @return Graphics context to draw on
	 */
	public Graphics2D checkoutGraphics()
	{
		return canvas.checkoutGraphics();
	}
	
	/**
	 * Check back in a graphics context borrowed from this game's canvas
	 * @param graphics The graphics context borrowed from this game's canvas
	 */
	public void checkinGraphics(Graphics2D graphics)
	{
		canvas.checkinGraphics(graphics);
	}
	
	/**
	 * Releases this view's window and its resources from the application
	 */
	public void dispose()
	{
		window.dispose();
	}

	/**
	 * Sets the dimensions of this view's window
	 * @param newWidth
	 * @param newHeight
	 */
	public void setDimensions(int newWidth, int newHeight)
	{
		canvas.setDimensions(newWidth, newHeight);
		window.invalidate();
		window.setSize(newWidth, newHeight);
		
		width = newWidth;
		height = newHeight;
	}

	/**
	 * Fixes the given location to be relative to this window
	 * @param location Global screen coordiante point to convert
	 */
	public void convertPointFromScreen(Point location)
	{
		SwingUtilities.convertPointFromScreen(location, window.getComponent(0));
	}
	
	/**
	 * Get the height of the window
	 * @return Vertical size of window in pixels
	 */
	public int getWidth()
	{
		return width;
	}
	
	/**
	 * Get the width of the window
	 * @return Horizontal size of window in pixels
	 */
	public int getHeight()
	{
		return height;
	}
}
