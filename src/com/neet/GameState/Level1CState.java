package com.neet.GameState;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import com.neet.Audio.JukeBox;
import com.neet.Entity.Enemy;
import com.neet.Entity.EnergyParticle;
import com.neet.Entity.Explosion;
import com.neet.Entity.HUD;
import com.neet.Entity.Player;
import com.neet.Entity.PlayerSave;
import com.neet.Entity.Portal;
import com.neet.Entity.Spirit;
import com.neet.Entity.Artfact.BottomLeftPiece;
import com.neet.Entity.Artfact.BottomRightPiece;
import com.neet.Entity.Artfact.TopLeftPiece;
import com.neet.Entity.Artfact.TopRightPiece;
import com.neet.Entity.Enemies.DarkEnergy;
import com.neet.Main.GamePanel;
import com.neet.TileMap.Background;
import com.neet.TileMap.TileMap;

/**
 * The final level where you fight against the boss !
 *
 * @author ForeignGuyMike(https://www.youtube.com/channel/UC_IV37n-uBpRp64hQIwywWQ)
 * @author Frédéric Delorme<frederic.delorme@web-context.com>(refactoring)
 *
 */
public class Level1CState extends LevelGameState {

	private Background temple;

	private TopLeftPiece tlp;
	private TopRightPiece trp;
	private BottomLeftPiece blp;
	private BottomRightPiece brp;
	private Portal portal;

	private Spirit spirit;

	// events
	private boolean eventPortal;
	private boolean flash;
	private boolean eventBossDead;

	/**
	 * This is the Boss Level !
	 * 
	 * @param gsm
	 */
	public Level1CState(GameStateManager gsm) {
		super(gsm);
		init();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neet.GameState.GameState#init()
	 */
	public void init() {

		// backgrounds
		temple = new Background("/Backgrounds/temple.gif", 0.5, 0);

		// tilemap
		tileMap = new TileMap(30);
		tileMap.loadTiles("/Tilesets/ruinstileset.gif");
		tileMap.loadMap("/Maps/level1c.map");
		tileMap.setPosition(140, 0);
		tileMap.setTween(1);

		// player
		player = new Player(tileMap);
		player.setPosition(50, 190);
		player.setHealth(PlayerSave.getHealth());
		player.setLives(PlayerSave.getLives());
		player.setTime(PlayerSave.getTime());

		// explosions
		explosions = new ArrayList<Explosion>();

		// enemies
		enemies = new ArrayList<Enemy>();
		populateEnemies();

		// energy particle
		energyParticles = new ArrayList<EnergyParticle>();

		// init player
		player.init(enemies, energyParticles);

		// hud
		hud = new HUD(player);

		// portal
		portal = new Portal(tileMap);
		portal.setPosition(160, 154);

		// artifact
		tlp = new TopLeftPiece(tileMap);
		trp = new TopRightPiece(tileMap);
		blp = new BottomLeftPiece(tileMap);
		brp = new BottomRightPiece(tileMap);
		tlp.setPosition(152, 102);
		trp.setPosition(162, 102);
		blp.setPosition(152, 112);
		brp.setPosition(162, 112);

		// start event
		eventStart = blockInput = true;
		tb = new ArrayList<Rectangle>();
		eventStart();

		// sfx
		JukeBox.load("/SFX/teleport.mp3", "teleport");
		JukeBox.load("/SFX/explode.mp3", "explode");
		JukeBox.load("/SFX/enemyhit.mp3", "enemyhit");

		// music
		JukeBox.load("/Music/level1boss.mp3", "level1boss");
		JukeBox.load("/Music/fanfare.mp3", "fanfare");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neet.GameState.LevelGameState#populateEnemies()
	 */
	public void populateEnemies() {
		enemies.clear();
		spirit = new Spirit(tileMap, player, enemies, explosions);
		spirit.setPosition(-9000, 9000);
		enemies.add(spirit);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neet.GameState.LevelGameState#update()
	 */
	public void update() {
		super.update();
		// check if boss dead event should start
		if (!eventFinish && spirit.isDead()) {
			eventBossDead = blockInput = true;
		}

		if (eventFinish)
			eventFinish();
		if (eventPortal)
			eventPortal();
		if (eventBossDead)
			eventBossDead();

		// update artifacts
		tlp.update();
		trp.update();
		blp.update();
		brp.update();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neet.GameState.LevelGameState#draw(java.awt.Graphics2D)
	 */
	public void draw(Graphics2D g) {
		super.draw(g);
		// draw background
		temple.draw(g);

		// draw artifact
		tlp.draw(g);
		trp.draw(g);
		blp.draw(g);
		brp.draw(g);

		// draw transition boxes
		g.setColor(java.awt.Color.BLACK);
		for (int i = 0; i < tb.size(); i++) {
			g.fill(tb.get(i));
		}

		// flash
		if (flash) {
			g.setColor(java.awt.Color.WHITE);
			g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		}

	}

	// /////////////////////////////////////////////////////
	// ////////////////// EVENTS
	// /////////////////////////////////////////////////////

	// reset level
	public void reset() {
		super.reset();
		player.setPosition(50, 190);
		populateEnemies();
		eventStart = blockInput = true;
		eventCount = 0;
		eventStart();
	}

	/**
	 * This is End !!!
	 */
	private void eventFinish() {
		eventCount++;
		if (eventCount == 1) {
			tb.clear();
			tb.add(new Rectangle(GamePanel.WIDTH / 2, GamePanel.HEIGHT / 2, 0,
					0));
		} else if (eventCount > 1) {
			tb.get(0).x -= 6;
			tb.get(0).y -= 4;
			tb.get(0).width += 12;
			tb.get(0).height += 8;
		}
		if (eventCount == 60) {
			PlayerSave.setHealth(player.getHealth());
			PlayerSave.setLives(player.getLives());
			PlayerSave.setTime(player.getTime());
			gsm.setState(GameStateManager.ACIDSTATE);
		}

	}

	/**
	 * Event Portal processing !
	 */
	private void eventPortal() {
		eventCount++;
		if (eventCount == 1) {
			if (portal.isOpened()) {
				eventCount = 360;
			}
		}
		if (eventCount > 60 && eventCount < 180) {
			energyParticles.add(new EnergyParticle(tileMap, 157, 107,
					(int) (Math.random() * 4)));
		}
		if (eventCount >= 160 && eventCount <= 180) {
			if (eventCount % 4 == 0 || eventCount % 4 == 1)
				flash = true;
			else
				flash = false;
		}
		if (eventCount == 181) {
			tileMap.setShaking(false, 0);
			flash = false;
			tlp.setVector(-0.3, -0.3);
			trp.setVector(0.3, -0.3);
			blp.setVector(-0.3, 0.3);
			brp.setVector(0.3, 0.3);
			player.setEmote(Player.SURPRISED);
		}
		if (eventCount == 240) {
			tlp.setVector(0, -5);
			trp.setVector(0, -5);
			blp.setVector(0, -5);
			brp.setVector(0, -5);
		}
		if (eventCount == 300) {
			player.setEmote(Player.NONE);
			portal.setOpening();
		}
		if (eventCount == 360) {
			flash = true;
			spirit.setPosition(160, 160);
			DarkEnergy de;
			for (int i = 0; i < 20; i++) {
				de = new DarkEnergy(tileMap);
				de.setPosition(160, 160);
				de.setVector(Math.random() * 10 - 5, Math.random() * -2 - 3);
				enemies.add(de);
			}
		}
		if (eventCount == 362) {
			flash = false;
			JukeBox.loop("level1boss", 0, 60000,
					JukeBox.getFrames("level1boss") - 4000);
		}
		if (eventCount == 420) {
			eventPortal = blockInput = false;
			eventCount = 0;
			spirit.setActive();
		}

	}

	public void eventBossDead() {
		eventCount++;
		if (eventCount == 1) {
			player.stop();
			JukeBox.stop("level1boss");
			enemies.clear();
		}
		if (eventCount <= 120 && eventCount % 15 == 0) {
			explosions
					.add(new Explosion(tileMap, spirit.getx(), spirit.gety()));
			JukeBox.play("explode");
		}
		if (eventCount == 180) {
			JukeBox.play("fanfare");
		}
		if (eventCount == 390) {
			eventBossDead = false;
			eventCount = 0;
			eventFinish = true;
		}
	}

}