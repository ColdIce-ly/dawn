package com.xiaoyuan.designpattern.strategy.v2;

/**
 * @author : liyuan  
 * @description :
 * @date : 2020/9/23 17:55  
 */
public class BiteAttack implements Attackable {

    @Override
    public void attack() {
        System.out.println("咬.");
    }
}
