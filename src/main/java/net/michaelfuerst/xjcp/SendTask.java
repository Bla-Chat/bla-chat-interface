package net.michaelfuerst.xjcp;

/**
 * A simple class used for sending information using a connection.
 * 
 * @author Daniel
 * @version 1.0
 * @since 21.3.14
 */
final class SendTask implements Runnable {
	private final String message;
	private final Connection connection;
	private final MessageParser parser;
	private final Handler handler;
	
	/**
	 * Creates a new SendTask.
	 * If the message is null it won't send anything 
	 * and no exception will be created.
	 *  
	 * @param message The message to be send (may be null).
	 * @param connection The connection used to send it.
	 * @param handler The handler to invoke using the response or null.
	 */
	protected SendTask(final String message, final Connection connection, 
			final MessageParser parser, final Handler handler) {	
		if (connection == null) {
			throw new IllegalArgumentException("connection was null");
		}
		if (handler != null && parser == null) {
			throw new IllegalArgumentException("parser was null but a handler was given.");
		}
		
		this.message = message;
		this.connection = connection;
		this.parser = parser;
		this.handler = handler;
	}
	
	@Override
	public void run() {
		if (message == null) {
			return;
		}
		
		String response = connection.send(message);
		
		if (handler != null) {
			handler.sendMessage(parser.parseMessage(response));
		}
	}
}
