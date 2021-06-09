package com.xiaoyuan.designpattern.create.abstractfactroy;

/**
 * @author : liyuan  
 *
 * @date : 2020/9/23 16:41  
 */
public class OracleCommand implements ICommand {

    @Override
    public void command() {
        System.out.println("oracle:command.");
    }
}
