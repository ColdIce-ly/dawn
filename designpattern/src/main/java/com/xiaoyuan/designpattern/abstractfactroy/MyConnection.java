package com.xiaoyuan.designpattern.abstractfactroy;

/**
 * @author : liyuan  
 * @description :
 * @date : 2020/9/23 16:40  
 */
public class MyConnection implements IConnection {

    @Override
    public void connection() {
        System.out.println("mysql: connect.");
    }
}
