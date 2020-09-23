package com.xiaoyuan.designpattern.builder.v1;

/**
 * @author : liyuan  
 * @description : 建造者模式
 * 1.需要生成的对象具有复杂的内部结构
 * 2.需要生成的对象内部属性本身相互依赖
 * 3.与不可变对象配合使用
 * 优点：
 * 1、建造者独立，易扩展。
 * 2、便于控制细节风险。
 * @date : 2020/9/23 16:45  
 */
public class BuilderTest {
    public static void main(String[] args) {

        ProductBuilder productBuilder = new DefaultProductBuilder();

        Director director = new Director(productBuilder);
        Product product = director.makeProduct("part1", "part2", "part3", "part4");
        System.out.println(product);


    }
}
