package com.xiaoyuan.designpattern.strategy.v1;

/**
 * @author : liyuan  
 * @description :
 * @date : 2020/9/23 17:44  
 */
public abstract class AbstractZombie {

    public abstract void display();

    public void attack() {
        System.out.println("咬.");
    }

    public void move() {
        System.out.println("一步一步移动.");
    }
}
