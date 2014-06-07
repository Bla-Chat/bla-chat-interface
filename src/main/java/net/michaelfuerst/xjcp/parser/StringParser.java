package net.michaelfuerst.xjcp.parser;

import java.util.LinkedList;
import java.util.List;

import com.google.gson.JsonPrimitive;

import net.michaelfuerst.xjcp.Message;
import net.michaelfuerst.xjcp.MessageParser;

/**
 * This class is used to convert to a Message.
 * 
 * @author TheDwoon
 * @version 1.0
 * @since 22.03.2014
 *
 */
public class StringParser implements MessageParser {
	private final int messageId;
	
	/**
	 * Creates a new StringParser.
	 * 
	 * @param messageId The messageId we should set for the message.
	 */
	public StringParser(int messageId) {
		this.messageId = messageId;
	}
	
	@Override
	public List<Message> parseMessage(String msg) {
		List<Message> results = new LinkedList<>();
		
		Message m = Message.obtain();
		m.what = messageId;
		m.obj = msg;
		results.add(m);
		
		return results;
	}

	@Override
	public List<Message> parseMessage(Object o) {
		if (o instanceof JsonPrimitive) {
			JsonPrimitive p = (JsonPrimitive) o;
			return parseMessage(p.getAsString());
		} else {
			return parseMessage((String) o);
		}
	}

}
