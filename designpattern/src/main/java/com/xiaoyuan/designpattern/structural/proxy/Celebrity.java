package com.xiaoyuan.designpattern.structural.proxy;

/**
 * @author : cold ice
 * @date : 2021-06-09 14:40
 */
public class Celebrity implements Concert {

    private final String name = "xx明星";

    @Override
    public void hold() {
        System.out.println(name + "; 举办演唱会------------");
    }
}
