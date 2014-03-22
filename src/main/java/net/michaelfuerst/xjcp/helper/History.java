package net.michaelfuerst.xjcp.helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The History defined in the specification.
 * 
 * @author TheDwoon
 * @version 1.0
 * @since 22.03.2014
 *
 */
public final class History {
	private final List<ChatMessage> messages;
	private final String conversation;
	
	/**
	 * Creates a new History.
	 * 
	 * @param messages A collection of messages.
	 * @param conversation The conversation.
	 */
	public History(Collection<ChatMessage> messages, String conversation) {
		this.messages = new ArrayList<>(messages);		
		this.conversation = conversation;
	}

	/**
	 * @return A copy of the list conating all messages.
	 */
	public List<ChatMessage> getMessages() {
		return new ArrayList<>(messages);
	}

	/**
	 * @return The conversation.
	 */
	public String getConversation() {
		return conversation;
	}
}
