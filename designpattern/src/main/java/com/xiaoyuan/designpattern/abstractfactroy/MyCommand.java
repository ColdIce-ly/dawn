package com.xiaoyuan.designpattern.abstractfactroy;

/**
 * @author : liyuan  
 * @description :
 * @date : 2020/9/23 16:40  
 */
public class MyCommand implements ICommand {

    @Override
    public void command() {
        System.out.println("mysql: command.");
    }
}
