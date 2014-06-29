package net.michaelfuerst.xjcp.helper;

/**
 * The ChatObject defined in the specification.
 * 
 * @author TheDwoon
 * @version 1.0
 * @since 22.03.2014
 *
 */
public final class ChatObject {
	private final String conversation;
	private final String name;
	private final String time;
	
	/**
	 * Creates a new ChatObject.
	 * 
	 * @param conversation The conversation.
	 * @param name The name.
	 * @param time The time.
	 */
	public ChatObject(String conversation, String name, String time) {
		this.conversation = conversation;
		this.name = name;
		this.time = time;
	}

	/**
	 * @return The conversation.
	 */
	public String getConversation() {
		return conversation;
	}

	/**
	 * @return The name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return The time.
	 */
	public String getTime() {
		return time;
	}

	@Override
	public String toString() {
		return "ChatObject [conversation=" + conversation + ", name=" + name
				+ ", time=" + time + "]";
	}
}
