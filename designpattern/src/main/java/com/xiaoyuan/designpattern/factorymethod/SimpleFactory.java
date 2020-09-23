package com.xiaoyuan.designpattern.factorymethod;

/**
 * @author : liyuan  
 * @description : 简单工厂
 * @date : 2020/9/23 16:34  
 */
public class SimpleFactory {
    public static Product createProduct(String type) {
        if ("A".equals(type)) {
            return new ProductA();
        }

        return null;
    }
}
