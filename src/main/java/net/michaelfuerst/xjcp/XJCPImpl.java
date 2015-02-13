package net.michaelfuerst.xjcp;

import java.util.Arrays;

import net.michaelfuerst.xjcp.connection.Connection;
import net.michaelfuerst.xjcp.transmitter.TransmitterHost;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonObject;


/**
 * This class implements XJCP.
 * 
 * @author TheDwoon
 * @since 21.3.14
 *
 */
public class XJCPImpl extends XJCP {	
	private static Logger LOG = LogManager.getLogger();
	
	private static final int MODE_MINIFIED = 0;
	private static final int MODE_NORMAL = 1;
	
	private static final int ID = 0;
	private static final int USER = 1;
	private static final int PASSWORD = 2;
	private static final int EVENTS = 3;
	private static final int CHATS = 4;
	private static final int CONTACTS = 5;
	private static final int HISTORY = 6;
	private static final int MESSAGE = 7;
	private static final int REMOVE_EVENT = 8;
	private static final int NEW_CONVERSATION = 9;
	private static final int RENAME_CONVERSATION = 10;
	private static final int SET_NAME = 11;
	private static final int ADD_FRIEND = 12;
	private static final int SET_STATUS = 13;
	private static final int SET_PROFILE_IMAGE = 14;
	private static final int SET_GROUP_IMAGE = 15;
	private static final int INJECT_EVENT = 16;
	private static final int DATA = 17;
	
	private static final String ATTR_CONVERSATION = "conversation";
	private static final String ATTR_MESSAGE = "message";
	private static final String ATTR_COUNT = "count";
	private static final String ATTR_NAME = "name";
	private static final String ATTR_TYPE = "type";
	
	private static final String[][] PROTOCOL = new String[][] {
		{"i", "id"},
		{"u", "user"},
		{"p", "pw"},
		{"e", "events"},
		{"c", "getChats"},
		{"k", "getContacts"},
		{"h", "getHistory"},
		{"m", "message"},
		{"d", "removeEvent"},
		{"n", "newConversation"},
		{"r", "renameConversation"},
		{"sN", "setName"},
		{"aF", "addFriend"},
		{"sS", "setStatus"},
		{"sP", "setProfileImage"},
		{"sG", "setGroupImage"},
		{"iE", "injectEvent"},
		{"da", "data"},
		};

	
	private final Gson gson;
	private final int mode;
	private final TransmitterHost transmitter;
	
	/** The user. */
	private String user;
	/** The users password. */
	private String passwd;
	/** Our client id, if we have one.*/
	private String clientId;
	
	private Thread t;
	private volatile long keepAliveInterval;
	
	private Handler eventHandler;
	
	public XJCPImpl(final boolean minified, final Connection connection, final MessageParser parser) {
		this.gson = new Gson();
		this.transmitter = new TransmitterHost(connection, parser);
		this.transmitter.start();
		
		if (minified) {
			this.mode = MODE_MINIFIED;
		} else {
			this.mode = MODE_NORMAL;
		}
		
		t = new Thread(new Runnable() {			
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(keepAliveInterval);
					} catch (InterruptedException e) {
						continue;
					}
					
					if (clientId != null) {
						JsonObject json = new JsonObject();
						json.addProperty(PROTOCOL[ID][mode], clientId);
						transmitter.submit(gson.toJson(json), eventHandler);
					}
				}
			}
		});
		
		t.start();
		
		setKeepAliveInterval(2000);
	}
	
	@Override
	public void setWLANOnly(boolean enabled) {
		transmitter.setWLANOnly(enabled);
	}

	@Override
	public void setKeepAliveInterval(int timeInMs) {
		keepAliveInterval = timeInMs;
	}

	@Override
	public void setEventHandler(Handler handler) {
		eventHandler = handler;
	}

	@Override
	public void setLoginData(String user, String password, Handler handler) {
		this.user = user;
		this.passwd = password;
		this.clientId = null;
		
		final JsonObject obj = new JsonObject();
		obj.addProperty(PROTOCOL[USER][mode], user);
		obj.addProperty(PROTOCOL[PASSWORD][mode], password);
		
		final String msg = gson.toJson(obj);
		
		ProxyHandler proxy = new ProxyHandler(handler) {			
			@Override
			public void handleBefore(Message msg) {
				if (msg.what == RPL_ID) {
					LOG.info("Got id: " + msg.obj);
					
					clientId = (String) msg.obj;
				} else {
					LOG.warn("Unexpected login response: " + msg);
				}
			}
		};
		
		transmitter.submit(msg, proxy);
	}

	@Override
	public void sendMessage(String conversation, String message, Handler handler) {
		if (clientId == null) {
			throw new IllegalStateException("Not logged in.");
		}
		
		final JsonObject msg = new JsonObject();
		msg.addProperty(ATTR_CONVERSATION, conversation);
		msg.addProperty(ATTR_MESSAGE, message);
		
		final JsonObject json = new JsonObject();		
		json.addProperty(PROTOCOL[ID][mode], clientId);
		json.add(PROTOCOL[MESSAGE][mode], msg);
		
		transmitter.submit(gson.toJson(json), new EventProxyHandler(handler, eventHandler));
	}

	@Override
	public void requestChats(Handler handler) {
		if (clientId == null) {
			throw new IllegalStateException("Not logged in.");
		}
		
		final JsonObject json = new JsonObject();	
		json.addProperty(PROTOCOL[ID][mode], clientId);
		json.add(PROTOCOL[CHATS][mode], new JsonObject());
		
		transmitter.submit(gson.toJson(json), new EventProxyHandler(handler, eventHandler));
	}

	@Override
	public void requestContacts(Handler handler) {
		if (clientId == null) {
			throw new IllegalStateException("Not logged in.");
		}
		
		final JsonObject json = new JsonObject();
		json.addProperty(PROTOCOL[ID][mode], clientId);
		json.add(PROTOCOL[CONTACTS][mode], new JsonObject());
		
		transmitter.submit(gson.toJson(json), new EventProxyHandler(handler, eventHandler));	
	}

	@Override
	public void requestHistory(String conversation, int count, Handler handler) {
		if (clientId == null) {
			throw new IllegalStateException("Not logged in.");
		}
		
		final JsonObject msg = new JsonObject();
		msg.addProperty(ATTR_CONVERSATION, conversation);
		msg.addProperty(ATTR_COUNT, count);
		
		final JsonObject json = new JsonObject();
		json.addProperty(PROTOCOL[ID][mode], clientId);
		json.add(PROTOCOL[HISTORY][mode], msg);
		
		transmitter.submit(gson.toJson(json), new EventProxyHandler(handler, eventHandler));	
	}

	@Override
	public void removeEvents(String conversation, Handler handler) {
		if (clientId == null) {
			throw new IllegalStateException("Not logged in.");
		}
		
		final JsonObject msg = new JsonObject();
		msg.addProperty(ATTR_CONVERSATION, conversation);
		
		final JsonObject json = new JsonObject();
		json.addProperty(PROTOCOL[ID][mode], clientId);
		json.add(PROTOCOL[REMOVE_EVENT][mode], msg);
		
		transmitter.submit(gson.toJson(json), new EventProxyHandler(handler, eventHandler));	
	}

	@Override
	public void createConversation(String[] participants, Handler handler) {
		if (clientId == null) {
			throw new IllegalStateException("Not logged in.");
		}
		
		String[] p = Arrays.copyOf(participants, participants.length);		
		Arrays.sort(p);
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < p.length; i++) {
			if (i > 0) {
				sb.append(',');
			}
			
			sb.append(p[i]);
		}
		
		final JsonObject msg = new JsonObject();
		msg.addProperty(ATTR_CONVERSATION, sb.toString());
		
		final JsonObject json = new JsonObject();
		json.addProperty(PROTOCOL[ID][mode], clientId);
		json.add(PROTOCOL[NEW_CONVERSATION][mode], msg);
		
		transmitter.submit(gson.toJson(json), new EventProxyHandler(handler, eventHandler));	
	}

	@Override
	public void renameConversation(String conversation, String name,
			Handler handler) {
		if (clientId == null) {
			throw new IllegalStateException("Not logged in.");
		}
		
		final JsonObject msg = new JsonObject();
		msg.addProperty(ATTR_CONVERSATION, conversation);
		msg.addProperty(ATTR_NAME, name);
		
		final JsonObject json = new JsonObject();
		json.addProperty(PROTOCOL[ID][mode], clientId);
		json.add(PROTOCOL[RENAME_CONVERSATION][mode], msg);
		
		transmitter.submit(gson.toJson(json), new EventProxyHandler(handler, eventHandler));	
	}

	@Override
	public void renameSelf(String name, Handler handler) {
		if (clientId == null) {
			throw new IllegalStateException("Not logged in.");
		}
				
		final JsonObject json = new JsonObject();
		json.addProperty(PROTOCOL[ID][mode], clientId);
		json.addProperty(PROTOCOL[SET_NAME][mode], name);
		
		transmitter.submit(gson.toJson(json), new EventProxyHandler(handler, eventHandler));	
	}

	@Override
	public void addFriend(String user, Handler handler) {
		if (clientId == null) {
			throw new IllegalStateException("Not logged in.");
		}
				
		final JsonObject json = new JsonObject();
		json.addProperty(PROTOCOL[ID][mode], clientId);
		json.addProperty(PROTOCOL[ADD_FRIEND][mode], user);
		
		transmitter.submit(gson.toJson(json), new EventProxyHandler(handler, eventHandler));	
	}

	@Override
	public void setStatus(int status, Handler handler) {
		if (clientId == null) {
			throw new IllegalStateException("Not logged in.");
		}
		
		final JsonObject json = new JsonObject();
		json.addProperty(PROTOCOL[ID][mode], clientId);
		json.addProperty(PROTOCOL[SET_STATUS][mode], status);
		
		transmitter.submit(gson.toJson(json), new EventProxyHandler(handler, eventHandler));	
	}

	@Override
	public void setProfileImage(Object data, Handler handler) {
		if (clientId == null) {
			throw new IllegalStateException("Not logged in.");
		}
		
		throw new UnsupportedOperationException("Not implemented, yet!");		
	}

	@Override
	public void setGroupImage(Object data, String conversation, Handler handler) {
		if (clientId == null) {
			throw new IllegalStateException("Not logged in.");
		}
		
		throw new UnsupportedOperationException("Not implemented, yet!");
	}

	@Override
	public void injectEvent(String conversation, String type, String message,
			Handler handler) {
		if (clientId == null) {
			throw new IllegalStateException("Not logged in.");
		}
		
		final JsonObject msg = new JsonObject();
		msg.addProperty(ATTR_CONVERSATION, conversation);
		msg.addProperty(ATTR_TYPE, type);
		msg.addProperty(ATTR_MESSAGE, message);
		
		final JsonObject json = new JsonObject();
		json.addProperty(PROTOCOL[ID][mode], clientId);
		
		transmitter.submit(gson.toJson(json), new EventProxyHandler(handler, eventHandler));	
	}

	@Override
	public void sendData(Object data, String conversation, Handler handler) {
		if (clientId == null) {
			throw new IllegalStateException("Not logged in.");
		}
		
		throw new UnsupportedOperationException("Not implemented, yet!");
	}
}
