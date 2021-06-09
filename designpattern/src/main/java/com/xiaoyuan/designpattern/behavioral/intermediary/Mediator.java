package com.xiaoyuan.designpattern.behavioral.intermediary;

public abstract class Mediator {
    public abstract void register(Colleague colleague);
    public abstract void relay(Colleague cl);
}