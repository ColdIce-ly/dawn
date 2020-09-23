package com.xiaoyuan.designpattern.factorymethod;

/**
 * @author : liyuan  
 * @description :
 * @date : 2020/9/23 16:36  
 */
public class ConcreteProductA extends Application{

    @Override
    public Product createProduct() {
        ProductA productA = new ProductA();
        // ...
        return productA;
    }
}
