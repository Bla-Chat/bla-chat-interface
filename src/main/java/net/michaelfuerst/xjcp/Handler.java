package net.michaelfuerst.xjcp;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * A message handler interface.
 * 
 * @author Michael Fürst
 * @author TheDwoon
 * @version 1.1
 * @since 21.3.14
 */
public abstract class Handler {
	/** Threads available to the handler. */
	private static final int THREADS = 4;
	
	/** The executor used. */
	private final ExecutorService executor;
	
	/**
	 * Creates a new handler.
	 */
	public Handler() {
		this.executor = Executors.newFixedThreadPool(THREADS);
	}
	
    /**
     * Subclasses must implement this to receive messages.
     * @param msg The message to handle.
     */
    public abstract void handleMessage(final Message msg);

    /**
     * Returns a new Message from the global message pool.
     * @return The message.
     */
    public final Message obtainMessage() {
        return Message.obtain();
    }

    /**
     * Same as obtainMessage(), except that it also sets the what, obj, arg1,and arg2 values on the returned Message.
     * @return The message.
     */
    public final Message obtainMessage(final int what, final int arg1, final int arg2, final Object obj) {
        Message m = Message.obtain();
        m.arg1 = arg1;
        m.arg2 = arg2;
        m.obj = obj;
        m.what = what;
        return m;
    }

    /**
     * Pushes a message onto the end of the message queue after all pending messages before the current time.
     * @param msg The message to send.
     * @return Weather the message was send or not.
     */
    public final boolean sendMessage(final Message msg) {
        final Handler that = this;
        executor.submit(new Runnable() {			
			@Override
			public void run() {
				that.handleMessage(msg);
			}
		});
        return true;
    }
}
