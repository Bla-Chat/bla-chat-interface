package net.michaelfuerst.xjcp;

/**
 * This class will only forward events.
 * 
 * @author TheDwoon
 * @date 22.09.2014
 * @version 1.0.0
 *
 */
public final class EventProxyHandler extends ProxyHandler {
	private final Handler eventHandler;
	
	/**
	 * Crates a new EventProxyHandler. All events will be forwarded to the given eventHander.
	 * the normal handler will receive all events as normal.
	 * 
	 * @param handler the normal handler.
	 * @param eventHandler the event handler.
	 */
	public EventProxyHandler(Handler handler, Handler eventHandler) {
		super(handler);

		this.eventHandler = eventHandler;
	}

	@Override
	public void handleBefore(Message msg) {
		if (eventHandler != null) {
			eventHandler.sendMessage(msg);
		}
	}
}
