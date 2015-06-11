package com.neet.GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.neet.Audio.JukeBox;
import com.neet.Entity.PlayerSave;
import com.neet.Handlers.InputHandler;
import com.neet.Main.GamePanel;

/**
 * The Menu for the game.
 * 
 * @author ForeignGuyMike(https://www.youtube.com/channel/UC_IV37n-uBpRp64hQIwywWQ)
 * @author Frédéric Delorme<frederic.delorme@web-context.com>(refactoring)
 *
 */
public class MenuState extends GameState {

	private BufferedImage head;

	private int currentChoice = 0;
	private String[] options = { "Start", "Quit" };

	private Color titleColor;
	private Font titleFont;

	private Font font;
	private Font font2;

	/**
	 * Default constructor to initialize this menu state.
	 * 
	 * @param gsm
	 */
	public MenuState(GameStateManager gsm) {

		super(gsm);

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neet.GameState.GameState#init()
	 */
	public void init() {
		// Nothing to init !
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neet.GameState.GameState#update()
	 */
	public void update(long delay) {

		// check keys
		handleInput();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neet.GameState.GameState#draw(java.awt.Graphics2D)
	 */
	public void draw(Graphics2D g) {

		// draw bg
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);

		// draw title
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString("A R T I F A C T", 70, 90);

		// draw menu options
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString("Start", 145, 165);
		g.drawString("Quit", 145, 185);

		// draw floating head
		if (currentChoice == 0)
			g.drawImage(head, 125, 154, null);
		else if (currentChoice == 1)
			g.drawImage(head, 125, 174, null);

		// other
		g.setFont(font2);
		g.drawString("2013 Mike S.", 10, 232);

	}

	/**
	 * Menu item select.
	 */
	private void select() {
		if (currentChoice == 0) {
			JukeBox.play("menuselect");
			PlayerSave.init();
			gsm.setActiveState(GameStateManager.LEVEL1ASTATE);
		} else if (currentChoice == 1) {
			System.exit(0);
		}
	}

	/**
	 * Handle input for the menu state.
	 */
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

}
