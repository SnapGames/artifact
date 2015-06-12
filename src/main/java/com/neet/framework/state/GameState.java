package com.neet.framework.state;

import java.awt.Graphics2D;

/**
 * The abstract class to not forgot to implements needed things.
 * 
 * @author ForeignGuyMike(https://www.youtube.com/channel/UC_IV37n-uBpRp64hQIwywWQ)
 * @author Frédéric Delorme<frederic.delorme@web-context.com>(refactoring)
 *
 */
public abstract class GameState {

	protected GameStateManager gsm;
	public GameState(GameStateManager gsm) {
		this.gsm = gsm;
	}

	public abstract void init();

	public abstract void update(long delay);

	public abstract void draw(Graphics2D g);

	public abstract void handleInput();

}
