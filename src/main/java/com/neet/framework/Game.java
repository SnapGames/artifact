package com.neet.framework;

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

	/**
	 * Translated i18n strings
	 */
	private static ResourceBundle props;
	/**
	 * Supported languages
	 */
	private Locale[] supportedLocales;
	/**
	 * current activated language.
	 */
	private Locale locale;

	/**
	 * Load properties file.
	 */
	public Game() {
		supportedLocales = Locale.getAvailableLocales();
		locale = Locale.getDefault();
		props = ResourceBundle.getBundle("game", locale);
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
		Game game = new Game();
		JFrame window = new JFrame(String.format("%s - %s (%s)",
				Game.getMessage("app.title"), 
				Game.getMessage("app.version"),
				Game.getMessage("app.createdAt")));

		window.add(new GamePanel());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}

}
