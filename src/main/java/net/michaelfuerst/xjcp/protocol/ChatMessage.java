package net.michaelfuerst.xjcp.protocol;

public final class ChatMessage {
	private String author;
	private String nick;
	private String time;
	private String text;
	
	public ChatMessage() {
		author = null;
		nick = null;
		time = null;
		text = null;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public String getNick() {
		return nick;
	}
	
	public String getTime() {
		return time;
	}
	
	public String getText() {
		return text;
	}
}
