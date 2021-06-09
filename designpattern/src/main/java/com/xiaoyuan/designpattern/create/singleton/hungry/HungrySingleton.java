package com.xiaoyuan.designpattern.create.singleton.hungry;

/**
 * 单例模式 恶汉式
 *
 * @author : liyuan  
 * @date : 2020/9/23 13:49  
 */
public class HungrySingleton {

    private static HungrySingleton instance = new HungrySingleton();

    private HungrySingleton() {
        System.out.println(this.getClass().getSimpleName() + " Instantiate ---------");
    }

    public static HungrySingleton getInstance() {
        return instance;
    }
}
