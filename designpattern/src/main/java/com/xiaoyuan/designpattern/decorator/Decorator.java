package com.xiaoyuan.designpattern.decorator;

/**
 * @author : liyuan  
 *
 * @date : 2020/9/23 17:30  
 */
public abstract class Decorator implements Component {
    Component component;

    public Decorator(Component component) {
        this.component = component;
    }
}
