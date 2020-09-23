package com.xiaoyuan.designpattern.builder.v1;

/**
 * @author : liyuan  
 * @description :
 * @date : 2020/9/23 16:44  
 */
public class DefaultProductBuilder implements ProductBuilder {

    private String part1;
    private String part2;
    private String part3;
    private String part4;

    @Override
    public void buildPart1(String part1) {
        this.part1 = part1;
    }

    @Override
    public void buildPart2(String part2) {
        this.part2 = part2;
    }

    @Override
    public void buildPart3(String part3) {
        this.part3 = part3;
    }

    @Override
    public void buildPart4(String part4) {
        this.part4 = part4;
    }

    @Override
    public Product build() {
        return new Product(part1, part2, part3, part4);
    }
}
