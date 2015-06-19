package com.neet.artifact.game.gamestate;

import com.neet.framework.audio.JukeBox;
import com.neet.framework.state.GSM;
import com.neet.framework.state.GameStateManager;

/**
 * Game state manager is the Earth Beat of the game. Switching and orchestrating
 * all states.
 * 
 * @author 
 *         ForeignGuyMike(https://www.youtube.com/channel/UC_IV37n-uBpRp64hQIwywWQ
 *         )
 * @author Frédéric Delorme<frederic.delorme@web-context.com>(refactoring)
 *
 */
public class ArtifactGameStateManager extends GameStateManager implements GSM {

	/**
	 * All games states index.
	 */
	public static final String MENUSTATE = "menu";
	public static final String OPTIONSTATE = "options";
	public static final String PAUSESTATE = "pause";
	public static final String ACIDSTATE = "acid";
	public static final String LEVEL1ASTATE = "level-1";
	public static final String LEVEL1BSTATE = "level-2";
	public static final String LEVEL1CSTATE = "level-3";

	/**
	 * Default constructor to initialize sub components like {@link JukeBox},
	 * the {@link PauseState}, and load the needed state.
	 */
	public ArtifactGameStateManager() {

		gameStates.put(MENUSTATE, MenuState.class);
		gameStates.put(PAUSESTATE, PauseState.class);
		gameStates.put(OPTIONSTATE, OptionState.class);
		gameStates.put(ACIDSTATE, AcidState.class);
		gameStates.put(LEVEL1ASTATE, Level1AState.class);
		gameStates.put(LEVEL1BSTATE, Level1BState.class);
		gameStates.put(LEVEL1CSTATE, Level1CState.class);

		pauseState = new PauseState(this);
		paused = false;

		currentState = MENUSTATE;
		JukeBox.init();
		loadState(currentState);

	}

}