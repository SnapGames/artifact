package com.neet.framework.handler;

import java.awt.event.KeyEvent;

/**
 * this class contains a boolean array of current and previous key state for the
 * 10 keys that are used for this game. a key k is down when keyState[k] is
 * true.
 * 
 * @author ForeignGuyMike(https://www.youtube.com/channel/UC_IV37n-uBpRp64hQIwywWQ)
 * @author Frédéric Delorme<frederic.delorme@web-context.com>(refactoring)
 *
 */
public class InputHandler {

	public static final int NUM_KEYS = 16;

	private static boolean keyState[] = new boolean[NUM_KEYS];
	private static boolean prevKeyState[] = new boolean[NUM_KEYS];

	/**
	 * List of available Keys for input handler.
	 * 
	 * @author frederic.delorme@web-context.com
	 *
	 */
	public enum KeyCode {
		UP(0),
		LEFT(1),
		DOWN(2),
		RIGHT(3),
		BUTTON1(4),
		BUTTON2(5),
		BUTTON3(6),
		BUTTON4(7),
		ENTER(8),
		ESCAPE(9);

		/**
		 * Internal key code.
		 */
		private int keyCode;

		/**
		 * private constructor to initialize specific value
		 * 
		 * @param i
		 */
		private KeyCode(int i) {
			keyCode = i;
		}

		public int code() {
			return keyCode;
		}
	}

	/**
	 * Verify Keys.
	 * 
	 * @param i
	 * @param b
	 */
	public static void keySet(int i, boolean b) {
		switch (i) {
		case KeyEvent.VK_UP:
			keyState[KeyCode.UP.code()] = b;
			break;
		case KeyEvent.VK_LEFT:
			keyState[KeyCode.LEFT.code()] = b;
			break;
		case KeyEvent.VK_DOWN:
			keyState[KeyCode.DOWN.code()] = b;
			break;
		case KeyEvent.VK_RIGHT:
			keyState[KeyCode.RIGHT.code()] = b;
			break;
		case KeyEvent.VK_W:
			keyState[KeyCode.BUTTON1.code()] = b;
			break;
		case KeyEvent.VK_E:
			keyState[KeyCode.BUTTON2.code()] = b;
			break;
		case KeyEvent.VK_R:
			keyState[KeyCode.BUTTON3.code()] = b;
			break;
		case KeyEvent.VK_F:
			keyState[KeyCode.BUTTON4.code()] = b;
			break;
		case KeyEvent.VK_ENTER:
			keyState[KeyCode.ENTER.code()] = b;
			break;
		case KeyEvent.VK_ESCAPE:
			keyState[KeyCode.ESCAPE.code()] = b;
			break;
		}
	}

	/**
	 * update key states
	 */
	public static void update(long delta) {
		for (int i = 0; i < NUM_KEYS; i++) {
			prevKeyState[i] = keyState[i];
		}
	}

	/**
	 * is specific <code>key</code> is pressed ?
	 * 
	 * @param key
	 *            key number to verirfy.
	 * @return
	 */
	public static boolean isPressed(KeyCode key) {
		return keyState[key.code()] && !prevKeyState[key.code()];
	}

	/**
	 * verify if any key was pressed.
	 * 
	 * @return
	 */
	public static boolean anyKeyPress() {
		for (int i = 0; i < NUM_KEYS; i++) {
			if (keyState[i])
				return true;
		}
		return false;
	}

	/**
	 * Retrieve <code>key</code> state
	 * 
	 * @param key
	 *            a KeyCode to retrieve state for.
	 * @return
	 */
	public static boolean getKeyState(KeyCode key) {
		return keyState[key.code()];
	}

	public static boolean getPreviousState(KeyCode key) {
		return prevKeyState[key.code()];
	}

}
