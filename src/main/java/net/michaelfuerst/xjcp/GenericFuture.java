package net.michaelfuerst.xjcp;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 
 * @author TheDwoon
 *
 * @param <V>
 */
public class GenericFuture<V> implements Future<V> {	
	private volatile Thread executor;
	private volatile V value;
	private volatile Throwable exceptionCaught;
	
	private volatile boolean canceled;
	private volatile boolean done;
	
	/**
	 * Creates a generic future with unknwon execution thread.
	 */
	public GenericFuture() {
		this(null);
	}
	
	public GenericFuture(final Thread executor) {
		this.executor = executor;
		this.value = null;
		this.canceled = false;
		this.done = false;
	}
	
	@Override
	public final boolean cancel(final boolean mayInterruptIfRunning) {
		synchronized (this) {
			if (isDone()) {
				return false;
			}
		
			done = true;
			canceled = true;
			
			if (executor != null && mayInterruptIfRunning) {
				executor.interrupt();
			}
			
			notifyAll();
			
			return true;
		}
	}

	@Override
	public final V get() throws InterruptedException, ExecutionException {
		synchronized (this) {			
			while (!isDone()) {
				wait();
			}
			
			if (isCancelled()) {
				throw new CancellationException();
			} else if (exceptionCaught != null) {
				throw new ExecutionException(exceptionCaught);
			}
			
			return value;
		}		
	}

	@Override
	public final V get(final long timeout, final TimeUnit unit) throws InterruptedException,
			ExecutionException, TimeoutException {
		synchronized (this) {
			long waitTime = 0;
			while (!isDone()) {
				long start = System.currentTimeMillis();
				wait(unit.toMillis(timeout));
				waitTime += System.currentTimeMillis() - start;				
				if (unit.toMillis(timeout) <= waitTime) {
					throw new TimeoutException();
				}
			}
			
			if (isCancelled()) {
				throw new CancellationException();
			} else if (exceptionCaught != null) {
				throw new ExecutionException(exceptionCaught);
			}
			
			return value;
		}
	}

	@Override
	public final boolean isCancelled() {
		return canceled;
	}

	@Override
	public final boolean isDone() {
		return done;
	}

	public synchronized void setValue(final V value) {
		this.value = value;
		this.done = true;		
		
		notifyAll();
	}
	
	public synchronized void setException(final Throwable e) {
		if (e == null) {
			return;
		}
		
		this.done = true;
		this.exceptionCaught = e;
		
		notifyAll();
	}
	
	public synchronized void setExecutor(final Thread t) {
		this.executor = t;
	}
}
