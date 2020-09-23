package com.xiaoyuan.designpattern.abstractfactroy;

/**
 * @author : liyuan  
 * @description :
 * @date : 2020/9/23 16:41  
 */
public class OracleConnection implements IConnection {


    @Override
    public void connection() {
        System.out.println("oracle:connect.");
    }
}
