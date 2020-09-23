package com.xiaoyuan.designpattern.strategy.v2;

/**
 * @author : liyuan  
 * @description :
 * @date : 2020/9/23 17:55  
 */
public class HitAttack implements Attackable {

    @Override
    public void attack() {
        System.out.println("打.");
    }
}
