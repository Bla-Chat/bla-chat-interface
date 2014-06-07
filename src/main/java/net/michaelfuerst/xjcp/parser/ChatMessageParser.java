package net.michaelfuerst.xjcp.parser;

import java.util.LinkedList;
import java.util.List;

import net.michaelfuerst.xjcp.Message;
import net.michaelfuerst.xjcp.MessageParser;
import net.michaelfuerst.xjcp.XJCP;
import net.michaelfuerst.xjcp.helper.ChatMessage;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * This class is used to convert a JsonObject to a Message.
 * 
 * @author TheDwoon
 * @version 1.0
 * @since 22.03.2014
 *
 */
public final class ChatMessageParser implements MessageParser {
	private static final String AUTHOR = "author";
	private static final String NICK = "nick";
	private static final String TIME = "time";
	private static final String TEXT = "text";
	
	private JsonParser parser;
	
	public ChatMessageParser() {
		parser = new JsonParser();
	}
	
	@Override
	public List<Message> parseMessage(String msg) {
		return parseMessage(parser.parse(msg));
	}

	@Override
	public List<Message> parseMessage(Object o) {
		JsonObject jo = (JsonObject) o;
		
		List<Message> results = new LinkedList<>();
		
		ChatMessage cm = new ChatMessage(jo.get(AUTHOR).getAsString(), jo.get(NICK).getAsString(),
				jo.get(TIME).getAsString(), jo.get(TEXT).getAsString());
		Message m = Message.obtain();
		
		m.what = XJCP.RPL_CHATMESSAGE;
		m.obj = cm;
		
		results.add(m);
		
		return results;
	}
}
