package net.michaelfuerst.xjcp.parser;

import java.util.LinkedList;
import java.util.List;

import net.michaelfuerst.xjcp.Message;
import net.michaelfuerst.xjcp.MessageParser;
import net.michaelfuerst.xjcp.XJCP;
import net.michaelfuerst.xjcp.helper.Event;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * This class is used to convert a JsonObject into a Message.
 * 
 * @author TheDwoon
 * @version 1.0
 * @since 22.03.2014
 *
 */
public final class EventParser implements MessageParser {
	private static final String TYPE = "type";
	private static final String MSG = "msg";
	private static final String NICK = "nick";
	private static final String TEXT = "text";
	
	private final JsonParser parser;
	
	public EventParser() {
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

		Event e = new Event(jo.get(TYPE).getAsString(), jo.get(MSG).getAsString(), jo.get(NICK).getAsString(), jo.get(TEXT).getAsString());
		Message m = Message.obtain();
		
		m.what = XJCP.RPL_EVENT;
		m.obj = e;
		
		results.add(m);
		
		return results;
	}
}
