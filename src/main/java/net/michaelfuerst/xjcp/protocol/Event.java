package net.michaelfuerst.xjcp.protocol;

public final class Event {
	private String type;
	private String msg;
	private String nick;
	private String text;
	
	public String getType() {
		return type;
	}
	
	public String getMessage() {
		return msg;
	}

	public String getText() {
		return text;
	}
	
	public String getNick() {
		return nick;
	}

	@Override
	public String toString() {
		return "Event [type=" + type + ", msg=" + msg + ", nick=" + nick
				+ ", text=" + text + "]";
	}
}
