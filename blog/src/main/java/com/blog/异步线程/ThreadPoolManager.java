package com.blog.异步线程;

import java.security.SecureRandom;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * <p>
 * 程池管理类
 * </p>
 *
 * @ClassName: MediaRecordThreadPoolManager
 * @Author: Bianhl
 * @Description: 线程池管理类
 * @since: create in 2021/4/22 18:58
 */
public class ThreadPoolManager {

	/**
	 * the cpu size
	 */
	private static final int CPU_SIZE = Runtime.getRuntime().availableProcessors();
	/**
	 * thread pool core size
	 */
	private static final int CORE_POOL_SIZE = 20;
	/**
	 * thread pool max size
	 */
	private static final int MAX_POOL_SIZE = 50;


	private static final int QUEUE_MAX_SIZE = 10000;

	private static final class Holder {

		/**
		 * random object
		 */
		private static final Random RANDOM = new SecureRandom();

		/**
		 * 设置名称
		 */
		/*
		 * private static final ThreadFactory NAMEDTHREADFACTORY = new
		 * ThreadFactoryBuilder() .setNameFormat("thread-pool").build();
		 */

		private static final BlockingQueue<Runnable> QUEUE = new LinkedBlockingQueue<>(QUEUE_MAX_SIZE);

		//变更点，注意，此处把线程池中的阻塞队列拿出来，重新put Runnable
		/**
		 * <p>
		 * 参数1:  corePoolSize： <br />
		 * 线程池的基本大小，即在没有任务需要执行的时候线程池的大小，并且只有在工作队列满了的情况下才会创建超出这个数量的线程。<br />
		 * 这里需要注意的是：<br />
		 * 在刚刚创建ThreadPoolExecutor的时候，线程并不会立即启动，而是要等到有任务提交时才会启动<br />
		 * 除非调用了prestartCoreThread/prestartAllCoreThreads事先启动核心线程。<br />
		 * 再考虑到keepAliveTime和allowCoreThreadTimeOut超时参数的影响，所以没有任务需要执行的时候，线程池的大小不一定是corePoolSize。<br />
		 * 参数2:  maximumPoolSize：<br />
		 * 线程池中允许的最大线程数，线程池中的当前线程数目不会超过该值。如果队列中任务已满，并且当前线程个数小于maximumPoolSize，那么会创建新的线程来执行任务。<br />
		 * 这里值得一提的是largestPoolSize，该变量记录了线程池在整个生命周期中曾经出现的最大线程个数。为什么说是曾经呢？因为线程池创建之后，<br />
		 * 可以调用setMaximumPoolSize()改变运行的最大线程的数目。<br />
		 * 参数3:  keepAliveTime：<br />
		 * 如果一个线程处在空闲状态的时间超过了该属性值，就会因为超时而退出。举个例子，如果线程池的核心大小corePoolSize=5，而当前大小poolSize =8，<br />
		 * 那么超出核心大小的线程，会按照keepAliveTime的值判断是否会超时退出。如果线程池的核心大小corePoolSize=5，而当前大小poolSize =5，<br />
		 * 那么线程池中所有线程都是核心线程，这个时候线程是否会退出，取决于allowCoreThreadTimeOut。<br />
		 * 参数4:  TimeUnit unit, 上一个参数（keepAliveTime）的单位<br />
		 * 参数5:  BlockingQueue  阻塞队列  通过BlockingQueue暂存还没有来得及执行的任务。  构造传入大小<br />
		 * 参数6:  ThreadFactory 线程工厂，传入Guava创建的ThreadFactoryBuilder 这里可以用其他的<br />
		 * 参数7： RejectedExecutionHandler 拒绝执行策略 多种<br />
		 * </p>
		 */
		private static ExecutorService EXECUTOR_SERVICE = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE,
				0L, TimeUnit.MILLISECONDS, QUEUE,new TelThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());



		private static void init() {
		}

		private static ExecutorService getExecutorService() {
			if (EXECUTOR_SERVICE == null) {
				EXECUTOR_SERVICE = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE,
						0L, TimeUnit.MILLISECONDS, QUEUE,new TelThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());
			}
			return EXECUTOR_SERVICE;
		}

		public static void destroy() {
			if (EXECUTOR_SERVICE != null) {
				EXECUTOR_SERVICE.shutdown();
			}
		}
	}

	/**
	 * 静态变量
	 */
	private static ThreadPoolManager threadPool = null;

	/**
	 * 私有构造
	 */
	private ThreadPoolManager() {
	}

	/**
	 * 获取单例方法
	 */
	public synchronized static ThreadPoolManager getInstance() {
		if (threadPool == null) {
			threadPool = new ThreadPoolManager();
		}
		return threadPool;
	}

	/**
	 * 执行方法
	 */
	public void execute(Runnable runnable) {
		getPool().execute(runnable);
	}

	/**
	 * 执行方法
	 */
	public <T> Future<T> submit(Callable<T> task) {
		return getPool().submit(task);
	}

	/**
	 * 执行方法
	 */
	public <T> Future<T> submit(Runnable task, T result) {
		return getPool().submit(task, result);
	}

	/**
	 * 执行方法
	 */
	public Future<?> submit(Runnable task) {
		return getPool().submit(task);
	}

	public void addTask(Runnable runnable) {
		Holder.QUEUE.add(runnable);
	}

	/**
	 * 批量执行任务
	 */
	public <T> List<Future<T>> batchExecute(Collection<? extends Callable<T>> tasks) throws InterruptedException {
		return getPool().invokeAll(tasks);
	}

	public void init() {
		Holder.init();
	}

	public void destroy() {
		try {
			Holder.destroy();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public ExecutorService getPool() {
		return Holder.EXECUTOR_SERVICE;
	}

	public void addQueue(Runnable runnable) {
		Holder.QUEUE.add(runnable);
	}

	public Runnable pollTask() {
		return Holder.QUEUE.poll();
	}
}
