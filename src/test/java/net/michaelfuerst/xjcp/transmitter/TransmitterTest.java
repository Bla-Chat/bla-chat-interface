package net.michaelfuerst.xjcp.transmitter;

import net.michaelfuerst.xjcp.ConnectionMock;
import net.michaelfuerst.xjcp.DeviceMock;
import net.michaelfuerst.xjcp.HandlerMock;
import net.michaelfuerst.xjcp.MessageParser;
import net.michaelfuerst.xjcp.XJCP;
import net.michaelfuerst.xjcp.parser.MessageParserImpl;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TransmitterTest {
	private ConnectionMock connection;
	private DeviceMock device;
	private HandlerMock handler;
	private MessageParser parser;
	
	@Before
	public void setUp() {
		device = new DeviceMock(false, false, false);
		connection = new ConnectionMock("Testhost", device);
		handler = new HandlerMock();
		parser = MessageParserImpl.obtain();
		
		connection.setDefaultResponse("{data:\"Useless\"}");		
	}
	
	@Test(timeout = 2000)
	public void defaultTransmitterConnected() throws InterruptedException {
		DefaultTransmitter transmitter = new DefaultTransmitter(connection, parser, 10);
		transmitter.start();
		
		transmitter.submit("Hello World!", handler);
		device.setMobileInternet(true);
		
		while (!handler.isCalled()) {
			Thread.sleep(1);
		}
		
		assertNotNull(handler.getMessage());
		assertNotNull(handler.getMessage().obj);
		assertEquals(handler.getMessage().what, XJCP.RPL_DATA);
		assertEquals(handler.getMessage().obj, "Useless");
		
		transmitter.stop();
	}
	
	@Test(timeout = 2000)
	public void defaultTransmitterDisconnected() throws InterruptedException {
		DefaultTransmitter transmitter = new DefaultTransmitter(connection, parser, 10);
		transmitter.start();
		
		transmitter.submit("Hello World!", handler);
		
		Thread.sleep(100);
		
		assertFalse(handler.isCalled());
		device.setMobileInternet(true);
		
		while (!handler.isCalled()) {
			Thread.sleep(1);
		}		
		
		assertNotNull(handler.getMessage());
		assertNotNull(handler.getMessage().obj);
		assertEquals(handler.getMessage().what, XJCP.RPL_DATA);
		assertEquals(handler.getMessage().obj, "Useless");		
		
		transmitter.stop();
	}
	
	@Test(timeout = 2000)
	public void lanTransmitterMobileInternet() throws InterruptedException {
		LANTransmitter transmitter = new LANTransmitter(connection, parser, 10);
		transmitter.start();
		
		transmitter.submit("Hello World!", handler);
		device.setMobileInternet(true);
		
		Thread.sleep(100);
		
		assertFalse(handler.isCalled());
		assertNull(handler.getMessage());
		
		transmitter.stop();
	}
	
	@Test(timeout = 2000)
	public void lanTransmitterWLAN() throws InterruptedException {
		LANTransmitter transmitter = new LANTransmitter(connection, parser, 10);
		transmitter.start();
		
		transmitter.submit("Hello World!", handler);
		
		Thread.sleep(100);
		
		assertFalse(handler.isCalled());
		device.setWlan(true);
		
		while (!handler.isCalled()) {
			Thread.sleep(1);
		}		
		
		assertNotNull(handler.getMessage());
		assertNotNull(handler.getMessage().obj);
		assertEquals(handler.getMessage().what, XJCP.RPL_DATA);
		assertEquals(handler.getMessage().obj, "Useless");		
		
		transmitter.stop();
	}
	
	@Test(timeout = 2000)
	public void lanTransmitterLAN() throws InterruptedException {
		LANTransmitter transmitter = new LANTransmitter(connection, parser, 10);
		transmitter.start();
		
		transmitter.submit("Hello World!", handler);
		
		Thread.sleep(100);
		
		assertFalse(handler.isCalled());
		device.setLAN(true);
		
		while (!handler.isCalled()) {
			Thread.sleep(1);
		}		
		
		assertNotNull(handler.getMessage());
		assertNotNull(handler.getMessage().obj);
		assertEquals(handler.getMessage().what, XJCP.RPL_DATA);
		assertEquals(handler.getMessage().obj, "Useless");		
		
		transmitter.stop();
	}
}
