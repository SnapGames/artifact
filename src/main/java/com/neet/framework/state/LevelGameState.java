package com.neet.framework.state;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.neet.artifact.game.entity.EnemyProjectile;
import com.neet.artifact.game.entity.EnergyParticle;
import com.neet.artifact.game.entity.Explosion;
import com.neet.artifact.game.entity.HUD;
import com.neet.artifact.game.entity.Player;
import com.neet.artifact.game.entity.Teleport;
import com.neet.artifact.game.entity.Title;
import com.neet.framework.GamePanel;
import com.neet.framework.entity.Enemy;
import com.neet.framework.event.EventManager;
import com.neet.framework.gfx.tilemap.TileMap;
import com.neet.framework.handler.InputHandler;

/**
 * The inherited default class for all Level for this game, implementing basic
 * common tasks.
 * 
 * @author 
 *         ForeignGuyMike(https://www.youtube.com/channel/UC_IV37n-uBpRp64hQIwywWQ
 *         )
 * @author Frédéric Delorme<frederic.delorme@web-context.com>(refactoring)
 *
 */
public abstract class LevelGameState extends GameState {
	/**
	 * Main player
	 */
	protected Player player;
	/**
	 * Tilemap to render full game.
	 */
	protected TileMap tileMap;
	/**
	 * All enemies attacking our hero.
	 */
	protected ArrayList<Enemy> enemies;
	/**
	 * the projectiles sent by enemies.
	 */
	protected ArrayList<EnemyProjectile> eprojectiles;
	/**
	 * Particules to be rendered.
	 */
	protected ArrayList<EnergyParticle> energyParticles;
	/**
	 * Explosions effects.
	 */
	protected ArrayList<Explosion> explosions;
	/**
	 * Head Up Display, to render, lifes, time, score.
	 */
	protected HUD hud;

	/**
	 * Image buffered to render some titles.
	 */
	protected BufferedImage hageonText;

	protected Title title;
	protected Title subtitle;

	protected Teleport teleport;
	/**
	 * Event manager to manage Event.
	 */
	protected EventManager eventManager;

	// TODO remove this event count after migrating old event to EventManager.
	protected int eventCount = 0;

	protected ArrayList<Rectangle> tb;

	/**
	 * Default constructor to initialize level.
	 * 
	 * @param gsm
	 */
	public LevelGameState(GameStateManager gsm) {
		super(gsm);
		eventManager = new EventManager();
		// Register event Start.
		registerEvents();
		attributes.put("blockInput", false);
	}

	/**
	 * Register Event for this level.
	 */
	protected void registerEvents() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neet.framework.state.GameState#update(long)
	 */
	@Override
	public void update(long delta) {
		// check keys
		handleInput();

		// check if end of level event should start
		if (teleport.contains(player)) {
			eventManager.activate("EventFinish");
			attributes.put("blockInput", true);
		}

		eventManager.process(this);

		updateWelcomeTitle(delta);

		// update player
		player.update(delta);
		if (player.getHealth() == 0 || player.gety() > tileMap.getHeight()) {
			eventManager.activate("EventDead");
			attributes.put("blockInput", true);
		}
		// updates
		updateTileMap(delta);
		updateEnemies(delta);
		updateProjectiles(delta);
		updateExplosions(delta);
		updateTeleport(delta);

	}

	/**
	 * @param delta
	 */
	private void updateWelcomeTitle(long delta) {
		// move title and subtitle
		if (title != null) {
			title.update(delta);
			if (title.shouldRemove())
				title = null;
		}
		if (subtitle != null) {
			subtitle.update(delta);
			if (subtitle.shouldRemove())
				subtitle = null;
		}
	}

	/**
	 * @param delta
	 */
	private void updateTileMap(long delta) {
		tileMap.setPosition(GamePanel.WIDTH / 2 - player.getx(),
				GamePanel.HEIGHT / 2 - player.gety());
		tileMap.update(delta);
		tileMap.fixBounds();
	}

	/**
	 * @param delta
	 */
	private void updateTeleport(long delta) {
		// update teleport
		teleport.update(delta);
	}

	/**
	 * @param delta
	 */
	private void updateExplosions(long delta) {
		// update explosions
		for (int i = 0; i < explosions.size(); i++) {
			explosions.get(i).update(delta);
			if (explosions.get(i).shouldRemove()) {
				explosions.remove(i);
				i--;
			}
		}
	}

	/**
	 * @param delta
	 */
	private void updateProjectiles(long delta) {
		// update enemy projectiles
		for (int i = 0; i < eprojectiles.size(); i++) {
			EnemyProjectile ep = eprojectiles.get(i);
			ep.update(delta);
			if (ep.shouldRemove()) {
				eprojectiles.remove(i);
				i--;
			}
		}
	}

	/**
	 * @param delta
	 */
	private void updateEnemies(long delta) {
		// update enemies
		for (int i = 0; i < enemies.size(); i++) {
			Enemy e = enemies.get(i);
			e.update(delta);
			if (e.isDead()) {
				enemies.remove(i);
				i--;
				explosions.add(new Explosion(tileMap, e.getx(), e.gety()));
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neet.framework.state.GameState#draw(java.awt.Graphics2D)
	 */
	@Override
	public void draw(Graphics2D g) {

		// draw tilemap
		tileMap.draw(g);

		// draw enemies
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).draw(g);
		}

		// draw enemy projectiles
		for (int i = 0; i < eprojectiles.size(); i++) {
			eprojectiles.get(i).draw(g);
		}

		// draw explosions
		for (int i = 0; i < explosions.size(); i++) {
			explosions.get(i).draw(g);
		}

		// draw player
		player.draw(g);

		// draw teleport
		teleport.draw(g);

		// draw hud
		hud.draw(g);

		// draw title
		if (title != null)
			title.draw(g);
		if (subtitle != null)
			subtitle.draw(g);

		eventManager.draw(g);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neet.framework.state.GameState#handleInput()
	 */
	@Override
	public void handleInput() {
		if (InputHandler.isPressed(InputHandler.KeyCode.ESCAPE))
			gsm.setPaused(true);
		if ((Boolean) attributes.get("blockInput") || player.getHealth() == 0) {
			return;
		}
		player.setUp(InputHandler.getKeyState(InputHandler.KeyCode.UP));
		player.setLeft(InputHandler.getKeyState(InputHandler.KeyCode.LEFT));
		player.setDown(InputHandler.getKeyState(InputHandler.KeyCode.DOWN));
		player.setRight(InputHandler.getKeyState(InputHandler.KeyCode.RIGHT));
		player.setJumping(InputHandler
				.getKeyState(InputHandler.KeyCode.BUTTON1));
		player.setDashing(InputHandler
				.getKeyState(InputHandler.KeyCode.BUTTON2));
		if (InputHandler.isPressed(InputHandler.KeyCode.BUTTON3))
			player.setAttacking();
		if (InputHandler.isPressed(InputHandler.KeyCode.BUTTON4))
			player.setCharging();
	}

	protected void populateEnemies() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neet.framework.state.GameState#reset()
	 */
	@Override
	public void reset() {
		player.loseLife();
		player.reset();
		populateEnemies();
		attributes.put("blockInput", true);
		eventCount = 0;
		tileMap.setShaking(false, 0);

		eventManager.resetEvents();
		eventManager.activate("EventStart");
		eventManager.process(this);

		gameObjects.put("player", player);
		gameObjects.put("tilemap", tileMap);
		gameObjects.put("title", title);
		gameObjects.put("subtitle", subtitle);

	}

	protected String getNextLevel() {
		return "";
	}

	public void load(String levelFile) {

	}

}