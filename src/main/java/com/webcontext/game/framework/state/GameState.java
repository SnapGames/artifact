package com.webcontext.game.framework.state;

import java.awt.Graphics2D;

import com.webcontext.game.framework.entity.GameObject;

public interface GameState {

	/**
	 * Initializing the GameState.
	 */
	public abstract void init();

	public abstract void reset();

	/**
	 * Update all events, objets and so on, for this game state.
	 * 
	 * @param delay
	 *            duration from last call (in milliseconds).
	 */
	public abstract void update(long delay);

	/**
	 * Draw the full state for this Game state.
	 * 
	 * @param g
	 *            the Graphics API to used to render all beautiful things !
	 */
	public abstract void draw(Graphics2D g);

	/**
	 * Handle input for this game state.
	 */
	public abstract void handleInput();

	public abstract GameObject getGameObject(String name);

	/**
	 * Return Game State Manager.
	 * 
	 * @return
	 */
	public abstract GSM getGsm();

	/**
	 * If needed return the next Level.
	 * 
	 * @return
	 */
	public abstract String nextState();

	/**
	 * Return the <code>name</code> attribute.
	 * 
	 * @param name
	 * @return
	 */
	public abstract Object getAttribute(String name);

	/**
	 * Set <code>value</code> attribute to <code>name</code>.
	 * 
	 * @param name
	 * @param value
	 */
	public abstract void setAttribute(String name, Object value);

	/**
	 * Is <code>name</code>'s attribute exists in attributes list ?
	 * 
	 * @param name
	 *            name of the attribute to search for.
	 * @return true if attribute exists.
	 */
	public abstract boolean containsAttribute(String name);

}