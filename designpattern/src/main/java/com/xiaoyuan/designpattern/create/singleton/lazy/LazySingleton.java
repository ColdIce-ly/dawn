package com.xiaoyuan.designpattern.create.singleton.lazy;

/**
 * 单例模式 懒汉式
 *
 * @author : liyuan  
 * @date : 2020/9/23 13:47  
 */
public class LazySingleton {

    private volatile static LazySingleton instance;

    public LazySingleton() {
        System.out.println(this.getClass().getSimpleName() + " Instantiate ---------");
    }

    public static LazySingleton getInstance() {

        if (instance == null) {
            synchronized (LazySingleton.class) {
                if (instance == null) {
                    // JIT CPU
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
