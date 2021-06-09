package com.xiaoyuan.designpattern.create.abstractfactroy;

/**
 * @author : liyuan  
 *
 * @date : 2020/9/23 16:40  
 */
public class MySQLConnection implements IConnection {

    @Override
    public void connection() {
        System.out.println("mysql: connect.");
    }
}
