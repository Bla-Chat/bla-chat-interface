package net.michaelfuerst.xjcp.parser;

import java.util.LinkedList;
import java.util.List;

import net.michaelfuerst.xjcp.Message;
import net.michaelfuerst.xjcp.MessageParser;
import net.michaelfuerst.xjcp.XJCP;
import net.michaelfuerst.xjcp.helper.ChatMessage;
import net.michaelfuerst.xjcp.helper.History;

import com.google.gson.JsonArray;
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
public final class HistoryParser implements MessageParser {
	private static final String MESSAGES = "messages";	
	private static final String CONVERSATION = "conversation";
	
	private final JsonParser jparser;
	
	private ChatMessageParser parser;
	
	public HistoryParser() {
		this.parser = new ChatMessageParser();
		this.jparser = new JsonParser();
	}
	
	@Override
	public List<Message> parseMessage(String msg) {
		return parseMessage(jparser.parse(msg));
	}

	@Override
	public List<Message> parseMessage(Object o) {
		JsonObject jo = (JsonObject) o;
		
		List<Message> results = new LinkedList<>();
		
		List<ChatMessage> cms = new LinkedList<>();
		JsonArray jArray = jo.get(MESSAGES).getAsJsonArray();
		for (int i = 0; i < jArray.size(); i++) {
			//Dirty.
			cms.add((ChatMessage) parser.parseMessage((JsonObject)jArray.get(i)).get(0).obj);
		}
				
		History h = new History(cms, jo.get(CONVERSATION).getAsString());
		
		Message m = Message.obtain();
		m.what = XJCP.RPL_HISTORY;
		m.obj = h;
		
		results.add(m);
		
		return results;
	}

}
