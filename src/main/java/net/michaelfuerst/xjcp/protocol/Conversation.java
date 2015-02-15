package net.michaelfuerst.xjcp.protocol;

public final class Conversation {
	private String conversation;
	private String name;
	private String time;
	
	public Conversation() {
		conversation = null;
		name = null;
		time = null;
	}
	
	public String getConversation() {
		return conversation;
	}
	
	public String getName() {
		return name;
	}
	
	public String getTime() {
		return time;
	}

	@Override
	public String toString() {
		return "Conversation [conversation=" + conversation + ", name=" + name
				+ ", time=" + time + "]";
	}
}
