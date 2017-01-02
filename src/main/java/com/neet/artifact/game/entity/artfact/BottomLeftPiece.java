package com.neet.artifact.game.entity.artfact;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.snapgames.framework.entity.MapObject;
import com.snapgames.framework.gfx.tilemap.TileMap;

public class BottomLeftPiece extends MapObject {
	
	private BufferedImage[] sprites;
	
	public BottomLeftPiece(TileMap tm) {
		super(tm);
		try {	
			BufferedImage spritesheet = ImageIO.read(
				getClass().getResourceAsStream("/Sprites/Other/Game.gif")
			);
			sprites = new BufferedImage[1];
			width = height = 4;
			sprites[0] = spritesheet.getSubimage(0, 10, 10, 10);
			animation.setFrames(sprites);
			animation.setDelay(-1);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g) {
		super.draw(g);
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(long delay) {
		x += dx;
		y += dy;
		animation.update(delay);
	}
	
}
