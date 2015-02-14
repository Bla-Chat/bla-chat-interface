package net.michaelfuerst.xjcp.protocol;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.google.gson.Gson;

public class ProtocolTest {
	private Gson gson = new Gson();

	@Test
	public void parseEvent() {
		final String type = "onMessage";
		final String msg = "a,b";
		final String nick = "user";
		final String text = "my text";
		final String json = String.format("{\"type\":\"%s\",\"msg\":\"%s\",\"nick\":\"%s\",\"text\":\"%s\"}",
				type, msg, nick, text);
		
		Event event = gson.fromJson(json, Event.class);
		
		assertEquals(type, event.getType());
		assertEquals(msg, event.getMessage());
		assertEquals(nick, event.getNick());
		assertEquals(text, event.getText());
	}
	
	@Test
	public void parseContact() {
		final String nick = "user";
		final String name = "test";
		final int status = 0;
		final String json = String.format("{\"nick\":\"%s\",\"name\":\"%s\",\"status\":\"%d\"}", nick, name, status);
		
		Contact contact = gson.fromJson(json, Contact.class);
		assertEquals(nick, contact.getNick());
		assertEquals(name, contact.getName());
		assertEquals(status, contact.getStatus());
	}
	
	@Test
	public void parseConversation() {
		final String conversation = "a,b";
		final String name = "test";
		final String time = "2015";
		final String json = String.format("{\"conversation\":\"%s\",\"name\":\"%s\",\"time\":\"%s\"}", conversation, name, time);
		
		Conversation c = gson.fromJson(json, Conversation.class);
		
		assertEquals(conversation, c.getConversation());
		assertEquals(name, c.getName());
		assertEquals(time, c.getTime());
	}
	
	@Test
	public void parseChatMessage() {
		final String author = "me";
		final String nick = "user";
		final String time = "2015";
		final String text = "Hello World!";
		final String json = String.format("{\"author\":\"%s\",\"nick\":\"%s\",\"time\":\"%s\",\"text\":\"%s\"}",
				author, nick, time, text);
		
		ChatMessage m = gson.fromJson(json, ChatMessage.class);
		assertEquals(author, m.getAuthor());
		assertEquals(nick, m.getNick());
		assertEquals(time, m.getTime());
		assertEquals(text, m.getText());
	}
	
	@Test
	public void parseHistory() {
		final String json = "{\"type\":\"onGetHistory\",\"messages\":[{\"author\":\"Daniel\",\"authorNick\":\"thedwoon\","
				+ "\"time\":\"2015-02-13 17:15:28\",\"message\":\"ping\"},{\"author\":\"Daniel\",\"authorNick\":\"thedwoon\","
				+ "\"time\":\"2014-09-22 13:21:50\",\"message\":\"SPAM\"}],\"conversation\":\"penguinmenac3,thedwoon\"}";
		
		ChatHistory history = gson.fromJson(json, ChatHistory.class);
		
		assertEquals(2, history.getMessages().size());
		assertEquals("penguinmenac3,thedwoon", history.getConversation());
	}
}
