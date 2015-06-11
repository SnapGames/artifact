package com.neet.gamestate;

import com.neet.audio.JukeBox;
import com.neet.game.GamePanel;

/**
 * Game state manager is the Earth Beat of the game. Switching and orchestrating
 * all states.
 * 
 * @author ForeignGuyMike(https://www.youtube.com/channel/UC_IV37n-uBpRp64hQIwywWQ)
 * @author Frédéric Delorme<frederic.delorme@web-context.com>(refactoring)
 *
 */
public class GameStateManager {

	/**
	 * Array of all possible game states.
	 */
	private GameState[] gameStates;
	/**
	 * index of current active state.
	 */
	private int currentState;

	/**
	 * The Pause State (when player push pause button.
	 */
	private PauseState pauseState;

	/**
	 * Pause state engaged ?
	 */
	private boolean paused;

	/**
	 * All games states index.
	 */
	public static final int MENUSTATE = 0;
	public static final int LEVEL1ASTATE = 2;
	public static final int LEVEL1BSTATE = 3;
	public static final int LEVEL1CSTATE = 4;
	public static final int ACIDSTATE = 15;
	public static final int NUMGAMESTATES = 16;

	/**
	 * Default constructor to initialize sub components like {@link JukeBox},
	 * the {@link PauseState}, and load the needed state.
	 */
	public GameStateManager() {

		JukeBox.init();

		gameStates = new GameState[NUMGAMESTATES];

		pauseState = new PauseState(this);
		paused = false;

		currentState = MENUSTATE;
		loadState(currentState);

	}

	/**
	 * Prepare {@link GameState} for the game <code>state</code>.
	 * 
	 * @param state
	 */
	private void loadState(int state) {
		if (state == MENUSTATE)
			gameStates[state] = new MenuState(this);
		else if (state == LEVEL1ASTATE)
			gameStates[state] = new Level1AState(this);
		else if (state == LEVEL1BSTATE)
			gameStates[state] = new Level1BState(this);
		else if (state == LEVEL1CSTATE)
			gameStates[state] = new Level1CState(this);
		else if (state == ACIDSTATE)
			gameStates[state] = new AcidState(this);
	}

	/**
	 * remove state.
	 * 
	 * @param state
	 */
	private void unloadState(int state) {
		gameStates[state] = null;
	}

	/**
	 * set active State.
	 * @param state
	 */
	public void setActiveState(int state) {
		unloadState(currentState);
		currentState = state;
		loadState(currentState);
	}

	public void setPaused(boolean b) {
		paused = b;
	}

	public void update(long delay) {
		if (paused) {
			pauseState.update(delay);
			return;
		}
		if (gameStates[currentState] != null)
			gameStates[currentState].update(delay);
	}

	public void draw(java.awt.Graphics2D g) {
		if (paused) {
			pauseState.draw(g);
			return;
		}
		if (gameStates[currentState] != null)
			gameStates[currentState].draw(g);
		else {
			g.setColor(java.awt.Color.BLACK);
			g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		}
	}

}