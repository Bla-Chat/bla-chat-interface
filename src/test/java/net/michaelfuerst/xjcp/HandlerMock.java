package net.michaelfuerst.xjcp;

import net.michaelfuerst.xjcp.Handler;
import net.michaelfuerst.xjcp.Message;

/**
 * This class is for testing purposes only.
 * 
 * @author TheDwoon
 * @version 1.0
 * @since 23.03.2014
 *
 */
public final class HandlerMock extends Handler {
	private volatile Message message;
	private volatile boolean called;
	
	public HandlerMock() {
		message = null;
		called = false;
	}
	
	@Override
	public void handleMessage(Message msg) {
		called = true;
		message = msg;
	}

	/**
	 * @return The message passed or null.
	 */
	public Message getMessage() {
		return message;
	}

	/**
	 * @return True if it was called, false otherwise.
	 */
	public boolean isCalled() {
		return called;
	}
}
