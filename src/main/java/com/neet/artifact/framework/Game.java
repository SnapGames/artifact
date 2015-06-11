package com.neet.artifact.framework;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFrame;

/**
 * Game The game !
 * 
 * @author 
 *         ForeignGuyMike<https://www.youtube.com/channel/UC_IV37n-uBpRp64hQIwywWQ
 *         >
 * @author Frédéric Delorme<frederic.delorme@web-context.com>(refactoring)
 * 
 * @see https://www.youtube.com/channel/UC_IV37n-uBpRp64hQIwywWQ
 */
public class Game {

	private static ResourceBundle props;

	static {
		props = ResourceBundle.getBundle("game", Locale.ENGLISH);
	}

	/**
	 * Load properties file.
	 */
	public Game() {
	}

	/**
	 * return the message corresponding to <code>key</code>.
	 * 
	 * @param key
	 * @return
	 */
	public static String getMessage(String key) {
		return (String) props.getString(key);
	}

	public static void setLanguage(Locale locale) {
		props = ResourceBundle.getBundle("game", locale);
	}

	/**
	 * Main method to launch game.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		JFrame window = new JFrame(String.format("%s - %s (%s)",
				Game.getMessage("app.title"), Game.getMessage("app.version"),
				Game.getMessage("app.createdAt")));

		window.add(new GamePanel());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}

}
