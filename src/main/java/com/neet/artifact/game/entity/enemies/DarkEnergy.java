package com.neet.artifact.game.entity.enemies;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.neet.framework.entity.Enemy;
import com.neet.framework.gfx.tilemap.TileMap;
import com.neet.framework.handler.Content;

/**
 * Dark Energy enemy object
 * 
 * @authorForeignGuyMike(https://www.youtube.com/channel/UC_IV37n-uBpRp64hQIwywWQ)
 * @author Frédéric Delorme<frederic.delorme@web-context.com>(refactoring)
 *
 */
public class DarkEnergy extends Enemy {

	private BufferedImage[] startSprites;
	private BufferedImage[] sprites;

	private boolean start;
	private boolean permanent;

	private int type = 0;
	public static int VECTOR = 0;
	public static int GRAVITY = 1;
	public static int BOUNCE = 2;

	private int bounceCount = 0;

	public DarkEnergy(TileMap tm) {

		super(tm);

		health = maxHealth = 1;

		width = 20;
		height = 20;
		cwidth = 12;
		cheight = 12;

		damage = 1;
		moveSpeed = 5;

		startSprites = Content.DarkEnergy[0];
		sprites = Content.DarkEnergy[1];

		animation.setFrames(startSprites);
		animation.setDelay(2);

		start = true;
		flinching = true;
		permanent = false;

	}

	public void setType(int i) {
		type = i;
	}

	public void setPermanent(boolean b) {
		permanent = b;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neet.framework.entity.Enemy#update(long)
	 */
	@Override
	public void update(long delta) {

		if (start) {
			if (animation.hasPlayedOnce()) {
				animation.setFrames(sprites);
				animation.setNumFrames(3);
				animation.setDelay(2);
				start = false;
			}
		}

		if (type == VECTOR) {
			x += dx;
			y += dy;
		} else if (type == GRAVITY) {
			dy += 0.2;
			x += dx;
			y += dy;
		} else if (type == BOUNCE) {
			double dx2 = dx;
			double dy2 = dy;
			checkTileMapCollision();
			if (dx == 0) {
				dx = -dx2;
				bounceCount++;
			}
			if (dy == 0) {
				dy = -dy2;
				bounceCount++;
			}
			x += dx;
			y += dy;
		}

		// update animation
		animation.update(delta);

		if (!permanent) {
			if (x < 0 || x > tileMap.getWidth() || y < 0
					|| y > tileMap.getHeight()) {
				remove = true;
			}
			if (bounceCount == 3) {
				remove = true;
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neet.framework.entity.MapObject#draw(java.awt.Graphics2D)
	 */
	@Override
	public void draw(Graphics2D g) {
		super.draw(g);
	}

}
