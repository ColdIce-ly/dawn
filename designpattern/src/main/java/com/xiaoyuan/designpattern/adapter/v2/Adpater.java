package com.xiaoyuan.designpattern.adapter.v2;

/**
 * @author : liyuan  
 * @description :
 * @date : 2020/9/23 17:26  
 */
public class Adpater extends Adaptee implements Target {

    @Override
    public int output5v() {
        int i = output220v();
        //  ......
        System.out.println(String.format("原始电压： %d v  - >  输出电压： %d  v  ", i, 5));
        return 5;
    }
}
