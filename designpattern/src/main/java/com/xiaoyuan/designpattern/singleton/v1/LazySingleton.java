package com.xiaoyuan.designpattern.singleton.v1;

/**
 * @author : liyuan  
 * @description : 单例模式 懒汉式
 * @date : 2020/9/23 13:47  
 */
public class LazySingleton {

    private volatile static LazySingleton instance;

    private LazySingleton() {

    }

    public static LazySingleton getInstance() {

        if (instance == null) {

            synchronized (LazySingleton.class) {
                if (instance == null) {
                    // JIT， CPU
                    instance = new LazySingleton();
                    //  1.分配空间
                    //  2.初始化
                    //  3.赋值

                }

            }

        }
        return instance;
    }
}
