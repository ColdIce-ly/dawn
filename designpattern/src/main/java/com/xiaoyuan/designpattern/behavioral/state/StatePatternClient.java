package com.xiaoyuan.designpattern.behavioral.state;

public class StatePatternClient {
    public static void main(String[] args) {
        Context context = new Context();
        context.Handle();
        context.Handle();
        context.Handle();
        context.Handle();
    }
}
