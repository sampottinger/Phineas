package org.phineas.contrib;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

// Thanks http://www.cokeandcode.com/index.html?page=tutorials/spaceinvaders101

/**
 * Caching singleton factory that provides an interface for loading
 * sprites from URLs
 * @author Sam Pottinger
 */
class PhineasSpriteLoader
{
	private static PhineasSpriteLoader instance = null;
	
	private Map<String, Image> images;
	
	/**
	 * Get access to a shared instance of this sprite factory
	 * @return Shared instance of this singleton
	 */
	public static PhineasSpriteLoader getInstance()
	{
		if(instance == null)
			instance = new PhineasSpriteLoader();
		return instance;
	}
	
	/**
	 * Private constructor for this singleton
	 */
	private PhineasSpriteLoader()
	{
		images = new HashMap<String, Image>();
	}
	
	/**
	 * Loads the image from the given location, pulling
	 * it from a cache if possible
	 * @param loc The location of the image to load
	 * @return BufferedImage loaded from that location
	 * @throws IOException 
	 */
	public Image loadSprite(String loc) throws IOException
	{
		// Load from cache if possible
		if(images.containsKey(loc))
			return images.get(loc);
		
		// Load image
		URL url = this.getClass().getClassLoader().getResource(loc);
		Image targetImage = ImageIO.read(url);
		
		// create an accelerated image of the right size to store our sprite in
		GraphicsConfiguration gc = GraphicsEnvironment.
		        getLocalGraphicsEnvironment().
		        getDefaultScreenDevice().
		        getDefaultConfiguration();
		
		// Create compatable image and draw to it
		Image compatableImage = gc.createCompatibleImage(targetImage.getWidth(null),
		                                       targetImage.getHeight(null),
		                                       Transparency.BITMASK);
		compatableImage.getGraphics().drawImage(targetImage, 0, 0, null);
		
		// Cache
		images.put(loc, compatableImage);
		
		return compatableImage;
	}
}
