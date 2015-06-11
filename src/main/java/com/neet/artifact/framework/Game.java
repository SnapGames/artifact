package com.neet.artifact.framework;

import java.io.IOException;
import java.util.Properties;

import javax.swing.JFrame;

/**
 * Game The game !
 * 
 * @author 
 *         ForeignGuyMike(https://www.youtube.com/channel/UC_IV37n-uBpRp64hQIwywWQ
 *         )
 * @author Frédéric Delorme<frederic.delorme@web-context.com>(refactoring)
 * 
 * @see https://www.youtube.com/channel/UC_IV37n-uBpRp64hQIwywWQ
 */
public class Game {

	private Properties props = new Properties();

	/**
	 * Load properties file.
	 */
	public Game() {
		try {
			props.load(this.getClass().getResourceAsStream(
					"/artifact.properties"));
		} catch (IOException e) {
			System.out
					.println("Unable to read release file artifact.properties.");
		}

	}

	/**
	 * return the message corresponding to <code>key</code>.
	 * 
	 * @param key
	 * @return
	 */
	public String getMessage(String key) {
		return (String) props.get(key);
	}

	/**
	 * Main method to launch game.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		Game game = new Game();
		JFrame window = new JFrame(String.format("%s - %s (%s)",
				game.getMessage("title"), game.getMessage("version"),
				game.getMessage("createdAt")));
		window.add(new GamePanel());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}

}
