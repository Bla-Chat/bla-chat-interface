package net.michaelfuerst.xjcp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.Test;

public final class GenericFutureTest {
	private Object v = new Object();
	
	@Test(timeout = 200)
	public void get() throws InterruptedException, ExecutionException {
		final GenericFuture<Object> future = new GenericFuture<Object>();
		runDelayed(() -> future.setValue(v), 50);
		
		assertEquals(v, future.get());
	}
	
	@Test(timeout = 200)
	public void getTimed() throws InterruptedException, ExecutionException, TimeoutException {
		final GenericFuture<Object> future = new GenericFuture<Object>();
		runDelayed(() -> future.setValue(v), 50);
		
		assertEquals(v, future.get(100, TimeUnit.MILLISECONDS));
	}
	
	@Test(timeout = 200, expected = TimeoutException.class)
	public void getTimedWithTimeout() throws InterruptedException, ExecutionException, TimeoutException {
		final GenericFuture<Object> future = new GenericFuture<Object>();
		runDelayed(() -> future.setValue(v), 50);
		
		assertEquals(v, future.get(25, TimeUnit.MILLISECONDS));
	}
	
	@Test(timeout = 200, expected = CancellationException.class) 
	public void cancel() throws InterruptedException, ExecutionException, TimeoutException {
		final GenericFuture<Object> future = new GenericFuture<Object>();
		runDelayed(() -> future.setValue(v), 50);
		runDelayed(() -> future.cancel(false), 10);
		
		assertNull(future.get());
	}
	
	@Test(timeout = 50, expected = ExecutionException.class) 
	public void exception() throws InterruptedException, ExecutionException, TimeoutException {
		final GenericFuture<Object> future = new GenericFuture<Object>();
		runDelayed(() -> future.setValue(v), 100);
		runDelayed(() -> future.setException(new RuntimeException()), 10);
		
		assertNull(future.get());
	}
	
	private Thread runDelayed(final Runnable r, final long delay) {
		Thread t = new Thread(new Runnable() {		
			@Override
			public void run() {
				try {
					Thread.sleep(delay);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
				
				r.run();
			}
		});
		
		t.start();
		return t;
	}
}
