/*
 * Copyright (C) 2012, 2013 RICOH Co., Ltd. All rights reserved.
 */

import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Simple Thread Pool class.
 *
 * This class can be used to dispatch an Runnable object to
 * be exectued by a thread.
 *
 * [Instruction]
 *  Implement one constructor and three methods.
 *  Don't forget to write a Test program to test this class. 
 *  Pay attention to @throws tags in the javadoc.
 *  If needed, you can put "synchronized" keyword to methods.
 *  All classes for implementation must be private inside this class.
 *  Don't use java.util.concurrent package.
 */
public class ThreadPool {
  private boolean started = false;
  private BlockingQueue<Runnable> queue = null;
  private List<Worker> workers = new ArrayList<>();

  /**
   * Constructs ThreadPool.
   *
   * @param queueSize the max size of queue
   * @param numberOfThreads the number of threads in this pool.
   *
   * @throws IllegalArgumentException if either queueSize or numberOfThreads
   *         is less than 1
   */
  public ThreadPool(int queueSize, int numberOfThreads) {
    if (queueSize <= 0) {
      throw new IllegalArgumentException("queueSize shoule be >= 0.");
    }
    if (numberOfThreads <= 0) {
      throw new IllegalArgumentException("numberOfThreads shoule be >= 0.");
    }
    queue = new LinkedBlockingQueue<>(queueSize);
    for (int i = 0; i < numberOfThreads; i++) {
      workers.add(new Worker(String.format("worker %d", i), queue));
    }
  }

  /**
   * Starts threads.
   *
   * @throws IllegalStateException if threads has been already started.
   */
  public void start() {
    if (started) {
      throw new IllegalStateException("This pool has been already started.");
    }
    started = true;
    for (Worker w : workers) { w.start(); }
  }

  /**
   * Stop all threads and wait for their terminations.
   *
   * @throws IllegalStateException if threads has not been started.
   */
  public void stop() {
    if (!started) {
      throw new IllegalStateException("This pool has not been started.");
    }
    started = false;
    for (Worker w : workers) { w.stop(); }
  }

  /**
   * Executes the specified Runnable object, using a thread in the pool.
   * run() method will be invoked in the thread. If the queue is full, then
   * this method invocation will be blocked until the queue is not full.
   * 
   * @param runnable Runnable object whose run() method will be invoked.
   *
   * @throws NullPointerException if runnable is null.
   * @throws IllegalStateException if this pool has not been started yet.
   */
  public synchronized void dispatch(Runnable runnable) {
    if (runnable == null) {
      throw new NullPointerException("runnable should not be null.");
    }
    if (!started) {
      throw new IllegalStateException("This pool has not been started yet.");
    }

    synchronized (queue) {
      while (!queue.offer(runnable)) {
        try {
          queue.wait();
        } catch (InterruptedException e) {
          return;
        }
      }
      queue.notifyAll();
    }
    try {
      Thread.sleep(0);
    } catch (Exception e) { }
  }

  private static class Worker implements Runnable {
    String name;
    BlockingQueue<Runnable> queue;
    boolean stopped = false;
    Thread thread = null;

    Worker(String name, BlockingQueue<Runnable> queue) {
      this.name = name;
      this.queue = queue;
    }

    public void start() {
      thread = new Thread(this);
      thread.start();
    }

    public void stop() {
      stopped = true;
      synchronized (queue) { queue.notifyAll(); }
      try {
        thread.join();
      } catch (InterruptedException e) { }
    }

    public void run() {
      for (;;) {
        Runnable runnable = null;
        synchronized (queue) {
          while (!stopped && queue.isEmpty()) {
            try {
              queue.wait();
            } catch (InterruptedException e) {
              return;
            }
          }
          if (stopped) { return; }
          runnable = queue.poll();
          queue.notifyAll();
        }
        runnable.run();
      }
    }
  }

	private static class CounterTask implements Runnable {
		private int runCount = 0;
		
		@Override
		public synchronized void run() {
			runCount++;
			notifyAll();
		}
		
		synchronized int waitForRunCount(int count) {
			while (this.runCount < count) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			return runCount;
		}
	}
	

  public static void main(String[] args) {
		// ThreadPool tp = new ThreadPool(1,1);
		// tp.start();
		// tp.stop();

		final Set<Thread> threads = Collections.synchronizedSet(new HashSet<Thread>());
		Runnable task = new Runnable() {
			@Override
			public void run() {
				threads.add(Thread.currentThread());
				try {
					Thread.sleep(500); // wait for a while
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		
		final int numberOfThreads = 10;
		ThreadPool tp = new ThreadPool(10,numberOfThreads);
		tp.start();
		for (int i = 0; i < numberOfThreads; i++)
			tp.dispatch(task);
		
		// By the specification, stop() will wait for the terminations of all threads.
		tp.stop();
    
		System.out.printf("%d should be equal to %d\n", numberOfThreads, threads.size());
  }
}
