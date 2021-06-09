package com.xiaoyuan.designpattern.strategy.v1;

/**
 * @author : liyuan  
 *
 * @date : 2020/9/23 17:46  
 */
public class NormalZombie extends AbstractZombie {

    @Override
    public void display() {
        System.out.println("我是普通僵尸。");
    }

}
