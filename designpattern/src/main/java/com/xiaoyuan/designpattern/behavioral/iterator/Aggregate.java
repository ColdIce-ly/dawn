package com.xiaoyuan.designpattern.behavioral.iterator;

interface Aggregate {
    void add(Object obj);
    Iterator getIterator();
}