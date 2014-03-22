package net.michaelfuerst.xjcp.parser;

import java.util.List;

import net.michaelfuerst.xjcp.Message;
import net.michaelfuerst.xjcp.MessageParser;

import org.json.JSONObject;

public class MessageParserImpl implements MessageParser {

	public static MessageParser obtain() {
		return new MessageParserImpl();
	}
	
	@Override
	public List<Message> parseMessage(String msg) {
		//TODO: Remove debug.
		System.out.println("Parsing: " + msg);

		return parseMessage(new JSONObject(msg));
	}

	@Override
	public List<Message> parseMessage(JSONObject jo) {
		// TODO Auto-generated method stub
		return null;
	}

}
