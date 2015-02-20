package net.michaelfuerst.xjcp;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.concurrent.Future;

import net.michaelfuerst.xjcp.protocol.ChatHistory;
import net.michaelfuerst.xjcp.protocol.Contact;
import net.michaelfuerst.xjcp.protocol.Conversation;

/**
 * An interface to the xjcp protocol.
 * @author Michael Fürst
 * @version 1.0
 */
public interface XJCP {

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
    void setKeepAliveInterval(int timeInMs);

    /**
     * Set the event handler.
     * 
     * @param handler event handler.
     */
    void setEventHandler(final EventHandler handler);


    /**
     * Setup the login data. The handler will be called if the logindata was proofed right or wrong.
     * 
     * @param user The user to login.
     * @param password The password to use.
     */
    Future<Boolean> login(final String user, final String password);

    /**
     * Send a text message to a given conversation.
     * 
     * @param conversation The conversation to send to.
     * @param message The text message.
     */
     Future<String> sendMessage(final String conversation, final String message);

    /**
     * Request the list of open chats.
     */
    Future<List<Conversation>> requestChats();

    /**
     * Request the list of contacts.
     */
    Future<List<Contact>> requestContacts();

    /**
     * Request the history of a given conversation.
     * 
     * @param conversation The conversation.
     * @param count The max amount of messages to return.
     */
    Future<ChatHistory> requestHistory(final String conversation, int count);

    /**
     * Remove all events from a given conversation. Assuming the user has read all messages.
     * 
     * @param conversation The conversation.
     */
    Future<Void> removeEvents(final String conversation);

    /**
     * Create a new conversation.
     * 
     * @param participants The participants you want in the conversation.
     */
    Future<String> createConversation(final String[] participants);

    /**
     * Rename an existing conversation for this user.
     * 
     * @param conversation The The conversation.
     * @param name The name to display.
     */
    Future<String> renameConversation(final String conversation, final String name);

    /**
     * Rename yourself. Change the name that is displayed to others.
     * 
     * @param name The name to display.
     */
    Future<Void> renameSelf(final String name);

    /**
     * Add a user as friend.
     * 
     * @param user The user to add.
     */
    Future<String> addFriend(final String user);

    /**
     * Set your status.
     * 
     * @param status The status. (0 is offline and 1 is online)
     */
    Future<Void> setStatus(final int status);

    /**
     * Set your own profile image.
     * 
     * @param image The image to set as profile image.
     */
    Future<Void> setProfileImage(final BufferedImage image);

    /**
     * Set the image of a conversation.
     * 
     * @param image The image to set as conversation image.
     * @param conversation The conversation.
     */
    Future<Void> setGroupImage(final BufferedImage image, final String conversation);

    /**
     * Inject an event into the event system.
     * 
     * @param conversation The conversation.
     * @param type The type of the event.
     * @param message The message of the event.
     */
    Future<String> injectEvent(final String conversation, final String type, final String message);

    /**
     * Send data to a chat.
     * 
     * @param data The data to send.
     * @param conversation The conversation.
     */
    Future<Void> sendData(final Object data, final String conversation);
    
    /**
     * Terminates all activities.
     */
    void shutdown();
}
