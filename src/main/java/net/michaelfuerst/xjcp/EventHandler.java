package net.michaelfuerst.xjcp;

import net.michaelfuerst.xjcp.protocol.Event;

public interface EventHandler {
	void handleEvent(Event event);
}
