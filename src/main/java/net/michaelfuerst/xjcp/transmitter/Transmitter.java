package net.michaelfuerst.xjcp.transmitter;

import net.michaelfuerst.xjcp.Handler;

/**
 * This interface defines methods for a {@link Transmitter}.
 * A transmitter is meant to send things using a connection. 
 * It must determine when and how to send a message.
 * 
 * @author TheDwoon
 * @version 1.0
 * @since 23.03.2014
 *
 */
public interface Transmitter {
	/**
	 * Pushes the message at the end of a queue.
	 * The transmitter will decide when it will be send.
	 * Either way the handler will be called if the server responded to your 
	 * message.
	 * 
	 * @param message The message to send. 
	 * @param handler The handler to call or null if not needed.
	 */
	void submit(String message, Handler handler);
	
	/**
	 * Starts the transmitter.
	 */
	void start();
	/**
	 * Stops the transmitter. 
	 * Jobs currently in progress will be finished first, 
	 * but no new jobs will start.
	 */
	void stop();
}
