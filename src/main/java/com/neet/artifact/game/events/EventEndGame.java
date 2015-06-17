/**
 * 
 */
package com.neet.artifact.game.events;

import java.awt.Rectangle;
import java.util.ArrayList;

import com.neet.artifact.game.entity.Player;
import com.neet.artifact.game.entity.PlayerSave;
import com.neet.framework.GamePanel;
import com.neet.framework.event.Event;
import com.neet.framework.state.GameState;

/**
 * Event raised at end of game.
 * 
 * @author Frederic Delorme
 *
 */
public class EventEndGame extends Event {
	protected ArrayList<Rectangle> tb;

	/**
	 * 
	 */
	public EventEndGame() {
		this.eventCode = "EventEndGame";
		tb = new ArrayList<>();
	}

	@Override
	public void run(GameState state) {
		Player player = ((Player) state.getGameObject("player"));
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
			state.getGsm().setActiveState(state.nextState());
		}
	}
}
