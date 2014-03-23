package net.michaelfuerst.xjcp.device;

/**
 * This class represents a Desktop Computer.
 * 
 * @author TheDwoon
 * @version 1.0
 * @since 23.03.2014
 *
 */
public class DesktopDevice implements Device {
	private boolean hasLan;
	private boolean hasWlan;
	
	/**
	 * Creates a new DesktopDevice.
	 * 
	 * @param hasLan True if we have LAN access.
	 * @param hasWlan True if we have WLAN access.
	 */
	public DesktopDevice(boolean hasLan, boolean hasWlan) {
		this.hasLan = hasLan;
		this.hasWlan = hasWlan;
	}
	
	@Override
	public boolean hasLAN() {
		return hasLan;
	}

	@Override
	public boolean hasWLAN() {
		return hasWlan;
	}

	@Override
	public boolean hasMobileInternet() {
		return false;
	}

	@Override
	public boolean hasInternet() {
		return hasLAN() || hasWLAN() || hasMobileInternet();
	}

}
