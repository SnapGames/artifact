/**
 * 
 */
package com.neet.artifact.game.events;

import java.awt.Rectangle;
import java.util.ArrayList;

import com.neet.artifact.game.entity.Player;
import com.neet.artifact.game.entity.PlayerSave;
import com.neet.framework.GamePanel;
import com.neet.framework.audio.JukeBox;
import com.neet.framework.event.Event;
import com.neet.framework.state.GameState;

/**
 * Event raised at Level Finish.
 * 
 * @author Frederic Delorme
 *
 */
public class EventFinish extends Event {

	protected ArrayList<Rectangle> tb;

	/**
	 * 
	 */
	public EventFinish() {
		this.eventCode = "EventFinish";
		tb = new ArrayList<>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.neet.framework.event.Event#run(com.neet.framework.state.GameState)
	 */
	@Override
	public void run(GameState state) {
		eventCount++;
		Player player = (Player) (state.getGameObject("player"));
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
			this.status = EventStatus.DONE;
			state.getGsm().setActiveState(state.nextState());
		}

	}

}
