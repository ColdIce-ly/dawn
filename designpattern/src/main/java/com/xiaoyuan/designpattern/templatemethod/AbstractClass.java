package com.xiaoyuan.designpattern.templatemethod;

/**
 * @author : liyuan  
 *
 * @date : 2020/9/23 18:12  
 */
public abstract class AbstractClass {

    public void operation() {
        // open
        System.out.println(" pre ... ");

        System.out.println(" step1 ");

        System.out.println(" step2 ");

        templateMethod();
        // ....


    }

    abstract protected void templateMethod();
}
