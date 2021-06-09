package com.xiaoyuan.designpattern.decorator;

/**
 * @author : liyuan  
 *
 * @date : 2020/9/23 17:30  
 */
public class ConreteDecorator1 extends Decorator {

    public ConreteDecorator1(Component component) {
        super(component);
    }

    @Override
    public void operation() {
        System.out.println("添加美颜.");
        component.operation();
    }
}
