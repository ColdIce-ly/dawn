package com.xiaoyuan.threadjuc.thread.pool.common;


import java.util.HashMap;
import java.util.concurrent.*;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author : liyuan  
 * @description : 普通线程池,专门处理业务逻辑
 * @date : 2020/1/17 12:26  
 */
public class CommonThreadPool {

    /**
     * @description 创建线程池
     * @param corePoolSize    核心线程池的数量,创建完成后准备就绪,等待被调用,除非线程设置了超时设置
     * @param maximumPoolSize 最大线程数量;控制资源并发
     * @param keepAliveTime   存活时间 ,当前线程数量大于最大线程数量时,释放空闲的线程.只要线程空闲大于指定的存货时间.核心线程不会被释放
     * @param unit            时间单位
     * @param workQueue       BlockingQueue<Runnable> workQueue, 阻塞队列,如果任务有很多,就会将目前多的任务放在队列里面,只要有线程空闲,就会去队列里面取出新的任务继续执行
     * @param threadFactory   创建线程的工厂
     * @param handler         如果阻塞队列满了,按照我们指定的拒绝策略拒绝执行任务
     */
    public static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 20, 30, TimeUnit.SECONDS, new LinkedBlockingDeque<>(100), Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());

    /**
     * @param commonThreadFunction 业务
     * @description 执行业务, 无返回值; 当心吞异常哦!
     */
    public static void perform(CommonThreadFunction commonThreadFunction) {
        CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getId() + "线程执行----------");
            commonThreadFunction.perform();
        }, threadPoolExecutor).exceptionally(throwable -> {
            System.out.println(Thread.currentThread().getId() + "线程报错----------" + throwable.getMessage());
            return null;
        });
    }

    /**
     * @param supplier 有返回值业务
     * @description 执行业务, 无返回值; 当心吞异常哦!
     */
    public static <T> T perform(Supplier<T> supplier) throws ExecutionException, InterruptedException {
        CompletableFuture<T> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getId() + "线程执行----------");
            return supplier.get();
        }, threadPoolExecutor).whenComplete((res, exception) -> {
            System.out.println("成功:" + res + "异常是:" + exception);
        }).exceptionally(throwable -> {
            System.out.println(Thread.currentThread().getId() + "线程报错----------" + throwable.getMessage());
            return null;
        });
        return completableFuture.get();
    }

    /**
     * @param supplier 有返回值业务,并且对返回值和异常一起处理
     * @description 执行业务, 无返回值; 当心吞异常哦!
     */
    public static <T, Throwable, R> R perform(Supplier<T> supplier, BiFunction<T, Throwable, R> biFunction) throws ExecutionException, InterruptedException {
        CompletableFuture<R> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getId() + "线程执行----------");
            return supplier.get();
        }, threadPoolExecutor).handle((res, thr) -> {
            // 方法执行完成之后的处理
            return biFunction.apply(res, (Throwable) thr);
        });
        return completableFuture.get();
    }

    /**
     * @param commonThreadFunction1 双业务,第二个线程不接受上一个线程的结果
     * @param commonThreadFunction2 双业务,第二个线程不接受上一个线程的结果
     * @description 执行业务, 无返回值; 当心吞异常哦!
     */
    public static void perform(CommonThreadFunction commonThreadFunction1, CommonThreadFunction commonThreadFunction2) {
        CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getId() + "线程执行----------");
            commonThreadFunction1.perform();
        }, threadPoolExecutor).thenRunAsync(() -> {
            System.out.println("串行处理没有返回值,不接受第一次的返回值");
            commonThreadFunction2.perform();
        }, threadPoolExecutor).exceptionally(exception -> {
            System.out.println(exception.getMessage());
            return null;
        });
    }

    /**
     * @param supplier 双业务,第二个线程接受上一个线程的结果,返回第一个线程的返回值,等待两个线程全部执行结束
     * @description 执行业务, 无返回值; 当心吞异常哦!
     */
    public static <U> U perform(Supplier<U> supplier, Consumer<U> consumer) throws ExecutionException, InterruptedException {
        HashMap<Integer, U> hashMap = new HashMap<>(16);
        CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getId() + "线程执行第一个----------");
            U perform = supplier.get();
            hashMap.put(0, perform);
            return perform;
        }, threadPoolExecutor).thenAcceptAsync(res -> {
            System.out.println("串行处理没有返回值,接受第一次的返回值" + res);
            consumer.accept(res);
        }, threadPoolExecutor).exceptionally(exception -> {
            System.out.println(exception.getMessage());
            hashMap.put(0, null);
            return null;
        });
        // 阻塞,确保第二个线程执行结束
        completableFuture.get();
        return hashMap.get(0);
    }

    /**
     * @param threadSupplier 双业务,第二个线程接受上一个线程的结果,返回值是第二个线程的
     * @description 执行业务, 无返回值; 当心吞异常哦!
     */
    public static <T, R> R perform(Supplier<T> threadSupplier, Function<T, R> function) throws ExecutionException, InterruptedException {
        CompletableFuture<R> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getId() + "线程执行----------");
            return threadSupplier.get();
        }, threadPoolExecutor).thenApplyAsync(res -> {
            System.out.println(Thread.currentThread().getId() + "线程执行----------");
            return function.apply(res);
        }, threadPoolExecutor).exceptionally(exception -> {
            System.out.println(exception.getMessage());
            return null;
        });
        return completableFuture.get();
    }
}
