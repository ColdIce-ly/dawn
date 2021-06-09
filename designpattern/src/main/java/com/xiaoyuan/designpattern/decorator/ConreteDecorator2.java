package com.xiaoyuan.designpattern.decorator;

/**
 * @author : liyuan  
 *
 * @date : 2020/9/23 17:31  
 */
public class ConreteDecorator2 extends Decorator {


    public ConreteDecorator2(Component component) {
        super(component);
    }

    @Override
    public void operation() {
        System.out.println("添加滤镜.");
        component.operation();
    }
}
