package com.xiaoyuan.designpattern.create.builder.v1;

/**
 * @author : liyuan  
 * @date : 2020/9/23 16:45  
 */
public interface ProductBuilder {

    void buildName(String name);

    void buildColour(String colour);

    void buildSize(String size);

    void buildUnitPrice(String unitPrice);

    Product build();
}
