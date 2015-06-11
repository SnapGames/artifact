package com.neet.Main;

import javax.swing.JFrame;

/**
 * Artifact The game !
 * 
 * @author ForeignGuyMike(https://www.youtube.com/channel/UC_IV37n-uBpRp64hQIwywWQ)
 * @author Frédéric Delorme<frederic.delorme@web-context.com>(refactoring)
 * 
 * @see https://www.youtube.com/channel/UC_IV37n-uBpRp64hQIwywWQ
 */
public class Game {

	/**
	 * Main method to launch game.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame window = new JFrame("Artifact");
		window.add(new GamePanel());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}

}
