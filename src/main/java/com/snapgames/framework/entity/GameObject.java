package com.snapgames.framework.entity;

import java.awt.Graphics2D;

/**
 * Game Object is the basic interface for all graphics Object to be displayed.
 * 
 * @author Frédéric Delorme.
 *
 */
public interface GameObject {

	/**
	 * Initialize object
	 */
	public default void init() {

	}

	/**
	 * reset the object at its default values.
	 */
	public abstract void reset();

	/**
	 * update object according to the delay from last call.
	 * 
	 * @param delay
	 */
	public abstract void update(long delay);

	/**
	 * draw the object with eth Graphics2D interface.
	 * 
	 * @param g
	 */
	public abstract void draw(Graphics2D g);

}