package com.xiaoyuan.designpattern.facade;

/**
 * @author : liyuan  
 *
 * @date : 2020/9/23 13:36  
 */
public class Client2 {

    Facade facade = new Facade();

    public void doSomething2() {
        facade.doSomethingFacade();
    }

}
