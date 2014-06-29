package net.michaelfuerst.xjcp;

import net.michaelfuerst.xjcp.device.Device;

/**
 * An interface to the xjcp protocol.
 * @author Michael Fürst
 * @version 1.0
 */
public abstract class XJCP {
	public static final int RPL_CHATMESSAGE = 1;
	public static final int RPL_CHATOBJECT = 2;
	public static final int RPL_CONTACTOBJECT = 3;
	public static final int RPL_EVENT = 4;
	public static final int RPL_HISTORY = 5;
	public static final int RPL_ID = 6;
	public static final int RPL_LOGINERROR = 7;
	public static final int RPL_MESSAGE = 8;
	public static final int RPL_NEWCONVERSATION = 9;
	public static final int RPL_RENAMECONVERSATION = 10;
	public static final int RPL_ADDFRIEND = 11;
	public static final int RPL_SETPROFILEIMAGE = 12;
	public static final int RPL_SETGROUPIMAGE = 13;
	public static final int RPL_INJECTEVENT = 14;
	public static final int RPL_DATA = 15;
	
    // Creating an xjcp client.
    /**
     * Create an implementation of the xjcp for the minified protocol.
     *
     * @param host The host to use.
     * @param useHttp Weather to use http. (Default should be true.)
     * @param minified Weather the protocol is minified or not.
     * @param connection Identify the connectivity in realtime.
     * @return The freshly created xjcp implementation.
     */
    public static XJCP createXJCP(final String host, final boolean useHttp, final boolean minified, final Device connection) {
        return null;
    }

    // Configuring your xjcp client.
    /**
     * Set the XJCP to only use WLAN or LAN but not mobile internet.
     * @param enabled True if only WLAN and LAN should be used.
     */
    public abstract void setWLANOnly(boolean enabled);

    /**
     * Setup the keep alive interval.
     * When the user is successfully logged in and there was no communication with the server for the given time,
     * send a keep alive message.
     *
     * A keep alive message is a message that only contains the id.
     *
     * If the xjcp client does not have a valid id, it does nothing.
     *
     * @param timeInMs The time until a keep alive message has to be send in ms.
     */
    public abstract void setKeepAliveInterval(int timeInMs);

    /**
     * Set the event handler.
     * @param handler The event handler.
     */
    public abstract void setEventHandler(final Handler handler);


    // Communicating with the server.
    /**
     * Setup the login data. The handler will be called if the logindata was proofed right or wrong.
     * @param user The user to login.
     * @param password The password to use.
     * @param handler The handler.
     */
    public abstract void setLoginData(final String user, final String password, final Handler handler);

    /**
     * Send a text message to a given conversation.
     * @param conversation The conversation to send to.
     * @param message The text message.
     * @param handler The handler.
     */
    public abstract void sendMessage(final String conversation, final String message, final Handler handler);

    /**
     * Request the list of open chats.
     * @param handler The handler to call when the list is ready.
     */
    public abstract void requestChats(final Handler handler);

    /**
     * Request the list of contacts.
     * @param handler The handler to call when the list is ready.
     */
    public abstract void requestContacts(final Handler handler);

    /**
     * Request the history of a given conversation.
     * @param conversation The conversation.
     * @param count The max amount of messages to return.
     * @param handler The handler to call when the history is ready.
     */
    public abstract void requestHistory(final String conversation, int count, final Handler handler);

    /**
     * Remove all events from a given conversation. Assuming the user has read all messages.
     * @param conversation The conversation.
     * @param handler The handler.
     */
    public abstract void removeEvents(final String conversation, final Handler handler);

    /**
     * Create a new conversation.
     * @param participants The participants you want in the conversation.
     * @param handler The handler.
     */
    public abstract void createConversation(final String[] participants, final Handler handler);

    /**
     * Rename an existing conversation for this user.
     * @param conversation The The conversation.
     * @param name The name to display.
     * @param handler The handler.
     */
    public abstract void renameConversation(final String conversation, final String name, final Handler handler);

    /**
     * Rename yourself. Change the name that is displayed to others.
     * @param name The name to display.
     * @param handler The handler.
     */
    public abstract void renameSelf(final String name, final Handler handler);

    /**
     * Add a user as friend.
     * @param user The user to add.
     * @param handler The handler.
     */
    public abstract void addFriend(final String user, final Handler handler);

    /**
     * Set your status.
     * @param status The status. (0 is offline and 1 is online)
     * @param handler The handler.
     */
    public abstract void setStatus(final int status, final Handler handler);

    /**
     * Set your own profile image.
     * @param data The image to set as profile image.
     * @param handler The handler.
     */
    public abstract void setProfileImage(final Object data, final Handler handler); // fixme Bitmap is android only

    /**
     * Set the image of a conversation.
     * @param data The image to set as conversation image.
     * @param conversation The conversation.
     * @param handler The handler.
     */
    public abstract void setGroupImage(final Object data, final String conversation, final Handler handler); // fixme Bitmap is android only

    /**
     * Inject an event into the event system.
     * @param conversation The conversation.
     * @param type The type of the event.
     * @param message The message of the event.
     * @param handler The handler.
     */
    public abstract void injectEvent(final String conversation, final String type, final String message, final Handler handler);

    /**
     * Send data to a chat.
     * @param data The data to send.
     * @param conversation The conversation.
     * @param handler The handler.
     */
    public abstract void sendData(final Object data, final String conversation, final Handler handler); // fixme a more flexible data type is required, that would also solve previous fixmes
}
