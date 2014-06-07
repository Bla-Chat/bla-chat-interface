package net.michaelfuerst.xjcp;

import java.util.List;


/**
 * A parser used to convert a server reply to a {@link Message}.
 * 
 * @author TheDwoon
 * @version 1.0
 * @since 21.03.2014
 *
 */
public interface MessageParser {
	/**
	 * Converts the given string to a {@link Message}.
	 * 
	 * @param msg The message.
	 * @return The representation as a {@link Message}.
	 */
	List<Message> parseMessage(String msg);
	
	/**
	 * Converts the given Json to a {@link Message}.
	 * 
	 * @param jo The {@link JSONObject}.
	 * @return The representation as a {@link Message}.
	 */
	List<Message> parseMessage(Object o);
}
