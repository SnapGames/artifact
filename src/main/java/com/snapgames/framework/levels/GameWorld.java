/**
 * 
 */
package com.snapgames.framework.levels;

import java.util.List;

/**
 * This World class designed to contains a set of game level. A world
 * corresponds to a chapter for the global story of the game. Usually, A World
 * is composed of unity of graphic design.
 * 
 * @author Frédéric Delorme.
 *
 */
public class GameWorld {
	/**
	 * Unique identifier for this worlf in the game.
	 */
	private long worldId;
	/**
	 * Ttile of this world.
	 */
	private String title;
	/**
	 * Sub title for this world.
	 */
	private String subtitle;
	/**
	 * Some story lines for this world.
	 */
	private String story;

	/**
	 * GameLevels contained by this world.
	 */
	private List<GameLevel> gameLevels;

	public GameWorld() {
		gameLevels = null;
	}

	/**
	 * @return the worldId
	 */
	public long getWorldId() {
		return worldId;
	}

	/**
	 * @param worldId
	 *            the worldId to set
	 */
	public void setWorldId(long worldId) {
		this.worldId = worldId;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the subtitle
	 */
	public String getSubtitle() {
		return subtitle;
	}

	/**
	 * @param subtitle
	 *            the subtitle to set
	 */
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	/**
	 * @return the story
	 */
	public String getStory() {
		return story;
	}

	/**
	 * @param story
	 *            the story to set
	 */
	public void setStory(String story) {
		this.story = story;
	}

	/**
	 * @return the gameLevels
	 */
	public List<GameLevel> getGameLevels() {
		return gameLevels;
	}

	/**
	 * @param gameLevels
	 *            the gameLevels to set
	 */
	public void setGameLevels(List<GameLevel> gameLevels) {
		this.gameLevels = gameLevels;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GameWorld [worldId=").append(worldId)
				.append(", title=").append(title).append(", subtitle=")
				.append(subtitle).append(", story=").append(story)
				.append(", gameLevels=").append(gameLevels).append("]");
		return builder.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((story == null) ? 0 : story.hashCode());
		result = prime * result
				+ ((subtitle == null) ? 0 : subtitle.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + (int) (worldId ^ (worldId >>> 32));
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GameWorld other = (GameWorld) obj;
		if (story == null) {
			if (other.story != null)
				return false;
		} else if (!story.equals(other.story))
			return false;
		if (subtitle == null) {
			if (other.subtitle != null)
				return false;
		} else if (!subtitle.equals(other.subtitle))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (worldId != other.worldId)
			return false;
		return true;
	}

}
