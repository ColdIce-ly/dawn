package com.xiaoyuan.designpattern.create.factorymethod;

import java.math.BigDecimal;

/**
 * @author : liyuan  
 * @date : 2020/9/23 16:34  
 */
public interface Car {

    /**
     * 汽车名称
     */
    String name();

    /**
     * 汽车价格
     */
    BigDecimal price();
}
