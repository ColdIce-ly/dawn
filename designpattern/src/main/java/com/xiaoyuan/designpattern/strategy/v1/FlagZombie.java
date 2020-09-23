package com.xiaoyuan.designpattern.strategy.v1;

/**
 * @author : liyuan  
 * @description :
 * @date : 2020/9/23 17:46  
 */
public class FlagZombie extends AbstractZombie {

    @Override
    public void display() {
        System.out.println("我是旗手僵尸");
    }

}
