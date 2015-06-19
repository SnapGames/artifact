package com.neet.artifact.game.main;

import com.neet.artifact.game.gamestate.ArtifactGameStateManager;
import com.webcontext.game.framework.GamePanel;

@SuppressWarnings("serial")
public class ArtifactPanel extends GamePanel {

	public void init() {
		super.init();
		gsm = new ArtifactGameStateManager();
	}

}
