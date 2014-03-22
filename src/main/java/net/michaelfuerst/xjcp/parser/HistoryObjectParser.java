package net.michaelfuerst.xjcp.parser;

import java.util.LinkedList;
import java.util.List;

import net.michaelfuerst.xjcp.Message;
import net.michaelfuerst.xjcp.MessageParser;
import net.michaelfuerst.xjcp.XJCP;
import net.michaelfuerst.xjcp.helper.ChatMessage;
import net.michaelfuerst.xjcp.helper.ContactObject;
import net.michaelfuerst.xjcp.helper.History;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * This class is used to convert a JsonObject to a Message.
 * 
 * @author TheDwoon
 * @version 1.0
 * @since 22.03.2014
 *
 */
public final class HistoryObjectParser implements MessageParser {
	private static final String MESSAGES = "messages";	
	private static final String CONVERSATION = "conversation";
	
	private ChatObjectParser parser;
	
	public HistoryObjectParser() {
		this.parser = new ChatObjectParser();
	}
	
	@Override
	public List<Message> parseMessage(String msg) {
		return parseMessage(new JSONObject(msg));
	}

	@Override
	public List<Message> parseMessage(JSONObject jo) {
		List<Message> results = new LinkedList<>();
		
		List<ChatMessage> cms = new LinkedList<>();
		JSONArray jArray = jo.getJSONArray(MESSAGES);
		for (int i = 0; i < jArray.length(); i++) {
			//Dirty.
			cms.add((ChatMessage) parser.parseMessage((JSONObject)jArray.get(i)).get(0).obj);
		}
		
		History o = new History(cms, jo.getString(CONVERSATION));
		
		Message m = Message.obtain();
		m.what = XJCP.RPL_CONTACTOBJECT;
		m.obj = o;
		
		results.add(m);
		
		return results;
	}

}
