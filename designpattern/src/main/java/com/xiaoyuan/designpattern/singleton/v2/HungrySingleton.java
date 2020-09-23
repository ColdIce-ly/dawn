package com.xiaoyuan.designpattern.singleton.v2;

/**
 * @author : liyuan  
 * @description : 单例模式 恶汉式
 * @date : 2020/9/23 13:49  
 */
public class HungrySingleton {

    private static HungrySingleton instance = new HungrySingleton();

    private HungrySingleton() {

    }

    public static HungrySingleton getInstance() {
        return instance;
    }
}
