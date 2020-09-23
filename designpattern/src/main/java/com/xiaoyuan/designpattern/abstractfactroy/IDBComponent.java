package com.xiaoyuan.designpattern.abstractfactroy;

/**
 * @author : liyuan  
 * @description :
 * @date : 2020/9/23 16:40  
 */
public interface IDBComponent {

    IConnection getConnection();

    ICommand getCommand();
}
