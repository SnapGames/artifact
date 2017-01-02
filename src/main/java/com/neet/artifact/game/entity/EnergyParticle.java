package com.neet.artifact.game.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.snapgames.framework.entity.MapObject;
import com.snapgames.framework.gfx.tilemap.TileMap;
import com.snapgames.framework.resources.ContentManager;

/**
 * Graphic Object to provide animated Energetic Particle.
 * 
 * @author Frédéric Delorme.
 *
 */
public class EnergyParticle extends MapObject {

	/**
	 * Direction State. this object intends to provide a direction for
	 * particles.
	 * 
	 * @author Frédéric Delorme
	 *
	 */
	public enum Direction {
		UP, LEFT, DOWN, RIGHT;
	}

	/**
	 * number of particles.
	 */
	private int count;
	/**
	 * Particle to be removed flag.
	 */
	private boolean remove;

	/**
	 * Sprites to animate particle.
	 */
	private BufferedImage[] sprites;

	/**
	 * Create a new particle.
	 * 
	 * @param tm
	 *            TileMap where to animate particle,
	 * @param x
	 *            X axis position
	 * @param y
	 *            Y axis position
	 * @param dir
	 *            Direction of the particle.
	 */
	public EnergyParticle(TileMap tm, double x, double y, Direction dir) {
		super(tm);
		this.x = x;
		this.y = y;
		double d1 = Math.random() * 2.5 - 1.25;
		double d2 = -Math.random() - 0.8;
		switch (dir) {
		case UP:
			dx = d1;
			dy = d2;
			break;
		case LEFT:
			dx = d2;
			dy = d1;
			break;
		case DOWN:
			dx = d1;
			dy = -d2;
			break;
		case RIGHT:
			dx = -d2;
			dy = d1;
			break;
		}

		count = 0;
		sprites = ContentManager.getImageBank("EnergyParticle")[0];
		animation.setFrames(sprites);
		animation.setDelay(-1);
	}

	public void update() {
		x += dx;
		y += dy;
		count++;
		if (count == 60)
			remove = true;
	}

	public boolean shouldRemove() {
		return remove;
	}

	public void draw(Graphics2D g) {
		super.draw(g);
	}

	@Override
	public void reset() {

	}

	@Override
	public void update(long delay) {

	}

}
