package com.xiaoyuan.designpattern.builder.v2;

public class BuilderTest2 {
    public static void main(String[] args) {

        Product.Builder builder = new Product.Builder().builderPart1("part1").builderPart2("part2");
        builder.builderPart3("part3");
        Product.Builder part4 = builder.builderPart4("part4");
        Product product = part4.build();
        System.out.println(product);
    }
}
