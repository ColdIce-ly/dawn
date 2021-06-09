package com.xiaoyuan.designpattern.create.factorymethod;

import java.math.BigDecimal;

/**
 * ferrari 法拉利
 *
 * @author : liyuan  
 * @date : 2020/9/23 16:35  
 */
public class Ferrari implements Car {

    @Override
    public String name() {
        return "法拉利";
    }

    @Override
    public BigDecimal price() {
        return BigDecimal.valueOf(4000000);
    }
}
