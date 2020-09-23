package com.xiaoyuan.designpattern.singleton.v1;

/**
 * @author : liyuan  
 * @description : 单例模式 懒汉式
 * @date : 2020/9/23 13:47  
 */
public class LazySingletonTest {
    public static void main(String[] args) {


        Thread thread1 = new Thread(() -> {
            LazySingleton instance = LazySingleton.getInstance();
            System.out.println(instance);

        });


        Thread thread2 = new Thread(() -> {
            LazySingleton instance = LazySingleton.getInstance();
            System.out.println(instance);

        });

        thread1.start();
        thread2.start();


    }
}