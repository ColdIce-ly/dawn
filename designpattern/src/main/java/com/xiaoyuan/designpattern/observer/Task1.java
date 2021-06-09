package com.xiaoyuan.designpattern.observer;

/**
 * @author : liyuan  
 *
 * @date : 2020/9/23 18:18  
 */
public class Task1 implements Observer {

    @Override
    public void update(Object object) {
        System.out.println(" task1 received: " + object);
    }
}
