package com.xiaoyuan.designpattern.create.factorymethod;

/**
 * 工厂方法
 *
 * @author : liyuan  
 * @date : 2020/9/23 16:35  
 */
public abstract class CarFactory {

    /**
     * 工厂方法 组装汽车
     *
     * @return Car
     */
    public abstract Car assembleCar();

    /**
     * 拿个车看看呢
     *
     * @return Car
     */
    public Car getCar() {

        Car car = assembleCar();
        // ......
        return car;
    }
}
