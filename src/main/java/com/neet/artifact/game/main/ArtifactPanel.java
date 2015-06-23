package com.neet.artifact.game.main;

import com.neet.artifact.game.gamestate.ArtifactGameStateManager;
import com.webcontext.game.framework.GamePanel;

/**
 * This class provide specific managers.
 *
 * @author Frédéric Delorme
 */
@SuppressWarnings("serial")
public class ArtifactPanel extends GamePanel {

    public void init() {
        super.init();
        gsm = new ArtifactGameStateManager();
    }

}
