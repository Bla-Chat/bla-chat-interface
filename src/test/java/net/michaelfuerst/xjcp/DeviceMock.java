package net.michaelfuerst.xjcp;

/**
 * This class is only for testing purposes.
 * 
 * @author TheDwoon
 * @version 1.0
 * @since 22.03.2014
 *
 */
public final class DeviceMock implements Device {
	private boolean hasLan;
	private boolean hasWlan;
	private boolean hasMobileInternet;
	
	public DeviceMock(boolean hasLan, boolean hasWlan, boolean hasMobileInternet) {
		this.hasLan = hasLan;
		this.hasWlan = hasWlan;
		this.hasMobileInternet = hasMobileInternet;
	}
	@Override
	public boolean hasLAN() {
		return hasLan;		
	}

	/**
	 * Changes wheather we have LAN or not.
	 * 
	 * @param hasLan The LAN state.
	 */
	public void setLAN(boolean hasLan) {
		this.hasLan = hasLan;
	}
	
	@Override
	public boolean hasWLAN() {
		return hasWlan;
	}

	/**
	 * Changes wheather we have WLAN or not.
	 * 
	 * @param hasWlan The WLAN state.
	 */
	public void setWlan(boolean hasWlan) {
		this.hasWlan = hasWlan;
	}
	
	@Override
	public boolean hasMobileInternet() {
		return hasMobileInternet;
	}
	
	/**
	 * Changes weather we have mobileInternet or not.
	 * 
	 * @param hasMobileInternet
	 */
	public void setMobileInternet(boolean hasMobileInternet) {
		this.hasMobileInternet = hasMobileInternet;
	}
}
