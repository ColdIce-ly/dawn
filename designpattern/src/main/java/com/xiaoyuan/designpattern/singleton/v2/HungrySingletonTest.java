package com.xiaoyuan.designpattern.singleton.v2;

/**
 * @author : liyuan  
 * @description :
 * @date : 2020/9/23 13:49  
 */
public class HungrySingletonTest {
    public static void main(String[] args) {
        HungrySingleton instance = HungrySingleton.getInstance();
        HungrySingleton instance1 = HungrySingleton.getInstance();
        System.out.println(instance == instance1);
    }
}