package net.michaelfuerst.xjcp.parser;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import net.michaelfuerst.xjcp.Message;
import net.michaelfuerst.xjcp.MessageParser;
import net.michaelfuerst.xjcp.XJCP;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class MessageParserImpl implements MessageParser {
	private static final Logger LOG = LogManager.getLogger();
	
	private HashMap<String, MessageParser> parser;
	
	private final JsonParser jparser;
	
	public MessageParserImpl() {
		this.parser = new HashMap<>();
		this.jparser = new JsonParser();
	}
	
	public static MessageParser obtain() {
		MessageParserImpl m = new MessageParserImpl();
		
		m.parser.put("onGetChats", new ChatObjectParser());
		m.parser.put("events", new EventParser());
		m.parser.put("onGetHistory", new HistoryParser());
		m.parser.put("onGetContacts", new ContactObjectParser());
		
		m.parser.put("id", new StringParser(XJCP.RPL_ID));
		m.parser.put("onLoginError", new StringParser(XJCP.RPL_LOGINERROR));
		m.parser.put("onMessage", new StringParser(XJCP.RPL_MESSAGE));
		m.parser.put("onNewConversation", new StringParser(XJCP.RPL_NEWCONVERSATION));
		m.parser.put("onRenameConversation", new StringParser(XJCP.RPL_RENAMECONVERSATION));
		m.parser.put("onAddFriend", new StringParser(XJCP.RPL_ADDFRIEND));
		m.parser.put("onSetProfileImage", new StringParser(XJCP.RPL_SETPROFILEIMAGE));
		m.parser.put("onSetGroupImage", new StringParser(XJCP.RPL_SETGROUPIMAGE));
		m.parser.put("onInjectEvent", new StringParser(XJCP.RPL_INJECTEVENT));
		m.parser.put("data", new StringParser(XJCP.RPL_DATA));
		
		return m;
	}
	
	@Override
	public List<Message> parseMessage(String msg) {
		if (msg == null || msg.isEmpty()) {
			return new LinkedList<>();
		}
		
		try {
			return parseMessage(jparser.parse(msg));
		} catch (JsonSyntaxException e) {
			LOG.error("Malformed Json: " + msg);
			
			return new LinkedList<>();
		}
	}

	@Override
	public List<Message> parseMessage(Object o) {
		JsonObject jo = (JsonObject) o;
		
		List<Message> results = new LinkedList<>();		
		
		for (Entry<String, JsonElement> entry : jo.entrySet()) {
			//Check if we need to process the entry.
			if (entry.getValue() == null) {
				continue;
			}
			
			JsonElement obj = entry.getValue();
			MessageParser p = parser.get(entry.getKey());
			if (p == null) {
				LOG.warn("Missing parser for \"" + entry.getKey() + "\"");
				continue;
			}
			
			if (obj.isJsonArray()) {
				//We have found an array of entries.
				JsonArray ja = obj.getAsJsonArray();
				for (int i = 0; i < ja.size(); i++) {
					results.addAll(p.parseMessage(ja.get(i)));
				}
			} else {				
				//We only found one entry.
				results.addAll(p.parseMessage(obj));
			}			
		}
		
		return results;
	}

}
