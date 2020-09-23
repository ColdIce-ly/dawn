package com.xiaoyuan.designpattern.builder.v1;

/**
 * @author : liyuan  
 * @description :
 * @date : 2020/9/23 16:45  
 */
public interface ProductBuilder {

    void buildPart1(String part1);

    void buildPart2(String part2);

    void buildPart3(String part3);

    void buildPart4(String part4);

    Product build();
}
