package com.xiaoyuan.designpattern.behavioral.iterator;

interface Iterator {
    Object first();
    Object next();
    boolean hasNext();
}