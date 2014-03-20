package net.penguinmenac3.xjcp;

/**
 * An interface to the xjcp protocol.
 * @author Michael Fürst
 * @version 1.0
 */
public interface XJCP {
    void setLoginData(String user, String password, Handler handler);
    void sendMessage(String conversation, String message, Handler handler);
}
