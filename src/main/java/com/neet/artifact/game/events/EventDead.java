/**
 * 
 */
package com.neet.artifact.game.events;

import java.awt.Rectangle;
import java.util.ArrayList;

import com.neet.artifact.game.entity.Player;
import com.neet.artifact.game.gamestate.ArtifactGameStateManager;
import com.webcontext.game.framework.GamePanel;
import com.webcontext.game.framework.audio.JukeBox;
import com.webcontext.game.framework.event.Event;
import com.webcontext.game.framework.state.GameState;

/**
 * Event raised at Player's dead.
 * 
 * @author Frédéric Delorme
 *
 */
public class EventDead extends Event {

	protected ArrayList<Rectangle> tb;

	/**
	 * 
	 */
	public EventDead() {
		this.eventCode = "EventDead";
		tb = new ArrayList<>();
	}

	@Override
	public void run(GameState state) {
		Player player = ((Player) state.getGameObject("player"));
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
			if (player.getLives() <= 0) {
				this.status=EventStatus.DONE;
				if(state.containsAttribute("music.clip")){
					JukeBox.stop((String)state.getAttribute("music.clip"));
				}
				state.getGsm().setActiveState(ArtifactGameStateManager.ACIDSTATE);
			} else {
				state.setAttribute("blockInput", false);
				eventManager.resetCount();
				player.loseLife();
				this.status=EventStatus.DONE;
				state.reset();
			}
		}
	}
}
