package com.neet.artifact.game.entity.enemies;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.neet.artifact.game.entity.Player;
import com.webcontext.game.framework.entity.Enemy;
import com.webcontext.game.framework.gfx.tilemap.TileMap;
import com.webcontext.game.framework.resources.ContentManager;

/**
 * Tengu game object.
 * 
 * @authorForeignGuyMike(https://www.youtube.com/channel/UC_IV37n-uBpRp64hQIwywWQ)
 * @author Frédéric Delorme<frederic.delorme@web-context.com>(refactoring)
 *
 */
public class Tengu extends Enemy {

	private Player player;
	private ArrayList<Enemy> enemies;

	private BufferedImage[] idleSprites;
	private BufferedImage[] jumpSprites;
	private BufferedImage[] attackSprites;

	private boolean jumping;

	private static final int IDLE = 0;
	private static final int JUMPING = 1;
	private static final int ATTACKING = 2;

	private int attackTick;
	private int attackDelay = 30;
	private int step;

	public Tengu(TileMap tm, Player p, ArrayList<Enemy> en) {

		super(tm);
		player = p;
		enemies = en;

		health = maxHealth = 4;

		width = 30;
		height = 30;
		cwidth = 20;
		cheight = 26;

		damage = 1;
		moveSpeed = 1.5;
		fallSpeed = 0.15;
		maxFallSpeed = 4.0;
		jumpStart = -5;

		idleSprites = ContentManager.getImageBank("Tengu")[0];
		jumpSprites = ContentManager.getImageBank("Tengu")[1];
		attackSprites = ContentManager.getImageBank("Tengu")[2];

		animation.setFrames(idleSprites);
		animation.setDelay(-1);

		attackTick = 0;

	}

	private void getNextPosition() {
		if (left)
			dx = -moveSpeed;
		else if (right)
			dx = moveSpeed;
		else
			dx = 0;
		if (falling) {
			dy += fallSpeed;
			if (dy > maxFallSpeed)
				dy = maxFallSpeed;
		}
		if (jumping && !falling) {
			dy = jumpStart;
		}
	}

	public void update(long delta) {

		// check if done flinching
		if (flinching) {
			flinchCount++;
			if (flinchCount == 6)
				flinching = false;
		}

		getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp, ytemp);

		// update animation
		animation.update(delta);

		if (player.getx() < x)
			facingRight = false;
		else
			facingRight = true;

		// idle
		if (step == 0) {
			if (currentAction != IDLE) {
				currentAction = IDLE;
				animation.setFrames(idleSprites);
				animation.setDelay(-1);
			}
			attackTick++;
			if (attackTick >= attackDelay && Math.abs(player.getx() - x) < 60) {
				step++;
				attackTick = 0;
			}
		}
		// jump away
		if (step == 1) {
			if (currentAction != JUMPING) {
				currentAction = JUMPING;
				animation.setFrames(jumpSprites);
				animation.setDelay(-1);
			}
			jumping = true;
			if (facingRight)
				left = true;
			else
				right = true;
			if (falling) {
				step++;
			}
		}
		// attack
		if (step == 2) {
			if (dy > 0 && currentAction != ATTACKING) {
				currentAction = ATTACKING;
				animation.setFrames(attackSprites);
				animation.setDelay(3);
				DarkEnergy de = new DarkEnergy(tileMap);
				de.setPosition(x, y);
				if (facingRight)
					de.setVector(3, 3);
				else
					de.setVector(-3, 3);
				enemies.add(de);
			}
			if (currentAction == ATTACKING && animation.hasPlayedOnce()) {
				step++;
				currentAction = JUMPING;
				animation.setFrames(jumpSprites);
				animation.setDelay(-1);
			}
		}
		// done attacking
		if (step == 3) {
			if (dy == 0)
				step++;
		}
		// land
		if (step == 4) {
			step = 0;
			left = right = jumping = false;
		}

	}

	public void draw(Graphics2D g) {

		if (flinching) {
			if (flinchCount == 0 || flinchCount == 2)
				return;
		}

		super.draw(g);

	}

}
