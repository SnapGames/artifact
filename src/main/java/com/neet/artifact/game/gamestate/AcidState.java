package com.neet.artifact.game.gamestate;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.neet.framework.GamePanel;
import com.neet.framework.handler.InputHandler;
import com.neet.framework.state.GameState;
import com.neet.framework.state.GameStateManager;

/**
 * 
 * 
 * @author 
 *         ForeignGuyMike(https://www.youtube.com/channel/UC_IV37n-uBpRp64hQIwywWQ
 *         )
 * @author Frédéric Delorme<frederic.delorme@web-context.com>(refactoring)
 *
 */
public class AcidState extends GameState {

	private float hue;
	private Color color;

	private double angle;
	private BufferedImage image;

	public AcidState(GameStateManager gsm) {
		super(gsm);
		try {
			image = ImageIO.read(
					getClass().getResourceAsStream(
							"/Sprites/Player/PlayerSprites.gif")).getSubimage(
					0, 0, 40, 40);
		} catch (Exception e) {
		}
	}

	public void init() {
	}

	public void update(long delay) {
		handleInput();
		color = Color.getHSBColor(hue, 1f, 1f);
		hue += 0.01;
		if (hue > 1)
			hue = 0;
		angle += 0.1;
	}

	public void draw(Graphics2D g) {
		g.setColor(color);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		AffineTransform at = new AffineTransform();
		at.translate(GamePanel.WIDTH / 2, GamePanel.HEIGHT / 2);
		at.rotate(angle);
		g.drawImage(image, at, null);
	}

	public void handleInput() {
		if (InputHandler.isPressed(InputHandler.KeyCode.ESCAPE))
			gsm.setActiveState(ArtifactGameStateManager.MENUSTATE);
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

}
