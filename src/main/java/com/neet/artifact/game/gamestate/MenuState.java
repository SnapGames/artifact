package com.neet.artifact.game.gamestate;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.neet.artifact.game.entity.PlayerSave;
import com.neet.framework.Game;
import com.neet.framework.GamePanel;
import com.neet.framework.audio.JukeBox;
import com.neet.framework.gfx.font.FontManager;
import com.neet.framework.handler.InputHandler;
import com.neet.framework.state.GameState;
import com.neet.framework.state.GameStateManager;

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

	private String[] options = { 
			Game.getMessage("menu.item.start"),
			Game.getMessage("menu.item.options"),
			Game.getMessage("menu.item.quit")
			};

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
			titleFont = FontManager.getFont("Livingst.ttf");
			//titleFont.deriveFont(Font.BOLD,24);
			font = FontManager.getFont("mael.ttf");
			//font.deriveFont(Font.PLAIN,40);
			font2 = FontManager.getFont("mael.ttf");
			//font2.deriveFont(Font.PLAIN,40);

			// load sound fx
			JukeBox.load("/SFX/menuoption.mp3", "menuoption");
			JukeBox.load("/SFX/menuselect.mp3", "menuselect");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public void setLanguage(){
		options[0]=Game.getMessage("menu.item.start");
		options[1]=Game.getMessage("menu.item.options");
		options[2]=Game.getMessage("menu.item.quit");
	}
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neet.artifact.gamestate.GameState#init()
	 */
	public void init() {
		setLanguage();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neet.artifact.gamestate.GameState#update()
	 */
	public void update(long delay) {

		// check keys
		handleInput();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neet.artifact.gamestate.GameState#draw(java.awt.Graphics2D)
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
		g.drawString(options[0], 145, 165);
		g.drawString(options[1], 145, 185);
		g.drawString(options[2], 145, 205);

		// draw floating head
		g.drawImage(head, 125, 154+(20*currentChoice), null);

		// other
		g.setFont(font2);
		g.drawString("2013 Mike S.", 10, 232);

	}

	/**
	 * Menu item select.
	 */
	private void select() {
		switch(currentChoice){
		case 0:
			JukeBox.play("menuselect");
			PlayerSave.init();
			gsm.setActiveState(ArtifactGameStateManager.LEVEL1ASTATE);
			break;
		case 1:
			gsm.setActiveState(ArtifactGameStateManager.OPTIONSTATE);
			break;
		case 2:
			System.exit(0);
			break;
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
