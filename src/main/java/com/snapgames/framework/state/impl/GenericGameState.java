package com.snapgames.framework.state.impl;

import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;

import com.snapgames.framework.entity.GameObject;
import com.snapgames.framework.state.GSM;
import com.snapgames.framework.state.GameState;

/**
 * The abstract class to not forgot to implements needed things.
 * 
 * @author 
 *         ForeignGuyMike(https://www.youtube.com/channel/UC_IV37n-uBpRp64hQIwywWQ
 *         )
 * @author Frédéric Delorme<frederic.delorme@web-context.com>(refactoring)
 *
 */
public abstract class GenericGameState implements GameState {

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
	public GenericGameState(GameStateManager gsm) {
		this.gsm = gsm;
		gameObjects = new HashMap<>();
		attributes = new HashMap<>();
	}

	/* (non-Javadoc)
	 * @see com.snapgames.framework.state.IGameState#init()
	 */
	@Override
	public abstract void init();

	/* (non-Javadoc)
	 * @see com.snapgames.framework.state.IGameState#reset()
	 */
	@Override
	public abstract void reset();

	/* (non-Javadoc)
	 * @see com.snapgames.framework.state.IGameState#update(long)
	 */
	@Override
	public abstract void update(long delay);

	/* (non-Javadoc)
	 * @see com.snapgames.framework.state.IGameState#draw(java.awt.Graphics2D)
	 */
	@Override
	public abstract void draw(Graphics2D g);

	/* (non-Javadoc)
	 * @see com.snapgames.framework.state.IGameState#handleInput()
	 */
	@Override
	public abstract void handleInput();

	/* (non-Javadoc)
	 * @see com.snapgames.framework.state.IGameState#getGameObject(java.lang.String)
	 */
	@Override
	public GameObject getGameObject(String name) {
		return gameObjects.get(name);
	}

	/* (non-Javadoc)
	 * @see com.snapgames.framework.state.IGameState#getGsm()
	 */
	@Override
	public GSM getGsm() {
		return gsm;
	}

	/* (non-Javadoc)
	 * @see com.snapgames.framework.state.IGameState#nextState()
	 */
	@Override
	public String nextState() {
		return "";
	}

	/* (non-Javadoc)
	 * @see com.snapgames.framework.state.IGameState#getAttribute(java.lang.String)
	 */
	@Override
	public Object getAttribute(String name) {
		return attributes.get(name);
	}

	/* (non-Javadoc)
	 * @see com.snapgames.framework.state.IGameState#setAttribute(java.lang.String, java.lang.Object)
	 */
	@Override
	public void setAttribute(String name, Object value) {
		attributes.put(name, value);
	}

	/* (non-Javadoc)
	 * @see com.snapgames.framework.state.IGameState#containsAttribute(java.lang.String)
	 */
	@Override
	public boolean containsAttribute(String name) {
		return attributes.containsKey(name);
	}

}
