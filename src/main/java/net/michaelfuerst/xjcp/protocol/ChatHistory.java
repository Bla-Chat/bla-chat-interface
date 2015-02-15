package net.michaelfuerst.xjcp.protocol;

import java.util.ArrayList;
import java.util.List;

public final class ChatHistory {
	private List<ChatMessage> messages;
	private String conversation;
	
	public ChatHistory() {
		messages = new ArrayList<>();
		conversation = null;
	}
	
	public List<ChatMessage> getMessages() {
		return new ArrayList<>(messages);
	}
	
	public String getConversation() {
		return conversation;
	}

	@Override
	public String toString() {
		return "ChatHistory [messages=" + messages + ", conversation="
				+ conversation + "]";
	}
}
