package com.xiaoyuan.designpattern.decorator;

/**
 * @author : liyuan  
 *  装饰者模式 ,在不改变原有对象的基础上，将功能附加到对象上
 * 应用场景：
 * 扩展一个类的功能或给一个类添加附加职责
 * 优点:
 * 1.不改变原有对象的情况下给一个对象扩展功能
 * 2.使用不同的组合可以实现不同的效果
 * 3.符合开闭原则
 * @date : 2020/9/23 17:31  
 */
public class DecoratorTest {
    public static void main(String[] args) {
        Component component = new ConreteDecorator1(new ConreteDecorator2(new ConcreteComponent()));
        component.operation();
    }
}










































































