package net.michaelfuerst.xjcp;

/**
 * This interface defines a connection to a server.
 * 
 * @author Daniel
 * @version 1.0
 * @since 21.3.14
 *
 */
public interface Connection {
	/**
	 * Sends a message. 
	 * 
	 * @param message The message.
	 * @return The resonse from the server.
	 */
	String send(String message);
	/**
	 * Gets a string representation of the host we are connected to.
	 * 
	 * @return The host we are connected to.
	 */
	String getHost();
}
