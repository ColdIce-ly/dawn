package com.xiaoyuan.designpattern.create.abstractfactroy;

/**
 * @author : liyuan  
 *
 * @date : 2020/9/23 16:40  
 */
public interface IDBComponent {

    IConnection getConnection();

    ICommand getCommand();
}
