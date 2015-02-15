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
public final class GenericFuture<V> implements Future<V> {
	private final Thread executor;
	
	private volatile V value;
	private Throwable exceptionCaught;
	
	private volatile boolean canceled;
	private volatile boolean done;
	
	/**
	 * Creates a generic future with unknwon execution thread.
	 */
	public GenericFuture() {
		this(null);
	}
	
	public GenericFuture(Thread executor) {
		this.executor = executor;
		this.value = null;
		this.canceled = false;
		this.done = false;
	}
	
	@Override
	public boolean cancel(boolean mayInterruptIfRunning) {
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
	public V get() throws InterruptedException, ExecutionException {
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
	public V get(long timeout, TimeUnit unit) throws InterruptedException,
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
	public boolean isCancelled() {
		return canceled;
	}

	@Override
	public boolean isDone() {
		return done;
	}

	public synchronized void setValue(V value) {
		this.value = value;
		this.done = true;		
		
		notifyAll();
	}
	
	public synchronized void setException(Throwable e) {
		if (e == null) {
			return;
		}
		
		this.done = true;
		this.canceled = false;
		this.exceptionCaught = e;
		
		notifyAll();
	}
}
