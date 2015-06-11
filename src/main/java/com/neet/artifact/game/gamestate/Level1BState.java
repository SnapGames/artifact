package com.neet.artifact.game.gamestate;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.neet.artifact.framework.audio.JukeBox;
import com.neet.artifact.framework.entity.Enemy;
import com.neet.artifact.framework.gfx.Background;
import com.neet.artifact.framework.gfx.tilemap.TileMap;
import com.neet.artifact.framework.state.GameStateManager;
import com.neet.artifact.framework.state.LevelGameState;
import com.neet.artifact.game.entity.EnemyProjectile;
import com.neet.artifact.game.entity.EnergyParticle;
import com.neet.artifact.game.entity.Explosion;
import com.neet.artifact.game.entity.HUD;
import com.neet.artifact.game.entity.Player;
import com.neet.artifact.game.entity.PlayerSave;
import com.neet.artifact.game.entity.Teleport;
import com.neet.artifact.game.entity.Title;
import com.neet.artifact.game.entity.enemies.Gazer;
import com.neet.artifact.game.entity.enemies.GelPop;
import com.neet.artifact.game.entity.enemies.Tengu;

/**
 * A second level to discover more things
 *
 * @author ForeignGuyMike(https://www.youtube.com/channel/UC_IV37n-uBpRp64hQIwywWQ)
 * @author Frédéric Delorme<frederic.delorme@web-context.com>(refactoring)
 * 
 */
public class Level1BState extends LevelGameState {

	/**
	 * Background temple.
	 */
	Background temple;

	/**
	 * A new event to managed is Earth Quake !
	 */
	boolean eventQuake;

	/**
	 * Level1 constructor.
	 * 
	 * @param gsm
	 */
	public Level1BState(GameStateManager gsm) {
		super(gsm);
		init();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neet.artifact.gamestate.GameState#init()
	 */
	public void init() {

		// backgrounds
		temple = new Background("/Backgrounds/temple.gif", 0.5, 0);

		// tilemap
		tileMap = new TileMap(30);
		tileMap.loadTiles("/Tilesets/ruinstileset.gif");
		tileMap.loadMap("/Maps/level1b.map");
		tileMap.setPosition(140, 0);
		tileMap.setTween(1);

		// player
		player = new Player(tileMap);
		player.setPosition(300, 131);
		player.setHealth(PlayerSave.getHealth());
		player.setLives(PlayerSave.getLives());
		player.setTime(PlayerSave.getTime());

		// enemies
		enemies = new ArrayList<Enemy>();
		eprojectiles = new ArrayList<EnemyProjectile>();
		populateEnemies();

		// energy particle
		energyParticles = new ArrayList<EnergyParticle>();

		player.init(enemies, energyParticles);

		// explosions
		explosions = new ArrayList<Explosion>();

		// hud
		hud = new HUD(player);

		// title and subtitle
		try {
			hageonText = ImageIO.read(getClass().getResourceAsStream(
					"/HUD/HageonTemple.gif"));
			title = new Title(hageonText.getSubimage(0, 0, 178, 20));
			title.sety(60);
			subtitle = new Title(hageonText.getSubimage(0, 33, 91, 13));
			subtitle.sety(85);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// teleport
		teleport = new Teleport(tileMap);
		teleport.setPosition(2850, 371);

		// start event
		eventStart = true;
		tb = new ArrayList<Rectangle>();
		eventStart();

		// sfx
		JukeBox.load("/SFX/teleport.mp3", "teleport");
		JukeBox.load("/SFX/explode.mp3", "explode");
		JukeBox.load("/SFX/enemyhit.mp3", "enemyhit");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neet.artifact.gamestate.LevelGameState#populateEnemies()
	 */
	public void populateEnemies() {
		enemies.clear();
		GelPop gp;
		Gazer g;
		Tengu t;
		gp = new GelPop(tileMap, player);
		gp.setPosition(750, 100);
		enemies.add(gp);
		gp = new GelPop(tileMap, player);
		gp.setPosition(900, 150);
		enemies.add(gp);
		gp = new GelPop(tileMap, player);
		gp.setPosition(1320, 250);
		enemies.add(gp);
		gp = new GelPop(tileMap, player);
		gp.setPosition(1570, 160);
		enemies.add(gp);
		gp = new GelPop(tileMap, player);
		gp.setPosition(1590, 160);
		enemies.add(gp);
		gp = new GelPop(tileMap, player);
		gp.setPosition(2600, 370);
		enemies.add(gp);
		gp = new GelPop(tileMap, player);
		gp.setPosition(2620, 370);
		enemies.add(gp);
		gp = new GelPop(tileMap, player);
		gp.setPosition(2640, 370);
		enemies.add(gp);

		g = new Gazer(tileMap);
		g.setPosition(904, 130);
		enemies.add(g);
		g = new Gazer(tileMap);
		g.setPosition(1080, 270);
		enemies.add(g);
		g = new Gazer(tileMap);
		g.setPosition(1200, 270);
		enemies.add(g);
		g = new Gazer(tileMap);
		g.setPosition(1704, 300);
		enemies.add(g);

		t = new Tengu(tileMap, player, enemies);
		t.setPosition(1900, 580);
		enemies.add(t);
		t = new Tengu(tileMap, player, enemies);
		t.setPosition(2330, 550);
		enemies.add(t);
		t = new Tengu(tileMap, player, enemies);
		t.setPosition(2400, 490);
		enemies.add(t);
		t = new Tengu(tileMap, player, enemies);
		t.setPosition(2457, 430);
		enemies.add(t);

	}

	// /////////////////////////////////////////////////////
	// ////////////////// EVENTS
	// /////////////////////////////////////////////////////

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neet.artifact.gamestate.LevelGameState#reset()
	 */
	public void reset() {
		super.reset();
		player.setPosition(300, 131);
		tileMap.setShaking(false, 0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neet.artifact.gamestate.LevelGameState#update()
	 */
	public void update(long delay) {
		super.update(delay);
		// check if quake event should start
		if (player.getx() > 2175 && !tileMap.isShaking()) {
			eventQuake = blockInput = true;
		}

		if (eventQuake)
			eventQuake();

		// move backgrounds
		temple.setPosition(tileMap.getx(), tileMap.gety());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neet.artifact.gamestate.LevelGameState#draw(java.awt.Graphics2D)
	 */
	public void draw(Graphics2D g) {
		super.draw(g);
		// draw background
		temple.draw(g);
	}

	/**
	 * manage the new Earthquake event.
	 */
	public void eventQuake() {
		eventCount++;
		if (eventCount == 1) {
			player.stop();
			player.setPosition(2175, player.gety());
		}
		if (eventCount == 60) {
			player.setEmote(Player.CONFUSED);
		}
		if (eventCount == 120)
			player.setEmote(Player.NONE);
		if (eventCount == 150)
			tileMap.setShaking(true, 10);
		if (eventCount == 180)
			player.setEmote(Player.SURPRISED);
		if (eventCount == 300) {
			player.setEmote(Player.NONE);
			eventQuake = blockInput = false;
			eventCount = 0;
		}
	}

	public int getNextState() {
		return GameStateManager.LEVEL1CSTATE;
	}

}