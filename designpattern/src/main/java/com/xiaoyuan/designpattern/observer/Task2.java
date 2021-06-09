package com.xiaoyuan.designpattern.observer;

/**
 * @author : liyuan  
 *
 * @date : 2020/9/23 18:19  
 */
public class Task2 implements Observer {

    @Override
    public void update(Object object) {
        System.out.println(" task2 received: " + object);
    }
}
