package net.michaelfuerst.xjcp.device;

/**
 * This class represents a mobile device.
 * 
 * @author TheDwoon
 * @version 1.0
 * @since 23.03.2014
 *
 */
public class MobileDevice implements Device {
	public MobileDevice() {
		throw new UnsupportedOperationException("Not implemented, yet!");
	}
	
	@Override
	public boolean hasLAN() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasWLAN() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasMobileInternet() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasInternet() {
		// TODO Auto-generated method stub
		return false;
	}

}
