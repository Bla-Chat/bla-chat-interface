package net.michaelfuerst.xjcp.transmitter;

import net.michaelfuerst.xjcp.MessageParser;
import net.michaelfuerst.xjcp.connection.Connection;
import net.michaelfuerst.xjcp.device.Device;

/**
 * This class transmits messages only if their is a WLAN or LAN connection.
 * 
 * @author TheDwoon
 * @version 1.0
 * @since 23.03.2014
 *
 */
public final class LANTransmitter extends QueuedTransmitter {	

	public LANTransmitter(final Connection connection, final MessageParser parser, final int sleep) {
		super(parser, connection, sleep);		
	}
	
	@Override
	protected boolean shouldSend() {
		Device device = getConnection().getDevice();
		
		return device.hasWLAN() || device.hasLAN();
	}	
}
