package com.xiaoyuan.designpattern.create.builder.v1;

/**
 * @author : liyuan  
 *
 * @date : 2020/9/23 16:45  
 */
public class Director {

    private ProductBuilder productBuilder;

    public Director(ProductBuilder productBuilder) {
        this.productBuilder = productBuilder;
    }

    public Product makeProduct(String part1, String part2, String part3, String part4) {
        productBuilder.buildName(part1);
        productBuilder.buildColour(part2);
        productBuilder.buildSize(part3);
        productBuilder.buildUnitPrice(part4);
        return productBuilder.build();

    }
}
