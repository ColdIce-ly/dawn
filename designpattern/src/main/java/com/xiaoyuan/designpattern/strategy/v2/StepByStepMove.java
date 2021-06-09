package com.xiaoyuan.designpattern.strategy.v2;

/**
 * @author : liyuan  
 *
 * @date : 2020/9/23 17:54  
 */
public class StepByStepMove implements Moveable {

    @Override
    public void move() {

        System.out.println("一步一步移动.");
    }
}
