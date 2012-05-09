package org.phineas.core;

/**
 * Exception specific to the Phineas game framework
 * @author Sam Pottinger
 */
public class PhineasException extends Exception {

	private static final long serialVersionUID = -3852664002196464279L;
	
	public PhineasException(String text)
	{
		super(text);
	}

}
