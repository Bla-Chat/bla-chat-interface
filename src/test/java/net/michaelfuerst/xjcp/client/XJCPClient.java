package net.michaelfuerst.xjcp.client;

import net.michaelfuerst.xjcp.Handler;
import net.michaelfuerst.xjcp.Message;
import net.michaelfuerst.xjcp.XJCP;
import net.michaelfuerst.xjcp.XJCPImpl;
import net.michaelfuerst.xjcp.connection.Connection;
import net.michaelfuerst.xjcp.connection.HttpConnection;
import net.michaelfuerst.xjcp.device.DesktopDevice;
import net.michaelfuerst.xjcp.parser.MessageParserImpl;

/**
 * A simple client for testing purpose only.
 * 
 * @author TheDwoon
 * @since 29.06.2014
 * @version 1.0.0
 *
 */
public final class XJCPClient implements Runnable {
	private static final String HOST = "https://www.ssl-id.de/bla.f-online.net/api/xjcp.php";
	
	private String user = null;
	private String password = null;
	
	private final XJCP xjcp;
	
	public XJCPClient(Connection connection) {
		xjcp = new XJCPImpl(false, connection, MessageParserImpl.obtain());
	}

	public static void main(String[] args) throws Exception {		
		Connection connection = new HttpConnection(HOST, new DesktopDevice(true, true));
		XJCPClient client = new XJCPClient(connection);
		
		client.user = args[0];
		client.password = args[1];
		
		try {
			client.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Thread.sleep(2000);
		
		System.exit(0);
	}

	@Override
	public void run() {		
		Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				System.out.println(msg);
			}
		};
		
		System.out.println("Perfoming log in...");
		xjcp.setLoginData(user, password, handler);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {		
			e.printStackTrace();
		}
		
		xjcp.requestChats(handler);
	}
}
