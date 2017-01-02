package com.neet.artifact.game.gamestate;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.snapgames.framework.GamePanel;
import com.snapgames.framework.handler.InputHandler;
import com.snapgames.framework.state.impl.GameStateManager;
import com.snapgames.framework.state.impl.GenericGameState;

/**
 * Death and Teleport state.
 * 
 * @author 
 *         ForeignGuyMike
 * @author Frédéric Delorme
 *
 */
public class AcidState extends GenericGameState {

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
	}

}
