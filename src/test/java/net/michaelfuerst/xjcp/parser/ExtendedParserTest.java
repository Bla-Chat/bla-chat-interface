package net.michaelfuerst.xjcp.parser;

import java.util.List;

import net.michaelfuerst.xjcp.Message;
import net.michaelfuerst.xjcp.MessageParser;
import net.michaelfuerst.xjcp.XJCP;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ExtendedParserTest {
	private static final String RAW_SINGLE = "{id:\"LONG\"}";
	private static final String RAW_MULTY = "{data:[\"Hallo\", \" \", \"Welt!\"]}";
	
	private MessageParser parser;
	
	@Before
	public void initParser() {
		parser = MessageParserImpl.obtain();
	}
	
	@Test
	public void parseSingleResponse() {
		List<Message> results = parser.parseMessage(RAW_SINGLE);
		
		assertNotNull(results);
		assertEquals(results.size(), 1);
		
		Message m = results.get(0);
		assertEquals(m.what, XJCP.RPL_ID);
		assertEquals(m.obj, "LONG");
	}
	
	@Test
	public void parseBatchResponse() {
		List<Message> results = parser.parseMessage(RAW_MULTY);
		
		assertNotNull(results);
		assertEquals(results.size(), 3);
		
		String[] assertions = new String[] {"Hallo", " ", "Welt!"};
		
		for (int i = 0; i < results.size(); i++) {
			Message m = results.get(i);
			assertEquals(m.what, XJCP.RPL_DATA);
			assertEquals(m.obj, assertions[i]);
		}
	}
}
