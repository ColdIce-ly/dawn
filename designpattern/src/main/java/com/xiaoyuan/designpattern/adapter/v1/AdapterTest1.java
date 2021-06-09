package com.xiaoyuan.designpattern.adapter.v1;

/**
 * @author : liyuan  
 *  适配器模式-对象
 * @date : 2020/9/23 17:21  
 */
public class AdapterTest1 {
    public static void main(String[] args) {
        Adaptee adaptee = new Adaptee();

        Target target = new Adapter(adaptee);
        target.output5v();

    }
}


