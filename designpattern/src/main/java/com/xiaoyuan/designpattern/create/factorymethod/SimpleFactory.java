package com.xiaoyuan.designpattern.create.factorymethod;

/**
 * @author : liyuan  
 * 简单工厂
 * @date : 2020/9/23 16:34  
 */
public class SimpleFactory {
    public static Car createProduct(String type) {
        if ("ferrari".equals(type)) {
            return new Ferrari();
        }
        return null;
    }
}
