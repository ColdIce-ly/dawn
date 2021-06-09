package com.xiaoyuan.designpattern.create.singleton.lazy;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 单例模式 懒汉式
 *
 * @author : liyuan  
 * @date : 2020/9/23 13:47  
 */
public class LazySingletonTest {

    public static void main(String[] args) {

        CountDownLatch countDownLatch = new CountDownLatch(1);

        int cpu = Runtime.getRuntime().availableProcessors();

        for (int i = 0; i < cpu; i++) {
            new Thread(() -> {
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                LazySingleton instance = LazySingleton.getInstance();

                System.out.println(instance);

            }).start();
        }
        countDownLatch.countDown();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}