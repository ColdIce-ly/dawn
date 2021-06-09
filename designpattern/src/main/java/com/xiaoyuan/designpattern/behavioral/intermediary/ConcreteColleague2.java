package com.xiaoyuan.designpattern.behavioral.intermediary;

public class ConcreteColleague2 extends Colleague {
    @Override
    public void receive() {
        System.out.println("具体同事类2收到请求。");
    }
    @Override
    public void send() {
        System.out.println("具体同事类2发出请求。");
        mediator.relay(this);
    }
}