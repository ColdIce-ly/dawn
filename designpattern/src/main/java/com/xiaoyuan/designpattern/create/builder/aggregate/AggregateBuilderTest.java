package com.xiaoyuan.designpattern.create.builder.aggregate;

public class AggregateBuilderTest {
    public static void main(String[] args) {


        Product.Builder builder = new Product.Builder().builderName("椰子").buildColour("棕色");
        builder.buildSize("个");
        Product.Builder part4 = builder.buildUnitPrice("30.00$");
        Product product = part4.build();
        System.out.println(product.getDescription());


        System.out.println("\n");


        Product build = new Product.Builder().builderName("苹果").buildColour("红色").buildSize("500g").buildUnitPrice("5.00$").build();
        System.out.println(build.getDescription());

    }
}
