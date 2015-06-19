/**
 * 
 */
package com.neet.artifact.game.main;

import com.webcontext.game.framework.Game;
import com.webcontext.game.framework.GamePanel;

/**
 * @author frederic
 *
 */
public class Artifact extends Game {
	public GamePanel create() {
		return new ArtifactPanel();
	}

	/**
	 * Main method to launch game.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Artifact game = new Artifact();
		game.start();
	}

}
