package com.neet.artifact.framework.entity;

import com.neet.artifact.framework.audio.JukeBox;
import com.neet.artifact.framework.gfx.tilemap.TileMap;
/**
 * Default generic enemy object.
 * 
 * @authorForeignGuyMike(https://www.youtube.com/channel/UC_IV37n-uBpRp64hQIwywWQ)
 * @author Frédéric Delorme<frederic.delorme@web-context.com>(refactoring)
 *
 */
public abstract class Enemy extends MapObject implements GameObject{
	
	protected int health;
	protected int maxHealth;
	protected boolean dead;
	protected int damage;
	protected boolean remove;
	
	protected boolean flinching;
	protected long flinchCount;
	
	public Enemy(TileMap tm) {
		super(tm);
		remove = false;
	}
	
	public boolean isDead() { return dead; }
	public boolean shouldRemove() { return remove; }
	
	public int getDamage() { return damage; }
	
	public void hit(int damage) {
		if(dead || flinching) return;
		JukeBox.play("enemyhit");
		health -= damage;
		if(health < 0) health = 0;
		if(health == 0) dead = true;
		if(dead) remove = true;
		flinching = true;
		flinchCount = 0;
	}
	
	public abstract void update(long delay);

	public void reset() {
		// TODO Auto-generated method stub
		
	}
	
}














