/**
 *
 */
package com.neet.artifact.game.main;

import com.webcontext.game.framework.Game;
import com.webcontext.game.framework.GamePanel;
import com.webcontext.game.framework.resources.ContentManager;

/**
 * The main class to run the Artifact Game.
 *
 * @author Frédéric Delorme
 */
public class Artifact extends Game {
    /**
     * Main method to launch game.
     *
     * @param args
     */
    public static void main(String[] args) {
        Artifact game = new Artifact();
        game.start();
    }

    public GamePanel create() {

        ContentManager.loadImageBank("EnergyParticle",
                "/Sprites/Player/EnergyParticle.gif", 5, 5);
        ContentManager.loadImageBank("Explosion", "/Sprites/Enemies/Explosion.gif", 30,
                30);
        ContentManager.loadImageBank("Gazer", "/Sprites/Enemies/Gazer.gif", 39, 20);
        ContentManager.loadImageBank("Tengu", "/Sprites/Enemies/Tengu.gif", 30, 30);
        ContentManager.loadImageBank("GelPop", "/Sprites/Enemies/GelPop.gif", 25, 25);
        ContentManager.loadImageBank("DarkEnergy", "/Sprites/Enemies/DarkEnergy.gif",
                20, 20);

        return new ArtifactPanel();
    }

}
