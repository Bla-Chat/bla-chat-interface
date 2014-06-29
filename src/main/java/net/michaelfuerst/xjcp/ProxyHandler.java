package net.michaelfuerst.xjcp;

/**
 * This is proxy for {@link Handler}s. All messages passed to this handler will
 * first be handled by {@link #handleBefore(Message)} Method before forwarding
 * them.
 * 
 * @author TheDwoon
 * @since 29.06.2014
 * @version 1.0.0
 * 
 */
public abstract class ProxyHandler extends Handler {
	private final Handler handler;

	public ProxyHandler(Handler handler) {
		this.handler = handler;
	}

	@Override
	public void handleMessage(Message msg) {
		handleBefore(msg);
		
		if (handler != null) {
			handler.sendMessage(msg);			
		}
	}

	/**
	 * Will be called before the message is passed to the second handler.
	 * 
	 * @param msg The message handled.
	 */
	public abstract void handleBefore(Message msg);
}
