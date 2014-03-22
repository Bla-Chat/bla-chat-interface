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
	private final String nick;
	private final String name;
	private final String time;
	
	/**
	 * Creates a new ChatObject.
	 * 
	 * @param nick The nick.
	 * @param name The name.
	 * @param time The time.
	 */
	public ChatObject(String nick, String name, String time) {
		this.nick = nick;
		this.name = name;
		this.time = time;
	}

	/**
	 * @return The nick.
	 */
	public String getNick() {
		return nick;
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
}
