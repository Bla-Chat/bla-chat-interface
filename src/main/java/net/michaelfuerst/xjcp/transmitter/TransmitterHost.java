package net.michaelfuerst.xjcp.transmitter;

import net.michaelfuerst.xjcp.Handler;
import net.michaelfuerst.xjcp.MessageParser;
import net.michaelfuerst.xjcp.connection.Connection;

/**
 * This abstract class holds a list of Transmitter.
 * 
 * @author TheDwoon
 * @version 1.0
 * @since 23.03.2014
 *
 */
public final class TransmitterHost implements Transmitter {
	private static final int MOBILEPACKETLIMIT = 1024;
	private static final int DEFAULT_TRANSMITTER = 0;
	private static final int LAN_TRANSMITTER = 1;
	
	private final Transmitter[] transmitter;
	
	/**
	 * Creates a new TransmitterHost.
	 * 
	 * @param connection The connection used to transmit.
	 * @param parser The parser used to parse the responses.
	 */
	public TransmitterHost(final Connection connection, final MessageParser parser) {
		this.transmitter = new Transmitter[2];
		
		this.transmitter[0] = new DefaultTransmitter(connection, parser);
		this.transmitter[1] = new LANTransmitter(connection, parser);
	}

	@Override
	public void submit(String message, Handler handler) {
		if (message == null) {
			return;
		}
		
		int size = message.getBytes().length;
		
		if (size <= MOBILEPACKETLIMIT) {
			transmitter[DEFAULT_TRANSMITTER].submit(message, handler);
		} else {
			transmitter[LAN_TRANSMITTER].submit(message, handler);
		}
	}

	@Override
	public void start() {
		for (Transmitter t : transmitter) {
			t.start();
		}
	}

	@Override
	public void stop() {
		for (Transmitter t : transmitter) {
			t.stop();
		}
	}
}
