package com.xiaoyuan.designpattern.factorymethod;

/**
 * @author : liyuan  
 * @description :
 * @date : 2020/9/23 16:36  
 */
public class ConcreteProductB extends Application {

    @Override
    public Product createProduct() {
        ProductB productB = new ProductB();
        return productB;
    }
}
