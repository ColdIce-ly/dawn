package com.xiaoyuan.designpattern.flyweight;

/**
 * @author : liyuan  
 * @description :
 * @date : 2020/9/23 11:11  
 */
public class TreeType {

    private final String name;
    private final String data;

    public TreeType(String name, String data) {
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return name;
    }


    public String getData() {
        return data;
    }

}
