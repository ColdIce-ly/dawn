package com.xiaoyuan.designpattern.create.factorymethod;

public class FactoryMethodTest {
    public static void main(String[] args) {

        CarFactory carFactory1 = new LamborghiniFactory();
        Car car1 = carFactory1.getCar();
        System.out.println(car1.name());
        System.out.println(car1.price());

        System.out.println("-----------------------");

        CarFactory carFactory2 = new FerrariFactory();
        Car car2 = carFactory2.getCar();
        System.out.println(car2.name());
        System.out.println(car2.price());
    }

}
