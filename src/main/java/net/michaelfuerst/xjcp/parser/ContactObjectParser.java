package net.michaelfuerst.xjcp.parser;

import java.util.LinkedList;
import java.util.List;

import net.michaelfuerst.xjcp.Message;
import net.michaelfuerst.xjcp.MessageParser;
import net.michaelfuerst.xjcp.XJCP;
import net.michaelfuerst.xjcp.helper.ContactObject;

import org.json.JSONObject;

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
	
	public ContactObjectParser() {
		
	}
	
	@Override
	public List<Message> parseMessage(String msg) {
		return parseMessage(new JSONObject(msg));
	}

	@Override
	public List<Message> parseMessage(Object o) {
		JSONObject jo = (JSONObject) o;
		
		List<Message> results = new LinkedList<>();
		
		ContactObject co = new ContactObject(jo.getString(NICK), jo.getString(NAME), jo.getInt(STATUS));
		
		Message m = Message.obtain();
		m.what = XJCP.RPL_CONTACTOBJECT;
		m.obj = co;
		
		results.add(m);
		
		return results;
	}

}
