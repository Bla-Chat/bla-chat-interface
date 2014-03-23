package net.michaelfuerst.xjcp.transmitter;

import java.util.List;

import net.michaelfuerst.xjcp.Handler;
import net.michaelfuerst.xjcp.Message;

/**
 * This class is utility class for the transmitter to store their data in.
 * 
 * @author TheDwoon
 * @version 1.0
 * @since 23.03.2014
 *
 */
class Entry {
	private final String message;
	private final Handler handler;
	
	/**
	 * Creates a new Entry.
	 * 
	 * @param message The message to store.
	 * @param handler The handler to store.
	 */
	protected Entry(final String message, final Handler handler) {
		 this.message = message;
		 this.handler = handler;
	}

	/**
	 * Sends the messages to the handler if it exists.
	 * 
	 * @param messages The messages to pass.
	 */
	public final void invokeHandler(List<Message> messages) {
		if (handler != null) {			
			for (Message m : messages) {
				handler.sendMessage(m);
			}
		}
	}

	/**
	 * Sends the message to the handler if it exists.
	 * 
	 * @param m The message to pass.
	 */
	public final void invokeHandler(Message m) {
		if (handler != null) {
			handler.sendMessage(m);
		}
	}

	/**
	 * @return The stored message.
	 */
	public final String getMessage() {
		return message;
	}

	/**
	 * @return The handler to call.
	 */
	public final Handler getHandler() {
		return handler;
	}
}
