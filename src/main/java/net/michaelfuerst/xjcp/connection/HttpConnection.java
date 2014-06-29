package net.michaelfuerst.xjcp.connection;

import net.michaelfuerst.xjcp.device.Device;
import net.michaelfuerst.xjcp.web.HTTPService;
import net.michaelfuerst.xjcp.web.HttpParameter;

/**
 * HTTP connection.
 * 
 * @author TheDwoon
 * @since 29.06.2014
 * @version 1.0.0
 *
 */
public final class HttpConnection implements Connection {
	private final String host;
	private final Device device;
	
	public HttpConnection(String host, Device device) {
		this.host = host;
		this.device = device;
	}
	
	@Override
	public String send(String message) {
		HttpParameter parameter = new HttpParameter("m", message);
		
		return HTTPService.sendPostRequest(host, parameter);
	}

	@Override
	public String getHost() {
		return host;
	}

	@Override
	public Device getDevice() {
		return device;
	}

}
