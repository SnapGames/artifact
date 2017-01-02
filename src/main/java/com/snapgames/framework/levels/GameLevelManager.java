/**
 * 
 */
package com.snapgames.framework.levels;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is the Level object for this game. It contains all graphical and
 * Action/Event objects for a {@link GameLevel} of a {@link GameWorld}.
 * 
 * @author Frédéric Delorme.
 *
 */
public class GameLevelManager {

	private static GameLevelManager _instance = null;

	public Map<Long, GameWorld> gameWorlds = null;

	/**
	 * Default constructor for the Game Level Manager. It is private because
	 * this piece of code is only called as a Singleton object.
	 */
	private GameLevelManager() {
		gameWorlds = new HashMap<>();
	}

	/**
	 * Load a World structure, based on a <code>worldFilename</code> file,
	 * containing the full description of a world. A cached flag can be set to
	 * tell Game engine to move this to cache.
	 * 
	 * @param worldFileName
	 *            Filename for the world description.
	 * @param cached
	 *            flag to move world description to GameEngine cache mechanism.
	 * @return
	 */
	public GameWorld loadWorld(String worldFilename, boolean cached) {
		GameWorld world = new GameWorld();
		gameWorlds.put(world.getWorldId(), world);

		return world;
	}

	/**
	 * Default Constructor for this GameLevel.
	 * 
	 * @param levelFileName
	 * @param cached
	 * @return
	 */
	public GameLevel loadLevel(String levelFileName, boolean cached) {
		GameLevel level = new GameLevel();

		return level;
	}

	/**
	 * If instance does no exist, getINstance(à create it and return this
	 * instance.
	 * 
	 * @return
	 */
	public static GameLevelManager getInstance() {
		if (_instance == null) {
			_instance = new GameLevelManager();
		}
		return _instance;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GameLevelManager [gameWorlds=").append(gameWorlds)
				.append("]");
		return builder.toString();
	}
}
