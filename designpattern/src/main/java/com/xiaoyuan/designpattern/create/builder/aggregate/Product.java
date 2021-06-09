package com.xiaoyuan.designpattern.create.builder.aggregate;

/**
 * @author : liyuan  
 *
 * @date : 2020/9/23 16:52  
 */
public class Product {

    private String name;
    private String colour;
    private String size;
    private String unitPrice;
    private String description;

    public Product(String name, String colour, String size, String unitPrice) {
        this.name = name;
        this.colour = colour;
        this.size = size;
        this.unitPrice = unitPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    static class Builder {
        private String name;
        private String colour;
        private String size;
        private String unitPrice;

        public Builder builderName(String name) {
            this.name = name;
            return this;
        }

        public Builder buildColour(String colour) {
            this.colour = colour;
            return this;
        }

        public Builder buildSize(String size) {
            this.size = size;
            return this;
        }

        public Builder buildUnitPrice(String unitPrice) {
            this.unitPrice = unitPrice;
            return this;
        }

        Product build() {
            Product product = new Product(name, colour, size, unitPrice);

            product.setDescription("商品信息:\n名称:" + product.getName() + ";\n颜色:" + product.getColour() + ";\n价格:" + product.getUnitPrice() + "/" + product.getSize());

            return product;
        }
    }

}
