package net.michaelfuerst.xjcp.helper;

/**
 * The ChatMessage defined in the specification.
 * 
 * @author TheDwoon
 * @version 1.0
 * @since 22.03.2014
 *
 */
public final class ChatMessage {
	private final String author;
	private final String nick;
	private final String time;
	private final String text;
	
	/**
	 * Creates a new ChatMessage.
	 * 
	 * @param author The author.
	 * @param nick The nick.
	 * @param time The time.
	 * @param text The text.
	 */
	public ChatMessage(String author, String nick, String time, String text) {
		this.author = author;
		this.nick = nick;
		this.time = time;
		this.text = text;
	}

	/**
	 * @return The author.
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @return The nick.
	 */
	public String getNick() {
		return nick;
	}

	/**
	 * @return The time.
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @return The text.
	 */
	public String getText() {
		return text;
	}

	@Override
	public String toString() {
		return "ChatMessage [author=" + author + ", nick=" + nick + ", time="
				+ time + ", text=" + text + "]";
	}
}
