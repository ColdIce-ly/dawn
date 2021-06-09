package com.xiaoyuan.designpattern.create.builder.v1;

/**
 * @author : liyuan  
 * 建造者模式
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

        Product product = new Director(new SellBuilder()).makeProduct("苹果", "红色", "500g", "5.00$");

        System.out.println(product.getDescription());

    }
}
