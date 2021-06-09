package com.xiaoyuan.designpattern.create.abstractfactroy;

/**
 * @author : liyuan  
 *
 * @date : 2020/9/23 16:40  
 */
public class MySQLCommand implements ICommand {

    @Override
    public void command() {
        System.out.println("mysql: command.");
    }
}
