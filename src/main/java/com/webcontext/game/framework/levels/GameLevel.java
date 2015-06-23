/**
 *
 */
package com.webcontext.game.framework.levels;

import com.webcontext.game.framework.entity.GameObject;

import java.util.ArrayList;
import java.util.List;

/**
 * The Game level class provide the generic mechanism to menage Level inside the game.
 * A {@link GameLevel} is linked to a {@link GameWorld}
 *
 * @author Frédéric Delorme
 */
public class GameLevel {

    /**
     * Internall unique Id for the level manager.
     */
    private Long id;
    /**
     * Title for this level.
     */
    private String title;
    /**
     * Sub title for this level.
     */
    private String subtitle;
    /**
     * Small story for this level.
     */
    private String story;
    /**
     * Linked World for this story;
     */
    private GameWorld world;
    /**
     * previous level. null if not.
     */
    private long previousLevel;
    /**
     * next level. null if not.
     */
    private long nextLevel;
    /**
     * List of GameObject for this level.
     */
    private List<GameObject> gameObjects = new ArrayList<>();

    /**
     * Default Constructor.
     */
    public GameLevel() {
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
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
     * @param subtitle the subtitle to set
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
     * @param story the story to set
     */
    public void setStory(String story) {
        this.story = story;
    }

    /**
     * @return the world
     */
    public GameWorld getWorld() {
        return world;
    }

    /**
     * @param world the world to set
     */
    public void setWorld(GameWorld world) {
        this.world = world;
    }

    /**
     * @return the previousLevel
     */
    public long getPreviousLevel() {
        return previousLevel;
    }

    /**
     * @param previousLevel the previousLevel to set
     */
    public void setPreviousLevel(long previousLevel) {
        this.previousLevel = previousLevel;
    }

    /**
     * @return the nextLevel
     */
    public long getNextLevel() {
        return nextLevel;
    }

    /**
     * @param nextLevel the nextLevel to set
     */
    public void setNextLevel(long nextLevel) {
        this.nextLevel = nextLevel;
    }

    /**
     * @return the gameObjects
     */
    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    /**
     * @param gameObjects the gameObjects to set
     */
    public void setGameObjects(List<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("GameLevel [id=").append(id).append(", title=")
                .append(title).append(", subtitle=").append(subtitle)
                .append(", story=").append(story).append(", world=")
                .append(world).append(", previousLevel=").append(previousLevel)
                .append(", nextLevel=").append(nextLevel).append("]");
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
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((story == null) ? 0 : story.hashCode());
        result = prime * result
                + ((subtitle == null) ? 0 : subtitle.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
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
        GameLevel other = (GameLevel) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
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
        return true;
    }
}
