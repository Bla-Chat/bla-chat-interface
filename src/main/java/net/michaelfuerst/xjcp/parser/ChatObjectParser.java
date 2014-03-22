package net.michaelfuerst.xjcp.parser;

import java.util.LinkedList;
import java.util.List;

import net.michaelfuerst.xjcp.Message;
import net.michaelfuerst.xjcp.MessageParser;
import net.michaelfuerst.xjcp.XJCP;
import net.michaelfuerst.xjcp.helper.ChatObject;

import org.json.JSONObject;

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
	
	public ChatObjectParser() {
		
	}
	
	@Override
	public List<Message> parseMessage(String msg) {
		return parseMessage(new JSONObject(msg));
	}

	@Override
	public List<Message> parseMessage(Object o) {
		JSONObject jo = (JSONObject) o;
		List<Message> results = new LinkedList<>();
		
		ChatObject co = new ChatObject(jo.getString(CONVERSATION), jo.getString(NAME), jo.getString(TIME));
		
		Message m = Message.obtain();
		m.what = XJCP.RPL_CHATOBJECT;
		m.obj = co;
		
		results.add(m);
		
		return results;
	}

}
