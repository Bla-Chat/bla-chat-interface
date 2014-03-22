package net.michaelfuerst.xjcp.parser;

import java.util.LinkedList;
import java.util.List;

import org.json.JSONObject;

import net.michaelfuerst.xjcp.Message;
import net.michaelfuerst.xjcp.MessageParser;
import net.michaelfuerst.xjcp.XJCP;
import net.michaelfuerst.xjcp.helper.ChatMessage;

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
	
	public ChatMessageParser() {
		
	}
	
	@Override
	public List<Message> parseMessage(String msg) {
		return parseMessage(new JSONObject(msg));
	}

	@Override
	public List<Message> parseMessage(Object o) {
		JSONObject jo = (JSONObject) o;
		
		List<Message> results = new LinkedList<>();
		
		ChatMessage cm = new ChatMessage(jo.getString(AUTHOR), jo.getString(NICK), jo.getString(TIME), jo.getString(TEXT));
		Message m = Message.obtain();
		
		m.what = XJCP.RPL_CHATMESSAGE;
		m.obj = cm;
		
		results.add(m);
		
		return results;
	}

}
