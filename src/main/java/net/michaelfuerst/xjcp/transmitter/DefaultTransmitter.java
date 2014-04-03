package net.michaelfuerst.xjcp.transmitter;

import net.michaelfuerst.xjcp.MessageParser;
import net.michaelfuerst.xjcp.connection.Connection;

/**
 * This class transmits messages whenever it has access to the interntet.
 * 
 * @author TheDwoon
 * @version 1.0
 * @since 23.03.2014
 *
 */
public final class DefaultTransmitter extends QueuedTransmitter {
	
	/**
	 * Creates a new DefaultTransmitter.
	 * 
	 * @param connection The connection we should use.
	 * @param parser The parser we should use.
	 * @param sleep The amount of ms we should wait to send again.
	 */
	public DefaultTransmitter(final Connection connection, final MessageParser parser, final int sleep) {
		super(parser, connection, sleep);
	}

	@Override
	protected boolean shouldSend() {
		return getConnection().getDevice().hasInternet();
	}
}
