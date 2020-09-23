package com.xiaoyuan.designpattern.strategy.v1;

/**
 * @author : liyuan  
 * @description :
 * @date : 2020/9/23 17:45  
 */
public class BigHeadZombie extends AbstractZombie {

    @Override
    public void display() {
        System.out.println("大头.");
    }

    @Override
    public void attack() {
        // ...
        // ...
        System.out.println("头撞");
    }
}
