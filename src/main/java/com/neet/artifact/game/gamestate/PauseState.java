package com.neet.artifact.game.gamestate;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import com.snapgames.framework.Game;
import com.snapgames.framework.GamePanel;
import com.snapgames.framework.handler.InputHandler;
import com.snapgames.framework.state.impl.GameStateManager;
import com.snapgames.framework.state.impl.GenericGameState;

/**
 * The Pause state for this game.
 *
 * @author ForeignGuyMike
 * @author Frédéric Delorme
 */
public class PauseState extends GenericGameState {

    private Font font;

    /**
     * @param gsm
     */
    public PauseState(GameStateManager gsm) {
        super(gsm);
        // fonts
        font = new Font("Century Gothic", Font.PLAIN, 14);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.neet.artifact.gamestate.GameState#init()
     */
    public void init() {
    }

    /*
     * (non-Javadoc)
     *
     * @see com.neet.artifact.gamestate.GameState#update()
     */
    public void update(long delay) {
        handleInput();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.neet.artifact.gamestate.GameState#draw(java.awt.Graphics2D)
     */
    public void draw(Graphics2D g) {
        // retrieve translated sentence.
        String pause = Game.getMessage("pause.main.title");
        String exit = Game.getMessage("pause.item.exit");
        String back = Game.getMessage("pause.item.back");
        // prepare font
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
        g.setColor(Color.WHITE);
        g.setFont(font);
        // center pause sentence.
        Rectangle2D rect = g.getFontMetrics(font).getStringBounds(pause, g);
        g.drawString(pause, (int) (GamePanel.WIDTH - rect.getWidth()) / 2,
                (int) (GamePanel.HEIGHT - rect.getHeight()) / 2);

        Rectangle2D rect2 = g.getFontMetrics(font).getStringBounds(exit, g);
        g.drawString(exit, (int) (GamePanel.WIDTH - rect2.getWidth()) / 2,
                (int) ((GamePanel.HEIGHT - rect2.getHeight()) / 2) + 36);

        Rectangle2D rect3 = g.getFontMetrics(font).getStringBounds(back, g);
        g.drawString(back, (int) (GamePanel.WIDTH - rect3.getWidth()) / 2,
                (int) ((GamePanel.HEIGHT - rect3.getHeight()) / 2) + 60);

    }

    /*
     * (non-Javadoc)
     *
     * @see com.neet.artifact.gamestate.GameState#handleInput()
     */
    public void handleInput() {
        if (InputHandler.isPressed(InputHandler.KeyCode.ESCAPE))
            gsm.setPaused(false);
        if (InputHandler.isPressed(InputHandler.KeyCode.BUTTON1)) {
            gsm.setPaused(false);
            gsm.setActiveState(ArtifactGameStateManager.MENUSTATE);
        }
    }

    @Override
    public void reset() {

    }

}
