package net.michaelfuerst.xjcp.connection;

import net.michaelfuerst.xjcp.device.Device;

/**
 * This interface defines a connection to a server.
 * A connection MUST BE threadsafe.
 * 
 * @author TheDwoon
 * @version 1.1
 * @since 21.3.14
 *
 */
public interface Connection {
	/**
	 * Sends a message. 
	 * 
	 * @param message The message.
	 * @return The response from the server.
	 */
	String send(String message);
	/**
	 * Gets a string representation of the host we are connected to.
	 * 
	 * @return The host we are connected to.
	 */
	String getHost();	
	/**
	 * Gets general information about the {@link Device} we are running on.
	 *  
	 * @return The {@link Device} using this connection.
	 */
	Device getDevice();
}
