package net.penguinmenac3.xjcp;

/**
 * An interface to the xjcp protocol.
 * @author Michael Fürst
 * @version 1.0
 */
public abstract class XJCP {
    /**
     * Create an implementation of the xjcp for the minified protocol.
     *
     * @param host The host to use.
     * @param useHttp Weather to use http. (Default should be true.)
     * @param minified Weather the protocol is minified or not.
     * @return The freshly created xjcp implementation.
     */
    public XJCP createXJCP(final String host, final boolean useHttp, final boolean minified) {
        return null;
    }

    public abstract void setLoginData(final String user, final String password, final Handler handler);
    public abstract void sendMessage(final String conversation, final String message, final Handler handler);
    public abstract void requestChats(final Handler handler);
    public abstract void requestContacts(final Handler handler);
    public abstract void requestHistory(final String conversation, final Handler handler);
    public abstract void removeEvents(final String conversation, final Handler handler);
    public abstract void createConversation(final String[] participants, final Handler handler);
    public abstract void renameConversation(final String conversation, final String name, final Handler handler);
    public abstract void renameSelf(final String name, final Handler handler);
    public abstract void addFriend(final String user, final Handler handler);
    public abstract void setStatus(final int status, final Handler handler);
    public abstract void setProfileImage(final Object data, final Handler handler); // fixme Bitmap is android only
    public abstract void setGroupImage(final Object data, final String conversation, final Handler handler); // fixme Bitmap is android only
    public abstract void injectEvent(final String conversation, final String type, final String message, final Handler handler);
    public abstract void sendData(final Object data, final String conversation, final Handler handler); // fixme a more flexible data type is required, that would also solve previous fixmes
}
