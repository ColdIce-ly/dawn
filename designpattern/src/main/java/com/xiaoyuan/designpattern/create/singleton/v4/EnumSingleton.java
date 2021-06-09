package com.xiaoyuan.designpattern.create.singleton.v4;

public enum EnumSingleton {
    INSTANCE;

    public void print() {
        System.out.println(hashCode());
    }

}