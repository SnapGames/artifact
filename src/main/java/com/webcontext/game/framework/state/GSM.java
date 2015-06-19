package com.webcontext.game.framework.state;

import java.awt.Graphics2D;

public interface GSM {
	/**
	 * Update current active State
	 * 
	 * @param delay
	 */
	public void update(long delay);

	/**
	 * draw Current state.
	 * 
	 * @param g
	 */
	public void draw(Graphics2D g);

	/**
	 * set active State.
	 * 
	 * @param state
	 */
	public void setActiveState(String state);

	/**
	 * Ask the game state manager to switch temporarily to PauseState.
	 * 
	 * @param b
	 */
	public void setPaused(boolean b);

}