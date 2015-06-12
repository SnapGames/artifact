package com.neet.framework.state;

import java.awt.Graphics2D;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import com.neet.artifact.game.gamestate.PauseState;
import com.neet.framework.GamePanel;
import com.neet.framework.audio.JukeBox;

public class GameStateManager {

	/**
	 * Array of all possible game states.
	 */
	protected Map<String, Class<?>> gameStates = new HashMap<>();
	/**
	 * index of current active state.
	 */
	protected String currentState;
	/**
	 * The Pause State (when player push pause button.
	 */
	protected PauseState pauseState;
	/**
	 * Pause state engaged ?
	 */
	protected boolean paused;
	private Map<String, GameState> activeStates = new HashMap<String,GameState>();

	public GameStateManager() {
		JukeBox.init();
		loadState(currentState);
	}

	/**
	 * Prepare {@link GameState} for the game <code>state</code>.
	 * 
	 * @param state
	 */
	protected void loadState(String state) {
		if (gameStates.containsKey(state)) {
			Class<?> stateClass = gameStates.get(state);
			currentState = state;
			try {
				// Constructor for the level.
				Constructor<?> activeStateCst = stateClass.getDeclaredConstructor(new Class[] { GameStateManager.class });
				// instances the GameSate, and store to cache..
				activeStates.put(state, (GameState) activeStateCst.newInstance(new Object[] { this }));
				
			} catch (InstantiationException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	/**
	 * remove state.
	 * 
	 * @param state
	 */
	private void unloadState(String state) {
		activeStates.remove(state);
	}

	/**
	 * set active State.
	 * 
	 * @param state
	 */
	public void setActiveState(String state) {
		unloadState(currentState);
		currentState = state;
		loadState(currentState);
	}

	public void setPaused(boolean b) {
		paused = b;
	}

	/**
	 * Update current active State
	 * @param delay
	 */
	public void update(long delay) {
		if (paused) {
			pauseState.update(delay);
			return;
		}
		if (activeStates.containsKey(currentState))
			activeStates.get(currentState).update(delay);
	}

	/**
	 * draw Current state.
	 * @param g
	 */
	public void draw(Graphics2D g) {
		if (paused) {
			pauseState.draw(g);
			return;
		}
		if (activeStates.containsKey(currentState))
			activeStates.get(currentState).draw(g);
		else {
			g.setColor(java.awt.Color.BLACK);
			g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		}
	}

}