package com.xiaoyuan.designpattern.factorymethod;

/**
 * @author : liyuan  
 * @description : 工厂方法
 * @date : 2020/9/23 16:35  
 */
public abstract class Application {

    // 工厂方法
    public abstract Product createProduct();

    public Product getObject() {

        Product product = createProduct();
        // ......
        return product;
    }
}
