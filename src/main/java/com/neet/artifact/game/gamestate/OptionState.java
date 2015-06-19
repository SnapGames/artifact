/**
 * 
 */
package com.neet.artifact.game.gamestate;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Locale;

import javax.imageio.ImageIO;

import com.webcontext.game.framework.Game;
import com.webcontext.game.framework.GamePanel;
import com.webcontext.game.framework.audio.JukeBox;
import com.webcontext.game.framework.handler.InputHandler;
import com.webcontext.game.framework.state.impl.GameStateManager;
import com.webcontext.game.framework.state.impl.GenericGameState;

/**
 * @author frederic
 *
 */
public class OptionState extends GenericGameState {

	private BufferedImage head;

	private int currentChoice = 0, selectedChoice = 0;

	private Color titleColor;
	private Font titleFont;

	private Font font;
	private Font font2;

	Locale[] supportedLocales = { Locale.ENGLISH, Locale.FRENCH };

	private String[] options = { Game.getMessage("options.language.english"),
			Game.getMessage("options.language.french"),
			Game.getMessage("options.language.back") };

	public OptionState(GameStateManager gsm) {
		super(gsm);
		setLanguage();
		try {

			// load floating head
			head = ImageIO.read(getClass().getResourceAsStream("/HUD/Hud.gif"))
					.getSubimage(0, 12, 12, 11);

			// titles and fonts
			titleColor = Color.WHITE;
			titleFont = new Font("Times New Roman", Font.PLAIN, 28);
			font = new Font("Arial", Font.PLAIN, 14);
			font2 = new Font("Arial", Font.PLAIN, 10);

			// load sound fx
			JukeBox.load("/SFX/menuoption.mp3", "menuoption");
			JukeBox.load("/SFX/menuselect.mp3", "menuselect");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 */
	private void setLanguage() {
		options[0] = Game.getMessage("options.language.english");
		options[1] = Game.getMessage("options.language.french");
		options[2] = Game.getMessage("options.language.back");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neet.framework.state.GameState#init()
	 */
	@Override
	public void init() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neet.framework.state.GameState#update(long)
	 */
	@Override
	public void update(long delay) {
		// check keys
		handleInput();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neet.framework.state.GameState#draw(java.awt.Graphics2D)
	 */
	@Override
	public void draw(Graphics2D g) {
		// draw bg
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);

		// draw title
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString("O P T I O N S", 70, 90);

		// draw menu options
		g.setFont(font);
		g.setColor(Color.WHITE);
		for (int i = 0; i < options.length; i++) {
			if (i == selectedChoice) {
				g.setColor(Color.GREEN);
			} else {
				g.setColor(Color.WHITE);
			}
			g.drawString(options[i], 145, 165 + (20 * i));

		}

		// draw floating head
		g.drawImage(head, 125, 154 + (20 * currentChoice), null);

		// other
		g.setColor(Color.WHITE);
		g.setFont(font2);
		g.drawString("2013 Mike S.", 10, 232);

	}

	/**
	 * Menu item select.
	 */
	private void select() {
		switch (currentChoice) {
		case 0:
			setChoice();
			break;
		case 1:
			setChoice();
			break;
		case 2:
			gsm.setActiveState(ArtifactGameStateManager.MENUSTATE);
			break;
		}
	}

	/**
	 * 
	 */
	private void setChoice() {
		selectedChoice = currentChoice;
		Game.setLanguage(supportedLocales[selectedChoice]);
		setLanguage();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neet.framework.state.GameState#handleInput()
	 */
	@Override
	public void handleInput() {
		if (InputHandler.isPressed(InputHandler.KeyCode.ENTER))
			select();
		if (InputHandler.isPressed(InputHandler.KeyCode.UP)) {
			if (currentChoice > 0) {
				JukeBox.play("menuoption", 0);
				currentChoice--;
			}
		}
		if (InputHandler.isPressed(InputHandler.KeyCode.DOWN)) {
			if (currentChoice < options.length - 1) {
				JukeBox.play("menuoption", 0);
				currentChoice++;
			}
		}

	}

	@Override
	public void reset() {

	}

}
