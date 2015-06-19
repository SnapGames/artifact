package com.neet.artifact.game.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.webcontext.game.framework.entity.MapObject;
import com.webcontext.game.framework.gfx.tilemap.TileMap;

public class FireBall extends MapObject {

	private boolean hit;
	private boolean remove;
	private BufferedImage[] sprites;
	private BufferedImage[] hitSprites;

	public FireBall(TileMap tm, boolean right) {

		super(tm);

		facingRight = right;

		moveSpeed = 3.8;
		if (right)
			dx = moveSpeed;
		else
			dx = -moveSpeed;

		width = 30;
		height = 30;
		cwidth = 14;
		cheight = 14;

		// load sprites
		try {

			BufferedImage spritesheet = ImageIO.read(getClass()
					.getResourceAsStream("/Sprites/Player/fireball.gif"));

			sprites = new BufferedImage[4];
			for (int i = 0; i < sprites.length; i++) {
				sprites[i] = spritesheet.getSubimage(i * width, 0, width,
						height);
			}

			hitSprites = new BufferedImage[3];
			for (int i = 0; i < hitSprites.length; i++) {
				hitSprites[i] = spritesheet.getSubimage(i * width, height,
						width, height);
			}

			animation.setFrames(sprites);
			animation.setDelay(4);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void setHit() {
		if (hit)
			return;
		hit = true;
		animation.setFrames(hitSprites);
		animation.setDelay(4);
		dx = 0;
	}

	public boolean isHit() {
		return hit;
	}

	public boolean shouldRemove() {
		return remove;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neet.framework.entity.GameObject#reset()
	 */
	@Override
	public void reset() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neet.framework.entity.GameObject#update(long)
	 */
	@Override
	public void update(long delta) {

		checkTileMapCollision();
		setPosition(xtemp, ytemp);

		if (dx == 0 && !hit) {
			setHit();
		}

		animation.update(delta);
		if (hit && animation.hasPlayedOnce()) {
			remove = true;
		}

	}

}
