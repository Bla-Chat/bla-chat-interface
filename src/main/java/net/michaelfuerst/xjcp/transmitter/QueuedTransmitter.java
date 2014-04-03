package net.michaelfuerst.xjcp.transmitter;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import net.michaelfuerst.xjcp.Handler;
import net.michaelfuerst.xjcp.Message;
import net.michaelfuerst.xjcp.MessageParser;
import net.michaelfuerst.xjcp.connection.Connection;

/**
 * This transmitter uses a queue to store its messages in.
 * 
 * @author TheDwoon
 * @version 1.0
 * @since 23.03.2014
 *
 */
public abstract class QueuedTransmitter implements Transmitter {	
	private final Connection connection;
	private final LinkedBlockingQueue<Entry> queue;
	private final MessageParser parser;
	private final Worker worker;
	private final int sleep;
	
	/**
	 * Creates a new QueuedTransmitter.
	 * 
	 * @param connection The connection it should use.
	 */
	protected QueuedTransmitter(MessageParser parser, Connection connection, int sleep) {
		this.connection = connection;
		this.queue = new LinkedBlockingQueue<>();
		this.parser = parser;
		this.worker = new Worker();
		this.sleep = sleep;
	}
	
	@Override
	public final void submit(String message, Handler handler) {
		queue.add(new Entry(message, handler));
	}

	@Override
	public final void start() {
		worker.start();
	}

	@Override
	public final void stop() {
		worker.interrupt();
	}

	/**
	 * This method is called whenever a message is ready to be send.
	 * When this method returns true if will attempt to send the message.
	 * If the methods returns false it will sleep a given amount of time and
	 * call this method again.
	 * 
	 * @return True if the message should be send now, false if it should be send later.
	 */
	protected abstract boolean shouldSend();
	
	/**
	 * @return The connection used in this transmitter.
	 */
	protected final Connection getConnection() {
		return connection;
	}
	
	private final class Worker extends Thread {
		private Worker() {
			
		}
		
		@Override
		public void run() {			
			while (!isInterrupted()) {
				Entry entry;
				try {
					entry = queue.take();
				} catch (InterruptedException e1) {
					return;
				}
				
				//Wait until we should send it.
				while (!shouldSend()) {
					try {
						Thread.sleep(sleep);
					} catch (InterruptedException e) {						
						return;
					}
				}
								
				String response = connection.send(entry.getMessage());
				List<Message> messages = parser.parseMessage(response);
				entry.invokeHandler(messages);
			}
		}
	}
}
