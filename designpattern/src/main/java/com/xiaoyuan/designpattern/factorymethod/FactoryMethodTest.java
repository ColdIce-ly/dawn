package com.xiaoyuan.designpattern.factorymethod;

public class FactoryMethodTest {
    public static void main(String[] args) {
        Application application = new ConcreteProductB();
        Product product = application.getObject();
        product.method1();

    }
}
