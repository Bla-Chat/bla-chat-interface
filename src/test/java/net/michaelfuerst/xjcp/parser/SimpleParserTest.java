package net.michaelfuerst.xjcp.parser;

import java.util.List;

import net.michaelfuerst.xjcp.Message;
import net.michaelfuerst.xjcp.MessageParser;
import net.michaelfuerst.xjcp.XJCP;
import net.michaelfuerst.xjcp.helper.ChatMessage;
import net.michaelfuerst.xjcp.helper.ChatObject;
import net.michaelfuerst.xjcp.helper.ContactObject;
import net.michaelfuerst.xjcp.helper.Event;
import net.michaelfuerst.xjcp.helper.History;

import org.junit.Test;

import static org.junit.Assert.*;

public final class SimpleParserTest {
	private static final String RAW_CHATMESSAGE = "{author:\"Author\", nick:\"Nick\", time:\"0-0-0\", text:\"Text\"}";
	private static final String RAW_CHATOBJECT = "{conversation:\"Conversation\", name:\"Name\", time:\"0-0-0\"}";
	private static final String RAW_CONTACTOBJECT = "{nick:\"Nick\", name:\"Name\", status:\"0\"}";
	private static final String RAW_EVENT = "{type:\"Type\", msg:\"Msg\", nick:\"Nick\", text:\"Text\"}";
	private static final String RAW_HISTORY = "{messages:[{author:\"Author\", nick:\"Nick\", time:\"0-0-0\", text:\"Text\"},"
			+ "{author:\"Author\", nick:\"Nick\", time:\"0-0-0\", text:\"Text\"}],"
			+ " conversation:\"Conversation\"}";
	
	private MessageParser parser;
	
	@Test
	public void parseChatMessage() {
		parser = new ChatMessageParser();
		List<Message> m  = parser.parseMessage(RAW_CHATMESSAGE);
		
		assertNotNull(m);
		assertEquals(m.size(), 1);
		assertNotNull(m.get(0));
		assertEquals(m.get(0).what, XJCP.RPL_CHATMESSAGE);
			
		ChatMessage cm = (ChatMessage) m.get(0).obj;
		assertEquals(cm.getAuthor(), "Author");
		assertEquals(cm.getNick(), "Nick");
		assertEquals(cm.getText(), "Text");
		assertEquals(cm.getTime(), "0-0-0");
	}
	
	@Test
	public void parseChatObject() {
		parser = new ChatObjectParser();
		List<Message> m  = parser.parseMessage(RAW_CHATOBJECT);
		
		assertNotNull(m);
		assertEquals(m.size(), 1);
		assertNotNull(m.get(0));
		assertEquals(m.get(0).what, XJCP.RPL_CHATOBJECT);
			
		ChatObject cm = (ChatObject) m.get(0).obj;
		assertEquals(cm.getConversation(), "Conversation");
		assertEquals(cm.getName(), "Name");
		assertEquals(cm.getTime(), "0-0-0");
	}
	
	@Test
	public void parseContactObject() {
		parser = new ContactObjectParser();
		List<Message> m  = parser.parseMessage(RAW_CONTACTOBJECT);
		
		assertNotNull(m);
		assertEquals(m.size(), 1);
		assertNotNull(m.get(0));
		assertEquals(m.get(0).what, XJCP.RPL_CONTACTOBJECT);
		
		ContactObject o = (ContactObject) m.get(0).obj;
		assertEquals(o.getName(), "Name");
		assertEquals(o.getNick(), "Nick");
		assertEquals(o.getStatus(), 0);
	}
	
	@Test
	public void parseEvent() {
		parser = new EventParser();
		List<Message> m  = parser.parseMessage(RAW_EVENT);
		
		assertNotNull(m);
		assertEquals(m.size(), 1);
		assertNotNull(m.get(0));
		assertEquals(m.get(0).what, XJCP.RPL_EVENT);
		
		Event e = (Event) m.get(0).obj;
		assertEquals(e.getMsg(), "Msg");
		assertEquals(e.getNick(), "Nick");
		assertEquals(e.getText(), "Text");
		assertEquals(e.getType(), "Type");
	}
	
	@Test
	public void parseHistoryObject() {
		parser = new HistoryParser();		
		List<Message> m  = parser.parseMessage(RAW_HISTORY);
		
		assertNotNull(m);
		assertEquals(m.size(), 1);
		assertNotNull(m.get(0));
		assertEquals(m.get(0).what, XJCP.RPL_HISTORY);
		
		History h = (History) m.get(0).obj;
		//Message parsing already tested. We just need to check if we parse the correct amount.
		assertEquals(h.getMessages().size(), 2);
		assertEquals(h.getConversation(), "Conversation");
	}
}
