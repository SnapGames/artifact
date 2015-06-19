package com.neet.framework.state;

import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;

import com.neet.framework.entity.GameObject;

/**
 * The abstract class to not forgot to implements needed things.
 * 
 * @author 
 *         ForeignGuyMike(https://www.youtube.com/channel/UC_IV37n-uBpRp64hQIwywWQ
 *         )
 * @author Frédéric Delorme<frederic.delorme@web-context.com>(refactoring)
 *
 */
public abstract class GameState {

	/**
	 * Objects managed on this level.
	 */
	protected Map<String, GameObject> gameObjects;

	/**
	 * Internal GameState attributes.
	 */
	protected Map<String, Object> attributes;

	/**
	 * Internal Game State Manager reference.
	 */
	protected GameStateManager gsm;

	/**
	 * default constructor.
	 * 
	 * @param gsm
	 */
	public GameState(GameStateManager gsm) {
		this.gsm = gsm;
		gameObjects = new HashMap<>();
		attributes = new HashMap<>();
	}

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

	public GameObject getGameObject(String name) {
		return gameObjects.get(name);
	}

	/**
	 * Return Game State Manager.
	 * 
	 * @return
	 */
	public GSM getGsm() {
		return gsm;
	}

	/**
	 * If needed return the next Level.
	 * 
	 * @return
	 */
	public String nextState() {
		return "";
	}

	/**
	 * Return the <code>name</code> attribute.
	 * 
	 * @param name
	 * @return
	 */
	public Object getAttribute(String name) {
		return attributes.get(name);
	}

	/**
	 * Set <code>value</code> attribute to <code>name</code>.
	 * 
	 * @param name
	 * @param value
	 */
	public void setAttribute(String name, Object value) {
		attributes.put(name, value);
	}

	/**
	 * Is <code>name</code>'s attribute exists in attributes list ?
	 * 
	 * @param name
	 *            name of the attribute to search for.
	 * @return true if attribute exists.
	 */
	public boolean containsAttribute(String name) {
		return attributes.containsKey(name);
	}

}
