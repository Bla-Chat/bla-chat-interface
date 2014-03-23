package net.michaelfuerst.xjcp;

import net.michaelfuerst.xjcp.connection.Connection;
import net.michaelfuerst.xjcp.transmitter.Transmitter;


/**
 * This class implements XJCP.
 * 
 * @author TheDwoon
 * @since 21.3.14
 *
 */
public class XJCPImpl extends XJCP {
	private final boolean minified;
	private final Connection connection;
	private final Transmitter transmitter;
	
	/** The user. */
	private String user;
	/** The users password. */
	private String passwd;
	/** Our client id, if we have one.*/
	private String clientId;
	
	public XJCPImpl(final boolean minified, final Connection connection, final Transmitter transmitter) {
		this.minified = minified;	
		this.connection = connection;
		this.transmitter = transmitter;
	}
	
	@Override
	public void setWLANOnly(boolean enabled) {
		
	}

	@Override
	public void setKeepAliveInterval(int timeInMs) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setEventHandler(Handler handler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLoginData(String user, String password, Handler handler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendMessage(String conversation, String message, Handler handler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void requestChats(Handler handler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void requestContacts(Handler handler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void requestHistory(String conversation, Handler handler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeEvents(String conversation, Handler handler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createConversation(String[] participants, Handler handler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void renameConversation(String conversation, String name,
			Handler handler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void renameSelf(String name, Handler handler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addFriend(String user, Handler handler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setStatus(int status, Handler handler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setProfileImage(Object data, Handler handler) {
		throw new UnsupportedOperationException("Not implemented, yet!");		
	}

	@Override
	public void setGroupImage(Object data, String conversation, Handler handler) {
		throw new UnsupportedOperationException("Not implemented, yet!");
	}

	@Override
	public void injectEvent(String conversation, String type, String message,
			Handler handler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendData(Object data, String conversation, Handler handler) {
		throw new UnsupportedOperationException("Not implemented, yet!");
	}
}
