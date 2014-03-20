package net.penguinmenac3.xjcp;

/**
 * A Message that is conform to the android message.
 * @author Michael Fürst
 * @version 1.0
 */
public class Message {
    /**
     * User-defined message code so that the recipient can identify what this message is about.
     */
    public int what;
    /**
     * An arbitrary object to send to the recipient.
     */
    public Object obj;
    /**
     * arg1 and arg2 are lower-cost alternatives to using setData() if you only need to store a few integer values.
     */
    public int arg1;
    /**
     * arg1 and arg2 are lower-cost alternatives to using setData() if you only need to store a few integer values.
     */
    public int arg2;
    /**
     * Optional Messenger where replies to this message can be sent.
     */
    public Object replyTo;

    public static Message obtain() {return new Message();}
}
