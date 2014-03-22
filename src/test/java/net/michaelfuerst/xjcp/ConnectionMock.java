package net.michaelfuerst.xjcp;

import java.util.HashMap;

/**
 * This class is for testing purpose only.
 * 
 * @author TheDwoon
 * @version 1.0
 * @since 22.03.2014
 *
 */
public final class ConnectionMock implements Connection {
	private String host;
	private Device device;
	
	/** This map is used to link a message with its response. */
	private final HashMap<String, String> responseMap;
	private String defaultResponse;
	
	/**
	 * Creats a new mocked connection.
	 * 
	 * @param host The host we pretend to be.
	 * @param device The device we pretend to be.
	 */
	public ConnectionMock(String host, Device device) {
		this.host = host;
		this.device = device;
		this.responseMap = new HashMap<>();
	}
	
	@Override
	public String send(String message) {
		String respone = responseMap.get(message);
		if (respone == null) {
			respone = defaultResponse;
		}
		
		return respone;
	}

	@Override
	public String getHost() {
		return host;
	}

	/**
	 * Changes the host. 
	 * 
	 * @param host The new host.
	 */
	public void setHost(String host) {
		this.host = host;
	}
	
	@Override
	public Device getDevice() {
		return device;
	}

	/**
	 * Assositates the given key with the given message.
	 * If a client send the key he will recieve the message.
	 * 
	 * @param key The key.
	 * @param message The message.
	 */
	public void addResponse(String key, String message) {
		responseMap.put(key, message);
	}
	
	/**
	 * Removes a key, message pair.
	 * 
	 * @param key The key to remove.
	 */
	public void removeResponse(String key) {
		responseMap.remove(key);
	}
	
	/**
	 * Changes the device.
	 * 
	 * @param device The new device.
	 */
	public void setDevice(Device device) {
		this.device = device;
	}
	
	/**
	 * Gets the response which will be send if nothing matches the input.
	 * Could be null.
	 * 
	 * @return The default response.
	 */
	public String getDefaultResponse() {
		return defaultResponse;
	}

	/**
	 * Changes the default response.
	 * 
	 * @param defaultResponse New default response.
	 */
	public void setDefaultResponse(String defaultResponse) {
		this.defaultResponse = defaultResponse;
	}
}
