/**
 * 
 */
package com.neet.framework.event;

import java.awt.Graphics2D;

import com.neet.framework.state.GameState;

/**
 * An Event used in GameState.
 * 
 * @author Frederic Delorme
 *
 */
public class Event {

	public enum EventStatus {
		NONE,
		BEFORE,
		EXECUTING,
		AFTER,
		DONE;
	}

	protected EventManager eventManager;

	/**
	 * Default status of this event;
	 */
	protected EventStatus status;

	/**
	 * the event count to trigger specific processing.
	 */
	protected static int eventCount = 0;

	/**
	 * The internal code event to be set à constructor.
	 */
	protected String eventCode;

	/**
	 * Default constructor for this event.
	 */
	public Event() {
		this.status = EventStatus.BEFORE;
		this.eventCode = "defaultEvent";
		this.eventManager = null;
	}

	public void setEventManaget(EventManager eventManager) {
		this.eventManager = eventManager;
	}

	/**
	 * execute event.
	 * 
	 * @param state
	 */
	public void run(GameState state) {

	}

	public void upate(long delta) {

	}

	public void draw(Graphics2D g) {

	}

	/**
	 * @return the eventCode
	 */
	public String getEventCode() {
		return eventCode;
	}

	/**
	 * @param eventCode
	 *            the eventCode to set
	 */
	public void setEventCode(String eventCode) {
		this.eventCode = eventCode;
	}

}
