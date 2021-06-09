package com.xiaoyuan.designpattern.structural.proxy;

/**
 * @author : cold ice
 * @date : 2021-06-09 14:47
 */
public class ProxyTest {

    public static void main(String[] args) {
        new Agent().bind(new Celebrity()).hold();
    }

}
