package com.neet.gamestate;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import com.neet.game.GamePanel;
import com.neet.handlers.InputHandler;

/**
 * The Pause state for this game.
 * 
 * @author ForeignGuyMike(https://www.youtube.com/channel/UC_IV37n-uBpRp64hQIwywWQ)
 * @author Frédéric Delorme<frederic.delorme@web-context.com>(refactoring)
 *
 */
public class PauseState extends GameState {

	private Font font;

	/**
	 * 
	 * @param gsm
	 */
	public PauseState(GameStateManager gsm) {
		super(gsm);
		// fonts
		font = new Font("Century Gothic", Font.PLAIN, 14);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neet.gamestate.GameState#init()
	 */
	public void init() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neet.gamestate.GameState#update()
	 */
	public void update(long delay) {
		handleInput();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neet.gamestate.GameState#draw(java.awt.Graphics2D)
	 */
	public void draw(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		g.setColor(Color.WHITE);
		g.setFont(font);
		g.drawString("Game Paused", 90, 90);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neet.gamestate.GameState#handleInput()
	 */
	public void handleInput() {
		if (InputHandler.isPressed(InputHandler.KeyCode.ESCAPE))
			gsm.setPaused(false);
		if (InputHandler.isPressed(InputHandler.KeyCode.BUTTON1)) {
			gsm.setPaused(false);
			gsm.setActiveState(GameStateManager.MENUSTATE);
		}
	}

}
