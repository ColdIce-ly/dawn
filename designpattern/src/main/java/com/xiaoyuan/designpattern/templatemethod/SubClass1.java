package com.xiaoyuan.designpattern.templatemethod;

/**
 * @author : liyuan  
 *
 * @date : 2020/9/23 18:12  
 */
public class SubClass1 extends AbstractClass {

    @Override
    protected void templateMethod() {
        System.out.println("SubClass1 executed .  ");
    }
}
