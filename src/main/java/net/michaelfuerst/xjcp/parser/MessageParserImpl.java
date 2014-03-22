package net.michaelfuerst.xjcp.parser;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import net.michaelfuerst.xjcp.Message;
import net.michaelfuerst.xjcp.MessageParser;
import net.michaelfuerst.xjcp.XJCP;

import org.json.JSONArray;
import org.json.JSONObject;

public class MessageParserImpl implements MessageParser {
	private HashMap<String, MessageParser> parser;
	
	public MessageParserImpl() {
		this.parser = new HashMap<>();
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
		//TODO: Remove debug.
		System.out.println("Parsing: " + msg);

		return parseMessage(new JSONObject(msg));
	}

	@Override
	public List<Message> parseMessage(Object o) {
		JSONObject jo = (JSONObject) o;
		
		List<Message> results = new LinkedList<>();
		
		String[] keys = JSONObject.getNames(jo);
		for (String key : keys) {
			//Check if we need to process the key.
			if (jo.isNull(key)) {
				continue;
			}
			
			Object obj = jo.get(key);
			MessageParser p = parser.get(key);
			if (p == null) {
				//TODO: Remove debug.
				System.out.println("ERROR: Missing parser for \"" + key + "\"");
				continue;
			}
			
			if (obj instanceof JSONArray) {
				//We have found an array of entries.
				JSONArray ja = (JSONArray) obj;
				for (int i = 0; i < ja.length(); i++) {
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
