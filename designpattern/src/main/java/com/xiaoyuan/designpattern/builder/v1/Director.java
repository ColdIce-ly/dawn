package com.xiaoyuan.designpattern.builder.v1;

/**
 * @author : liyuan  
 * @description :
 * @date : 2020/9/23 16:45  
 */
public class Director {

    private ProductBuilder productBuilder;

    public Director(ProductBuilder productBuilder) {
        this.productBuilder = productBuilder;
    }

    public Product makeProduct(String part1, String part2, String part3, String part4) {
        productBuilder.buildPart1(part1);
        productBuilder.buildPart2(part2);
        productBuilder.buildPart3(part3);
        productBuilder.buildPart4(part4);
        Product product = productBuilder.build();
        return product;

    }
}
