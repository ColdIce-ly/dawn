package com.xiaoyuan.designpattern.behavioral.state;

//抽象状态类
public abstract class State {
    public abstract void Handle(Context context);
}