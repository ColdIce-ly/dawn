package com.xiaoyuan.designpattern.create.abstractfactroy;

/**
 * @author : liyuan  
 *
 * @date : 2020/9/23 16:41  
 */
public class OracleConnection implements IConnection {

    @Override
    public void connection() {
        System.out.println("oracle:connect.");
    }
}
