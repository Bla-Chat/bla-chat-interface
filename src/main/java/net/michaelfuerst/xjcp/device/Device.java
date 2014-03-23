package net.michaelfuerst.xjcp.device;

/**
 * An interface that helps the api to determine the status of the internet connection.
 * 
 * @author Michael Fürst
 * @author TheDwoon
 * @version 1.0
 * @since 23.03.14
 */
public interface Device {
    /**
     * Determine if the hardware has a LAN connection.
     * @return True if LAN is connected.
     */
    boolean hasLAN();

    /**
     * Determine if the hardware has a WLAN connection.
     * @return True if WLAN is connected.
     */
    boolean hasWLAN();

    /**
     * Determine if the hardware has mobile Internet (edge, 3G, LTE, ...).
     * @return True if mobile Internet is connected.
     */
    boolean hasMobileInternet();
    
    /**
     * Determine if the hardware has Internet.
     * @return True if it can access the Internet.
     */
    boolean hasInternet();
}
