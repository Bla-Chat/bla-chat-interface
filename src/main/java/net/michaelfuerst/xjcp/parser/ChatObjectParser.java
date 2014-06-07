package net.michaelfuerst.xjcp.parser;

import java.util.LinkedList;
import java.util.List;

import net.michaelfuerst.xjcp.Message;
import net.michaelfuerst.xjcp.MessageParser;
import net.michaelfuerst.xjcp.XJCP;
import net.michaelfuerst.xjcp.helper.ChatObject;

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
public final class ChatObjectParser implements MessageParser {
	private static final String CONVERSATION = "conversation";
	private static final String NAME = "name";
	private static final String TIME = "time";
	
	private final JsonParser parser;
	
	public ChatObjectParser() {
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
		
		ChatObject co = new ChatObject(jo.get(CONVERSATION).getAsString(), jo.get(NAME).getAsString(), jo.get(TIME).getAsString());
		
		Message m = Message.obtain();
		m.what = XJCP.RPL_CHATOBJECT;
		m.obj = co;
		
		results.add(m);
		
		return results;
	}

}
