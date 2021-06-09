package com.xiaoyuan.designpattern.create.factorymethod;

/**
 * @author : liyuan  
 *
 * @date : 2020/9/23 16:36  
 */
public class LamborghiniFactory extends CarFactory {

    @Override
    public Car assembleCar() {
        return new Lamborghini();
    }
}
