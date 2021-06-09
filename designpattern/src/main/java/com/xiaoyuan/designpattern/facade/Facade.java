package com.xiaoyuan.designpattern.facade;

/**
 * @author : liyuan  
 *
 * @date : 2020/9/23 13:37  
 */
public class Facade {

    SubSystem1 subSystem1 = new SubSystem1();
    SubSystem2 subSystem2 = new SubSystem2();
    SubSystem3 subSystem3 = new SubSystem3();

    public void doSomethingFacade() {
        subSystem1.method1();
        subSystem2.method2();
        subSystem3.method3();
    }

}
