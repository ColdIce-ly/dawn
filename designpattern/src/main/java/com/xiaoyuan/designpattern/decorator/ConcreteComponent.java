package com.xiaoyuan.designpattern.decorator;

/**
 * @author : liyuan  
 *
 * @date : 2020/9/23 17:30  
 */
public class ConcreteComponent  implements Component {

    @Override
    public void operation() {
        System.out.println("拍照.");
    }
}
