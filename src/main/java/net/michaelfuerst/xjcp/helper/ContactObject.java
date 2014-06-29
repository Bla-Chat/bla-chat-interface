package net.michaelfuerst.xjcp.helper;

/**
 * The ContactObject defined in the specification.
 * 
 * @author TheDwoon
 * @version 1.0
 * @since 22.03.2014
 *
 */
public final class ContactObject {
	private final String nick;
	private final String name;
	private final int status;
	
	public ContactObject(String nick, String name, int status) {
		super();
		this.nick = nick;
		this.name = name;
		this.status = status;
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
	 * @return The status.
	 */
	public int getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "ContactObject [nick=" + nick + ", name=" + name + ", status="
				+ status + "]";
	}
}
