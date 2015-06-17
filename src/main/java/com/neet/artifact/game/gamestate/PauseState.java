package com.neet.artifact.game.gamestate;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import com.neet.framework.Game;
import com.neet.framework.GamePanel;
import com.neet.framework.handler.InputHandler;
import com.neet.framework.state.GameState;
import com.neet.framework.state.GameStateManager;

/**
 * The Pause state for this game.
 * 
 * @author 
 *         ForeignGuyMike(https://www.youtube.com/channel/UC_IV37n-uBpRp64hQIwywWQ
 *         )
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
	 * @see com.neet.artifact.gamestate.GameState#init()
	 */
	public void init() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neet.artifact.gamestate.GameState#update()
	 */
	public void update(long delay) {
		handleInput();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neet.artifact.gamestate.GameState#draw(java.awt.Graphics2D)
	 */
	public void draw(Graphics2D g) {
		// retrieve translated sentence.
		String pause = Game.getMessage("pause.main");
		// prepare font
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		g.setColor(Color.WHITE);
		g.setFont(font);
		// center pause sentence.
		Rectangle2D rect = g.getFontMetrics(font).getStringBounds(pause, g);
		g.drawString(pause, (int) (GamePanel.WIDTH - rect.getWidth()) / 2,
				(int) (GamePanel.HEIGHT - rect.getHeight()) / 2);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neet.artifact.gamestate.GameState#handleInput()
	 */
	public void handleInput() {
		if (InputHandler.isPressed(InputHandler.KeyCode.ESCAPE))
			gsm.setPaused(false);
		if (InputHandler.isPressed(InputHandler.KeyCode.BUTTON1)) {
			gsm.setPaused(false);
			gsm.setActiveState(ArtifactGameStateManager.MENUSTATE);
		}
	}

	@Override
	public void reset() {

	}

}
