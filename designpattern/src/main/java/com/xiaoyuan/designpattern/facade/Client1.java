package com.xiaoyuan.designpattern.facade;

/**
 * @author : liyuan  
 *
 * @date : 2020/9/23 13:35  
 */
public class Client1 {

    Facade facade = new Facade();

    public void doSomething1() {
        facade.doSomethingFacade();
    }
}
