package com.xiaoyuan.designpattern.create.factorymethod;

import java.math.BigDecimal;

/**
 * lamborghini 兰博基尼
 *
 * @author : liyuan  
 * @date : 2020/9/23 16:35  
 */
public class Lamborghini implements Car {

    @Override
    public String name() {
        return "兰博基尼";
    }

    @Override
    public BigDecimal price() {
        return BigDecimal.valueOf(7000000);
    }
}
