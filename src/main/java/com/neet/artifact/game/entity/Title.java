package com.neet.artifact.game.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.snapgames.framework.GamePanel;
import com.snapgames.framework.entity.GameObject;

/**
 * Title object to be displayed on some GameState
 * 
 * @author ForeignGuyMike
 * @author Frederic Delorme
 *
 */
public class Title implements GameObject {

	public BufferedImage image;

	public int count;
	private boolean done;
	private boolean remove;

	private double x;
	private double y;
	private double dx;

	private int width;

	public Title(String s) {

		try {
			image = ImageIO.read(getClass().getResourceAsStream(s));
			width = image.getWidth();
			x = -width;
			done = false;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Title(BufferedImage image) {
		this.image = image;
		width = image.getWidth();
		x = -width;
		reset();
	}

	public void sety(double y) {
		this.y = y;
	}

	public void begin() {
		dx = 10;
	}

	public boolean shouldRemove() {
		return remove;
	}

	public void update(long delay) {
		if (!done) {
			if (x >= (GamePanel.WIDTH - width) / 2) {
				x = (GamePanel.WIDTH - width) / 2;
				count++;
				if (count >= 120)
					done = true;
			} else {
				x += dx;
			}
		} else {
			x += dx;
			if (x > GamePanel.WIDTH)
				remove = true;
		}
	}

	public void draw(Graphics2D g) {
		g.drawImage(image, (int) x, (int) y, null);
	}

	@Override
	public void reset() {
		done = false;
	}

}
