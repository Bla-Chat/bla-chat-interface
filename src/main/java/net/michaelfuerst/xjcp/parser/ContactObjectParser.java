package net.michaelfuerst.xjcp.parser;

import java.util.LinkedList;
import java.util.List;

import net.michaelfuerst.xjcp.Message;
import net.michaelfuerst.xjcp.MessageParser;
import net.michaelfuerst.xjcp.XJCP;
import net.michaelfuerst.xjcp.helper.ContactObject;

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
public final class ContactObjectParser implements MessageParser {
	private static final String NICK = "nick";
	private static final String NAME = "name";
	private static final String STATUS = "status";
	
	private final JsonParser parser;
	
	public ContactObjectParser() {
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
		
		ContactObject co = new ContactObject(jo.get(NICK).getAsString(), jo.get(NAME).getAsString(), jo.get(STATUS).getAsInt());
		
		Message m = Message.obtain();
		m.what = XJCP.RPL_CONTACTOBJECT;
		m.obj = co;
		
		results.add(m);
		
		return results;
	}

}
