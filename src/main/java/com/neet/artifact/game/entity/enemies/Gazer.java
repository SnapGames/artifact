package com.neet.artifact.game.entity.enemies;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.neet.artifact.framework.entity.Enemy;
import com.neet.artifact.framework.gfx.tilemap.TileMap;
import com.neet.artifact.framework.handler.Content;

/**
 * Gaze enemy.
 * 
 * @authorForeignGuyMike(https://www.youtube.com/channel/UC_IV37n-uBpRp64hQIwywWQ)
 * @author Frédéric Delorme<frederic.delorme@web-context.com>(refactoring)
 *
 */
public class Gazer extends Enemy {

	private BufferedImage[] idleSprites;

	private int tick;
	private double a;
	private double b;

	public Gazer(TileMap tm) {

		super(tm);

		health = maxHealth = 2;

		width = 39;
		height = 20;
		cwidth = 25;
		cheight = 15;

		damage = 1;
		moveSpeed = 5;

		idleSprites = Content.Gazer[0];

		animation.setFrames(idleSprites);
		animation.setDelay(4);

		tick = 0;
		a = Math.random() * 0.06 + 0.07;
		b = Math.random() * 0.06 + 0.07;

	}

	public void update(long delta) {

		// check if done flinching
		if (flinching) {
			flinchCount++;
			if (flinchCount == 6)
				flinching = false;
		}

		tick++;
		x = Math.sin(a * tick) + x;
		y = Math.sin(b * tick) + y;

		// update animation
		animation.update();

	}

	public void draw(Graphics2D g) {

		if (flinching) {
			if (flinchCount == 0 || flinchCount == 2)
				return;
		}

		super.draw(g);

	}

}
