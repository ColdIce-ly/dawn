package com.xiaoyuan.designpattern.create.prototype;

/**
 * @author
 * @description 原型模式
 * 如果对应的类中的字段为 8 种原生数据类型，或者8种原生数据类型的包装类型，或String，BigInteger
 * 则只需要实现Cloneable这个接口且覆盖Object.clone方法，即可利用jvm的克隆机制，完成对象的拷贝
 * 这种方式即是浅拷贝， 如果对应的类中数据为自定义数据类型，或者其他可变的数据类型（如Date,或者其
 * 他对象类型），要借助jvm的克隆机制完成数据的拷贝，则需要实现所有的对象字段的遍历拷贝，即是深拷贝
 */
public class PrototypeTest {
    public static void main(String[] args) throws CloneNotSupportedException {

        BaseInfo baseInfo = new BaseInfo("burgeon");
        Product product = new Product("part1", "part2", "part3", "part4", baseInfo);

        Product clone = product.clone();
        System.out.println("original: " + product);
        System.out.println("clone:  " + clone);
        product.getBaseInfo().setCompanyName("xxxx");
        System.out.println("original: " + product);
        System.out.println("clone:  " + clone);

    }
}