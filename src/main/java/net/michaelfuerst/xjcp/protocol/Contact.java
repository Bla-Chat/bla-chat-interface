package net.michaelfuerst.xjcp.protocol;

public final class Contact {
	private String nick;
	private String name;
	private int status;
	
	public Contact() {
		nick = null;
		name = null;
		status = 0;
	}
	
	public String getNick() {
		return nick;
	}
	
	public String getName() {
		return name;
	}
	
	public int getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "Contact [nick=" + nick + ", name=" + name + ", status="
				+ status + "]";
	}
}
