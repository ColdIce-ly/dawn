package com.xiaoyuan.designpattern.create.builder.v1;

/**
 * @author : liyuan  
 * @date : 2020/9/23 16:44  
 */
public class SellBuilder implements ProductBuilder {

    private String name;
    private String colour;
    private String size;
    private String unitPrice;

    @Override
    public void buildName(String name) {
        this.name = name;
    }

    @Override
    public void buildColour(String colour) {
        this.colour = colour;
    }

    @Override
    public void buildSize(String size) {
        this.size = size;
    }

    @Override
    public void buildUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public Product build() {

        Product product = new Product(name, colour, size, unitPrice);
        product.setDescription("商品:" + product.getName() + ";颜色:" + product.getColour() + ";价格:" + product.getUnitPrice() + "/" + product.getSize());
        return product;
    }
}
