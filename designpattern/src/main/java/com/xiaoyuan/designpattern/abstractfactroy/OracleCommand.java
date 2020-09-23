package com.xiaoyuan.designpattern.abstractfactroy;

/**
 * @author : liyuan  
 * @description :
 * @date : 2020/9/23 16:41  
 */
public class OracleCommand implements ICommand {

    @Override
    public void command() {
        System.out.println("oracle:command.");
    }
}
