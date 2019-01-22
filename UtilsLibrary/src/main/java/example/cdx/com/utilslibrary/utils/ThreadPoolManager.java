package example.cdx.com.utilslibrary.utils;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程池管理类
 */
public class ThreadPoolManager {

    /**
     * 获得实例（单例）
     * @return
     */
    public static ThreadPoolManager getInstance() {
        return Inner.threadPoolManager;
    }

    /**
     * 静态内部类实现单例
     */
    private static class Inner {
        static ThreadPoolManager threadPoolManager = new ThreadPoolManager();
    }

    /**
     * 核心线程池的数量，同时能够执行的线程数量，当前设备可用处理器核心数*2 + 1,能够让cpu的效率得到最大程度执行（有研究论证的）
     */
    private int corePoolSize = 5;

    /**
     * 最大线程池数量，表示当缓冲队列满的时候能继续容纳的等待任务的数量，虽然maximumPoolSize用不到，但是需要赋值，否则报错
     */
    private int maximumPoolSize = Runtime.getRuntime().availableProcessors() * 2 + 1;

    /**
     * 存活时间
     */
    private long keepAliveTime = 30;
    private TimeUnit mTimeUnit = TimeUnit.MINUTES;
    private ThreadPoolExecutor mMultipleExecutor;
    private ThreadPoolExecutor mSingleExecutor;

    private ThreadPoolManager() {

        //参1:核心线程数，当某个核心任务执行完毕，会依次从缓冲队列中取出等待任务。
        //默认情况下，核心线程会一直存在。
        //参2:最大线程数,它的数量包含了corePoolSize
        //超过这个数的线程将被阻塞。
        //当任务队列为没有设置大小的LinkedBlockingDeque时，这个值无效。
        // 参3:非核心线程的闲置时间，超过这个时间就会被回收
        // 参4:指定keepAliveTime的单位。
        // 参5:线程队列，缓冲队列，用于存放等待任务，Linked的先进先出;
        //常用的有3种队列：SynchronousQueue,LinkedBlockingDeque,ArrayBlockingQueue。
        // 参6:生产线程的工厂。
        // 参7:线程异常处理策略,用来对超出maximumPoolSize的任务的处理策略
        mMultipleExecutor = new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                mTimeUnit,
                new LinkedBlockingQueue<Runnable>(),
                new DefaultThreadFactory(Thread.NORM_PRIORITY, "hdl-mPool-"),
                new ThreadPoolExecutor.DiscardPolicy()
        );

        //参1:核心线程数，当某个核心任务执行完毕，会依次从缓冲队列中取出等待任务。
        // 参2:最大线程数,它的数量包含了corePoolSize
        // 参3:线程休眠时间,表示的是maximumPoolSize当中等待任务的存活时间
        // 参4:时间单位;
        // 参5:线程队列，缓冲队列，用于存放等待任务，Linked的先进先出;
        // 参6:生产线程的工厂;
        // 参7:线程异常处理策略,用来对超出maximumPoolSize的任务的处理策略
        mSingleExecutor = new ThreadPoolExecutor(
                1,
                1,
                keepAliveTime,
                mTimeUnit,
                new LinkedBlockingQueue<Runnable>(),
                new DefaultThreadFactory(Thread.NORM_PRIORITY, "hdl-sPool-"),
                new ThreadPoolExecutor.DiscardPolicy()
        );
    }

    /**
     * 执行任务
     *
     * @param runnable
     */
    public void execute(Runnable runnable) {
        if (runnable != null) {
            mMultipleExecutor.execute(runnable);
        }
    }

    /**
     * 移除任务（如果这个任务在队列中的话，移除任务）
     */
    public void remove(Runnable runnable) {
        if (runnable != null) {
            mMultipleExecutor.remove(runnable);
        }
    }

    /**
     * 执行任务
     *
     * @param runnable
     */
    public void executeSingle(Runnable runnable) {
        if (runnable != null) {
            mSingleExecutor.execute(runnable);
        }
    }

    /**
     * 移除任务
     */
    public void removeSingle(Runnable runnable) {
        if (runnable != null) {
            mSingleExecutor.remove(runnable);
        }
    }

    /**
     * 创建线程的工厂，设置线程的优先级，group，以及命名
     * （默认的线程工厂）
     */
    private static class DefaultThreadFactory implements ThreadFactory {
        /**
         * 线程池的计数
         */
        private static final AtomicInteger poolNumber = new AtomicInteger(1);

        /**
         * 线程的计数
         */
        private final AtomicInteger threadNumber = new AtomicInteger(1);

        private final ThreadGroup group;//线程组
        private final String namePrefix;//前缀
        private final int threadPriority;//线程的优先级

        DefaultThreadFactory(int threadPriority, String threadNamePrefix) {
            this.threadPriority = threadPriority;
            this.group = Thread.currentThread().getThreadGroup();
            namePrefix = threadNamePrefix + poolNumber.getAndIncrement() + "-t-";
        }

        @Override
        public Thread newThread(Runnable r) {
            //参数1：线程组
            //参数2：线程的名字
            //参数3：表示栈中需要的大小，传入0，表示这个参数将要被忽略
            Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
            //线程是否为守护线程，如果返回true，表示为守护线程。
            //守护线程：要有守护对象，守护对象没有了，守护线程也就失去了意义。
            if (t.isDaemon()) {
                t.setDaemon(false);
            }
            //设置线程的有优先级
            t.setPriority(threadPriority);
            return t;
        }
    }
}
