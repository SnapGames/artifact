package com.neet.gamestate;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.neet.audio.JukeBox;
import com.neet.entity.Enemy;
import com.neet.entity.EnemyProjectile;
import com.neet.entity.EnergyParticle;
import com.neet.entity.Explosion;
import com.neet.entity.HUD;
import com.neet.entity.Player;
import com.neet.entity.PlayerSave;
import com.neet.entity.Teleport;
import com.neet.entity.Title;
import com.neet.game.GamePanel;
import com.neet.handlers.InputHandler;
import com.neet.tilemap.TileMap;

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

	protected Player player;
	protected TileMap tileMap;
	protected ArrayList<Enemy> enemies;
	protected ArrayList<EnemyProjectile> eprojectiles;
	protected ArrayList<EnergyParticle> energyParticles;
	protected ArrayList<Explosion> explosions;
	protected HUD hud;
	protected BufferedImage hageonText;
	protected Title title;
	protected Title subtitle;
	protected Teleport teleport;
	protected boolean blockInput = false;
	protected int eventCount = 0;
	protected boolean eventStart;
	protected ArrayList<Rectangle> tb;
	protected boolean eventFinish;
	protected boolean eventDead;

	/**
	 * Default constructor to intialize level.
	 * 
	 * @param gsm
	 */
	public LevelGameState(GameStateManager gsm) {
		super(gsm);
	}

	public void update(long delta) {
		// check keys
		handleInput();

		// check if end of level event should start
		if (teleport.contains(player)) {
			eventFinish = blockInput = true;
		}

		// play events
		if (eventStart)
			eventStart();
		if (eventDead)
			eventDead();
		if (eventFinish)
			eventFinish();

		// move title and subtitle
		if (title != null) {
			title.update();
			if (title.shouldRemove())
				title = null;
		}
		if (subtitle != null) {
			subtitle.update();
			if (subtitle.shouldRemove())
				subtitle = null;
		}

		// update player
		player.update(delta);
		if (player.getHealth() == 0 || player.gety() > tileMap.getHeight()) {
			eventDead = blockInput = true;
		}

		// update tilemap
		tileMap.setPosition(GamePanel.WIDTH / 2 - player.getx(),
				GamePanel.HEIGHT / 2 - player.gety());
		tileMap.update();
		tileMap.fixBounds();

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

		// update enemy projectiles
		for (int i = 0; i < eprojectiles.size(); i++) {
			EnemyProjectile ep = eprojectiles.get(i);
			ep.update(delta);
			if (ep.shouldRemove()) {
				eprojectiles.remove(i);
				i--;
			}
		}

		// update explosions
		for (int i = 0; i < explosions.size(); i++) {
			explosions.get(i).update();
			if (explosions.get(i).shouldRemove()) {
				explosions.remove(i);
				i--;
			}
		}

		// update teleport
		teleport.update();

	}

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

		// draw transition boxes
		g.setColor(java.awt.Color.BLACK);
		for (int i = 0; i < tb.size(); i++) {
			g.fill(tb.get(i));
		}

	}

	public void handleInput() {
		if (InputHandler.isPressed(InputHandler.KeyCode.ESCAPE))
			gsm.setPaused(true);
		if (blockInput || player.getHealth() == 0)
			return;
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

	protected void eventStart() {
		eventCount++;
		if (eventCount == 1) {
			tb.clear();
			tb.add(new Rectangle(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT / 2));
			tb.add(new Rectangle(0, 0, GamePanel.WIDTH / 2, GamePanel.HEIGHT));
			tb.add(new Rectangle(0, GamePanel.HEIGHT / 2, GamePanel.WIDTH,
					GamePanel.HEIGHT / 2));
			tb.add(new Rectangle(GamePanel.WIDTH / 2, 0, GamePanel.WIDTH / 2,
					GamePanel.HEIGHT));
		}
		if (eventCount > 1 && eventCount < 60) {
			tb.get(0).height -= 4;
			tb.get(1).width -= 6;
			tb.get(2).y += 4;
			tb.get(3).x += 6;
		}
		if (eventCount == 30)
			title.begin();
		if (eventCount == 60) {
			eventStart = blockInput = false;
			eventCount = 0;
			subtitle.begin();
			tb.clear();
		}
	}

	/**
	 * Process the dead event !
	 */
	protected void eventDead() {
		eventCount++;
		if (eventCount == 1)
			player.setDead();
		if (eventCount == 60) {
			tb.clear();
			tb.add(new Rectangle(GamePanel.WIDTH / 2, GamePanel.HEIGHT / 2, 0,
					0));
		} else if (eventCount > 60) {
			tb.get(0).x -= 6;
			tb.get(0).y -= 4;
			tb.get(0).width += 12;
			tb.get(0).height += 8;
		}
		if (eventCount >= 120) {
			if (player.getLives() == 0) {
				gsm.setActiveState(GameStateManager.MENUSTATE);
			} else {
				eventDead = blockInput = false;
				eventCount = 0;
				player.loseLife();
				reset();
			}
		}
	}

	protected void populateEnemies() {

	}

	public void reset() {
		player.loseLife();
		player.reset();
		populateEnemies();
		blockInput = true;
		eventCount = 0;
		tileMap.setShaking(false, 0);
		eventStart = true;
		eventStart();
		title = new Title(hageonText.getSubimage(0, 0, 178, 20));
		title.sety(60);
		subtitle = new Title(hageonText.getSubimage(0, 33, 91, 13));
		subtitle.sety(85);
	}

	protected int getNextLevel() {
		return 0;
	}

	private void eventFinish() {
		eventCount++;
		if (eventCount == 1) {
			JukeBox.play("teleport");
			player.setTeleporting(true);
			player.stop();
		} else if (eventCount == 120) {
			tb.clear();
			tb.add(new Rectangle(GamePanel.WIDTH / 2, GamePanel.HEIGHT / 2, 0,
					0));
		} else if (eventCount > 120) {
			tb.get(0).x -= 6;
			tb.get(0).y -= 4;
			tb.get(0).width += 12;
			tb.get(0).height += 8;
			JukeBox.stop("teleport");
		}
		if (eventCount == 180) {
			PlayerSave.setHealth(player.getHealth());
			PlayerSave.setLives(player.getLives());
			PlayerSave.setTime(player.getTime());
			gsm.setActiveState(getNextLevel());
		}

	}

}