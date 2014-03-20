package net.michaelfuerst.xjcp;

/**
 * An interface that helps the api to determine the status of the internet connection.
 * @author Michael Fürst
 * @version 1.0
 */
public interface Connection {
    /**
     * Determine if the hardware has a lan connection.
     * @return True if LAN is connected.
     */
    boolean hasLAN();

    /**
     * Determine if the hardware has a wlan connection.
     * @return True if WLAN is connected.
     */
    boolean hasWLAN();

    /**
     * Determine if the hardware has mobile internet (edge, 3G, LTE, ...).
     * @return True if mobile internet is connected.
     */
    boolean hasMobileInternet();
}
