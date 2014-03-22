package net.michaelfuerst.xjcp.helper;

/**
 * This represents the event class defined in the specification.
 * And yes, it is named poorly.
 * 
 * @author TheDwoon
 * @version 1.0
 * @since 22.03.2014
 *
 */
public class Event {
	private final String type;
	private final String msg;
	private final String nick;
	private final String text;
	
	/**
	 * Creates a new event.
	 * 
	 * @param type The type.
	 * @param msg The message.
	 * @param nick The nick.
	 * @param text The text.
	 */
	public Event(String type, String msg, String nick, String text) {
		this.type = type;
		this.msg = msg;
		this.nick = nick;
		this.text = text;
	}

	/**
	 * @return The type.
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return The message.
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @return The nick.
	 */
	public String getNick() {
		return nick;
	}

	/**
	 * @return The text.
	 */
	public String getText() {
		return text;
	}
}
